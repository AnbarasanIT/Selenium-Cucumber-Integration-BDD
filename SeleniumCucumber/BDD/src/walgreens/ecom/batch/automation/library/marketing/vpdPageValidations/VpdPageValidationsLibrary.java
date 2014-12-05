package walgreens.ecom.batch.automation.library.marketing.vpdPageValidations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import walgreens.ecom.batch.automation.library.common.CommonLibrary;
import walgreens.ecom.batch.framework.common.beans.ScenarioBean;
import walgreens.ecom.batch.framework.common.beans.StepBean;

public class VpdPageValidationsLibrary extends CommonLibrary {

    /**
     ************************************************************* 
     * @Purpose - Method to verify validations in vpd page
     * @author - Mohana Janakavalli K
     * @Created - 11-feb-2013
     * @Modified By -
     * @Modified Date -
     ************************************************************* 
     */

    public static void vpdPageValidationsLibrary(EventFiringWebDriver browser, ScenarioBean scenariobean, StepBean stepBean, Map<String, List<String>> dataMap) {
	String status = PASS;
	String ShoppingCartProd_ELM = "a[class='def_txt']~CSS";
	String Swatches_ELM = "//*[@id='swatches']~XPATH";
	String ColorName_ELM = "//*[@id='colorname']~XPATH";
	String ColorDropDown_LST = "select[id='colorId']~CSS";
	List<WebElement> webElements = null;
	Map<String, String> messagesMap = null;
	try {
	    if (messagesMap == null) {
		messagesMap = new HashMap<String, String>();
		stepBean.setStepStatusMessages(messagesMap);
	    }
	    int totalRowCount = Integer.parseInt(dataMap.get("totalRowCount").get(0));
	    for (int i = 0; i < totalRowCount; i++) {
		String ProdType = getTestData(dataMap.get("InputFileName").get(i), dataMap.get("SheetName").get(i), dataMap.get("RowId").get(i), "ProdType");
		if (ProdType.equalsIgnoreCase("ColorOptions")) {
		    if (!isElementPresentVerification(Swatches_ELM, browser)) {
			messagesMap.put("Color Swatches", "Swatches section available in left navigation");
			status = WARNING;
		    }
		    webElements = browser.findElements(By.xpath("//div[@id='swatches']/ul/li"));
		    int res = webElements.size();
		    for (int count = 0; count < webElements.size(); count++) {
			if (!isElementPresentVerifyClick("//div[@id='swatches']/ul/li[" + i + "]/a/img", browser)) {
			    messagesMap.put("Onlineprivary", "Online privary and Security policy");
			    status = WARNING;
			}
			String SwatchImgName = getElementByProperty("//div[@id='swatches']/ul/li[" + i + "]/a/img", browser).getAttribute("Title");
			// String SwatchImgName =
			// getElementByProperty(SwatchName_IMG,
			// browser).getAttribute("Title");
			String ColorName = getElementByProperty(ColorName_ELM, browser).getText();
			String ColorNameDropdown = getElementByProperty(ColorDropDown_LST, browser).getText();
			if ((!SwatchImgName.contains(ColorName)) || (!ColorNameDropdown.contains(ColorName))) {
			    messagesMap.put("Color Swatches", "Swatches section available in left navigation");
			    status = WARNING;
			}
		    }
		}

	    }
	} catch (Exception e) {
	    status = FAIL;
	    messagesMap.put("An Exception Occured:", e.getMessage());
	    LogIt(e, null, stepBean);
	} finally {
	    stepBean.setStepStatus(status);
	}
    }

    /**
     ************************************************************* 
     * @Purpose - Method to validate Sort Drop down in Tier3 page
     * @author - Fyrose Mohamed M.R.
     * @Created - 12-feb-2013
     * @Modified By -
     * @Modified Date -
     ************************************************************* 
     */

    public static void validateTier3Dropdown(EventFiringWebDriver browser, ScenarioBean scenariobean, StepBean stepBean, Map<String, List<String>> dataMap) {
	String status = PASS;
	// String SortDropdown_LST="//select[@id='sortSelectTop']~XPATH";
	List<WebElement> webElements = null;
	Map<String, String> messagesMap = null;
	try {
	    if (messagesMap == null) {
		messagesMap = new HashMap<String, String>();
		stepBean.setStepStatusMessages(messagesMap);
	    }
	    int totalRowCount = Integer.parseInt(dataMap.get("totalRowCount").get(0));
	    for (int i = 0; i < totalRowCount; i++) {
		String SortDropdown_LST = getTestData(dataMap.get("InputFileName").get(i), dataMap.get("SheetName").get(i), dataMap.get("RowId").get(i), "DropdownObject");
		String VisibleText = getTestData(dataMap.get("InputFileName").get(i), dataMap.get("SheetName").get(i), dataMap.get("RowId").get(i), "VisibleText");
		if (isElementPresentVerification(SortDropdown_LST, browser)) {
		    if (!selectByVisibleText(SortDropdown_LST, VisibleText, browser)) {
			messagesMap.put("Tier3 Sort Dropdown", "Not able to select " + VisibleText + "  Option from dropdown");
			status = WARNING;
		    }
		    List<WebElement> DefaultOption = getElementByProperty(SortDropdown_LST, browser).findElements(By.xpath("//option[@selected='']"));
		    String DropVal = DefaultOption.get(0).getText();
		    if (!DropVal.equalsIgnoreCase(VisibleText)) {
			messagesMap.put("Tier3 Sort Dropdown", "Visible Text and Passed value is not same. Failed to select given option from Sort Dropdown");
			status = WARNING;
		    }
		} else {
		    throw new Exception("Sort Drop not found in Tier3 Page");
		}
	    }

	} catch (Exception e) {
	    status = FAIL;
	    messagesMap.put("An Exception Occured:", e.getMessage());
	    LogIt(e, null, stepBean);
	} finally {
	    stepBean.setStepStatus(status);
	}
    }

    /**
     ************************************************************* 
     * @Purpose - Method to Validate Price display for different kinds of
     *          Products (Rule3SaleRule3, Rule2SaleRule3.....)
     * @author - Siva
     * @Created - 09-JULY-2013
     * @Modified By -Siva,Ygnash
     * @Modified Date -17-JULY-2013,18-JUL-2013
     ************************************************************* 
     */
    public static void validatePriceDisplay(EventFiringWebDriver browser, ScenarioBean scenariobean, StepBean stepBean, Map<String, List<String>> dataMap) {
	String status = PASS;
	String PriceAmount_ELM = "//*[@id='price_amount' or @class='Rprice']~XPATH";
	String SaleAmount_ELM = "//*[@id='sale_amount' or @class='LSprice']~XPATH";
	String OverlayPriceAmount_ELM = "//div[@class='prod-pricing']/p~XPATH";
	String OverlaySaleAmount_ELM = "//p[contains(@class,'LSprice')]~XPATH";
	String LoyaltyLogo_IMG = "img[alt='earn points']~CSS";
	String QualifyingItemsHeader_ELM = "//h2[contains(text(),'Qualifying Items')]~XPATH";
	String Close_BTN = "//a[contains(text(),'Close')]~XPATH";
	String PricingSectionDiv_ELM = "//div[@class='prod-pricing']~XPATH";
	Map<String, String> messagesMap = null;
	String RulePrice = null, SalePrice = null;
	String BOGOLEP50Message_ELM="//p[contains(text(),'Buy 1, Get 1 50% OFF ')]~XPATH";
	String BOGOLEPFREEMessage_ELM="//div[4]/p[contains(text(),'Buy 1, Get 1 FREE')]~XPATH";
	String BAGAFREEMessage_ELM="//p[contains(text(),'Buy 1, Get Same Item FREE')]~XPATH";
	String BAGB_BDiscounted_ELM="//p[contains(text(),'Discount with qualifying purchase')]~XPATH";
	String SelectQualifyingItem_LNK="//a[contains(text(),'Select qualifying item')]~XPATH";

	try {
	    if (messagesMap == null) {
		messagesMap = new HashMap<String, String>();
		stepBean.setStepStatusMessages(messagesMap);
	    }
	    int totalRowCount = Integer.parseInt(dataMap.get("totalRowCount").get(0));
	    for (int i = 0; i < totalRowCount; i++) {
		String isValidationinOverlay = getTestData(dataMap.get("InputFileName").get(i), dataMap.get("SheetName").get(i), dataMap.get("RowId").get(i), "isValidationinOverlay");
		String PriceRule = getTestData(dataMap.get("InputFileName").get(i), dataMap.get("SheetName").get(i), dataMap.get("RowId").get(i), "PriceRule");
		String LoyatlyPoints = getTestData(dataMap.get("InputFileName").get(i), dataMap.get("SheetName").get(i), dataMap.get("RowId").get(i), "LoyatlyPoints");
		String closeOverlay = getTestData(dataMap.get("InputFileName").get(i), dataMap.get("SheetName").get(i), dataMap.get("RowId").get(i), "closeOverlay");
		String BOGOMessageType=getTestData(dataMap.get("InputFileName").get(i), dataMap.get("SheetName").get(i), dataMap.get("RowId").get(i), "BOGOMessageType");
		if (isValidationinOverlay != null && isValidationinOverlay.equalsIgnoreCase("Yes")) {
		    switchToOverlay(browser);
		}
		if (PriceRule != null && !PriceRule.equals("-")) {
		    String[] arrRuleSplit = PriceRule.split(",");
		    if (isElementPresentVerification(QualifyingItemsHeader_ELM, browser)) {
			List<WebElement> ptagsinPricingSection = getElementByProperty(PricingSectionDiv_ELM, browser).findElements(By.tagName("p"));
			RulePrice = ptagsinPricingSection.get(0).getText().trim();
			SalePrice = ptagsinPricingSection.get(1).getText().trim();
		    } else if (isValidationinOverlay != null && isValidationinOverlay.equalsIgnoreCase("Yes")) {
			RulePrice = getElementByProperty(OverlayPriceAmount_ELM, browser).getText().trim();
			SalePrice = getElementByProperty(OverlaySaleAmount_ELM, browser).getText().trim();
		    } else {
			RulePrice = getElementByProperty(PriceAmount_ELM, browser).getText().trim();
			SalePrice = getElementByProperty(SaleAmount_ELM, browser).getText().trim();
		    }
		    if (arrRuleSplit[0].equalsIgnoreCase("R1")) {
			if (!(RulePrice.contains("$") && !RulePrice.contains("/"))) {
			    messagesMap.put("Rule-1 Price", "Price is not displayed as per the Rule");
			    status = WARNING;
			}
		    } else if (arrRuleSplit[0].equalsIgnoreCase("R2")) {
			String[] arrRulePrice = RulePrice.split("/");
			if (!(arrRulePrice[0].trim().equals("2") && arrRulePrice[1].contains("$"))) {
			    messagesMap.put("Rule-2 Price", "Price is not displayed as per the Rule");
			    status = WARNING;
			}
		    } else if (arrRuleSplit[0].equalsIgnoreCase("R3")) {
			String[] arrRulePrice = RulePrice.split("or");
			String[] arrFirstPartRulePrice = arrRulePrice[0].split("/");
			String[] arrsecondPartRulePrice = arrRulePrice[1].split("/");
			if (!(arrFirstPartRulePrice[0].trim().equals("3") && arrFirstPartRulePrice[1].contains("$") && arrsecondPartRulePrice[0].trim().equals("1") && arrsecondPartRulePrice[1]
				.contains("$"))) {
			    messagesMap.put("Rule-3 Price", "Price is not displayed as per the Rule");
			    status = WARNING;
			}
		    }
		    if (arrRuleSplit[1].equalsIgnoreCase("SR1")) {
			if (!(SalePrice.contains("$") && !SalePrice.contains("/"))) {
			    messagesMap.put("Sale Rule-1 Price", "Price is not displayed as per the Rule");
			    status = WARNING;
			}
		    } else if (arrRuleSplit[1].equalsIgnoreCase("SR2")) {
			String[] arrSalePrice = SalePrice.split("/");
			if (!(arrSalePrice[0].trim().equals("2") && arrSalePrice[1].contains("$"))) {
			    messagesMap.put("Sale Rule-2 Price", "Price is not displayed as per the Rule");
			    status = WARNING;
			}
		    } else if (arrRuleSplit[1].equalsIgnoreCase("SR3")) {
			String[] arrSalePrice = SalePrice.split("or");
			String[] arrFirstPartSalePrice = arrSalePrice[0].split("/");
			String[] arrsecondPartSalePrice = arrSalePrice[1].split("/");
			if (!(arrFirstPartSalePrice[0].trim().equals("3") && arrFirstPartSalePrice[1].contains("$") && arrsecondPartSalePrice[0].trim().equals("1") && arrsecondPartSalePrice[1]
				.contains("$"))) {
			    messagesMap.put("Sale Rule-3 Price", "Price is not displayed as per the Rule");
			    status = WARNING;
			}
		    }
		    }
		if (LoyatlyPoints != null && LoyatlyPoints.equalsIgnoreCase("Yes")) {
		    if (!isElementPresentVerification(LoyaltyLogo_IMG, browser)) {
			messagesMap.put("Loyalty Logo", "Object is Missing");
			status = WARNING;
		    }
		}

		if(BOGOMessageType!=null && BOGOMessageType.equalsIgnoreCase("BOGOLEP50"))
		{
		    if(!isElementPresentVerification(BOGOLEP50Message_ELM, browser)&&(!isElementPresentVerification(SelectQualifyingItem_LNK, browser)))
		    {
			messagesMap.put("BOGOLEP 50 Message","Promotional message not displayed");
			status=WARNING;
		    }
		}
		else if(BOGOMessageType!=null && BOGOMessageType.equalsIgnoreCase("BOGOLEPFREE"))
		{
		    if(!isElementPresentVerification(BOGOLEPFREEMessage_ELM, browser)&&(!isElementPresentVerification(SelectQualifyingItem_LNK, browser)))
		    {
			messagesMap.put("BOGOLEP FREE Message","Promotional message not displayed");
			status=WARNING; 
		    }
		}
		else if(BOGOMessageType!=null && BOGOMessageType.equalsIgnoreCase("BAGA_FREE"))
		{
		    if(!isElementPresentVerification(BAGAFREEMessage_ELM, browser)&&(!isElementPresentVerification(SelectQualifyingItem_LNK, browser)))
		    {
			messagesMap.put("BAGA_FREE Message", "Promotional message not displayed");
			status=WARNING;
		    }
		}
		else if(BOGOMessageType!=null && BOGOMessageType.equalsIgnoreCase("BAGB_BDISCOUNTED"))
		{
		    if(!isElementPresentVerification(BAGB_BDiscounted_ELM, browser)&&(!isElementPresentVerification(SelectQualifyingItem_LNK, browser)))
		    {
			messagesMap.put("BAGB_BDISCOUNTED Message", "Promotional message not displayed");
			status=WARNING;
		    }
		}

		if (closeOverlay != null && closeOverlay.equalsIgnoreCase("Yes")) {
		    switchToDefault(browser);
		    if (!isElementPresentVerifyClick(Close_BTN, browser)) {
			throw new Exception("Not able to Click on close");
		    }
		}
	    } 

	} catch (Exception e) {
	    status = FAIL;
	    messagesMap.put("An Exception Occured:", e.getMessage());
	    LogIt(e, null, stepBean);
	} finally {
	    stepBean.setStepStatus(status);
	}
    }

    /**
     ************************************************************* 
     * @Purpose - Method to validate available information from See When this
     *          item arrives Overaly
     * @author - Siva Santhi Reddy
     * @Created - 10-JULY-2013
     * @Modified By -
     * @Modified Date -
     ************************************************************* 
     */

    public static void validateSeeWhenThisItemArrivesOverlay(EventFiringWebDriver browser, ScenarioBean scenariobean, StepBean stepBean, Map<String, List<String>> dataMap) {
	String status = PASS;
	String ProductImage_IMG = "img[id='main-product-image']~CSS";
	String SeeWhenThisItemArrives_LNK = "//a[contains(text(),'See when this item arrives')]~XPATH";
	String EstiamteYourDeliveryDate_ELM = "//h1[contains(text(),'Estimate your delivery date:')]~XPATH";
	String ProductName_ELM = "//iframe[contains(@title,'See when')]";//div[@id='content']//div[@id='paddedFrame']/p/strong~XPATH";
	String DestinationState_LST = "select[id='destState']~CSS";
	String ArrivalTime_BTN = "input[name='ArrivalTime']~CSS";
	String NoteMessage_ELM = "//p[contains(text(),'Estimate includes processing time plus standard ground shipping to your state.')]~XPATH";
	String NoteLabel_ELM = "//strong[contains(text(),'Note:')]~XPATH";
	String ShippingInfo_LNK = "//iframe[contains(@title,'See when')]//div[@id='content']//p[3]/a[contains(@href,'shipping_help_main.jsp')]~XPATH";
	String QuestionsText_ELM = "//strong[contains(text(),'Questions?')]~XPATH";
	String StandGroupShippingHeader_ELM = "//h1[contains(text(),'Standard ground shipping:')]~XPATH";
	String ArrivesInBusinessDaysMessage_ELM = "//p[contains(text(),'business days.')]~XPATH";
	String Close_BTN = "//a[contains(text(),'Close')]~XPATH";
	Map<String, String> messagesMap = null;
	try {
	    if (messagesMap == null) {
		messagesMap = new HashMap<String, String>();
		stepBean.setStepStatusMessages(messagesMap);
	    }

	    String ProductType = dataMap.get("ProdutType").get(0);
	    String ProductNameinVPDPage = getElementByProperty(ProductImage_IMG, browser).getAttribute("title");
	    // Since the Product name in the overlay is not displaying
	    // completely or in the same order as in VPD page, so taking portion
	    // of the Product Name.
	    ProductNameinVPDPage = ProductNameinVPDPage.split(" ")[1] + ProductNameinVPDPage.split(" ")[2];

	    if (!isElementPresentVerifyClick(SeeWhenThisItemArrives_LNK, browser)) {
		throw new Exception("Not able to Click on See When this Item Arrives Link");
	    }
	    switchToOverlay(browser);
	    // Verifying shipping info link to make sure overlay is loading.
	    if (!isElementPresentVerification(ShippingInfo_LNK, browser)) {
		messagesMap.put("Shipping Info link", "Link is missing");
		status = WARNING;
	    }
	    String ProductNameinOverlay = getElementByProperty(ProductName_ELM, browser).getText();
	    // Since the Product name in the overlay is not displaying
	    // completely or in the same order as in VPD page, so taking portion
	    // of the Product Name.
	    ProductNameinOverlay = ProductNameinOverlay.split(" ")[1] + ProductNameinOverlay.split(" ")[2];
	    if (!ProductNameinOverlay.equalsIgnoreCase(ProductNameinVPDPage)) {
		messagesMap.put("Product Name in Overlay", "Name is displaying wrongly");
		status = WARNING;
	    }
	    if (ProductType.equalsIgnoreCase("DC")) {
		String[] ElementsToVerify = { StandGroupShippingHeader_ELM, ArrivesInBusinessDaysMessage_ELM, QuestionsText_ELM, ShippingInfo_LNK };
		for (int i = 0; i < ElementsToVerify.length; i++) {
		    if (!isElementPresentVerification(ElementsToVerify[i], browser)) {
			messagesMap.put(ElementsToVerify[i], "Object is missing");
			status = WARNING;
		    }
		}
	    } else if (ProductType.equalsIgnoreCase("CH")) {
		String[] ElementsToVerify = { EstiamteYourDeliveryDate_ELM, DestinationState_LST, ArrivalTime_BTN, NoteMessage_ELM, NoteLabel_ELM, ShippingInfo_LNK, QuestionsText_ELM };
		for (int i = 0; i < ElementsToVerify.length; i++) {
		    if (!isElementPresentVerification(ElementsToVerify[i], browser)) {
			messagesMap.put(ElementsToVerify[i], "Object is missing");
			status = WARNING;
		    }
		}
	    }
	    switchToDefault(browser);
	    if (!isElementPresentVerifyClick(Close_BTN, browser)) {
		throw new Exception("Not able to Click on Close Button");
	    }
	    if (!isElementPresentVerification(ProductImage_IMG, browser)) {
		messagesMap.put("Product Image missing", "VPD page is not displaying after closing See when this item arrives overlay");
		status = WARNING;
	    }

	} catch (Exception e) {
	    status = FAIL;
	    messagesMap.put("An Exception Occured:", e.getMessage());
	    LogIt(e, null, stepBean);
	} finally {
	    stepBean.setStepStatus(status);
	}
    }

    /**
     ************************************************************* 
     * @Purpose - Method to Validate UI of Contact Lens VPD Page
     * @author - Siva Santhi Reddy
     * @Created - 18-JULY-2013
     * @Modified By -
     * @Modified Date -
     ************************************************************* 
     */

    public static void validateUIofCLVPDPage(EventFiringWebDriver browser, ScenarioBean scenariobean, StepBean stepBean) {
	String status = PASS;
	String Select_BTN = "img[alt='Select']~CSS";
	String SeeRebateSavings_LNK = "//a[contains(text(),'Rebate: Save up to')]~XPATH";
	String FSALogo_IMG = "img[alt='What is FSA?']~CSS";
	String RatingImage_IMG = "//img[contains(@src,'rating.gif')]~XPATH";
	String ReadAllReviews_LNK = "//a[@title='Read all reviews']~XPATH";
	String WriteaReview_LNK = "//div[@id='BVRRRatingSummaryLinkWriteID']~XPATH";
	String TwitterImage_IMG = "img[title='Tweet']~CSS";
	String FacebookLikeImage_IMG = "//div[@class='pluginConnectButton']/div[@title='Like']~XPATH";
	String FacebookSend_BTN = "//span[contains(text(),'Send')]~XPATH";
	String OverViewHeader_ELM = "//b[contains(text(),'Overview:')]~XPATH";
	String ProductsDetailsDiv_ELM = "//div[@id='product-detail-header']~XPATH";
	Map<String, String> messagesMap = null;
	try {
	    if (messagesMap == null) {
		messagesMap = new HashMap<String, String>();
		stepBean.setStepStatusMessages(messagesMap);
	    }
	    if (!isElementPresentVerifyClick(Select_BTN, browser)) {
		throw new Exception("Not able to click on Select Button");
	    }
	    String[] ElementstoVerify = { SeeRebateSavings_LNK, FSALogo_IMG, RatingImage_IMG, ReadAllReviews_LNK, WriteaReview_LNK, TwitterImage_IMG, FacebookLikeImage_IMG, FacebookSend_BTN,
		    OverViewHeader_ELM };
	    for (int i = 0; i < ElementstoVerify.length; i++) {
		if (!isElementPresentVerification(ElementstoVerify[i], browser)) {
		    messagesMap.put(ElementstoVerify[i], "Obect is Missing");
		    status = WARNING;
		}
	    }
	    String ProductsDetailsText = getElementByProperty(ProductsDetailsDiv_ELM, browser).getText().replaceAll("/n", " ");
	    if (!ProductsDetailsText.contains("Ships for Free Details")) {
		messagesMap.put("Ships for Free Details- Message", "Message is Missing");
		status = WARNING;
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
