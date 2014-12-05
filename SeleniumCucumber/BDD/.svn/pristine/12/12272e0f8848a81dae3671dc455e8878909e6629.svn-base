package walgreens.ecom.batch.framework.common.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.dbutils.DbUtils;
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

public class DatabaseManager {

    static Log logger = LogFactory.getLog(DatabaseManager.class);

    protected Configuration config;
    static String DATASOURCE_A = "DataSourceA";
    static String DATASOURCE_B = "DataSourceB";
    private Connection conn;

    public DatabaseManager() {
	try {
	    // If running locally without Launcher class
	   // config = new PropertiesConfiguration("config/jdbc.properties");


	    // If running with Launcher class
	    config = new PropertiesConfiguration("jdbc.properties");
	} catch (ConfigurationException e) {
	    e.printStackTrace();
	}
    }

    public Connection openConnection(String schema) {
	String url = null;
	String username = null;
	String password = null;
	String dataSource = null;
	if ("DODSDB01".equals(schema)) {
	    url = config.getString("DODSDB01.jdbcUrl");
	    username = config.getString("DODSDB01.user");
	    password = config.getString("DODSDB01.password");
	} else if ("profile".equals(schema)) {
	    url = config.getString("profile.jdbcUrl");
	    username = config.getString("profile.user");
	    password = config.getString("profile.password");
	} else if ("catalog".equals(schema)) {
	    dataSource = getLiveCatalog();
	    logger.debug("Live Catalog " + dataSource);
	    if (DATASOURCE_A.equals(dataSource)) {
		url = config.getString("catalog1.jdbcUrl");
		username = config.getString("catalog1.user");
		password = config.getString("catalog1.password");
	    } else if (DATASOURCE_B.equals(dataSource)) {
		url = config.getString("catalog2.jdbcUrl");
		username = config.getString("catalog2.user");
		password = config.getString("catalog2.password");
	    } else {
		url = config.getString("catalog.jdbcUrl");
		username = config.getString("catalog.user");
		password = config.getString("catalog.password");
	    }
	    logger.debug("Catalog User name " + username);
	} else if ("photo".equals(schema)) {
	    url = config.getString("photo.jdbcUrl");
	    username = config.getString("photo.user");
	    password = config.getString("photo.password");
	} else if ("readydose".equals(schema)) {
	    url = config.getString("readydose.jdbcUrl");
	    username = config.getString("readydose.user");
	    password = config.getString("readydose.password");
	} else if ("admin".equals(schema)) {
	    url = config.getString("admin.jdbcUrl");
	    username = config.getString("admin.user");
	    password = config.getString("admin.password");
	} else if ("was".equals(schema)) {
	    url = config.getString("was.jdbcUrl");
	    username = config.getString("was.user");
	    password = config.getString("was.password");
	} else if ("profilepurge".equals(schema)) {
	    url = config.getString("profilepurge.jdbcUrl");
	    username = config.getString("profilepurge.user");
	    password = config.getString("profilepurge.password");
	} else if ("edw_batch_user".equals(schema)) {
	    url = config.getString("edw_batch_user.jdbcUrl");
	    username = config.getString("edw_batch_user.user");
	    password = config.getString("edw_batch_user.password");
	}else if ("csc_agent_user".equals(schema)) {
	    url = config.getString("csc_user.jdbcUrl");
	    username = config.getString("csc.user");
	    password = config.getString("csc.password");
	} else if ("dguard_profile".equals(schema)) {
	    url = config.getString("dguard_profile.jdbcUrl");
	    username = config.getString("dguard_profile.user");
	    password = config.getString("dguard_profile.password");
	} else if ("dguard_catalog".equals(schema)) {
	    url = config.getString("dguard_catalog.jdbcUrl");
	    username = config.getString("dguard_catalog.user");
	    password = config.getString("dguard_catalog.password");
	} else if ("dguard_csc_user".equals(schema)) {
	    url = config.getString("dguard_csc_user.jdbcUrl");
	    username = config.getString("dguard_csc_user.user");
	    password = config.getString("dguard_csc_user.password");
	}  else if ("product_review".equals(schema)) {
	    url = config.getString("product_review.jdbcUrl");
	    username = config.getString("product_review.user");
	    password = config.getString("product_review.password");
	} else if ("marketing".equals(schema)){
	    url = config.getString("marketing_profile.jdbcUrl");
	    username = config.getString("marketing_profile.user");
	    password = config.getString("marketing_profile.password");
	} else if ("core".equals(schema)){
	    url = config.getString("core_profile.jdbcUrl");
	    username = config.getString("core_profile.user");
	    password = config.getString("core_profile.password");
	} else if ("pharmacy".equals(schema)){
	    url = config.getString("pharmacy_profile.jdbcUrl");
	    username = config.getString("pharmacy_profile.user");
	    password = config.getString("pharmacy_profile.password");
	}else if ("selfserve".equals(schema)){
	    url = config.getString("selfserve_profile.jdbcUrl");
	    username = config.getString("selfserve_profile.user");
	    password = config.getString("selfserve_profile.password");
	}
	else {
	    logger.error("invalid schema name");
	    return null;
	}
	try {
	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    conn = DriverManager.getConnection(url, username, password);
	} catch (SQLException e) {
	    logger.error("can not open connection: " + e);
	}
	return conn;
    }

    public void commit() {
	try {
	    if (conn != null)
		conn.commit();
	} catch (SQLException e) {
	    logger.error("can not commit transaction: " + e);
	}
    }

    public void rollback() {
	try {
	    if (conn != null)
		conn.rollback();
	} catch (SQLException e) {
	    logger.error("can not rollback transaction: " + e);
	}

    }

    public void closeConnection() {
	try {
	    if (conn != null)
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
     */
    public Object executeBeanQuery(Class type, String query, Object[] params)
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
	    // TODO Auto-generated catch block
	    logger.error("Exception: " + e);
	    throw e;
	}
	return p;
    }

    /**
     * Same as the above. Except that it return a list of objects
     * 
     * @throws SQLException
     */

    public List executeBeanListQuery(Class type, String query, Object[] params)
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
	    // TODO Auto-generated catch block
	    logger.error("Exception: " + e);
	    throw e;
	}
	return p;
    }

    public Map executeMapQuery(String query, Object[] params)
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
	    // TODO Auto-generated catch block
	    logger.error("Exception: " + e);
	    throw e;
	}
	return p;
    }

    public List executeMapListQuery(String query, Object[] params)
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
	    logger.error("Exception: " + e);
	    throw e;
	}
	return p;
    }

    public Object[] executeObjectArrayQuery(String query, Object[] params)
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
	    logger.error("Exception: " + e);
	    throw e;
	}
	return p;
    }

    public Object executeScalarQuery(String query) throws SQLException {
	QueryRunner run = new QueryRunner();

	ResultSetHandler h = new ScalarHandler();

	Object p = null;
	try {
	    p = run.query(conn, query, h);
	   if (p != null) {
		logger.debug("Resultset : " + p);
	    }
	} catch (SQLException e) {
	    logger.error("Exception: " + e);
	    throw e;
	}
	return p;
    }
    
    /**
     * Method to run parameterized Select Query
     * @param query
     * @param param
     * @return 
     * @throws SQLException
     */
    public Object executeScalarQuery(String query,Object[] param) throws SQLException {
	QueryRunner run = new QueryRunner();

	ResultSetHandler resultSetHandler = new ScalarHandler();

	Object p = null;
	try {
	    p=run.query(conn, query, param, resultSetHandler);
	    if (p != null) {
		logger.debug("Resultset : " + p);
	    }
	} catch (SQLException e) {
	    logger.error("Exception: " + e);
	    throw e;
	}
	return p;
    }

    public int executeUpdate(String query, Object[] params) throws SQLException {
	QueryRunner run = new QueryRunner();
	int count = 0;
	try {
	    count = run.update(conn, query, params);
	    if (count == 0) {
		logger.warn("no record updated:" + query);
	    }
	} catch (SQLException e) {
	    logger.error("Exception: " + e);
	    throw e;
	}
	return count;
    }

    /**
     * executeUpdateMatrix
     * 
     * @param query
     * @param params
     * @return
     * @throws SQLException
     */
    public int[] executeUpdateMatrix(String query, Object[][] params)
	    throws SQLException {
	QueryRunner run = new QueryRunner();

	int[] count;
	try {
	    count = run.batch(conn, query, params);

	} catch (SQLException e) {
	    logger.error("Exception: " + e);
	    throw e;
	}
	return count;
    }

    /**
     * This method is used to retrieve the live catalog DB
     * 
     * @return String which is catalog or catalog1 or catalog2
     */
    public String getLiveCatalog() {
	String liveCatalog = "catalog";
	String dataSource = null;
	try {
	    openConnection("profile");
	    String params[] = new String[0];
	    ArrayList sourceList = (ArrayList) executeMapListQuery(
		    "select curr_ds_name from das_sds", params);
	    HashMap sourceMap = (HashMap) sourceList.get(0);
	    dataSource = (String) sourceMap.get("curr_ds_name");
	    if (dataSource != null && dataSource.trim().length() > 10) {
		dataSource = dataSource.trim().substring(10);
	    }
	    liveCatalog = liveCatalog + dataSource;
	} catch (Exception e) {
	    logger.error("Exception: " + e);
	} finally {
	    try {
		conn.close();
	    } catch (Exception e) {
		logger.error("Exception :" + e);
	    }
	}
	return liveCatalog;
    }

    public void executeStoredProcedure(String sql, Object[] params)
	    throws SQLException {
	CallableStatement stmt = null;
	try {
	    stmt = conn.prepareCall(sql);
	    fillStatement(stmt, params);
	    stmt.execute();
	} catch (SQLException e) {
	    StringBuffer msg = new StringBuffer(e.getMessage());
	    msg.append(" Query: ");
	    msg.append(sql);
	    msg.append(" Parameters: ");
	    if (params == null)
		msg.append("[]");
	    else
		msg.append(Arrays.asList(params));
	    throw new SQLException(msg.toString(), e.getSQLState(), e
		    .getErrorCode());
	} finally {
	    DbUtils.close(stmt);
	}
    }

    protected void fillStatement(CallableStatement stmt, Object params[])
	    throws SQLException {
	if (params == null)
	    return;
	for (int i = 0; i < params.length; i++)
	    if (params[i] != null)
		stmt.setObject(i + 1, params[i]);
	    else
		stmt.setNull(i + 1, 12);

    }

    public void setAutoCommit(boolean autoCommitFlag) throws SQLException {
	if (conn != null) {
	    conn.setAutoCommit(autoCommitFlag);
	}
    }
}