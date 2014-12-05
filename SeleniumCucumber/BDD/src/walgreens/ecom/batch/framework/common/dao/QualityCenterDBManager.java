package walgreens.ecom.batch.framework.common.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.rowset.CachedRowSet;
import org.apache.commons.lang.StringUtils;
import walgreens.ecom.batch.framework.EcommTestRunner;
import walgreens.ecom.batch.framework.common.beans.ScenarioBean;
import walgreens.ecom.batch.framework.common.beans.ScenarioContollerData;
import walgreens.ecom.batch.framework.common.beans.ScenarioControllerRowData;

public class QualityCenterDBManager extends AutomationDBManager {

	private static Connection remoteDbConnection = null;
	private static Connection qualityCenterFileConnection = null;
	private static Map<String, Connection> qcFileConnectionsMap = null;
	private static ScenarioControllerParser readScenarioData = null;
	public static synchronized Connection getQualityCenterFileConnection(String fileName) {
		String INPUT_FILE_NAME = fileName + ".xls";
		System.out.println("getQualityCenterFileConnection:::INPUT_FILE_NAME::::"+INPUT_FILE_NAME);
		// RAM 12/27/12 - EcommTestRunner.scenarioControllerPath - To handle
		// scenario controller sheets seperately

		try {
			if (qcFileConnectionsMap == null) {
				qcFileConnectionsMap = new HashMap<String, Connection>();
			}

			if (qcFileConnectionsMap.get(INPUT_FILE_NAME) == null) {
				System.out.println("getQualityCenterFileConnectionIFFFFFFFFFFFFFFFFFFFFFFFF");
				qualityCenterFileConnection = DriverManager.getConnection((new StringBuilder("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=")).append(EcommTestRunner.scenarioControllerPath)
						.append(INPUT_FILE_NAME).append(";READONLY=FALSE").toString());
				qcFileConnectionsMap.put(INPUT_FILE_NAME, qualityCenterFileConnection);
			} else {
				System.out.println("getQualityCenterFileConnectionELSEEEEEEEEEEEEEEEEEEEEEEEEEE");
				qualityCenterFileConnection = qcFileConnectionsMap.get(INPUT_FILE_NAME);
				if (qualityCenterFileConnection.isClosed())
					System.out.println("@@@@@@@@@qualityCenterFileConnection.isClosed()############");
				// RAM - 2/8/13 - To
				// avoid fatal error
				// if connection is
				// closed by put
				// data
				qualityCenterFileConnection = DriverManager.getConnection((new StringBuilder("jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ="))
						.append(EcommTestRunner.scenarioControllerPath).append(INPUT_FILE_NAME).append(";READONLY=FALSE").toString());
				qcFileConnectionsMap.put(INPUT_FILE_NAME, qualityCenterFileConnection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qualityCenterFileConnection;
	}

	public static synchronized String getQCFileData(String inputFileName, String dataSheetName, String rowID, String columnName) throws Exception {
		String query = (new StringBuilder("SELECT [")).append(columnName).append("]").append(" FROM [").append(dataSheetName).append("$]").append(" WHERE ID = '").append(rowID).append("'").toString();
		System.out.println("!!!!getQCFileDatagetQCFileData&&&query:::"+query);
		CachedRowSet queryOutput = AutomationDBManager.executeQuery(query, getQualityCenterFileConnection(inputFileName));
		if (queryOutput.size() == 0) {
			throw new Exception((new StringBuilder("No Row found in the Excel Sheet for the current Row ID = ")).append(rowID).toString());
		}
		queryOutput.next();
		return queryOutput.getString(columnName);
	}

	public static synchronized void addScenarioData(ScenarioBean scenarioBean) {
		String[] scenarioNumber = null;
		String fileName = null;
		String sheetName = null;
		String scenarioId = null;
		try {
			System.out.println("addScenarioDataDaaaddScenarioData"+scenarioBean);
			if (StringUtils.isNotBlank(scenarioBean.getScenarioNumber())) {
				scenarioNumber = scenarioBean.getScenarioNumber().split("-");
				System.out.println("@@@@addScenarioDatascenarioNumber::"+scenarioNumber);
				fileName = "QC-" + scenarioNumber[0]+".xls";
			
				System.out.println("@@@@addScenariofileName::"+fileName);
				sheetName = scenarioNumber[1];
				System.out.println("@@@@addScenario@@sheetName::"+sheetName);
				scenarioId = scenarioBean.getScenarioNumber();
				System.out.println("@@@@addScenario@@scenarioId::"+scenarioId);
				scenarioBean.setScenarioId(scenarioId);
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
				System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
				String filePath = EcommTestRunner.scenarioControllerPath+fileName;
				System.out.println("filePath:"+filePath);
				ScenarioControllerRowData smoke1 = ScenarioControllerParser.readScenarioValue(filePath, sheetName, scenarioId);
			//	String scenarioQuery = (new StringBuilder("SELECT *")).append(" FROM [").append(sheetName).append("$]").append(" WHERE ID = '").append(scenarioId).append("'").toString();
			//	System.out.println("@@@addScenario@@@scenarioQuery:::"+scenarioQuery);
				//CachedRowSet scenarioResultSet = executeQuery(scenarioQuery, getQualityCenterFileConnection(fileName));
				//scenarioResultSet.next();
				// Scenario Data
				scenarioBean.setScenarioDescription(smoke1.getScenarioDescription());
				scenarioBean.setScenarioFeatureName(smoke1.getScenarioFeatureName());
				scenarioBean.setScenarioTagName(smoke1.getId());
				
				
			} else {
				// Scenario Data
				scenarioBean.setScenarioDescription("Development Scenario");
				scenarioBean.setScenarioFeatureName("Development Scenario");
				scenarioBean.setScenarioTagName("Development Scenario");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getRemoteDBConnection() {
		try {
			if (remoteDbConnection == null) {
				String username = EcommTestRunner.serverProp.getProperty("jdbc.username");
				String password = EcommTestRunner.serverProp.getProperty("jdbc.password");
				String host = EcommTestRunner.serverProp.getProperty("jdbc.host");
				String port = EcommTestRunner.serverProp.getProperty("jdbc.port");
				String schema = EcommTestRunner.serverProp.getProperty("jdbc.schema");
				String dbUrl = "jdbc:mysql://" + host + ":" + port + "/" + schema + "?user=" + username + "&password=" + password;
				Class.forName("com.mysql.jdbc.Driver");
				remoteDbConnection = DriverManager.getConnection(dbUrl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return remoteDbConnection;
	}
}
