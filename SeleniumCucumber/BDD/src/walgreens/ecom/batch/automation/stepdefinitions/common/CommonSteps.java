package walgreens.ecom.batch.automation.stepdefinitions.common;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import walgreens.ecom.batch.automation.library.common.CommonLibrary;
import walgreens.ecom.batch.automation.library.common.ReportingLibrary;
import walgreens.ecom.batch.framework.EcommTestRunner;
import walgreens.ecom.batch.framework.common.BrowserFactory;
import walgreens.ecom.batch.framework.common.beans.ScenarioBean;
import walgreens.ecom.batch.framework.common.beans.ScenarioControllerRowData;
import walgreens.ecom.batch.framework.common.beans.StepBean;
import walgreens.ecom.batch.framework.common.dao.ScenarioControllerParser;
import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.table.DataTable;

public class CommonSteps {

    @Given("^\"([^\"]*)\" Customer opens the browser$")
    public void OpenBrowserAndClearCache(String arg1) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	try {
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer opens the browser");
	    scenario.getStepBeans().add(step);
	    scenario.setScenarioBrowserName(EcommTestRunner.currentBrowserName);
	    browser = CommonLibrary.getNewBrowser(Thread.currentThread().getId());
	    browser.getWrappedDriver().manage().deleteAllCookies();
	    step.setStepStatus("pass");
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    scenario.setScenarioInitialize(true);
	    scenario.setScenarioCurrentUrl(EcommTestRunner.config.getString("walgreensURL"));
	    CommonLibrary.ReportIt(browser, scenario, step);
	    scenario.setScenarioInitialize(false);
	    // assertEquals("pass", step.getStepStatus());
	}
    }

    @Given("^\"([^\"]*)\" Customer starts the scenario:$")
    public void Customer_starts_the_scenario(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;

	try {
		System.out.println("walgreens.ecom.batch.automation.stepdefinitions.common CommonSteps     Customer");
		System.out.println("arg2:::"+arg2);
	    dataMap = CommonLibrary.getHorizontalData(arg2);
	    System.out.println("dataMap:::"+dataMap);
	    System.out.println("arg1:::"+arg1);
	    //doubt
	    scenario = CommonLibrary.getScenario(arg1);
	    System.out.println("scenario:::"+scenario.getScenarioBrowserName());
	    step = new StepBean();
	    step.setStepName("Customer starts the scenario");
	    scenario.getStepBeans().add(step);
	    step.setFirstStep(true);
	    scenario.setScenarioCreateDttm(new Date());
	    scenario.setScenarioBrowserName(EcommTestRunner.currentBrowserName);
	    System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------");
	    System.out.println("START SCENARIO - " + dataMap.get("RowId").get(0));
	    System.out.println("********************************** \n");
	    String inputFileName = EcommTestRunner.scenarioControllerPath + dataMap.get("InputFileName").get(0) + ".xls";
	    System.out.println("inputFileName:::::::"+inputFileName);
		String dataSheetName = dataMap.get("SheetName").get(0);
		System.out.println("dataSheetName:::::::"+dataSheetName);
		String rowID = dataMap.get("RowId").get(0);
		System.out.println("rowIDL:::"+rowID);
		//String isCucumberReport = CommonLibrary.getQCFileData(inputFileName, dataSheetName, rowID, "CucumberReportEnabled");
		ScenarioControllerRowData scenarioControllerRowData= ScenarioControllerParser.readScenarioValue(inputFileName, dataSheetName, rowID);
		String isCucumberReport = scenarioControllerRowData.getCucumberReportEnabled();
		System.out.println("isCucumberReportisCucumberReport::"+isCucumberReport);
	    if ("yes".equalsIgnoreCase(isCucumberReport)) {
		scenario.setCucumberReportEnabled(true);
	    }
	    String isExcelReport = scenarioControllerRowData.getExcelReportEnabled();
	    //String isExcelReport = CommonLibrary.getQCFileData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "ExcelReportEnabled");
	    if ("yes".equalsIgnoreCase(isExcelReport)) {
		scenario.setExcelReportEnabled(true);
	    }

	    if (EcommTestRunner.config.getString("ALMUpdate").equalsIgnoreCase("yes")) {
	  	    String isALMReport = scenarioControllerRowData.getaLMReportEnabled();
		//String isALMReport = CommonLibrary.getQCFileData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "ALMReportEnabled");
		if ("yes".equalsIgnoreCase(isALMReport)) {
		    scenario.setALMReportEnabled(true);
		}
	    }

	    // Get & Set the Module & Feature File Name
	    String strModuleName = dataMap.get("InputFileName").get(0).split("\\-")[1].trim();
	    strModuleName = strModuleName.toUpperCase();
	    scenario.setScenarioModuleName(strModuleName);

	   // String strFeatureName = CommonLibrary.getQCFileData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "ScenarioFeatureName");
	    String strFeatureName = scenarioControllerRowData.getScenarioFeatureName();
	    strFeatureName = strFeatureName.split("\\.")[0].trim();
	    scenario.setScenarioFeatureName(strFeatureName);

	   // String isEnableExecution = CommonLibrary.getQCFileData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "EnableExecution");
	    String isEnableExecution = scenarioControllerRowData.getEnableExecution();
	    if ("yes".equalsIgnoreCase(isEnableExecution)) {

		// Ram - 12/27/12 - Running the same scenario for
		// Production/WWW3 as well

		// RAM (1/14/13) For reporting purpose. Do this only if the
		// enable execution is marked as YES
		// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------

		// Set the Scenario Report Path
		String strScenarioModuleReporPath = ReportingLibrary.createFolder(EcommTestRunner.runReportPath, strModuleName);
		String strScenarioFullReportPath = ReportingLibrary.createFolder(strScenarioModuleReporPath, strFeatureName);
		scenario.setScenarioReportPath(strScenarioFullReportPath);

		// Set the Scenario Report File Name with full path
		String strScenarioReportFile = null;
		if (EcommTestRunner.runMode.equalsIgnoreCase("REGRESSION") || EcommTestRunner.runMode.equalsIgnoreCase("SMOKE")) {
		    strScenarioReportFile = arg1 + "_" + EcommTestRunner.runVDI.toUpperCase() + "_" + scenario.getScenarioBrowserName().toUpperCase();
		} else if (EcommTestRunner.runMode.equalsIgnoreCase("FUNCTIONAL")) {
		    strScenarioReportFile = arg1 + "_" + scenario.getScenarioBrowserName().toUpperCase();
		}

		ReportingLibrary.createExcelWorkBook(strScenarioFullReportPath, strScenarioReportFile, "SCENARIO_SUMMARY");
		scenario.setScenarioReportFile(strScenarioFullReportPath + File.separator + strScenarioReportFile);

		// Create the Scenario Report File
		ReportingLibrary.createScenarioSummaryExcelHeader(scenario.getScenarioReportFile() + ".xls");

		// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------

		if ("STAGING".equalsIgnoreCase(EcommTestRunner.runEnvironment)) { // STAGING

		    //String isOpenBrowser = CommonLibrary.getQCFileData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "OpenBrowser");
			String isOpenBrowser = scenarioControllerRowData.getOpenBrowser();
			if ("yes".equalsIgnoreCase(isOpenBrowser)) {
			browser = CommonLibrary.getNewBrowser(Thread.currentThread().getId());
		    }

		   // String isClearCache = CommonLibrary.getQCFileData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "ClearBrowserCache");
			String isClearCache = scenarioControllerRowData.getClearBrowserCache();
			if ("yes".equalsIgnoreCase(isClearCache)) {
			browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
			if (browser != null) {
			    browser.getWrappedDriver().manage().deleteAllCookies();
			}
		    }

		    step.setStepStatus("pass");

		} else if ("PRODUCTION".equalsIgnoreCase(EcommTestRunner.runEnvironment)) { // PRODUCTION

		    String isRunforProduction = CommonLibrary.getQCFileData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "Run_for_Production");

		    if ("yes".equalsIgnoreCase(isRunforProduction)) {

			String isOpenBrowser = CommonLibrary.getQCFileData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "OpenBrowser");
			if ("yes".equalsIgnoreCase(isOpenBrowser)) {
			    browser = CommonLibrary.getNewBrowser(Thread.currentThread().getId());
			}

			String isClearCache = CommonLibrary.getQCFileData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "ClearBrowserCache");
			if ("yes".equalsIgnoreCase(isClearCache)) {
			    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
			    if (browser != null) {
				browser.getWrappedDriver().manage().deleteAllCookies();
			    }
			}
			step.setStepStatus("pass");

		    } else {

			// Stop excecuting the currente case and proceed with
			// the nexte scenario with EXECUTION=YES &
			// PRODUCTION=YES
			scenario.setEnableExecution(false);
			step.setStepStatus("disabled");
			scenario.setScenarioStatus("disabled");
		    }
		}

	    } else {
		scenario.setEnableExecution(false);
		step.setStepStatus("disabled");
		scenario.setScenarioStatus("disabled");
	    }

	} catch (Exception e) {

	    CommonLibrary.LogIt(e, null, step);
	} finally {

	    scenario.setScenarioInitialize(true);
	    scenario.setScenarioCurrentUrl(EcommTestRunner.config.getString("walgreensURL"));
	    CommonLibrary.ReportIt(browser, scenario, step);
	    if ("disabled".equalsIgnoreCase(scenario.getScenarioStatus())) {
		assertEquals("fail", step.getStepStatus());
	    }
	}
    }

    @Then("^\"([^\"]*)\" Customer finished testing the scenario:$")
    public void Customer_finished_testing_the_scenario(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;

	try {
		
	    dataMap = CommonLibrary.getHorizontalData(arg2);
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer finished testing the scenario");
	    step.setFinalStep(true);
	    step.setStepStatus("pass");
	    scenario.getStepBeans().add(step);

	    scenario.setScenarioEndDttm(new Date());
	    
	    String inputFileName = EcommTestRunner.scenarioControllerPath + dataMap.get("InputFileName").get(0)+".xls";
	    String dataSheetName = dataMap.get("SheetName").get(0);
	    String rowID = dataMap.get("RowId").get(0);
	    ScenarioControllerRowData scenarioControllerRowData= ScenarioControllerParser.readScenarioValue(inputFileName, dataSheetName, rowID);
		String isCucumberReport = scenarioControllerRowData.getCucumberReportEnabled();
	   // String isCucumberReport = CommonLibrary.getQCFileData(, dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "CucumberReportEnabled");
	    if ("yes".equalsIgnoreCase(isCucumberReport)) {
		scenario.setCucumberReportEnabled(true);
	    }
	    System.out.println("\n" + "END SCENARIO - " + dataMap.get("RowId").get(0));
	    System.out.println("********************************** \n");

	    //String isExcelReport = CommonLibrary.getQCFileData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "ExcelReportEnabled");
	    String isExcelReport = scenarioControllerRowData.getExcelReportEnabled();
	    if ("yes".equalsIgnoreCase(isExcelReport)) {
		scenario.setExcelReportEnabled(true);
		scenario.setScenarioStatus(ReportingLibrary.COMPUTE_SCENARIO_STATUS);
	    }

	    //String isALMReport = CommonLibrary.getQCFileData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "ALMReportEnabled");
	    String isALMReport = scenarioControllerRowData.getaLMReportEnabled();
	    if ("yes".equalsIgnoreCase(isALMReport)) {
		scenario.setALMReportEnabled(true);
	    }

	} catch (Exception e) {
	    System.out.println("EXCEPTION in END");

	    CommonLibrary.LogIt(e, null, step);
	} finally {

	    ReportingLibrary.ReportIt(browser, scenario, step);
	    // assertEquals("pass", "pass");
	}
    }

    @Then("^\"([^\"]*)\" Customer closes the browser$")
    public void Customer_closes_the_browser(String arg1) {
	EventFiringWebDriver browser = null;
	try {
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    BrowserFactory.unLoadBrowser(Thread.currentThread().getId(), EcommTestRunner.currentBrowserName);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    assertEquals(CommonLibrary.PASS, CommonLibrary.PASS);
	}
    }

    @Given("^\"([^\"]*)\" Customer searches for a product using keywords in the search textbox:$")
    public void Customer_searches_for_a_product_using_keywords_in_the_search_textbox(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData((arg2));
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer searches for a product using keywords in the search textbox");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    CommonLibrary.searchProduct(browser, step, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @And("^\"([^\"]*)\" Customer close the Currently Opened Overlay to go back to the Main Page:$")
    public void Customer_close_the_Currently_Opened_Overlay_to_go_back_to_the_Main_Overlay(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData((arg2));
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer close the Currently Opened Overlay to go back to the Main Page:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    CommonLibrary.closeOverlay(browser, step, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @And("^\"([^\"]*)\" Customer navigates to the Walgreens Page:$")
    public void Customer_navigates_to_the_Walgreens_Page(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData((arg2));
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer navigates to the Walgreens Page:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    CommonLibrary.navigatintoWalgreensApp(browser, step, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @Then("^\"([^\"]*)\" Customer clicks on an object and validates the expected results:$")
    public void Customer_clicks_on_an_object_and_validates_the_expected_results(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData((arg2));
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer clicks on an object and validates the expected results:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    CommonLibrary.clickObjectAndVerify(browser, step, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @Then("^\"([^\"]*)\" Customer clicks on an object and validates the expected page URL:$")
    public void Customer_clicks_on_an_object_and_validates_the_expected_page_URL(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData((arg2));
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer clicks on an object and validates the expected page URL:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    CommonLibrary.verifyLinkNavigationPageURL(browser, step, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @Then("^\"([^\"]*)\" Customer validates Default Selected Option and links availability in the Page:$")
    public void Customer_validates_Default_Selected_Option_and_links_availability_in_the_Page(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData(arg2);
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer validates Default Selected Option and links availability in the Page:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    CommonLibrary.validateDefaultSelectedOptionAndLinksAvailability(browser, step, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @Then("^\"([^\"]*)\" Customer updates the required Order XML file:$")
    public void Customer_updates_the_required_Order_XML_file(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData(arg2);
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer updates the required Order XML file:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    CommonLibrary.verifyOrModifyAttributeinXML(browser, scenario,step, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }
    
    @Then("^\"([^\"]*)\" Customer finds the CL order XML file:$")
    public void Customer_finds_the_CL_order_XML_file(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData(arg2);
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer finds the CL order XML file:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    CommonLibrary.findCLOrderXML(browser, scenario,step, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }
    
    @Then("^\"([^\"]*)\" Customer creates the JRTR input file:$")
    public void Customer_creates_the_JRTR_input_file(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData(arg2);
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer creates the JRTR input file:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    CommonLibrary.createJRTRInputFile(browser, scenario,step, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

}
