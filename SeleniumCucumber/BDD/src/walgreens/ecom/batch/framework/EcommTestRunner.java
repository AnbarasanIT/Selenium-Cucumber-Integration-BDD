package walgreens.ecom.batch.framework;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import walgreens.ecom.batch.automation.library.common.ReportingLibrary;

import walgreens.ecom.batch.framework.common.BrowserFactory;
import walgreens.ecom.batch.framework.common.beans.ScenarioBean;
import walgreens.ecom.batch.framework.common.beans.StepBean;

import walgreens.ecom.batch.framework.common.utils.CucumberUtils;
import walgreens.ecom.batch.framework.common.utils.RemoteShellUtils;

public class EcommTestRunner {
	/**
	 * @param args
	 */

	// public static boolean isServerMode = false;
	public static boolean isDBReporting = false;
	public static String runMode = null;
	public static String runEnvironment = null;
	public static String runVDI = null;
	public static String currentlyRunningTagName = null;
	public static Log logger = LogFactory.getLog(EcommTestRunner.class);
	public static Map<String, ScenarioBean> registeredScenariosMap = null;
	public static Connection connection = null;
	public static Properties serverProp = null;
	public static Configuration config;
	public static String[] command = null;
	public static String driverPath = null;
	public static String currentBrowserName = null;
	public static String automationInputDataFilePath = null;
	public static String scenarioControllerPath = null;
	public static Runtime runtime = null;
	public static String currentDateTime = null;
	public static String currentDate = null;
	public static String closeBrowser = null;
	public static String maximizeBrowser = null;
	public static String cucumberReportPath = null;
	public static String customReportPath = null;
	public static String excelReportDirectory = null;
	public static String runReportPath = null;
	public static String overAllSummaryFile = null;
	public static String screenshotDirectory = null;
	public static FileWriter reportFileWriter = null;
	public static BufferedWriter customReportOutput = null;
	public static int warningsCounter = 0;
	public static int failuresCounter = 0;
	public static int passCounter = 0;
	public static int disabledCouter = 0;
	public static long totalExecutionTime = 0;
	public static long startTimeInMilliSeconds = 0;
	public static long endTimeInMilliSeconds = 0;
	public static boolean isReexecuteScenarios = false;
	public static String totalReexecuteFailScenarios = "";
	public static int reexecuteCnt = 0;
	public static String isChunksEnabled = null;
	public static String enableCucumberReport = null;
	public static String firefoxBinaryPath = null;
	public static String runfromVDI = null;
	public static String device = null;

	public EcommTestRunner() {
	}

	public static void main(String[] args) {
		List<String> scenariosList = null;
		Map<String, List<String>> featuresMap = null;

		try {
			EcommTestRunner ecommTestRunner = new EcommTestRunner();
			startTimeInMilliSeconds = Calendar.getInstance().getTimeInMillis();
			System.out.println("startTimeInMilliSeconds:::"+startTimeInMilliSeconds);
			init(args);
			//args[0]            args[1]       args[2]
			//EcommTestRunner functional @FULFILLMENT-DC-1
			//EcommTestRunner smoke staging ST2 @Smoke-Registration-1
			featuresMap = new HashMap<String, List<String>>();
			if (runMode.equalsIgnoreCase("functional")) {
				scenariosList = CucumberUtils.getTagsList(args[2]);
				System.out.println("scenariosList:::"+scenariosList);
				featuresMap.put("functional", scenariosList);
				ecommTestRunner.startTesting(featuresMap);
			} else if (runMode.equalsIgnoreCase("regression")) {
				scenariosList = CucumberUtils.getTagsList(args[4]);
				featuresMap.put("regression", scenariosList);
				ecommTestRunner.startTesting(featuresMap);
			} else if (runMode.equalsIgnoreCase("smoke")) {
				scenariosList = CucumberUtils.getTagsList(args[4]);
				featuresMap.put("smoke", scenariosList);
				ecommTestRunner.startTesting(featuresMap);
			} else if (runMode.equalsIgnoreCase("performance")) {
				scenariosList = CucumberUtils.getTagsList(args[4]);
				featuresMap.put("performance", scenariosList);
				ecommTestRunner.startTesting(featuresMap);
			}

			RemoteShellUtils.disconnectSessions();
			//DBLibrary.disconnectConnections();
			if (EcommTestRunner.customReportOutput != null) {

				EcommTestRunner.customReportOutput
				.write("######################################## Feature Execution SUMMARY ###################################################################################\n\n");
				EcommTestRunner.customReportOutput.write("WARNINGS: " + EcommTestRunner.warningsCounter + "\n");
				EcommTestRunner.customReportOutput.write("FAILURES: " + EcommTestRunner.failuresCounter + "\n");
				EcommTestRunner.customReportOutput
				.write("######################################################################################################################################################\n\n");

				EcommTestRunner.customReportOutput.close();
			}

			// RAM - 1/18/13 - REPORTING PURPOSE
			// ---------------------------------------------

			endTimeInMilliSeconds = Calendar.getInstance().getTimeInMillis();
			totalExecutionTime = (endTimeInMilliSeconds - startTimeInMilliSeconds) / 1000;

			ReportingLibrary.createScenarioSummaryDashboard(EcommTestRunner.overAllSummaryFile);

			// Copy the latest run report for easy reference into a folder
			// called LATEST_RUN_REPORT
			File fCopyFolder = new File(EcommTestRunner.excelReportDirectory + File.separator + "LATEST_RUN_REPORT");
			if (fCopyFolder.exists()) {
				FileUtils.deleteDirectory(fCopyFolder);

				File fSourceFolder = new File(EcommTestRunner.runReportPath);
				FileUtils.copyDirectory(fSourceFolder, fCopyFolder);

			} else {
				File fSourceFolder = new File(EcommTestRunner.runReportPath);
				FileUtils.copyDirectory(fSourceFolder, fCopyFolder);
			}

			// Update ALM with the scenario status
			if (config.getString("ALMUpdate").equalsIgnoreCase("YES"))
				ReportingLibrary.updateALMStatus(EcommTestRunner.overAllSummaryFile);

			// Invoke the Over All Summary once execution is done
			File fHtmlReport = new File(EcommTestRunner.overAllSummaryFile);
			Desktop desktop = Desktop.getDesktop();
			if (fHtmlReport.exists()) {
				desktop.open(fHtmlReport);
			}

			// ---------------------------------------------

			// Kill CHROME DRIVER.exe post end of testing
			// Please do not delete and do not uncomment - Imran
			// Runtime.getRuntime().exec("cmd /c start " +
			// System.getProperty("user.dir") +
			// "\\Extensions\\killchromedriver.bat");

			// ----------------------------------------------

			// RAM - 10/25/13 - Re-execute the failed scenarios alone twice,
			// just in case they are failed due to environment issues
			// --------------------------------------------------------------------------------------------------------------------
			if ((StringUtils.isNotBlank(totalReexecuteFailScenarios.trim()) && StringUtils.isNotEmpty(totalReexecuteFailScenarios.trim())) && (reexecuteCnt < config.getInt("reExecutionIterator"))) {

				System.out.println("########### Failed Scenarios Iteration " + reexecuteCnt + " started. ###########");

				isReexecuteScenarios = true;
				String failedSceanrios = totalReexecuteFailScenarios.trim();
				totalReexecuteFailScenarios = "";
				warningsCounter = 0;
				failuresCounter = 0;
				passCounter = 0;
				disabledCouter = 0;
				reexecuteCnt += 1;

				EcommTestRunner.main(new String[] { "", EcommTestRunner.runMode, EcommTestRunner.runEnvironment, EcommTestRunner.runVDI, failedSceanrios });
			}
			// --------------------------------------------------------------------------------------------------------------------

		} catch (Exception e) {
			LogIt(e, null, null);
		} finally {
			System.out.println("End of Testing");
		}
	}

	public int startTesting(Map<String, List<String>> featuresMap) throws Exception {
		final int PROCESSING = 0;
		final int COMPLETE = 1;
		int returnCode = PROCESSING;

		int threadPoolSize = 1;
		int scenariosChunkSize = 1000;
		long threadSleepTime = config.getLong("threadSleepTime");
		List<String>[] scenarioChunks = null;

		List<String> scenariosList = null;
		Map.Entry<String, List<String>> entry = null;

		ExecutorService fireFoxExecSvc = null;
		ExecutorService iosExecSvc = null;
		ExecutorService chromeExecSvc = null;
		ExecutorService internetExplorerExecSvc = null;
		//Set<Entry<String, List<String>>> entrySet = featuresMap.entrySet();
		//Iterator<Entry<String, List<String>>> iterator = entrySet.iterator();
		Iterator<Entry<String, List<String>>> iterator = featuresMap.entrySet().iterator();
		while (iterator.hasNext()) {
			entry = (Map.Entry<String, List<String>>) iterator.next();
			scenariosList = (List<String>) entry.getValue();
			System.out.println("@@@startTesting @@@scenariosList::::::::::::::::"+scenariosList);
			try {

				if (runMode.equalsIgnoreCase("regression")) {
					if (isReexecuteScenarios) {
						threadPoolSize = 1;
						scenariosChunkSize = 1;
					} else {
						threadPoolSize = config.getInt("thread.poolSize");
						scenariosChunkSize = config.getInt("thread.unitOfWork");
					}

					scenarioChunks = CucumberUtils.splitTheList(scenariosList, scenariosChunkSize);
				} else if (runMode.equalsIgnoreCase("smoke")) {

					if (isReexecuteScenarios) {
						threadPoolSize = 1;
						scenariosChunkSize = 1;
					} else {
						threadPoolSize = config.getInt("smoke.thread.poolSize");
						System.out.println("@@@startTesting @@threadPoolSize::::"+threadPoolSize);
						scenariosChunkSize = config.getInt("smoke.thread.unitOfWork");
						System.out.println("@@@startTesting @scenariosChunkSize::::"+scenariosChunkSize);
					}

					scenarioChunks = CucumberUtils.splitTheList(scenariosList, scenariosChunkSize);
				}else if (runMode.equalsIgnoreCase("performance")) {

					if (isReexecuteScenarios) {
						threadPoolSize = 1;
						scenariosChunkSize = 1;
					} else {
						threadPoolSize = config.getInt("thread.poolSize");
						scenariosChunkSize = config.getInt("thread.unitOfWork");
					}

					scenarioChunks = CucumberUtils.splitTheList(scenariosList, scenariosChunkSize);
				}

				//RAM 13-JAN-14 - User Agent/Device Detection
				device=config.getString("device");

				currentBrowserName = BrowserFactory.FIRE_FOX;
				if (config.getString(BrowserFactory.FIRE_FOX).equalsIgnoreCase("yes")) {
					//RAM - 11/15/2013 - Added for running Firefox from persistent drive in VDI
					firefoxBinaryPath=config.getString("firefoxBinaryPath");


					if (fireFoxExecSvc == null) {
						fireFoxExecSvc = Executors.newFixedThreadPool(threadPoolSize);
					}
					  System.out.println("@@startTesting@@isChunksEnabled"+isChunksEnabled);
					if ("yes".equalsIgnoreCase(isChunksEnabled)) {
						System.out.println("@@startTesting@@scenarioChunks.length:::"+scenarioChunks.length);
						for (int i = 0; i < scenarioChunks.length; i++) {
							System.out.println("@@TestThreadRunnerstartTesting@@scenarioChunks[i]:::"+"i=="+i+"scenarios:::"+scenarioChunks[i]);
							fireFoxExecSvc.execute(new TestThreadRunner(scenarioChunks[i], config, runEnvironment, runMode, BrowserFactory.FIRE_FOX));
						}
					} else {
						System.out.println("@@@@@@@@@@@TestThreadRunner:::Elseeee@@@@@@@@@@@@@"+scenariosList);
						fireFoxExecSvc.execute(new TestThreadRunner(scenariosList, config, runEnvironment, runMode, BrowserFactory.FIRE_FOX));
					}
					// shutdown so to prevent other tasks from being added to
					// the
					// queue
					// and await termination of threads
					try {
						fireFoxExecSvc.shutdown();
						// execSvc.awaitTermination(500, TimeUnit.MILLISECONDS);
					} catch (Exception ie) {
						fireFoxExecSvc.shutdownNow();
						// log.error("Thread interruption error encountered, error message: ",
						// ie);
						// processorLog.error("Thread interruption error encountered, error message: ",
						// ie);
					}

					// Keep waiting until all of the active threads ends
					int fireFoxRunningThreadsPoolCount = ((ThreadPoolExecutor) fireFoxExecSvc).getPoolSize();
					// log.info("Out of While: Current Threads Count + " +
					// runningThreadsPoolCount);
					while (fireFoxRunningThreadsPoolCount > 0) {
						// sleep for a while
						try {
							Thread.sleep(threadSleepTime);

							// Fire Fox Thread Pool Count
							fireFoxRunningThreadsPoolCount = ((ThreadPoolExecutor) fireFoxExecSvc).getPoolSize();
							System.out.println("Current Running Thread Count for Firefox: " + fireFoxRunningThreadsPoolCount);
						} catch (Exception e) {
							;
							// log.error("Sleep Interrupted");
							// processorLog.error("Sleep Interrupted");
						}
					}// End of While
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			try {
				if (config.getString(BrowserFactory.CHROME).equalsIgnoreCase("yes")) {
					currentBrowserName = BrowserFactory.CHROME;

					if (chromeExecSvc == null) {
						chromeExecSvc = Executors.newFixedThreadPool(threadPoolSize);
					}

					if ("yes".equalsIgnoreCase(isChunksEnabled)) {
						for (int i = 0; i < scenarioChunks.length; i++) {
							chromeExecSvc.execute(new TestThreadRunner(scenarioChunks[i], config, runEnvironment, runMode, BrowserFactory.CHROME));
						}
					} else {
						chromeExecSvc.execute(new TestThreadRunner(scenariosList, config, runEnvironment, runMode, BrowserFactory.CHROME));
					}

					// shutdown so to prevent other tasks from being added to
					// the
					// queue
					// and await termination of threads
					try {
						chromeExecSvc.shutdown();
						// execSvc.awaitTermination(500, TimeUnit.MILLISECONDS);
					} catch (Exception ie) {
						chromeExecSvc.shutdownNow();
						// log.error("Thread interruption error encountered, error message: ",
						// ie);
						// processorLog.error("Thread interruption error encountered, error message: ",
						// ie);
					}

					// Keep waiting until all of the active threads ends
					int chromeRunningThreadsPoolCount = ((ThreadPoolExecutor) chromeExecSvc).getPoolSize();
					while (chromeRunningThreadsPoolCount > 0) {
						// sleep for a while
						try {
							Thread.sleep(threadSleepTime);

							// Chrome Thread Pool Count
							chromeRunningThreadsPoolCount = ((ThreadPoolExecutor) chromeExecSvc).getPoolSize();
							System.out.println("Current Running Thread Count for Chrome: " + chromeRunningThreadsPoolCount);
						} catch (Exception e) {
							;
							// log.error("Sleep Interrupted");
							// processorLog.error("Sleep Interrupted");
						}
					}// End of While
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			
			try {
				if (config.getString(BrowserFactory.IOS_DEVICE_BROWSER).equalsIgnoreCase("yes")) {
					currentBrowserName = BrowserFactory.IOS_DEVICE_BROWSER;

					if (iosExecSvc == null) {
						iosExecSvc = Executors.newFixedThreadPool(threadPoolSize);
					}

					if ("yes".equalsIgnoreCase(isChunksEnabled)) {
						for (int i = 0; i < scenarioChunks.length; i++) {
							iosExecSvc.execute(new TestThreadRunner(scenarioChunks[i], config, runEnvironment, runMode, BrowserFactory.IOS_DEVICE_BROWSER));
						}
					} else {
						iosExecSvc.execute(new TestThreadRunner(scenariosList, config, runEnvironment, runMode, BrowserFactory.IOS_DEVICE_BROWSER));
					}

					// shutdown so to prevent other tasks from being added to
					// the
					// queue
					// and await termination of threads
					try {
						iosExecSvc.shutdown();
						// execSvc.awaitTermination(500, TimeUnit.MILLISECONDS);
					} catch (Exception ie) {
						iosExecSvc.shutdownNow();
						// log.error("Thread interruption error encountered, error message: ",
						// ie);
						// processorLog.error("Thread interruption error encountered, error message: ",
						// ie);
					}

					// Keep waiting until all of the active threads ends
					int iosRunningThreadsPoolCount = ((ThreadPoolExecutor) iosExecSvc).getPoolSize();
					while (iosRunningThreadsPoolCount > 0) {
						// sleep for a while
						try {
							Thread.sleep(threadSleepTime);

							// Chrome Thread Pool Count
							iosRunningThreadsPoolCount = ((ThreadPoolExecutor) iosExecSvc).getPoolSize();
							System.out.println("Current Running Thread Count for iosDeviceBrowser: " + iosRunningThreadsPoolCount);
						} catch (Exception e) {
							;
							// log.error("Sleep Interrupted");
							// processorLog.error("Sleep Interrupted");
						}
					}// End of While
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			
			
			try {
				if (config.getString(BrowserFactory.INTERNET_EXPLORER).equalsIgnoreCase("yes")) {
					currentBrowserName = BrowserFactory.INTERNET_EXPLORER;

					if (internetExplorerExecSvc == null) {
						internetExplorerExecSvc = Executors.newFixedThreadPool(threadPoolSize);
					}

					if ("yes".equalsIgnoreCase(isChunksEnabled)) {
						for (int i = 0; i < scenarioChunks.length; i++) {
							internetExplorerExecSvc.execute(new TestThreadRunner(scenarioChunks[i], config, runEnvironment, runMode, BrowserFactory.INTERNET_EXPLORER));
						}
					} else {
						internetExplorerExecSvc.execute(new TestThreadRunner(scenariosList, config, runEnvironment, runMode, BrowserFactory.INTERNET_EXPLORER));
					}

					// shutdown so to prevent other tasks from being added to
					// the
					// queue
					// and await termination of threads
					try {
						internetExplorerExecSvc.shutdown();
						// execSvc.awaitTermination(500, TimeUnit.MILLISECONDS);
					} catch (Exception ie) {
						internetExplorerExecSvc.shutdownNow();
						// log.error("Thread interruption error encountered, error message: ",
						// ie);
						// processorLog.error("Thread interruption error encountered, error message: ",
						// ie);
					}

					// Keep waiting until all of the active threads ends
					int internetExplorerThreadsPoolCount = ((ThreadPoolExecutor) internetExplorerExecSvc).getPoolSize();
					// log.info("Out of While: Current Threads Count + " +
					// runningThreadsPoolCount);
					while (internetExplorerThreadsPoolCount > 0) {
						// sleep for a while
						try {
							Thread.sleep(threadSleepTime);

							// Internet Explorer Thread Pool Count
							internetExplorerThreadsPoolCount = ((ThreadPoolExecutor) internetExplorerExecSvc).getPoolSize();
							System.out.println("Current Running Thread Count for Internet Explorer: " + internetExplorerThreadsPoolCount);
						} catch (Exception e) {
							;
							// log.error("Sleep Interrupted");
							// processorLog.error("Sleep Interrupted");
						}
					}// End of While
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		returnCode = COMPLETE;
		scenarioChunks = null;

		return returnCode;
	}

	/**
	 ************************************************************************ 
	 * @Purpose - Method to Log the Exceptions
	 * @Input - HashMap
	 * @author - Imran Aslam
	 * @Created - May 21st, 2012
	 * @Modified By -
	 * @Modified Date -
	 ************************************************************************ 
	 */
	public static void LogIt(Exception e, String eMsg, StepBean step) {
		System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");

		if (step != null) {
			System.out.println(step.getStepId());
			System.out.println(step.getStepName());
		}

		if (eMsg != null) {
			System.out.println(eMsg);
		}
		if (e != null) {
			e.printStackTrace();
		}

		System.out.println("------------------------------------------------------------------------------------------------------------------------------------\n\n");
	}

	public static void init(String[] args) {
		try {
			ConfigurationFactory factory = new ConfigurationFactory("config/automation/config.xml");
			config = factory.getConfiguration();
			currentDateTime = CucumberUtils.getCurrentDateTime();
			currentDate = CucumberUtils.getCurrentDate();
			closeBrowser = config.getString("closeBrowser");//yes
			System.out.println("@@init@@closeBrowser::"+closeBrowser);
			maximizeBrowser=config.getString("maximizeBrowser");//yes
			System.out.println("@@init@@maximizeBrowser::"+maximizeBrowser);
			//11/15/13 - Run from VDI Yes/No
			runfromVDI=config.getString("runfromVDI");//No
			System.out.println("@@init@@runfromVDI::"+runfromVDI);

			driverPath = config.getString("driverPath");//Drivers\\
			System.out.println("@@init@@driverPath::"+driverPath);
			enableCucumberReport = config.getString("enableCucumberReport");//no
			System.out.println("@@init@@enableCucumberReport::"+enableCucumberReport);
			if (args != null && args.length >= 3) {

				// runMode functional
				// smoke staging ST2 @Smoke-Registration-1
				if ("functional".equalsIgnoreCase(args[1])) {
					runMode = "functional";
					System.out.println("@@init@@runMode:::"+runMode);
				} else if ("regression".equalsIgnoreCase(args[1])) {
					runMode = "regression";
					System.out.println("@@init@@runMode:::"+runMode);
				} else if ("smoke".equalsIgnoreCase(args[1])) {
					runMode = "smoke";
					System.out.println("@@init@@runMode:::"+runMode);
				} else if ("performance".equalsIgnoreCase(args[1])) {
					runMode = "performance";
				}

				if (runMode.equalsIgnoreCase("regression")) {
					isChunksEnabled = config.getString("isChunksEnabled");	
					System.out.println("@@init@@isChunksEnabled:::"+isChunksEnabled);
				} else if (runMode.equalsIgnoreCase("smoke")) {
					isChunksEnabled = config.getString("smoke.isChunksEnabled");
					System.out.println("@@init@@isChunksEnabled:::"+isChunksEnabled);
				} else if (runMode.equalsIgnoreCase("performance")) {
					isChunksEnabled = config.getString("isChunksEnabled");
				}

				// runEnvironment
				// smoke staging ST2 @Smoke-Registration-1
				if ("functional".equalsIgnoreCase(args[1])) {
					runEnvironment = config.getString("runEnvironment");//staging
					System.out.println("@@init@@runEnvironment:::"+runEnvironment);
				} else if ("regression".equalsIgnoreCase(args[1]) || "smoke".equalsIgnoreCase(args[1]) || "performance".equalsIgnoreCase(args[1])) {
					runEnvironment = args[2];
					System.out.println("@@init@@runEnvironment:::"+runEnvironment);
				}

				// runVDI
				if ("regression".equalsIgnoreCase(args[1]) || "smoke".equalsIgnoreCase(args[1]) || "performance".equalsIgnoreCase(args[1])) {
					runVDI = args[3];
					System.out.println("@@init@@runVDI:::"+runVDI);
				} else if ("functional".equalsIgnoreCase(args[1])) {
					runVDI = "functional";
					System.out.println("@@init@@runVDI:::"+runVDI);
				}

				// InputDataTables Path - RAM - 3/1 - Changed from config file
				// to local varibale for RUN ENVIRONMENT
				if ("functional".equalsIgnoreCase(args[1])) {
					automationInputDataFilePath = config.getString("automationInputPath") + File.separator + runEnvironment + File.separator + runMode + File.separator;
					//=InputDataTables/staging/functional/
					System.out.println("@@init@@automationInputDataFilePath:::"+automationInputDataFilePath);
				} else if ("regression".equalsIgnoreCase(args[1]) || "smoke".equalsIgnoreCase(args[1]) || "performance".equalsIgnoreCase(args[1])) {
					automationInputDataFilePath = config.getString("automationInputPath") + File.separator + runEnvironment + File.separator + runMode + File.separator + runVDI + File.separator;
					System.out.println("@@init@@automationInputDataFilePath:::"+automationInputDataFilePath);
				}//automationInputDataFilePath=InputDataTables/staging/smoke/ST2/
			}

			// Scenario Controller Path
			scenarioControllerPath = config.getString("scenarioControllerPath") + File.separator + runMode + File.separator; // RAM
			System.out.println("@@init@@scenarioControllerPath"+scenarioControllerPath);
			//ScenarioController/functional/
			customReportPath = config.getString("cucumberReportPath") + File.separator + runEnvironment + File.separator + runMode + File.separator + currentDateTime + File.separator + "FAILURES.txt";
			//report/cucumber/staging/functional/currentime/FAILURES.txt
			System.out.println("@@init@@customReportPath"+customReportPath);
			excelReportDirectory = config.getString("excelReportPath") + File.separator + runEnvironment + File.separator + runMode 
			//report/excel/staging/functional
			/*
			 * +
			 * File
			 * .
			 * separator
			 * +
			 * "Report-"
			 * +
			 * currentDate
			 * +
			 * ".xls"
			 */;
			System.out.println("@@init@@excelReportDirectory"+excelReportDirectory);
			screenshotDirectory = config.getString("screenshotReportPath") + File.separator + runEnvironment + File.separator + runMode;
			System.out.println("@@init@@screenshotDirectory"+screenshotDirectory);
			//report/screenshots/staging/functional
			registeredScenariosMap = new HashMap<String, ScenarioBean>();

			// RAM (1/14/2013) - Create the Run Report Folder + Over All Summary
			// -----------------------------------------------------------------
			ReportingLibrary.createCurrentRunReportPath();
			// -----------------------------------------------------------------

			// For regression from Automation Server
			/*
			 * if (config.getBoolean("isServerMode")) { String serverConfigPath
			 * = config.getString("serverConfigPath"); String
			 * serverConfigFileName = config.getString("serverConfigFileName");
			 * serverProp = new Properties(); serverProp.load(new
			 * FileInputStream(serverConfigPath + serverConfigFileName)); if
			 * (serverProp != null && serverProp.getProperty("jdbc.schema") !=
			 * null) { isServerMode = true; } }
			 * 
			 * // For regression testing reporting from Automation server if
			 * (isServerMode && config.getBoolean("isDBReporting")) { connection
			 * = QualityCenterDBManager.getRemoteDBConnection(); if (connection
			 * != null) { isDBReporting = true; } }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// End of Init()

}
