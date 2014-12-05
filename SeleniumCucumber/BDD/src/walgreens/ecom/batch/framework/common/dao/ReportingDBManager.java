package walgreens.ecom.batch.framework.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import walgreens.ecom.batch.framework.common.beans.ScenarioBean;
import walgreens.ecom.batch.framework.common.beans.StepBean;

public class ReportingDBManager {
    private static String SCENARIO_INSERT = "INSERT INTO qa_scenarios (ScenarioCurrentStepId, ScenarioCurrentStepImageId, ScenarioNumber, ScenarioFeatureName, ScenarioDescription, ScenarioStatus, ScenarioCurrentUrl, ScenarioBrowserName, ScenarioCreateDttm, ScenarioUpdateDttm) "
	    + " VALUES (?,?,?,?,?,?,?,?,now(),now()) ";
    private static String SCENARIO_UPDATE = "UPDATE qa_scenarios " + " set ScenarioStatus = ?, " + " ScenarioCreateDttm = now() ";

    private static String STEP_INSERT = "INSERT INTO qa_steps (StepScenarioId, StepImageId, StepName, StepStatus, StepCurrentURL, StepRunAttemptCount, StepCreateDttm, StepUpdateDttm) "
	    + " VALUES (?,?,?,?,?,?,now(),now()) ";

    private static PreparedStatement insertScenario = null;
    private static PreparedStatement updateScenario = null;
    private static PreparedStatement insertStep = null;

    private static String SCENARIO_IN_PROCESS = "inprocess";
    private static String SCENARIO_SUCCESFULLY_PROCESSED = "pass";
    private static String SCENARIO_FAILED = "fail";

    public static synchronized void insertScenario(ScenarioBean scenario) {
	Connection connection = null;
	int rownum = 0;
	try {
	    connection = QualityCenterDBManager.getRemoteDBConnection();
	    connection.setAutoCommit(false);
	    if (insertScenario == null) {
		insertScenario = connection.prepareStatement(SCENARIO_INSERT, Statement.RETURN_GENERATED_KEYS);
	    } else {
		insertScenario.clearBatch();
		insertScenario.clearParameters();
	    }

	    insertScenario.setInt(1, 0);
	    insertScenario.setInt(2, 0);
	    insertScenario.setString(3, scenario.getScenarioNumber());
	    insertScenario.setString(4, scenario.getScenarioFeatureName());
	    insertScenario.setString(5, scenario.getScenarioDescription());
	    insertScenario.setString(6, SCENARIO_IN_PROCESS);
	    insertScenario.setString(7, scenario.getScenarioCurrentUrl());
	    insertScenario.setString(8, scenario.getScenarioBrowserName());

	    rownum = insertScenario.executeUpdate();
	    ResultSet rsGeneratedKey = insertScenario.getGeneratedKeys();
	    rsGeneratedKey.next();
	    //int scenarioId = rsGeneratedKey.getInt(1);
	    //scenario.setScenarioId(scenarioId);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (rownum != 0) {
		    connection.commit();
		} else {
		    connection.rollback();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }

    public static synchronized void insertStep(ScenarioBean scenario, StepBean step) {
	Connection connection = null;
	int rownum = 0;
	try {
	    connection = QualityCenterDBManager.getRemoteDBConnection();
	    connection.setAutoCommit(false);
	    if (insertStep == null) {
		insertStep = connection.prepareStatement(STEP_INSERT, Statement.RETURN_GENERATED_KEYS);
	    } else {
		insertStep.clearBatch();
		insertStep.clearParameters();
	    }

	    //insertStep.setInt(1, scenario.getScenarioId());
	    insertStep.setInt(2, step.getStepImageId());
	    insertStep.setString(3, step.getStepName());
	    insertStep.setString(4, step.getStepStatus());
	    insertStep.setString(5, step.getStepCurrentUrl());
	    insertStep.setInt(6, 0);
	    rownum = insertStep.executeUpdate();
	    ResultSet rsGeneratedKey = insertStep.getGeneratedKeys();
	    rsGeneratedKey.next();
	    int stepId = rsGeneratedKey.getInt(1);
	    step.setStepId(stepId);
	    scenario.setScenarioCurrentStepId(stepId);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (rownum != 0) {
		    connection.commit();
		} else {
		    connection.rollback();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }

    public static synchronized void updateScenario(ScenarioBean scenario, StepBean step) {
	Connection connection = null;
	int rownum = 0;
	try {
	    connection = QualityCenterDBManager.getRemoteDBConnection();
	    connection.setAutoCommit(false);
	    if (updateScenario == null) {
		updateScenario = connection.prepareStatement(SCENARIO_UPDATE);
	    } else {
		updateScenario.clearBatch();
		updateScenario.clearParameters();
	    }

	    if (step.isFinalStep() && SCENARIO_SUCCESFULLY_PROCESSED.equals(scenario.getScenarioStatus())) {
		updateScenario.setString(1, SCENARIO_SUCCESFULLY_PROCESSED);
	    } else {
		updateScenario.setString(1, SCENARIO_FAILED);
	    }
	    rownum = updateScenario.executeUpdate();

	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (rownum != 0) {
		    connection.commit();
		} else {
		    connection.rollback();
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }

}
