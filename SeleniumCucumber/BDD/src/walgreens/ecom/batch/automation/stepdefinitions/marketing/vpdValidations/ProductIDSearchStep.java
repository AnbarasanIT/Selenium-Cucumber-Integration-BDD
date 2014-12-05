package walgreens.ecom.batch.automation.stepdefinitions.marketing.vpdValidations;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.support.events.EventFiringWebDriver;

import walgreens.ecom.batch.automation.library.common.CommonLibrary;
import walgreens.ecom.batch.automation.library.marketing.vpdPageValidations.ProductIDSearch;
import walgreens.ecom.batch.framework.common.beans.ScenarioBean;
import walgreens.ecom.batch.framework.common.beans.StepBean;
import cucumber.annotation.en.Then;
import cucumber.table.DataTable;

public class ProductIDSearchStep {

	@Then("^\"([^\"]*)\" Customer Search for a product using product ID:$")
    public void Customer_Search_for_a_product_using_product_ID(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData((arg2));
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer Search for a product using product ID:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    ProductIDSearch.productIDSearch(browser, scenario, step, dataMap);
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

}
