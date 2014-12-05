package walgreens.ecom.batch.framework.common.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.rowset.CachedRowSet;
import com.sun.rowset.CachedRowSetImpl;

public abstract class AutomationDBManager {

    public static CachedRowSet executeQuery(String query, Connection connection) {
	CachedRowSet queryOutput = null;
	try {
	    Statement statement = connection.createStatement();
	    ResultSet resultSet = statement.executeQuery(query);
	    queryOutput = new CachedRowSetImpl();
	    queryOutput.populate(resultSet);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return queryOutput;
    }

    public static void disconnect(Connection connection) {
	try {
	    connection.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public static int executeUpdate(String query, Connection connection) {
	int updateStatus = 0;
	try {
	    Statement statement = connection.createStatement();
	    updateStatus = statement.executeUpdate(query);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	disconnect(connection); // RAM - 2/8/2013 - UnCommenting this out since
	// this will cause fatal error when doing put data and get data
	// simultaneously
	return updateStatus;
    }

}
