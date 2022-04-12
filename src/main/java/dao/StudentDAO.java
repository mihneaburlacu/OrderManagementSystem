package dao;

import connection.ConnectionFactory;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * <p>Clasa pentru studenti (students) din pachetul DataAcces prin care se realizeaza executia interogarilor facute abstract in clasa AbstractDAO</p>
 * <p>Aceasta extinde clasa AbstractDAO</p>
 */
public class StudentDAO extends AbstractDAO<Student> {

	// uses basic CRUD methods from superclass

	// TODO: create only student specific queries

    /**
     * <p>Metoda createProductFindByQuery ne creeaza un String care ne va ajuta sa gasim obiecte dupa valorile pe care le are campul trimis ca parametru (field)</p>
     * @param field
     * @return
     */
    private String createStudentFindByQuery(String field) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * ");
        sb.append("FROM `Student`");
        sb.append("WHERE " + field + " = ?");

        return sb.toString();


        //return "SELECT * FROM `Student` WHERE NAME = ?";
    }

    /**
     * <p>FindByName este o metoda care ne ajuta sa gasim studentii (clientii) dupa numele acestora</p>
     * <p>ea apeleaza metoda care creeaza interogarea, ii transmite parametrul "Name" si apoi executa</p>
     * @param name
     * @return
     */
    public Student findByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createStudentFindByQuery("NAME");

        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException | IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING,   "Student DAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.closeAll(connection, statement, resultSet);
        }

        return null;
    }

    /**
     * <p>FindByName este o metoda care ne ajuta sa gasim studentii (clientii) dupa varsta acestora</p>
     * <p>ea apeleaza metoda care creeaza interogarea, ii transmite parametrul "Age" si apoi executa</p>
     * @param age
     * @return
     */
    public Student findByAge(int age) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createStudentFindByQuery("age");

        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, age);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException | IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING,   "Student DAO:findByAge " + e.getMessage());
        } finally {
            ConnectionFactory.closeAll(connection, statement, resultSet);
        }

        return null;
    }
}
