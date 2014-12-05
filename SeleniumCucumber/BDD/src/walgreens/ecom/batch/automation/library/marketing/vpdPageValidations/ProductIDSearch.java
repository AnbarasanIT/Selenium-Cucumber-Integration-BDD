package walgreens.ecom.batch.automation.library.marketing.vpdPageValidations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.support.events.EventFiringWebDriver;

import walgreens.ecom.batch.automation.library.common.CommonLibrary;
import walgreens.ecom.batch.framework.common.beans.ScenarioBean;
import walgreens.ecom.batch.framework.common.beans.StepBean;

public class ProductIDSearch extends CommonLibrary {

	public static void productIDSearch(EventFiringWebDriver browser, ScenarioBean scenariobean, StepBean stepBean, Map<String, List<String>> dataMap) {
		String status = PASS;
		//String search_Excel = getTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "Object");
		String search_TXT = "//input[@id='query']~XPATH";
		String search_BTN = "//input[@value='Search']~XPATH";
		String shop_BTN= "//a[text()='Photo']~XPATH";
		Map<String, String> messagesMap = null;
		try {
			if (messagesMap == null) {
				messagesMap = new HashMap<String, String>();
				stepBean.setStepStatusMessages(messagesMap);
				//String prod_ID = getTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "Keyword");
				String prod_ID = "prod389133";
				
				if(!isElementPresentVerifyClick(shop_BTN, browser)){
					messagesMap.put("Shop Photo Button","Not able to click Shop Photo Button");
					status = WARNING;
				}
//				if(!clearAndEnterText(search_TXT, prod_ID, browser)){
//					throw new Exception("not able to enter product in search text box");
//				}
//				if(!isElementPresentVerifyClick(search_BTN, browser)){
//					messagesMap.put("Product Search Button","Not able to click Search Button");
//					status = WARNING;
//				}
			}



		} catch (Exception e) {
			status = FAIL;
			messagesMap.put("An Exception Occured:", e.getMessage());
			LogIt(e, null, stepBean);
		} finally {
			stepBean.setStepStatus(status);
		}
	}
}


