package dao;

import connection.ConnectionFactory;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * <p>Clasa pentru produse (products) din pachetul DataAcces prin care se realizeaza executia interogarilor facute abstract in clasa AbstractDAO</p>
 * <p>Aceasta extinde clasa AbstractDAO</p>
 */
public class ProductDAO extends AbstractDAO<Product>{
    /**
     * <p>Metoda createProductFindByQuery ne creeaza un String care ne va ajuta sa gasim obiecte dupa valorile pe care le are campul trimis ca parametru (field)</p>
     * @param field
     * @return
     */
    private String createProductFindByQuery(String field) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * ");
        sb.append("FROM `Product`");
        sb.append("WHERE " + field + " = ?");

        return sb.toString();
    }

    /**
     * <p>FindByName este o metoda care ne ajuta sa gasim produsele dupa numele acestora</p>
     * <p>ea apeleaza metoda care creeaza interogarea, ii transmite parametrul "Name" si apoi executa</p>
     * @param name
     * @return
     */
    public Product findByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createProductFindByQuery("NAME");

        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException | IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING,   "Product DAO:findByName " + e.getMessage());
        } finally {
            ConnectionFactory.closeAll(connection, statement, resultSet);
        }

        return null;
    }
}
