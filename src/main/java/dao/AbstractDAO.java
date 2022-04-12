package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;


/**
 * <p>Clasa AbstractDAO este o clasa abstracta care ne ajuta sa realizam executia interogarilor necesare aplicatiei noastre</p>
 * <p>Aceasta are un parametru T cu ajutorul caruia putem folosi orice clasa (Student, Product sau Order)</p>
 * @param <T>
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    /**
     * <p>Constructorul clasei</p>
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * <p>CreateSelectQuery ne creeaza un String care contine instructiunile unei interogari de tipul Select...From...Where</p>
     * <p>Aceasta primeste ca parametru un String field care va insemna coloana din tabel dupa care vom face cautarea</p>
     * @param field
     * @return
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + "=?");
        return sb.toString();
    }

    /**
     * <p>Metoda createSellectAllQuery() ne returneaza un String care contine instructiunile unei interogari de tip SELECT *</p>
     * @return
     */
    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * <p>Metoda findAll realizeaza executia interogarii create mai sus si la sfarsit ne va returna o lista de obiecte care semnifica rezultatul interogarii</p>
     * <p>Executia propriu-zisa a interogarii se realizeaza prin apelul metodei executeQuery()</p>
     * <p>La sfarsitul ei trebuie sa inchidem si conexiunea si statement-ul si resultSet-ul</p>
     * @return lista de obiecte gasite
     */
    public List<T> findAll() {
        List<T> entries = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            entries = this.createObjects(resultSet);

            return entries;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.closeAll(connection, statement, resultSet);
        }
        return null;
    }

    /**
     * <p>FindById este metoda prin care se executa o interogare de tipul gasirii unui obiect dupa id-ul trimis ca parametru</p>
     * <p>Dupa ce am executat interogarea, daca s-a gasit obiectul, il vom returna, daca nu, returnam null</p>
     * @param id
     * @return
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            List<T> list = createObjects(resultSet);
            return list.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * <p>Metoda pentru crearea de obiecte provenite din ResultSet dupa executia unei interogari</p>
     * @param resultSet
     * @return
     */
    protected List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * <p>Metoda createInsertQuery ne ajuta sa creeam un string care semnifica instructiunile unei interogari de tipul insert into</p>
     * <p>String-ul este creat parcurgand mai intai numele coloanelor (atributele obiectului) si apoi valorile din fiecare coloana, iar la sfarsit returnam tot String-ul</p>
     * @param t
     * @return
     */
    private String createInsertQuery(T t) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO `");
        stringBuilder.append(type.getSimpleName()).append("` (");
        int index = 0;
        for (Field field :
                type.getDeclaredFields()) {
            if (index == 0) {
                stringBuilder.append(field.getName()).append(", ");
                index = 1;
            } else if (index == 1) {
                stringBuilder.append(field.getName());
                index = 2;
            } else {
                stringBuilder.append(", ").append(field.getName());
            }
        }
        index = 0;
        stringBuilder.append(") VALUES (");
        for (Field field :
                type.getDeclaredFields()) {
            try {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                Method method = propertyDescriptor.getReadMethod();
                Object value = method.invoke(t);

                if (index == 0) {
                    stringBuilder.append(value.toString()).append(", ");
                    index = 1;
                } else if (index == 1) {
                    stringBuilder.append("'").append(value.toString()).append("'");
                    index = 2;
                } else {
                    stringBuilder.append(", ").append("'").append(value.toString()).append("'");
                }
            } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        stringBuilder.append(")");
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    /**
     * <p>insert este o metoda care realizeaza executia unei interogari de tipul inserarii in baza de date a unui obiect t</p>
     * <p>aceasta metoda returneaza obiectul inserat sau null daca nu am reusit sa-l inseram</p>
     * @param t
     * @return
     */
    public T insert(T t) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query = createInsertQuery(t);

        try {
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.closeAll(connection, statement, null);
        }
        return null;
    }

    private String createInsertQueryOrder(T t) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO `");
        stringBuilder.append(type.getSimpleName()).append("` (");
        int index = 0;
        for (Field field :
                type.getDeclaredFields()) {
            if (index == 0) {
                stringBuilder.append(field.getName()).append(", ");
                index = 1;
            } else if (index == 1) {
                stringBuilder.append(field.getName());
                index = 2;
            } else {
                stringBuilder.append(", ").append(field.getName());
            }
        }
        index = 0;
        stringBuilder.append(") VALUES (");
        for (Field field :
                type.getDeclaredFields()) {
            try {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                Method method = propertyDescriptor.getReadMethod();
                Object value = method.invoke(t);

                if (index == 0) {
                    stringBuilder.append(value.toString()).append(", ");
                    index = 1;
                }
                else if (index == 1) {
                    stringBuilder.append(value.toString()).append(", ");
                    index = 2;
                }
                else if (index == 2) {
                    stringBuilder.append(value.toString()).append(", ");
                    index = 3;
                }
                else if (index == 3) {
                    stringBuilder.append("'").append(value.toString()).append("'");
                    index = 4;
                }
                else {
                    stringBuilder.append(", ").append(value.toString());
                }
            } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        stringBuilder.append(")");
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    public T insertOrderTable(T t) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;
        String query = createInsertQueryOrder(t);

        try {
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

            //return createObjects(resultSet).get(0);
            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.closeAll(connection, statement, null);
        }

        return null;
    }

    /**
     * <p>CreateUpdateQuery ne returneaza un String care contine instructiunile necesare executiei unei interogari de tipul update</p>
     * <p>String-ul va contine noile valori care trebuie introduse (SET) si obiectul asupra caruia se va face update-ul (WHERE)</p>
     * @param t
     * @param id
     * @return
     */
    private String createUpdateQuery(T t, int id) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ").append(type.getSimpleName()).append(" SET ");
        StringBuilder updateId = new StringBuilder();
        int index = 0;
        for (Field field : type.getDeclaredFields()) {
            try {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                Method method = propertyDescriptor.getReadMethod();
                Object invoke = method.invoke(t);
                if (index == 0) {
                    updateId.append(field.getName());
                    index = 1;
                } else if (index == 1) {
                    stringBuilder.append(field.getName()).append(" = ").
                            append("'").append(invoke.toString()).append("'");
                    index = 2;
                } else {
                    stringBuilder.append(", ").append(field.getName()).append(" = ").
                            append("'").append(invoke.toString()).append("'");
                }
            } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        stringBuilder.append("WHERE ").append(updateId).append(" = ").append(id);
        return stringBuilder.toString();
    }

    /**
     * <p>Update este metoda care ne realizeaza executia interogarii de tip update</p>
     * <p>la sfarsit, aceasta va returna obiectul nou creat dupa executia instructiunilor</p>
     * @param t
     * @param id
     * @return
     */
    public T update(T t, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(t, id);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

            return t;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * <p>CreateRemoveQuery ne creeaza String-ul instructiunilor unei interogari de tipul Delete</p>
     * @return
     */
    private String createRemoveQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM `");
        sb.append(type.getSimpleName());
        sb.append("` WHERE id = ?");

        return sb.toString();
    }

    /**
     * <p>Metoda remove va realiza executia unei interogari de tipul Delete, prin care se sterge obiectul cu id-ul trimis ca parametru</p>
     * <p>aceasta va returna true (adevarat) daca stergerea a reusit sau false (fals) daca stergerea nu a reusit</p>
     * @param id
     * @return
     */
    public boolean remove(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createRemoveQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();

            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return false;
    }
}
