package ai.leucine.db.utils;

import java.io.InputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.tuple.Pair;
import org.postgresql.util.PSQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

 
/**
 * DatabaseUtility class for managing database connections.
 */
public class DatabaseUtility {

	private static final Logger LOGGER = Logger.getLogger(DatabaseUtility.class.getName());
	private static ComboPooledDataSource dataSource;

	static {
		try {
			if (dataSource == null) {
				// Initialize the ComboPooledDataSource
				dataSource = new ComboPooledDataSource();
				Properties prop = new Properties();
				InputStream input = DatabaseUtility.class.getClassLoader()
						.getResourceAsStream("application.properties");

				if (input == null) {
					LOGGER.log(Level.SEVERE, "Unable to find application.properties");
				}

				prop.load(input);
				String jdbcURL = prop.getProperty("database.url");
				String jdbcUsername = prop.getProperty("database.username");
				String jdbcPassword = prop.getProperty("database.password");

				dataSource.setDriverClass("org.postgresql.Driver"); // JDBC Driver
				dataSource.setJdbcUrl(jdbcURL);
				dataSource.setUser(jdbcUsername);
				dataSource.setPassword(jdbcPassword);

				// Configure C3P0 connection pool properties
				dataSource.setMinPoolSize(5);
				dataSource.setInitialPoolSize(5); // Set it to 5 or a higher value

				dataSource.setAcquireIncrement(5);
				dataSource.setMaxPoolSize(20);
				dataSource.setMaxStatements(100);
			}

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error initializing C3P0 DataSource", e);
		}
	}

	/**
	 * Establishes a connection to the database.
	 * 
	 * @return a Connection object
	 * @throws SQLException if a database access error occurs
	 */
	public static Connection connect() throws SQLException {
		return dataSource.getConnection();
	}

	/**
	 * Closes the database connection.
	 * 
	 * @param connection the connection to close
	 */
	public void disconnect(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "Error closing the database connection", e);
			}
		}
	}

	/**
	 * Executes a SQL query without parameters and returns the result as an
	 * ArrayList of HashMaps, specifically designed for preview purposes.
	 *
	 * @param sqlQuery The SQL query to execute.
	 * @return An ArrayList of HashMaps, where each HashMap represents a row of the
	 *         query result.
	 * @throws SQLException
	 * @throws Exception    If there is an issue executing the query.
	 */
	public static ArrayList<HashMap<String, String>> executeQueryForPreview(String sqlQuery) throws SQLException {
		long now = System.currentTimeMillis();
		ArrayList<HashMap<String, String>> table = new ArrayList<HashMap<String, String>>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(sqlQuery);
			resultSet = pstmt.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			ArrayList<String> columnnames = new ArrayList<String>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnnames.add(rsmd.getColumnName(i));
			}
			while (resultSet.next()) {
				HashMap<String, String> row = new HashMap<String, String>();
				for (String columnName : columnnames) {
					String first = resultSet.getString(columnName);
					row.put(columnName, first);
				}
				table.add(row);
			}
		} catch (PSQLException psqe) {
			LOGGER.log(Level.SEVERE, "Problem running this query ->" + sqlQuery, psqe);
			throw psqe;
		} catch (SQLException se) {
			throw se;
		} finally {
			try {
				if (pstmt != null)
					connection.close();
			} catch (SQLException se) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		if ((System.currentTimeMillis() - now) > 5000)
			LOGGER.log(Level.SEVERE, "Exceptionally long time (" + (System.currentTimeMillis() - now)
					+ ") taken for query ->" + sqlQuery);

		return table;
	}

	public static ArrayList<HashMap<String, String>> executeQueryForPreview(String sqlQuery, List<Object> parameters)
			throws SQLException {
		long now = System.currentTimeMillis();
		ArrayList<HashMap<String, String>> table = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {

			// Set the parameters on the PreparedStatement
			if (parameters != null) {
				for (int i = 0; i < parameters.size(); i++) {
					pstmt.setObject(i + 1, parameters.get(i));
				}
			}

			try (ResultSet resultSet = pstmt.executeQuery()) {
				ResultSetMetaData rsmd = resultSet.getMetaData();
				ArrayList<String> columnNames = new ArrayList<>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					columnNames.add(rsmd.getColumnName(i));
				}
				while (resultSet.next()) {
					HashMap<String, String> row = new HashMap<>();
					for (String columnName : columnNames) {
						row.put(columnName, resultSet.getString(columnName));
					}
					table.add(row);
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Problem running this query ->" + sqlQuery, e);
			throw e;
		} finally {
			if ((System.currentTimeMillis() - now) > 5000) {
				LOGGER.log(Level.SEVERE, "Exceptionally long time (" + (System.currentTimeMillis() - now)
						+ ") taken for query ->" + sqlQuery);
			}
		}
		return table;
	}

	public ArrayList<HashMap<String, String>> executeQuery(String sqlQuery, BigInteger idValue) {
		long now = System.currentTimeMillis();
		ArrayList<HashMap<String, String>> table = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			connection = connect();
			pstmt = connection.prepareStatement(sqlQuery);
			pstmt.setString(1, idValue.toString()); // Set id as string
			resultSet = pstmt.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			ArrayList<String> columnnames = new ArrayList<>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnnames.add(rsmd.getColumnName(i));
			}
			while (resultSet.next()) {
				HashMap<String, String> row = new HashMap<>();
				for (String columnName : columnnames) {
					String value = resultSet.getString(columnName);
					row.put(columnName, value);
				}
				table.add(row);
			}
		} catch (PSQLException psqe) {
			LOGGER.log(Level.SEVERE, "Problem running this query ->" + sqlQuery, psqe);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (pstmt != null)
					pstmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				LOGGER.log(Level.WARNING, "Error closing database resources", se);
			}
		}
		if ((System.currentTimeMillis() - now) > 5000) {
			LOGGER.log(Level.SEVERE, "Exceptionally long time (" + (System.currentTimeMillis() - now)
					+ ") taken for query ->" + sqlQuery);
		}
		return table;
	}

	/**
	 * Executes a SQL query without parameters and returns the result as an
	 * ArrayList of HashMaps.
	 *
	 * @param sqlQuery The SQL query to execute.
	 * @param string
	 * @return An ArrayList of HashMaps, where each HashMap represents a row of the
	 *         query result.
	 * @throws SQLException
	 
	public Pair<List<Column>, ArrayList<LinkedHashMap<Column, String>>> executeQuery(String sqlQuery)
			throws SQLException {
		List<Column> columns = new ArrayList<>();
		ArrayList<LinkedHashMap<Column, String>> table = new ArrayList<>();

		Connection connection = connect();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			// Establish a database connection (configure based on your database)

			pstmt = connection.prepareStatement(sqlQuery);
			resultSet = pstmt.executeQuery();

			// ResultSetMetaData for column metadata
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnCount = rsmd.getColumnCount();

			for (int i = 1; i <= columnCount; i++) {
				String columnName = rsmd.getColumnName(i);
				int columnType = rsmd.getColumnType(i);
				boolean isNullable = rsmd.isNullable(i) == ResultSetMetaData.columnNullable;
				LOGGER.info("" + rsmd.getColumnType(columnCount));
				// Create a Column object and set its properties
				Column column = new Column(columnName, columnType);

				// Set other column metadata properties here based on your database logic.
				// column.setForeignKey(isForeignKey(columnName, rsmd,connection));
				column.setBehavior(getBehavior(columnName, rsmd));
				column.setNullable(isNullable);

				columns.add(column);
			}

			while (resultSet.next()) {
				LinkedHashMap<Column, String> row = new LinkedHashMap<>();
				for (Column column : columns) {
					String columnName = column.getName();
					String columnValue = resultSet.getString(columnName);
					row.put(column, columnValue);
				}
				table.add(row);
			}
		} catch (SQLException e) {
			// this
			e.printStackTrace();
		} finally {
			// Close resources and handle exceptions...
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return Pair.of(columns, table);
	}
	 */
	/**
	 * Executes a SQL query without parameters and returns the result as an
	 * ArrayList of HashMaps.
	 *
	 * @param sqlQuery The SQL query to execute.
	 * @param string
	 * @return An ArrayList of HashMaps, where each HashMap represents a row of the
	 *         query result.
	 * @throws SQLException
	
	public Pair<List<Column>, ArrayList<LinkedHashMap<Column, String>>> executeMetaQuery(String sqlQuery)
			throws SQLException {
		List<Column> columns = new ArrayList<>();
		ArrayList<LinkedHashMap<Column, String>> table = new ArrayList<>();

		Connection connection = connect();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			// Establish a database connection (configure based on your database)

			pstmt = connection.prepareStatement(sqlQuery);
			resultSet = pstmt.executeQuery();

			// ResultSetMetaData for column metadata
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnCount = rsmd.getColumnCount();

			for (int i = 1; i <= columnCount; i++) {
				String columnName = rsmd.getColumnName(i);
				int columnType = rsmd.getColumnType(i);
				boolean isNullable = rsmd.isNullable(i) == ResultSetMetaData.columnNullable;
				LOGGER.info("" + rsmd.getColumnType(columnCount));
				// Create a Column object and set its properties
				Column column = new Column(columnName, columnType);

				// Set other column metadata properties here based on your database logic.
				// column.setForeignKey(isForeignKey(columnName, rsmd,connection));
				column.setBehavior(getBehavior(columnName, rsmd));
				column.setNullable(isNullable);

				columns.add(column);
			}

			while (resultSet.next()) {
				LinkedHashMap<ai.leucine.model.Column, String> row = new LinkedHashMap<>();
				for (Column column : columns) {
					String columnName = column.getName();
					String columnValue = resultSet.getString(columnName);
					row.put(column, columnValue);
				}
				table.add(row);
			}
		} catch (SQLException e) {
			// this
			e.printStackTrace();
		} finally {
			// Close resources and handle exceptions...
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return Pair.of(columns, table);
	}
 */
	public static ArrayList<HashMap<String, String>> executeSimpleQuery(String sqlQuery) {
		LOGGER.severe(sqlQuery);
		long now = System.currentTimeMillis();
		ArrayList<HashMap<String, String>> table = new ArrayList<HashMap<String, String>>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			connection = connect();
			pstmt = connection.prepareStatement(sqlQuery);
			resultSet = pstmt.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			ArrayList<String> columnnames = new ArrayList<String>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnnames.add(rsmd.getColumnName(i));
			}
			while (resultSet.next()) {
				HashMap<String, String> row = new HashMap<String, String>();
				for (String columnName : columnnames) {
					String first = resultSet.getString(columnName);
					row.put(columnName, first);
				}
				table.add(row);
			}
		} catch (PSQLException psqe) {
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					connection.close();
			} catch (SQLException se) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return table;
	}

	public static ArrayList<LinkedHashMap<String, String>> executeQueryOrdered(String sqlQuery) {

		long now = System.currentTimeMillis();
		ArrayList<LinkedHashMap<String, String>> table = new ArrayList<LinkedHashMap<String, String>>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			connection = connect();
			pstmt = connection.prepareStatement(sqlQuery);
			resultSet = pstmt.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			ArrayList<String> columnnames = new ArrayList<String>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnnames.add(rsmd.getColumnName(i));
			}
			while (resultSet.next()) {
				LinkedHashMap<String, String> row = new LinkedHashMap<String, String>();
				for (String columnName : columnnames) {
					String first = resultSet.getString(columnName);
					row.put(columnName, first);
				}
				table.add(row);
			}
		} catch (PSQLException psqe) {
			LOGGER.log(Level.SEVERE, "Problem running this query ->" + sqlQuery);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					connection.close();
			} catch (SQLException se) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		if ((System.currentTimeMillis() - now) > 5000)
			LOGGER.log(Level.SEVERE, "Exxceptionally long time (" + (System.currentTimeMillis() - now)
					+ ") taken for query ->" + sqlQuery);

		return table;
	}

	public static int[] updateInBatch(String sqlQuery, List<HashMap<Integer, Object>> dataList) throws SQLException {
		int[] retrunIndex = null;

		LOGGER.info(sqlQuery);
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

			for (HashMap<Integer, Object> data : dataList) {
				for (Integer index : data.keySet()) {
					if (data.get(index) != null) {
						switch (data.get(index).getClass().getName().toString()) {
						case "java.lang.Integer":
							pstmt.setInt(index, Integer.parseInt(data.get(index).toString()));
							break;
						case "java.lang.String":
							pstmt.setString(index, data.get(index).toString());
							break;
						case "java.lang.Long":
							pstmt.setLong(index, Long.parseLong(data.get(index).toString()));
							break;
						case "java.lang.Float":
							pstmt.setFloat(index, Float.parseFloat(data.get(index).toString()));
							break;
						case "java.lang.Boolean":
							pstmt.setBoolean(index, Boolean.parseBoolean(data.get(index).toString()));
							break;
						case "java.sql.Timestamp":
							pstmt.setTimestamp(index, (Timestamp) data.get(index));
							break;

						default:
							pstmt.setString(index, data.get(index).toString());
							break;
						}
					} else {
						pstmt.setObject(index, null);
					}

				}
				System.out.println(pstmt.toString());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			List<Integer> generatedKeysList = new ArrayList<>();

			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
				while (generatedKeys.next()) {
					generatedKeysList.add(generatedKeys.getInt(1)); // Assuming the generated key is of type Long
				}
			}
			// Convert List<Integer> to int[]
			retrunIndex = new int[generatedKeysList.size()];
			for (int i = 0; i < generatedKeysList.size(); i++) {
				retrunIndex[i] = generatedKeysList.get(i);
			}

		} catch (SQLException se) {
			LOGGER.log(Level.SEVERE, sqlQuery);
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					connection.close();
			} catch (SQLException se) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return retrunIndex;

	}

	/**
	 * Closes the database-related resources (Connection, PreparedStatement,
	 * ResultSet).
	 *
	 * @param connection        the Connection to close
	 * @param preparedStatement the PreparedStatement to close
	 * @param resultSet         the ResultSet to close
	 */
	public void disconnect(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "Error closing the ResultSet", e);
			}
		}

		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "Error closing the PreparedStatement", e);
			}
		}

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "Error closing the Connection", e);
			}
		}
	}

	public static int updateSQL(String sqlQuery) throws SQLException {
		int retrunIndex = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = connect();
			pstmt = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			retrunIndex = pstmt.executeUpdate();
			if (sqlQuery.toLowerCase().contains("insert") && retrunIndex > 0) {
				java.sql.ResultSet generatedKeys = pstmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					retrunIndex = generatedKeys.getInt(1);
				}
			} else {
				retrunIndex = 0;
			}
		} catch (SQLException se) {
			LOGGER.log(Level.WARNING, "Error closing the Connection", sqlQuery);
			se.printStackTrace();
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					connection.close();
			} catch (SQLException se) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return retrunIndex;
	}

	public String getColumnEnumName(String tableName, String columnName) {
		String query = "SELECT t.typname AS enum_name " + "FROM pg_type t " + "JOIN pg_enum e ON t.oid = e.enumtypid "
				+ "JOIN pg_catalog.pg_namespace n ON n.oid = t.typnamespace "
				+ "JOIN pg_attribute a ON a.atttypid = t.oid " + "JOIN pg_class c ON a.attrelid = c.oid "
				+ "WHERE c.relname = ? AND a.attname = ? AND n.nspname = 'public'";

		try (Connection connection = connect(); PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, tableName);
			pstmt.setString(2, columnName);
			try (ResultSet resultSet = pstmt.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getString("enum_name");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> getEnumValues(String enumTypeName) throws SQLException {
		List<String> enumValues = new ArrayList<>();
		String query = "SELECT e.enumlabel " + "FROM pg_enum e " + "JOIN pg_type t ON e.enumtypid = t.oid "
				+ "WHERE t.typname = ?";

		try (Connection connection = connect(); PreparedStatement pstmt = connection.prepareStatement(query)) {
			pstmt.setString(1, enumTypeName);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				enumValues.add(rs.getString("enumlabel"));
			}
		}

		return enumValues;
	}

	// Helper method to get the behavior of a column based on its name and metadata
	// Helper method to get the behavior of a column based on its data type
	private String getBehavior(String columnName, ResultSetMetaData rsmd) throws SQLException {
		int columnType = rsmd.getColumnType(1); // Assuming only one column is checked

		// Define a mapping between data types and behaviors
		HashMap<Integer, String> dataTypeToBehavior = new HashMap<>();
		dataTypeToBehavior.put(java.sql.Types.DATE, "date");
		dataTypeToBehavior.put(java.sql.Types.TIMESTAMP, "datetime");
		dataTypeToBehavior.put(java.sql.Types.INTEGER, "integer");
		dataTypeToBehavior.put(java.sql.Types.DOUBLE, "double");
		// Add more mappings as needed

		// Determine the behavior based on the column's data type
		String behavior = dataTypeToBehavior.get(columnType);

		if (columnName.toLowerCase().contains("percentage")) {
			behavior = "fas fa-percentage"; // Set the behavior for percentage columns
		} else if (columnName.toLowerCase().contains("enum")) {
			behavior = "badges"; // Set the behavior for enum columns
		} else if (columnName.toLowerCase().contains("temperature")) {
			behavior = "fas fa-thermometer"; // Set the behavior for temperature-related columns
		} else if (columnName.toLowerCase().contains("humidity")) {
			behavior = "fas fa-tint"; // Set the behavior for humidity-related columns
		} else if (columnName.toLowerCase().contains("ph")) {
			behavior = "fas fa-flask"; // Set the behavior for pH-related columns
		} else if (columnName.toLowerCase().contains("time")) {
			behavior = "fas fa-clock"; // Set the behavior for time-related columns
		} else if (columnName.toLowerCase().contains("currency")) {
			behavior = "fas fa-dollar-sign"; // Set the behavior for currency-related columns
		} else if (columnName.toLowerCase().contains("volume")) {
			behavior = "fas fa-flask"; // Set the behavior for volume-related columns
		} else if (columnName.toLowerCase().contains("energy")) {
			behavior = "fas fa-bolt"; // Set the behavior for energy-related columns
		} else if (columnName.toLowerCase().contains("quality")) {
			behavior = "fas fa-star"; // Set the behavior for quality-related columns
		} else if (columnName.toLowerCase().contains("alert")) {
			behavior = "fas fa-bell"; // Set the behavior for alert-related columns
		} else if (columnName.toLowerCase().contains("___")) {
			behavior = "<a href='#'>111</a>"; // Set the behavior for alert-related columns
		}

		return behavior != null ? behavior : "unknown"; // Default behavior for unknown types
	}

	/**
	 * Executes a parameterized SQL update query with a HashMap of data and returns
	 * the number of affected rows. Additionally, retrieves generated keys if the
	 * query is an INSERT operation.
	 *
	 * @param sqlQuery The parameterized SQL update query to execute.
	 * @param data     A HashMap containing index-value pairs for prepared statement
	 *                 parameters.
	 * @return The number of affected rows or the generated key if the query is an
	 *         INSERT.
	 * @throws SQLException If there is an issue executing the query.
	 */
	public static int updateObject(String sqlQuery, HashMap<Integer, Object> data) throws SQLException {
		int retrunIndex = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			if (data != null) {
				for (Integer index : data.keySet()) {
					if (data.get(index) != null) {
						switch (data.get(index).getClass().getName().toString()) {
						case "java.lang.Integer":
							pstmt.setInt(index, Integer.parseInt(data.get(index).toString()));
							break;
						case "java.lang.String":
							pstmt.setString(index, data.get(index).toString());
							break;
						case "java.lang.Long":
							pstmt.setLong(index, Long.parseLong(data.get(index).toString()));
							break;
						case "java.lang.Float":
							pstmt.setFloat(index, Float.parseFloat(data.get(index).toString()));
							break;
						case "java.lang.Boolean":
							pstmt.setBoolean(index, Boolean.parseBoolean(data.get(index).toString()));
							break;
						case "java.sql.Timestamp":
							pstmt.setTimestamp(index, (Timestamp) data.get(index));
							break;

						default:
							pstmt.setString(index, data.get(index).toString());
							break;
						}
					} else {
						pstmt.setObject(index, null);
					}
				}
			}
			retrunIndex = pstmt.executeUpdate();
			if (sqlQuery.toLowerCase().contains("insert") && retrunIndex > 0) {
				java.sql.ResultSet generatedKeys = pstmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					retrunIndex = generatedKeys.getInt(1);
				}
			} else {
				retrunIndex = 0;
			}
		} catch (SQLException se) {
			se.printStackTrace();
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					connection.close();
			} catch (SQLException se) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return retrunIndex;
	}

	public boolean testQuery(String sqlQuery) throws Exception {
		long now = System.currentTimeMillis();
		ArrayList<HashMap<String, String>> table = new ArrayList<HashMap<String, String>>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			pstmt = connection.prepareStatement(sqlQuery);
			resultSet = pstmt.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			ArrayList<String> columnnames = new ArrayList<String>();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnnames.add(rsmd.getColumnName(i));
			}
			while (resultSet.next()) {
				HashMap<String, String> row = new HashMap<String, String>();
				for (String columnName : columnnames) {
					String first = resultSet.getString(columnName);
					row.put(columnName, first);
				}
				table.add(row);
			}
			return true;
		} catch (PSQLException psqe) {
			throw psqe;
		} catch (SQLException se) {
			se.printStackTrace();
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (pstmt != null)
					connection.close();
			} catch (SQLException se) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}
}
