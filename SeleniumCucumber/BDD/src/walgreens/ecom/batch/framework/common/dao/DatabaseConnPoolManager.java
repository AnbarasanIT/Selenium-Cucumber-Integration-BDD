package walgreens.ecom.batch.framework.common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;


public class DatabaseConnPoolManager {

	static Log logger = LogFactory.getLog(DatabaseConnPoolManager.class);

	public DatabaseConnPoolManager(String schema, int connPoolSize) {
		
		try {
			// Uncomment this when running locally using LauncherBootStrap class or deploying
		    	Configuration config = new PropertiesConfiguration("jdbc.properties");
			// Uncomment this ONLY if you are running on your local machine in debug mode
//			Configuration config = new PropertiesConfiguration("config/jdbc.properties");
			
			//First we load the underlying JDBC driver.
			// You need this if you don't use the jdbc.drivers
			// system property.
			Class.forName("oracle.jdbc.driver.OracleDriver");			
			
			//Then we set up and register the PoolingDriver.
			setupDriver(config.getString(schema+".jdbcUri"), schema, connPoolSize);
			
			logger.debug("Connection Pool Name: " + schema + ", Size: " + connPoolSize);
			
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection(String poolName)
	{
		// Now, we can use JDBC as we normally would.
		// Using the connect string
		//  jdbc:apache:commons:dbcp:example
		// The general form being:
		//  jdbc:apache:commons:dbcp:<name-of-pool>
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:"+poolName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

//	public Connection openConnection(String schema) {
//		String url = null;
//		String username = null;
//		String password = null;
//		if (conn == null) {
//			if ("profile".equals(schema)) {
//			url = config.getString("profileJdbcUrl");
//			username = config.getString("prifleuser");
//			password = config.getString("profilepassword");
//			} else if ("catalog".equals(schema)) {
//				url = config.getString("profileJdbcUrl");
//				username = config.getString("prifleuser");
//				password = config.getString("profilepassword");
//			} else {
//				logger.error("invalid schema name");
//				return null;
//			}
//			try {
//				conn = DriverManager.getConnection(url, username, password);
//			} catch (SQLException e) {
//				logger.error("can not open connection: " + e);
//			}
//		}
//		return conn;
//	}

	public static void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			logger.error("can not commit transaction: " + e);
		}
	}

	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			logger.error("can not rollback transaction: " + e);
		}

	}

	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			logger.error("can not close connection: " + e);
		}

	}

	/*
	 * this method return an object. it converts the first ResultSet row into a
	 * JavaBean. BeanProcessor maps columns to bean properties as documented in
	 * the BeanProcessor.toBean() javadoc. Column names must match the bean's
	 * property names case insensitively. For example, the firstname column
	 * would be stored in the bean by calling its setFirstName() method.
	 * However, many database column names include characters that either can't
	 * be used or are not typically used in Java method names. You can do one of
	 * the following to map these columns to bean properties: 1. Alias the
	 * column names in the SQL so they match the Java names: select social_sec#
	 * as socialSecurityNumber from person 2. Subclass BeanProcessor and
	 * override the mapColumnsToProperties() method to strip out the offending
	 * characters.
	 * 
	 */
	public static Object executeBeanQuery(Connection conn, Class type, String query, Object[] params)
			throws SQLException {
		QueryRunner run = new QueryRunner();

		// Use the BeanHandler implementation to convert the first
		// ResultSet row into a JavaBean.
		ResultSetHandler h = new BeanHandler(type);

		// Execute the SQL statement with replacement parameters and
		// return the results in a new object generated by the
		// BeanHandler.
		Object p = null;
		try {
			p = run.query(conn, query, params, h);
		} catch (SQLException e) {
			throw e;
		}
		return p;
	}

	/**
	 * Same as the above. Except that it return a list of objects
	 * 
	 * @throws SQLException
	 */

	public static List executeBeanListQuery(Connection conn, Class type, String query, Object[] params)
			throws SQLException {
		QueryRunner run = new QueryRunner();

		// Use the BeanHandler implementation to convert all
		// ResultSet row into a list.
		ResultSetHandler h = new BeanListHandler(type);

		// Execute the SQL statement with parameters and
		// return the results in a list of object generated by the
		// BeanListHandler.
		List p = null;
		try {
			p = (List) run.query(conn, query, params, h);
		} catch (SQLException e) {
			throw e;
		}
		return p;
	}

	public static Map executeMapQuery(Connection conn, String query, Object[] params)
			throws SQLException {
		QueryRunner run = new QueryRunner();

		// Use the BeanHandler implementation to convert the first
		// ResultSet row into a map.
		ResultSetHandler h = new MapHandler();

		// Execute the SQL statement with replacement parameters and
		// return the results in a new Map object generated by the
		// MapHandler.
		Map p = null;
		try {
			p = (Map) run.query(conn, query, params, h);
		} catch (SQLException e) {
			throw e;
		}
		return p;
	}

	public static List executeMapListQuery(Connection conn, String query, Object[] params)
			throws SQLException {
		QueryRunner run = new QueryRunner();

		// Use the BeanHandler implementation to convert the first
		// ResultSet row into a map.
		ResultSetHandler h = new MapListHandler();

		// Execute the SQL statement with replacement parameters and
		// return the results in a new Lis of Map objects generated by the
		// ListMapHandler.
		List p = null;
		try {
			p = (List) run.query(conn, query, params, h);
		} catch (SQLException e) {
			throw e;
		}
		return p;
	}

	public static Object[] executeObjectArrayQuery(Connection conn, String query, Object[] params)
			throws SQLException {
		QueryRunner run = new QueryRunner();

		// 
		ResultSetHandler h = new ArrayHandler();

		Object[] p = null;
		try {
			p = (Object[]) run.query(conn, query, params, h);
			if (p != null) {
				logger.debug("p.length : " + p.length);
			}
		} catch (SQLException e) {
			throw e;
		}
		return p;
	}

	public static Object executeScalarQuery(Connection conn, String query)
	throws SQLException {
		QueryRunner run = new QueryRunner();


		ResultSetHandler h = new ScalarHandler();

		Object p = null;
		try {
			p = run.query(conn, query, h);
			if (p != null) {
				logger.debug("Resultset : " + p);
			}
		} catch (SQLException e) {
			throw e;
		}
		return p;
	}

	public static int executeUpdate(Connection conn, String query, Object[] params) throws SQLException {
		QueryRunner run = new QueryRunner();
		int count = 0;
		try {
			count = run.update(conn, query, params);
			if (count == 0) {
				logger.debug("no record updated:" + query);
			}
		} catch (SQLException e) {
			throw e;
		}
		return count;
	}

	private static void setupDriver(String connectURI, String schema, int connPoolSize) throws Exception {
		//
		// First, we'll need a ObjectPool that serves as the
		// actual pool of connections.
		//
		// We'll use a GenericObjectPool instance, although
		// any ObjectPool implementation will suffice.
		//
        
		GenericObjectPool connectionPool = new GenericObjectPool(null);
		connectionPool.setMaxActive(connPoolSize);		
		
		//
		// Next, we'll create a ConnectionFactory that the
		// pool will use to create Connections.
		// We'll use the DriverManagerConnectionFactory,
		// using the connect string passed in the command line
		// arguments.
		//
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connectURI,null);

		//
		// Now we'll create the PoolableConnectionFactory, which wraps
		// the "real" Connections created by the ConnectionFactory with
		// the classes that implement the pooling functionality.
		//
		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,connectionPool,null,null,false,true);

		//
		// Finally, we create the PoolingDriver itself...
		//
		Class.forName("org.apache.commons.dbcp.PoolingDriver");
		PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");

		//
		// ...and register our pool with it.
		//
		driver.registerPool(schema,connectionPool);

		//
		// Now we can just use the connect string "jdbc:apache:commons:dbcp:example"
		// to access our pool of Connections.
		//
	}

	public static void printDriverStats(String poolName) {
		try 
		{
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			ObjectPool connectionPool;
		
			connectionPool = driver.getConnectionPool(poolName);
			
			logger.debug("NumActive: " + connectionPool.getNumActive());
			logger.debug("NumIdle: " + connectionPool.getNumIdle());
		} catch (SQLException e) {			
			e.printStackTrace();
		}      
	}

	private static void shutdownDriver(String poolName) throws Exception {
		PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
		driver.closePool(poolName);
	}

}
