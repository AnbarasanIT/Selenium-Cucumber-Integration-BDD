package walgreens.ecom.batch.automation.stepdefinitions.marketing.vpdValidations;

import java.util.List;
import java.util.Map;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import walgreens.ecom.batch.automation.library.common.CommonLibrary;
import walgreens.ecom.batch.automation.library.marketing.vpdPageValidations.VpdPageValidationsLibrary;
import walgreens.ecom.batch.framework.common.beans.ScenarioBean;
import walgreens.ecom.batch.framework.common.beans.StepBean;
import cucumber.annotation.en.Then;
import cucumber.table.DataTable;

public class VpdValidationsSteps {
    @Then("^\"([^\"]*)\" Customer performs validations in VPD Page:$")
    public void Customer_performs_validations_in_VPD_Page(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData((arg2));
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer performs validations in VPD Page:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    VpdPageValidationsLibrary.vpdPageValidationsLibrary(browser, scenario, step, dataMap);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @Then("^\"([^\"]*)\" Customer validates Sort Drop down in Tier3 page:$")
    public void Customer_validates_Sort_Drop_down_in_Tier3_page(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData((arg2));
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer validates Sort Drop down in Tier3 page:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    VpdPageValidationsLibrary.validateTier3Dropdown(browser, scenario, step, dataMap);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @Then("^\"([^\"]*)\" Customer validates Price details of the Product:$")
    public void Customer_validates_Price_details_of_the_Product(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData((arg2));
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer validates Price details of the Product:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    VpdPageValidationsLibrary.validatePriceDisplay(browser, scenario, step, dataMap);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @Then("^\"([^\"]*)\" Customer validates available information from See when this item arrives overlay:$")
    public void Customer_validates_available_information_from_See_when_this_item_arrives_overlay(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData((arg2));
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer validates available information from See when this item arrives overlay");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    VpdPageValidationsLibrary.validateSeeWhenThisItemArrivesOverlay(browser, scenario, step, dataMap);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @Then("^\"([^\"]*)\" Customer validates UI and links in the Contact Lense VPD Page:$")
    public void Customer_validates_UI_and_links_in_the_Contact_Lense_VPD_Page(String arg1) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	try {
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer validates available information from See when this item arrives overlay");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    VpdPageValidationsLibrary.validateUIofCLVPDPage(browser, scenario, step);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }
}
