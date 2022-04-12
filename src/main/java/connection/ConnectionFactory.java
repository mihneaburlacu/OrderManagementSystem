package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>Clasa pentru realizarea conexiunii aplicatiei nostre cu baza de date din MySQL</p>
 */
public class ConnectionFactory {

	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/schooldb";
	private static final String USER = "root";
	private static final String PASS = "Mihnea_2001";

	private static ConnectionFactory singleInstance = new ConnectionFactory();

	/**
	 * Constructorul clasei
	 */
	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>Metoda createConnection() ne realizeaza conexiunea propriu-zisa cu baza de date</p>
	 * <p>Daca aceasta nu se poate realiza, functia ne va returna o eroare, altfel va returna conexiunea nou creata</p>
	 * @return
	 */
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * <p>Metoda getConnection() este una de tip getter prin care se obtine (este returnata) conexiunea curenta</p>
	 * @return ne returneaza conexiunea creata
	 */
	public static Connection getConnection() {
		return singleInstance.createConnection();
	}

	/**
	 * <p>Close este o metoda pentru a inchide conexiunea cu baza de date, care urmeaza sa fie suprascrisa si pentru instructiune (Statement) si setul de rezultate (ResultSet)</p>
	 * @param connection
	 */
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
			}
		}
	}

	/**
	 * <p>Metoda de inchidere a instructiunii (Statement)</p>
	 * @param statement
	 */
	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
			}
		}
	}

	/**
	 * <p>Metoda de inchidere a resultSet-ului</p>
	 * @param resultSet
	 */
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
			}
		}
	}

	/**
	 * <p>Metoda CloseAll ne ajuta sa inchidem si conexiunea cu baza de date si instructiunea curenta (Statement-ul) si setul de rezultate (ResultSet-ul)</p>
	 * <p>Daca nu reusim sa le inchidem pe toate, aceasta ne va trimite o exceptie de tipul SQLException</p>
	 * @param dbConnection
	 * @param statement
	 * @param resultSet
	 */
	public static void closeAll(Connection dbConnection, Statement statement, ResultSet resultSet) {
		try {
			if (dbConnection != null) {
				dbConnection.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException throwable) {
			LOGGER.log(Level.WARNING, "An error occurred while trying to close the connection, statement or result set");
			throwable.printStackTrace();
		}
	}
}
