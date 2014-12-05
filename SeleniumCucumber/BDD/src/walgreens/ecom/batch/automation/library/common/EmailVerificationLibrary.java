package walgreens.ecom.batch.automation.library.common;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import walgreens.ecom.batch.framework.common.beans.ScenarioBean;
import walgreens.ecom.batch.framework.common.beans.StepBean;
import walgreens.ecom.batch.framework.common.dao.TestDataDBManager;

public class EmailVerificationLibrary extends CommonLibrary {

	/**
	 ************************************************************* 
	 * @Purpose - Method to Login into Email Account (Yahoo or Hotmail)
	 * @author - Siva Santhi Reddy
	 * @Created - 17-Aug-2012
	 * @Modified By - Siva
	 * @Modified Date -15-Nov-12
	 ************************************************************* 
	 */

	public static void loginToEmailClient(EventFiringWebDriver browser, StepBean stepBean, Map<String, List<String>> dataMap) {
		String status = PASS;
		String UserName_TXT = "//input[@id='username' or @name='login']~XPATH";
		String Password_TXT = "//input[@id='passwd' or @name='passwd']~XPATH";
		String SignIn_BTN = "//*[@id='idSIButton9' or @type='submit']~XPATH";
		String Signout_LNK = "//a[contains(text(),'Sign Out') or @id='c_signout']~XPATH";

		Map<String, String> messagesMap = null;
		try {
			if (messagesMap == null) {
				messagesMap = new HashMap<String, String>();
				stepBean.setStepStatusMessages(messagesMap);
			}
			String Username = getTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "EmailAddress");
			String Password = getTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "EmailPassword");
			browser.navigate().to(getTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "EmailClient"));

			if (!clearAndEnterText(UserName_TXT, Username, browser)) {
				messagesMap.put("User Name", "Not able to enter User Name in the field");
				status = WARNING;
				// throw new
				// Exception("Not able to enter User Name in the field");
			}
			if (!clearAndEnterText(Password_TXT, Password, browser)) {
				messagesMap.put("Password", "Not able to enter Password in the field");
				status = WARNING;
				// throw new
				// Exception("Not able to enter Password in the field");
			}
			if (!isElementPresentVerifyClick(SignIn_BTN, browser)) {
				throw new Exception("Not able to click on Sign-in Button");
			}
			if (!isElementPresentVerification(Signout_LNK, browser)) {
				throw new Exception("Not able to find sign out link");
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
	 * @Purpose - Method to Navigate to Inbox of Email Account (Yahoo)
	 * @author - Siva Santhi Reddy
	 * @Created - 17-Aug-2012
	 * @Modified By - Siva
	 * @Modified Date -28-FEB-2013
	 ************************************************************* 
	 */
	public static void navigateToInbox(EventFiringWebDriver browser, StepBean stepBean, Map<String, List<String>> dataMap) {
		String status = PASS;
		String Inbox_LNK = "//*[contains(text(),'Inbox')]~XPATH";
		String NoEmailsinInboxMessage_ELM = "//p[contains(text(),'There are no emails in your Inbox')]~XPATH";
		long PRESENT_WAIT_FOR_EMAIL = EMAIL_MAX_WAIT_INTERVAL;
		Map<String, String> messagesMap = null;

		try {
			if (messagesMap == null) {
				messagesMap = new HashMap<String, String>();
				stepBean.setStepStatusMessages(messagesMap);
			}
			browser.navigate().to("http://" + getTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "EmailClient"));
			if (!isElementPresentVerifyClick(Inbox_LNK, browser)) {
				throw new Exception("Not able to click on Inbox element");
			}
			browser.navigate().refresh();
			do {
				browser.navigate().refresh();
				if (isElementPresentVerification(NoEmailsinInboxMessage_ELM, browser)) {
					Thread.sleep(EMAIL_MAX_WAIT_INTERVAL);
				} else {
					break;
				}
				PRESENT_WAIT_FOR_EMAIL = PRESENT_WAIT_FOR_EMAIL + EMAIL_MAX_WAIT_INTERVAL;
			} while (PRESENT_WAIT_FOR_EMAIL <= EMAIL_MAX_WAIT_THRESHOLD);

			if (isElementPresentVerification(NoEmailsinInboxMessage_ELM, browser)) {
				throw new Exception("No Emails received in Inbox");
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
	 * @Purpose - Method to delet All emails in Client Inbox
	 * @author - Siva Santhi Reddy
	 * @Created - 17-Aug-2012
	 * @Modified By -Siva
	 * @Modified Date -15-Nov-12
	 ************************************************************* 
	 */
	public static void deletingallMailsinInbox(EventFiringWebDriver browser, StepBean stepBean) {
		String status = PASS;
		// String SelectAllMails_CHK =
		// "input[title*='Select or deselect all messages']~CSS";
		String SelectAllMails_CHK = "//span[contains(@id,'cbox')]/label";
		String SelectOptionpane_LNK="//span[@id='btn-select-dd']/*";
		String OptionAll_LNK="//ul/li/a/span[contains(text(),'All')]";
		String Delete_BTN = "*[title*='Delete']~CSS";
		String Ok_BTN = "//a[contains(text(),'OK')]~XPATH";
		String EmptyMsg = "//*[contains(text(),'There are no')]~XPATH";
		boolean EmptyMsgdisplayed = false;

		Map<String, String> messagesMap = null;
		try {
			if (messagesMap == null) {
				messagesMap = new HashMap<String, String>();
				stepBean.setStepStatusMessages(messagesMap);
			}
			do {
				if (isElementPresentVerification(EmptyMsg, browser)) {

					EmptyMsgdisplayed = true;
				} else {
					if (!isElementPresentVerifyClick(SelectAllMails_CHK, browser)) {
						throw new Exception("Can't able to select the check box.");
					}
					if (!isElementPresentVerifyClick(Delete_BTN, browser)) {
						throw new Exception("Can't able to click on Delete button");
					}
					if (isElementPresentVerification(Ok_BTN, browser)) {
						getElementByProperty(Ok_BTN, browser).click();
					}
				}
			} while (!EmptyMsgdisplayed);

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
	 * @Purpose - Method to Verifying Loylaty Info in Rx email
	 * @author - Siva Santhi Reddy
	 * @Created - 17-Aug-2012
	 * @Modified By - Siva
	 * @Modified Date -15-Nov-12
	 ************************************************************* 
	 */

	public static String loyaltyInfoVerificationinRxEmail(EventFiringWebDriver browser, StepBean stepBean, Map<String, List<String>> dataMap) {
		String status = PASS;
		String ShowImages_LNK = "a[title='Display blocked images']~CSS";
		String LoyaltyLogo = "img[title='Balance Rewards']~CSS";
		String ActivateNow = "img[title='activate now']~CSS";
		String JoinNow = "img[title='join now']~CSS";
		String LearnMore = "//a[contains(text(),'Learn More')]~XPATH";
		// String Iframe = "//div[@class=' message  content']/iframe~XPATH";
		String Iframe = "//iframe[contains(@class,'om-views-messagepane')]~XPATH";
		String ActivateOnline_H1 = "//h1[contains(text(),'Activate Balance')]~XPATH";
		String JoinNowpage_H1 = "//h1[contains(text(),'Join Balance')]~XPATH";
		String LoyaltyBanner_DIV = "//div[contains(@class,'loyaltyUpSellBanner')]~XPATH";
		String FinalMethodStatus = null;

		Map<String, String> messagesMap = null;
		try {
			if (messagesMap == null) {
				messagesMap = new HashMap<String, String>();
				stepBean.setStepStatusMessages(messagesMap);
			}
			browser.switchTo().frame(getElementByProperty(Iframe, browser));
			String EntireFrameText = browser.getPageSource();
			String RxPointMessage = getTestData("CommonData", "GenaralInfo", "RxPointMessage", "Message");
			if (isElementPresentVerification(ShowImages_LNK, browser)) {
				getElementByProperty(ShowImages_LNK, browser).click();
			}
			if (dataMap.get("LoyaltyBanner").get(0).equalsIgnoreCase("Yes")) {
				if (!isElementPresentVerification(LoyaltyLogo, browser)) {
					messagesMap.put("Loyalty Logo", "Not able to find Loyalty Logo");
					status = WARNING;
					// throw new Exception("Not able to find Loyalty Logo");
				}
				if (!isElementPresentVerification(ActivateNow, browser)) {
					messagesMap.put("Activate Button", "Not able to find Activate Button");
					status = WARNING;
					// throw new Exception("Not able to find Activate Button");
				}
				if (!isElementPresentVerification(JoinNow, browser)) {
					messagesMap.put("Join Button", "Not able to find Join Button");
					status = WARNING;
					// throw new Exception("Not able to find Join Button");
				}
				if (!isElementPresentVerification(LearnMore, browser)) {
					messagesMap.put("Learn More", "Not able to find Learn More");
					status = WARNING;
					// throw new Exception("Not able to find Learn More");
				}
				if (!(EntireFrameText.contains(RxPointMessage))) {
					messagesMap.put("Rx Point Message", "Not able to find th Rx Point Message");
					status = WARNING;
					// throw new
					// Exception("Not able to find th Rx Point Message");
				}
			} else if (dataMap.get("LoyaltyBanner").get(0).equalsIgnoreCase("No")) {
				if (isElementPresentVerification(LoyaltyLogo, browser)) {
					messagesMap.put("Loyalty Logo", "Loyalty Logo Should not be displayed");
					status = WARNING;
					// throw new
					// Exception("Loyalty Logo Should not be displayed");
				}
				if (isElementPresentVerification(ActivateNow, browser)) {
					messagesMap.put("Activate Button", "Activate Button Should not be displayed");
					status = WARNING;
					// throw new
					// Exception("Activate Button Should not be displayed");
				}
				if (isElementPresentVerification(JoinNow, browser)) {
					messagesMap.put("Join Button", "Join Button Should not be displayed");
					status = WARNING;
					// throw new
					// Exception("Join Button Should not be displayed");
				}
				if (isElementPresentVerification(LearnMore, browser)) {
					messagesMap.put("Learn More", " Learn More link Should not be displayed");
					status = WARNING;
					// throw new
					// Exception("find Learn More Should not be displayed");
				}
			}
			if (dataMap.get("ActivateNow").get(0).equalsIgnoreCase("Yes")) {
				String WinHandleBefore = browser.getWindowHandle();
				if (!isElementPresentVerifyClick(ActivateNow, browser)) {
					throw new Exception("Not able to find the Activate Button");
				}
				for (String NewWinHandle : browser.getWindowHandles()) {
					browser.switchTo().window(NewWinHandle);
				}
				if (!isElementPresentVerification(ActivateOnline_H1, browser)) {
					messagesMap.put("Activate Online page H1", "Activate Online page H1 title is not found");
					status = WARNING;
					// throw new
					// Exception("Activate Online page H1 title is not found");
				}
				browser.close();
				browser.switchTo().window(WinHandleBefore);
			}
			if (dataMap.get("JoinNow").get(0).equalsIgnoreCase("Yes")) {
				browser.switchTo().frame(getElementByProperty(Iframe, browser));
				if (!isElementPresentVerifyClick(JoinNow, browser)) {
					throw new Exception("Not able to find the JoinNow Button");
				}
				String WinHandleBefore = browser.getWindowHandle();
				for (String NewWinHandle : browser.getWindowHandles()) {
					browser.switchTo().window(NewWinHandle);
				}
				if (!isElementPresentVerification(JoinNowpage_H1, browser)) {
					messagesMap.put("Join Now page H1", "Join Now page H1 title is not found");
					status = WARNING;
					// throw new
					// Exception("Join Now page H1 title is not found");
				}
				browser.close();
				browser.switchTo().window(WinHandleBefore);
			}
			if (dataMap.get("LearnMore").get(0).equalsIgnoreCase("Yes")) {
				browser.switchTo().frame(getElementByProperty(Iframe, browser));
				if (!isElementPresentVerifyClick(LearnMore, browser)) {
					throw new Exception("Not able to find the Learn More Link");
				}
				String WinHandleBefore = browser.getWindowHandle();
				for (String NewWinHandle : browser.getWindowHandles()) {
					browser.switchTo().window(NewWinHandle);
				}
				if (!isElementPresentVerification(LoyaltyBanner_DIV, browser)) {
					messagesMap.put("Loyalty JoinNow section", "Loyalty JoinNow section is not displayed in the account home page");
					status = WARNING;
					// throw new
					// Exception("Loyalty JoinNow section is not displayed in the account home page");
				}
				browser.close();
				browser.switchTo().window(WinHandleBefore);
			}
			// browser.switchTo().defaultContent();

		} catch (Exception e) {
			status = FAIL;
			messagesMap.put("An Exception Occured:", e.getMessage());
			LogIt(e, null, stepBean);
		}
		// FinalMethodStatus = status + "~" + statusDescription;
		FinalMethodStatus = status;
		return FinalMethodStatus;
	}

	/**
	 ************************************************************* 
	 * @Purpose - Method to Verifying Loylaty Info in Order Details Email(DL)
	 * @author - Siva Santhi Reddy
	 * @Created - 17-Aug-2012
	 * @Modified By - Siva Santhi Reddy
	 * @Modified Date - 15-Nov-2012
	 ************************************************************* 
	 */

	public static String loyaltyInfoVerificationinOrderDetailsEmail(EventFiringWebDriver browser, StepBean stepBean, Map<String, List<String>> dataMap) {
		String status = PASS;
		String JoinNow_LNK = "//a[contains(text(),'Join now')]~XPATH";
		// String Iframe = "//div[@class=' message  content']/iframe~XPATH";
		String Iframe = "//iframe[contains(@id,'om_default_view_id')]~XPATH";
		String LoyaltySection_DIV = "//table/tbody/tr[5]/td/table/tbody/tr[2]/td/div~XPATH";
		String JoinNowpage_H1 = "//h1[contains(text(),'Join Balance')]~XPATH";
		String FinalMethodStatus = null;
		Map<String, String> messagesMap = null;
		try {
			if (messagesMap == null) {
				messagesMap = new HashMap<String, String>();
				stepBean.setStepStatusMessages(messagesMap);
			}
			browser.switchTo().frame(getElementByProperty(Iframe, browser));
			String LoyaltyDivText = getElementByProperty(LoyaltySection_DIV, browser).getText();
			if (dataMap.get("LoyaltySaveMsg").get(0).equalsIgnoreCase("Yes") && dataMap.get("LoyaltyBonuPointMsg").get(0).equalsIgnoreCase("Yes")) {
				int SaveAmount = Integer.parseInt(LoyaltyDivText.split("\\$")[1].split("\\.")[0]);
				int EarnedPoints = Integer.parseInt(LoyaltyDivText.split("and earned")[1].split("points")[0].replaceAll(" ", ""));
				if (SaveAmount == 0 || EarnedPoints == 0) {
					messagesMap.put("Saved Amount or Points", "Saved Amount or Points are displaying as Zero.");
					status = WARNING;
					// throw new
					// Exception("Saved Amount or Points are displaying as Zero.");
				}
				String RequiredLoyaltyMessage = "You could have saved $" + SaveAmount + ".00 and earned " + EarnedPoints + " points on this order as a";
				if (!LoyaltyDivText.contains(RequiredLoyaltyMessage)) {
					messagesMap.put("Loylaty Message", "Not able to find the required Loylaty Message");
					status = WARNING;
					// throw new
					// Exception("Not able to find the required Loylaty Message");
				}

			} else if (dataMap.get("LoyaltySaveMsg").get(0).equalsIgnoreCase("Yes") && dataMap.get("LoyaltyBonuPointMsg").get(0).equalsIgnoreCase("No")) {
				int SaveAmount = Integer.parseInt(LoyaltyDivText.split("\\$")[1].split("\\.")[0]);
				if (SaveAmount == 0) {
					messagesMap.put("Saved Amount", "Saved Amount is displaying as Zero.");
					status = WARNING;
					// throw new
					// Exception("Saved Amount is displaying as Zero.");
				}
				String RequiredLoyaltyMessage = "You could have saved $" + SaveAmount + ".00 on this order as a";
				if (!LoyaltyDivText.contains(RequiredLoyaltyMessage)) {
					messagesMap.put("Loyalty Message", "Not able to find the required Loyalty Message");
					status = WARNING;
					// throw new
					// Exception("Not able to find the required Loyalty Message");
				}
			}
			if (dataMap.get("LoyaltySaveMsg").get(0).equalsIgnoreCase("No") && dataMap.get("LoyaltyBonuPointMsg").get(0).equalsIgnoreCase("Yes")) {
				int EarnedPoints = Integer.parseInt(LoyaltyDivText.split("earned")[1].split("points")[0].replaceAll(" ", ""));
				if (EarnedPoints == 0) {
					messagesMap.put("Saved Amount or Points", "Saved Amount or Points are displaying as Zero.");
					status = WARNING;
					// throw new
					// Exception("Saved Amount or Points are displaying as Zero.");
				}
				String RequiredLoyaltyMessage = "You could have earned " + EarnedPoints + " points on this order as a";
				if (!LoyaltyDivText.contains(RequiredLoyaltyMessage)) {
					messagesMap.put("Loylaty Message", "Not able to find the required Loylaty Message");
					status = WARNING;
					// throw new
					// Exception("Not able to find the required Loylaty Message");
				}
			} else if (dataMap.get("LoyaltySaveMsg").get(0).equalsIgnoreCase("No") && dataMap.get("LoyaltyBonuPointMsg").get(0).equalsIgnoreCase("No")) {
				if (LoyaltyDivText.contains("You could have")) {
					messagesMap.put("Saved Amount or Points message", "Loyalty Savings or Bonus Points Message should not be displayed");
					status = WARNING;
					// throw new
					// Exception("Loyalty Savings or Bonus Points Message should not be displayed");
				}
			}
			if (dataMap.get("JoinNowLInk").get(0).equalsIgnoreCase("Yes")) {
				if (!isElementPresentVerifyClick(JoinNow_LNK, browser)) {
					throw new Exception("not able to find the Join Now link");
				}
				String WinHandleBefore = browser.getWindowHandle();
				for (String NewWinHandle : browser.getWindowHandles()) {
					browser.switchTo().window(NewWinHandle);
				}
				if (!isElementPresentVerification(JoinNowpage_H1, browser)) {
					messagesMap.put("Join Now Page", "Page is not redirected to Join Now Page");
					status = WARNING;
					// throw new
					// Exception("Page is not redirected to Join Now Page");
				}
				browser.close();
				browser.switchTo().window(WinHandleBefore);
			} else if (dataMap.get("JoinNowLInk").get(0).equalsIgnoreCase("No")) {
				if (!isElementPresentVerification(JoinNow_LNK, browser)) {
					messagesMap.put("Join Now link ", "Join Now link should not be displayed");
					status = WARNING;
					// throw new
					// Exception("Join Now link should not be displayed");
				}
			}
		} catch (Exception e) {
			status = FAIL;
			messagesMap.put("An Exception Occured:", e.getMessage());
			LogIt(e, null, stepBean);
		}
		// FinalMethodStatus = status + "~" + statusDescription;
		FinalMethodStatus = status;
		return FinalMethodStatus;
	}

	/**
	 ************************************************************* 
	 * @Purpose - Method to open a required email
	 * @author - Siva Santhi Reddy
	 * @Created - 17-Aug-2012
	 * @Modified By -Siva
	 * @Modified Date -15-Nov-12
	 ************************************************************* 
	 */

	public static void verifiesLoyaltyInfoinEmail(EventFiringWebDriver browser, StepBean stepBean, Map<String, List<String>> dataMap) {
		String status = PASS;
		String StepStausinchildMethod = null;
		Map<String, String> messagesMap = null;
		try {
			if (messagesMap == null) {
				messagesMap = new HashMap<String, String>();
				stepBean.setStepStatusMessages(messagesMap);
			}
			if (dataMap.get("EmailType").get(0).equalsIgnoreCase("RxConfirmation")) {
				StepStausinchildMethod = loyaltyInfoVerificationinRxEmail(browser, stepBean, dataMap);
			} else if (dataMap.get("EmailType").get(0).equalsIgnoreCase("SSitemConfirmation")) {
				StepStausinchildMethod = loyaltyInfoVerificationinOrderDetailsEmail(browser, stepBean, dataMap);
			}
			status = StepStausinchildMethod.split("~")[0];
			status = StepStausinchildMethod;
			// statusDescription = StepStausinchildMethod.split("~")[1];
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
	 * @Purpose - Method to Verifying Loylaty Info in Email
	 * @author - Siva Santhi Reddy
	 * @Created - 24-Aug-2012
	 * @Modified By -Siva
	 * @Modified Date -15-Nov-12
	 ************************************************************* 
	 */

	public static void openingofAnEmail(EventFiringWebDriver browser, StepBean stepBean, Map<String, List<String>> dataMap) {
		String status = PASS;
		String RxEmailLink = "//div[contains(@class,'list-view-item')]//*[contains(text(), 'Order Confirmation')]~XPATH";
		// String RxEmailLink =
		// "//div[contains(text(),'Order Confirmation Details')]~XPATH";
		String SSitemEmailLink = "//div[contains(text(),'Order Details')]~XPATH";
		String ReplytoRefillReminderLink = "//div[contains(text(),'Reply to Refill')]~XPATH";
		Map<String, String> messagesMap = null;
		try {
			if (messagesMap == null) {
				messagesMap = new HashMap<String, String>();
				stepBean.setStepStatusMessages(messagesMap);
			}
			if (dataMap.get("EmailType").get(0).equalsIgnoreCase("RxConfirmation")) {
				if (!isElementPresentVerifyClick(RxEmailLink, browser)) {
					throw new Exception("Not able to find the Rx email");
				}
			} else if (dataMap.get("EmailType").get(0).equalsIgnoreCase("SSitemConfirmation")) {
				if (!isElementPresentVerifyClick(SSitemEmailLink, browser)) {
					throw new Exception("Not able to find the SS Email Link");
				}
			} else if (dataMap.get("EmailType").get(0).equalsIgnoreCase("ReplytoRefillReminder")) {
				if (!isElementPresentVerifyClick(ReplytoRefillReminderLink, browser)) {
					throw new Exception("Not able to find the Reply to Refill Reminder Email Link");
				}
			} else {
				throw new Exception("Enter Proper Email Type in the feature file step");
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
	 * @Purpose - Archine an Email(Assumption: User already created a folder
	 *          named 'Archive' in yahoo
	 * @author - Siva Santhi Reddy
	 * @Created - 27-Aug-2012
	 * @Modified By - Siva
	 * @Modified Date -19-FEB-2013
	 ************************************************************* 
	 */

	public static void archiveanEmail(EventFiringWebDriver browser, StepBean stepBean, Map<String, List<String>> dataMap) {
		String status = PASS;
		String Move_ICON = "//span[@id='btn-move']/span~XPATH";
		String EmailsSection_DIV = "//div[contains(@class,'list-view-items')]~XPATH";
		String Nextbutton = "a[title='Next Page']~CSS";
		String NextButtonDisabled = "//span[contains(@class,'unext disabled')]~XPATH";
		String SelectAll_CHK = "span[id*='cbox']>label>input~CSS";
		String NoEmailinInbox_ELM = "//*[contains(text(),'There are no emails in your Inbox folder.')]~XPATH";
		String EmailsDiv = "//div[@id='msg-list']/div/div[2]~XPATH";
		String EmailDiv = null;
		boolean flag = false, isNextDisplayed = false;
		Map<String, String> messagesMap = null;
		try {
			if (messagesMap == null) {
				messagesMap = new HashMap<String, String>();
				stepBean.setStepStatusMessages(messagesMap);
			}
			String RequiredEmailSubjectText = getTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("EmailSubject").get(0), "Message");
			String ArchiveFolderName = getTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("ArchiveFolderName").get(0), "Message");
			String EmailLink = "//div[contains(text(),'" + RequiredEmailSubjectText + "')]~XPATH";
			String ArchiveLinkinMoveMenu = "//span[contains(text(),'" + ArchiveFolderName + "')]~XPATH";
			String ArchiveFolder_LNK = "//i[contains(text(),'" + ArchiveFolderName + "')]~XPATH";


			if (!isElementPresentVerification(NoEmailinInbox_ELM, browser)) {
				// New code-- Archiving All emails if we mentioned Subject Line as
				// 'All' to the Archive Folder--SIVA
				if (RequiredEmailSubjectText.equalsIgnoreCase("All")) {
					do {
						if (isElementPresentVerification(NoEmailinInbox_ELM, browser)) {
							flag = true;
						} else {
							if (!isElementPresentVerifyClick(SelectAll_CHK, browser)) {
								throw new Exception("Not able to click on Select All check box");
							}
							Thread.sleep(1000);
							if (!isElementPresentVerifyClick(Move_ICON, browser)) {
								throw new Exception("Not able to click on Move items button");
							}
							Thread.sleep(1000);
							if (!isElementPresentVerifyClick(ArchiveLinkinMoveMenu, browser)) {
								throw new Exception("Not able to click on Archive link");
							}
						}
						if (!isElementPresentVerification(NextButtonDisabled, browser)) {
							isNextDisplayed = true;
						}

					} while ((!flag) && isNextDisplayed);
				}
				// New-code Ended

				else {
					if (isElementPresentVerification(EmailsDiv, browser)) {
						do {
							List<WebElement> IndividualEmailDivs = getElementByProperty(EmailsSection_DIV, browser).findElements(By.xpath("//div[contains(@class,'list-view-item ')]"));
							for (int i = 1; i < IndividualEmailDivs.size(); i++) {
								EmailDiv = "//div[@id='msg-list']/div/div[2]/div[" + i + "]";
								if (getElementByProperty(EmailDiv, browser).getText().contains(RequiredEmailSubjectText)) {
									if (isElementPresentVerification(EmailDiv, browser)) {
										WebElement Checkbox = getElementByProperty(EmailDiv, browser).findElement(By.tagName("input"));
										Checkbox.click();
										flag = true;
									} else {
										throw new Exception("You are trying to find the not existing Div or the searched div is out of box for the Inbxo Email Divs");
									}
									if (flag == true) {
										break;
									}
								}
							}
							if (!flag) {
								if (!isElementPresentVerification(NextButtonDisabled, browser)) {
									isNextDisplayed = true;
								} else {
									isNextDisplayed = false;
								}
							} else {
								isNextDisplayed = false;
							}
							if (isNextDisplayed) {
								getElementByProperty(Nextbutton, browser).click();
							}

						} while ((!flag) && isNextDisplayed);
						if (flag == false) {
							throw new Exception("not able to find the Required email in the inbox");
						}
					} else {
						throw new Exception("not abel to identify the Emails Divisions or No emails found in the inox");
					}

					if (!isElementPresentVerifyClick(Move_ICON, browser)) {
						throw new Exception("Not able to click on Move items button");
					}
					if (!isElementPresentVerifyClick(ArchiveLinkinMoveMenu, browser)) {
						throw new Exception("Not able to click on Archive link");
					}
					if (!isElementPresentVerifyClick(ArchiveFolder_LNK, browser)) {
						throw new Exception("not able to click or find Archive folder link");
					}

					if (!isElementPresentVerification(EmailLink, browser)) {
						messagesMap.put("Archive", "Required Email was not moved to Archive");
						status = WARNING;
						// throw new
						// Exception("Required Email was not moved to Archive");
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
	 * @Purpose - Verifying Information in Reply to Refill Reminder Email
	 * @author - Siva Santhi Reddy
	 * @Created - 27-Nov-12
	 * @Modified By - Siva
	 * @Modified Date -
	 ************************************************************* 
	 */

	public static void verifyInfoOnReplytoRefillEmail(EventFiringWebDriver browser, StepBean stepBean, Map<String, List<String>> dataMap) {
		String status = PASS;
		String Iframe = "//iframe[contains(@class,'om-views-messagepane')]~XPATH";
		String ShowImages_LNK = "a[title='Display blocked images']~CSS";
		String PrivacyPreference_LNK = "a[title='Change  privacy preferences.']~CSS";
		String CommunicationPreference_Header = "//h2[contains(text(),'Manage Your Communication Preferences')]~XPATH";
		String Reply_BTN = "a[title='Reply to sender  [R]']~CSS";
		String Send_BTN = "//a[contains(text(),'Send')]~XPATH";
		String EmailSentSuccessMsg_ELM = "//*[contains(text(),'Email Sent')]~XPATH";
		Map<String, String> messagesMap = null;
		try {
			if (messagesMap == null) {
				messagesMap = new HashMap<String, String>();
				stepBean.setStepStatusMessages(messagesMap);
			}
			browser.switchTo().frame(getElementByProperty(Iframe, browser));
			if (isElementPresentVerification(ShowImages_LNK, browser)) {
				getElementByProperty(ShowImages_LNK, browser).click();
			}
			if (dataMap.get("ChangeYourPrivacyPreferenceLink").get(0).equalsIgnoreCase("Yes")) {
				if (!isElementPresentVerifyClick(PrivacyPreference_LNK, browser)) {
					throw new Exception("Not able to click  on Privacy Preference Link in the RR email");
				}
				String WinHandleBefore = browser.getWindowHandle();
				for (String NewWinHandle : browser.getWindowHandles()) {
					browser.switchTo().window(NewWinHandle);
				}
				if (!isElementPresentVerification(CommunicationPreference_Header, browser)) {
					messagesMap.put("Privacy Preference LInk ", "Link is navigated wrongly");
					status = WARNING;
				}
				browser.close();
				browser.switchTo().window(WinHandleBefore);
			}
			if (dataMap.get("ReplyToEmail").get(0).equalsIgnoreCase("Yes")) {
				if (!isElementPresentVerifyClick(Reply_BTN, browser)) {
					throw new Exception("Not able to click on Reply button in the Email");
				}
				if (!isElementPresentVerifyClick(Send_BTN, browser)) {
					throw new Exception("Not able to click on Send button in the Email");
				}
				if (!isElementPresentVerification(EmailSentSuccessMsg_ELM, browser)) {
					messagesMap.put("Email Send Status", "Not able to find Email Send success message");
					status = WARNING;
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
	 * @Purpose -Retrieve Username or Change Password
	 * @author - Siva Santhi Reddy
	 * @Created - 20-MAY-2013
	 * @Modified By -
	 * @Modified Date -
	 ************************************************************* 
	 */

	public static void retrieveUserNameorChangePassword(EventFiringWebDriver browser, StepBean stepBean, Map<String, List<String>> dataMap) {
		String status = PASS;
		String PasswordResetRequestEmail_LNK = "//span[contains(text(),'Reset Password Request')]~XPATH";
		String ChangePassword_LNK = "//a[contains(text(),'/password/reset.jsp?')]~XPATH";
		String NewPassword_TXT = "input#new_password~CSS";
		String ConfirmPassword_TXT = "input#confirmNewPassword~CSS";
		String UpdatePassword_BTN = "input#updatepwd~CSS";
		String PasswordChangeSuccessMessage_ELM = "//div[contains(text(),'You have successfully updated your password.')]~XPATH";
		String RetrieveUserNameEmail_LNK = "//span[contains(text(),'Retrieve Username')]~XPATH";
		String WinHandleBefore = null, Password = "";
		Map<String, String> messagesMap = null;
		try {
			if (messagesMap == null) {
				messagesMap = new HashMap<String, String>();
				stepBean.setStepStatusMessages(messagesMap);
			}
			String UserName = getTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "UserName");
			String RequestType = getTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "RequestType");
			if (RequestType.equalsIgnoreCase("ResetPassword")) {
				if (!isElementPresentVerifyClick(PasswordResetRequestEmail_LNK, browser)) {
					throw new Exception("Not able to Click on Reset Password Request Email Link");
				}
				if (!isElementPresentVerifyClick(ChangePassword_LNK, browser)) {
					throw new Exception("Not able to Click on Change Password Link");
				}
				WinHandleBefore = browser.getWindowHandle();
				for (String NewWinHandle : browser.getWindowHandles()) {
					browser.switchTo().window(NewWinHandle);
				}
				for (;;) {
					Password = Password + randomNameGenerator();
					if (Password.length() > 8) {
						break;
					}
				}
				Password = Password + "1";
				TestDataDBManager.putTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "Password", Password);
				if (!clearAndEnterText(NewPassword_TXT, Password, browser)) {
					messagesMap.put("New Password", "Not able to Enter New Password");
					status = WARNING;
				}
				if (!clearAndEnterText(ConfirmPassword_TXT, Password, browser)) {
					messagesMap.put("Confirm Password", "Not able to Enter Confirm Password");
					status = WARNING;
				}
				if (!isElementPresentVerifyClick(UpdatePassword_BTN, browser)) {
					throw new Exception("Not able to click on Update Password Button");
				}
				if (!isElementPresentVerification(PasswordChangeSuccessMessage_ELM, browser)) {
					messagesMap.put("Password Change Success Message", "Not able to Find the Message");
					status = WARNING;
				}
			} else if (RequestType.equalsIgnoreCase("RetrieveUsername")) {
				if (!isElementPresentVerifyClick(RetrieveUserNameEmail_LNK, browser)) {
					throw new Exception("Not able to Click on Retrieve User Name Email Link");
				}
				String PageText = browser.getPageSource();
				if (!PageText.contains(UserName)) {
					messagesMap.put("Retrieve User Name", "User Name is missing in retrieve User Name Email");
					status = WARNING;
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
	 * @Purpose - Method to Verify Express refill Order confirmation email
	 * @author - Ambika C
	 * @Created - 17-June-2013
	 * @Modified By -
	 * @Modified Date -
	 ************************************************************* 
	 */

	public static String ExpressRxEmailVerification(EventFiringWebDriver browser, StepBean stepBean, Map<String, List<String>> dataMap) {
		String status = PASS;
		String ShowImages_LNK = "a[title='Display blocked images']~CSS";
		String WagLogo_LNK = "img[alt='Walgreens']~CSS";
		String PharmacyHealth_LNK = "//a/span[contains(text(),'Pharmacy & Health')]~XPATH";
		String Photo_LNK = "//a/span[contains(text(),'Photo')]~XPATH";
		String ShopProducts_LNK = "//a/span[contains(text(),'Shop Products')]~XPATH";
		String BalanceRewards_LNK = "img[alt='balance rewards']~CSS";
		String Header_TXT = "//td/span[contains(text(),'Your Prescription Refill Order Confirmation')]~XPATH";
		String RefillText_TXT = "//span[contains(text(),'This order contains')]~XPATH";
		String Header3Text_TXT = "//span[contains(text(),' We received the prescription refill order')]~XPATH";
		String Header2Text_TXT = "//p[contains(text(),'To view detailed prescription information please visit your ')]~XPATH";
		String ShipInfo_ELM = "//span[contains(text(),'Shipping Information')]~XPATH";
		String PaymentInfo_ELM = "//span[contains(text(),'Payment Information')]~XPATH";
		String Footer1_LNK = "//tr/td/a/span[contains(text(),' Refill Prescriptions')]~XPATH";
		String Footer2_LNK = "a[title='Photo Prints']~CSS";
		String Footer3_LNK = "a[title='Mobile Apps']~CSS";
		String Footer4_LNK = "a[title='Balance Rewards']~CSS";
		String Footer5_LNK = "a[title='Vitamins']~CSS";
		String Footer6_LNK = "a[title='Beauty Products']~CSS";
		String Footer7_LNK = "a[title='Contact Lenses']~CSS";
		String Footer8_LNK = "a[title='Find A Store']~CSS";
		String CreditType_ELM = "//span[contains(text(),'Credit Card')]~XPATH";
		String CreditNumber_ELM = "//span[contains(text(),'Card #')]~ XPATH";
		String CardInfo_ELM = "//span[contains(text(),'Your card will not be charged')]~ XPATH";
		String OrderNumber_ELM = "//span[contains(text(),'Order #')]~XPATH";

		String FinalMethodStatus = null, strStreetAddress = null, strState = null, strCity = null, strZipCode = null, strCardNumber = null;

		Map<String, String> messagesMap = null;
		strStreetAddress = getTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "StreetAddress");
		strCity = getTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "City");
		strState = getTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "State");
		strZipCode = getTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "ZipCode");
		strCardNumber = getTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "CardNumber");
		String OrderID = getTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "OrderID_1");
		String ShippingAddress1_DIV = "//*[contains(text(),'" + strStreetAddress + "')]~ XPATH";
		String ShippingAddress2_DIV = "//*[contains(text(),'" + strCity + ", " + strState + " " + strZipCode + "')]~ XPATH";

		try {
			if (messagesMap == null) {
				messagesMap = new HashMap<String, String>();
				stepBean.setStepStatusMessages(messagesMap);
			}

			String[] ElementsToBeVerfied = {WagLogo_LNK, PharmacyHealth_LNK, Photo_LNK, ShopProducts_LNK, ShopProducts_LNK, BalanceRewards_LNK, Header_TXT, Header2Text_TXT,
					RefillText_TXT, Header3Text_TXT, ShipInfo_ELM, PaymentInfo_ELM, Footer1_LNK, Footer2_LNK, Footer3_LNK, Footer4_LNK, Footer5_LNK, Footer6_LNK, Footer7_LNK, Footer8_LNK,
					CreditType_ELM, CreditNumber_ELM, CardInfo_ELM, ShippingAddress1_DIV, ShippingAddress2_DIV };

			if (isElementPresentVerification(ShowImages_LNK, browser)) {
				getElementByProperty(ShowImages_LNK, browser).click();
			}

			for (int elementNo = 0; elementNo < ElementsToBeVerfied.length; elementNo++) {
				if (!isElementPresentVerification(ElementsToBeVerfied[elementNo], browser)) {
					messagesMap.put(ElementsToBeVerfied[elementNo], "Object is missing");
					status = WARNING;
				}
			}

			if (!(getElementByProperty(OrderNumber_ELM, browser).getText().split("#")[1].equalsIgnoreCase(OrderID))) {
				messagesMap.put("ORDER ID verification in EMAIL", "Unable to found the order number in the Confirmation mail");
				status = WARNING;
			}
		} catch (Exception e) {
			status = FAIL;
			messagesMap.put("An Exception Occured:", e.getMessage());
			LogIt(e, null, stepBean);
		} finally {
			stepBean.setStepStatus(status);
		}
		// FinalMethodStatus = status + "~" + statusDescription;
		FinalMethodStatus = status;
		return FinalMethodStatus;
	}

	/**
	 ************************************************************* 
	 * @Purpose - Method to Verify Fulfillment confirmation email
	 * @author - Siva Santhi Reddy P
	 * @Created - 26-AUG-2013
	 * @Modified By -Siva
	 * @Modified Date -26-SEP-2013
	 ************************************************************* 
	 */

	public static void verifyFulfillmentOrderEmail(EventFiringWebDriver browser, StepBean stepBean, ScenarioBean scenarioBean, Map<String, List<String>> dataMap) {
		String status = PASS;
		String Inbox_LNK = "//*[contains(text(),'Inbox')]~XPATH";
		String EmailLink_ELM = "";
		String OrderCompleteMessage_ELM = "//*[contains(text(),'Order is Complete')]~XPATH";
		String YourOrderHasbeenShipingMessage_ELM = "//h1[contains(text(),'Your order has been shipped.')]~XPATH";
		String YourOrderHasbeenCancelledMessage_ELM = "//*[contains(text(),'all of your items were out of Stock so your order has been cancelled')]~XPATH";
		String MessageBody_ELM = "//div[contains(@class,'body')]~XPATH";
		String PartialFulfillmentMessage = "we found that some of the items you requested were out of stock. They will not be included with this order and you will not be charged for them";
		float Tax = 0, Total = 0, flttotalSalesTax = 0, flttotalOrdertotal = 0, fltappOrderTotal = 0, fltappSalesTax = 0;
		String appSalesTax = null, apprawSubTotal = null, appShippingTotal = null, totalSalesTax = null, totalOrdertotal = null;
		Map<String, String> getDBvlaues = null;
		Map<String, String> messagesMap = null;
		Map<String, String> sharedProperties = null;
		try {
			if (messagesMap == null) {
				messagesMap = new HashMap<String, String>();
				stepBean.setStepStatusMessages(messagesMap);
			}
			sharedProperties = scenarioBean.getSharedStepProperties();
			if (sharedProperties == null) {
				sharedProperties = new HashMap<String, String>();
				scenarioBean.setSharedStepProperties(sharedProperties);
			}
			int totalRowCount = Integer.parseInt(dataMap.get("totalRowCount").get(0));
			for (int i = 0; i < totalRowCount; i++) {
				String OrderID = getTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "OrderID_1");
				String EmailType = getTestData(dataMap.get("InputFileName").get(i), dataMap.get("SheetName").get(i), dataMap.get("RowId").get(i), "EmailType");
				String FulfillmentType = getTestData(dataMap.get("InputFileName").get(i), dataMap.get("SheetName").get(i), dataMap.get("RowId").get(i), "FulfillmentType");
				String verifyOrderTotal = getTestData(dataMap.get("InputFileName").get(i), dataMap.get("SheetName").get(i), dataMap.get("RowId").get(i), "verifyOrderTotal");
				if (EmailType != null && EmailType.equalsIgnoreCase("CL")) {
					EmailLink_ELM = "//span[contains(text(),'Your Contact Lens Order " + OrderID + "')]";
				} else if (EmailType != null && EmailType.equalsIgnoreCase("CancelFulfillment")) {
					EmailLink_ELM = "//span[contains(text(),'" + OrderID + " has been cancelled')]";
				} else {
					EmailLink_ELM = "//span[contains(text(),'" + OrderID + " is Complete')]";
				}
				browser.navigate().refresh();
				if (!isElementPresentVerifyClick(EmailLink_ELM, browser)) {
					throw new Exception("Not able to click on Fulfillment Order Complete Email");
				}
				if (EmailType != null && EmailType.equalsIgnoreCase("CancelFulfillment")) {
					if (!isElementPresentVerification(YourOrderHasbeenCancelledMessage_ELM, browser)) {
						messagesMap.put("Order Cancel Message Header", "Object is missing");
						status = WARNING;
					}
				} else {
					if (!(isElementPresentVerification(OrderCompleteMessage_ELM, browser) || isElementPresentVerification(YourOrderHasbeenShipingMessage_ELM, browser))) {
						messagesMap.put("Order Complete", "Message is not getting displayed");
						status = WARNING;
					}
				}
				if (FulfillmentType != null && FulfillmentType.equalsIgnoreCase("Partial")) {
					if (!getElementByProperty(MessageBody_ELM, browser).getText().contains(PartialFulfillmentMessage)) {
						messagesMap.put("Partial Fulfillment Message", "Message is not geting displayed");
						status = WARNING;
					}
				}
				getDBvlaues = scenarioBean.getscenarioDBvalues();
				if (getDBvlaues != null) {
					appSalesTax = getDBvlaues.get("TAX");
					apprawSubTotal = getDBvlaues.get("RAW_SUBTOTAL");
					appShippingTotal = getDBvlaues.get("SHIPPING");
				}
				if (sharedProperties != null) {
					totalSalesTax = sharedProperties.get("totalSalesTax");
					totalOrdertotal = sharedProperties.get("totalOrdertotal");
					if (totalSalesTax != null) {
						flttotalSalesTax = Float.parseFloat(totalSalesTax);
					}
					if (totalOrdertotal != null) {
						flttotalOrdertotal = Float.parseFloat(totalOrdertotal);
					}
				}
				List<WebElement> ordervalueTables = getElementByProperty(MessageBody_ELM, browser).findElements(By.xpath("//td[@colspan='6']"));
				for (int increment = 0; increment < ordervalueTables.size(); increment++) {
					int noofValuesFound = 0;
					List<WebElement> orderTablerows = ordervalueTables.get(increment).findElements(By.tagName("tr"));
					for (int row = 0; row < orderTablerows.size(); row++) {
						if (orderTablerows.get(row).getText().contains("Tax:")) {
							Tax = Float.parseFloat(orderTablerows.get(row).getText().split("Tax:")[1].replace("$", "").trim());
							flttotalSalesTax = flttotalSalesTax + Tax;
							noofValuesFound++;
						}
						if (orderTablerows.get(row).getText().contains("Total:")) {
							Total = Float.parseFloat(orderTablerows.get(row).getText().split("Total:")[1].replace("$", "").replaceAll(",", "").trim());
							flttotalOrdertotal = flttotalOrdertotal + Total;
							noofValuesFound++;
						}
						// Come out of the loop if it found both Tax and Total
						if (noofValuesFound == 2) {
							break;
						}
					}
				}
				sharedProperties.put("totalSalesTax", String.valueOf(flttotalSalesTax));
				sharedProperties.put("totalOrdertotal", String.valueOf(flttotalOrdertotal));
				if (verifyOrderTotal != null && verifyOrderTotal.equalsIgnoreCase("Yes")) {
					if (EmailType != null && EmailType.equalsIgnoreCase("CancelFulfillment")) {
						if ((flttotalSalesTax != 0.0) || (flttotalOrdertotal != 0.0)) {
							messagesMap.put("Sales Tax or Order Total", "Amount should should be displayed as Zero");
							status = WARNING;
						}
					} else {
						fltappOrderTotal = Float.parseFloat(appSalesTax) + Float.parseFloat(apprawSubTotal) + Float.parseFloat(appShippingTotal);
						fltappSalesTax = Float.parseFloat(appSalesTax);
						DecimalFormat decimalFormat = new DecimalFormat("#.##");
						fltappOrderTotal = Float.parseFloat(decimalFormat.format(fltappOrderTotal));
						if (Float.parseFloat(appSalesTax) != flttotalSalesTax) {
							messagesMap.put("totalSalesTax is mismatching", "OrdeConfirmation Page value is:" + appSalesTax + " and email value is:" + flttotalSalesTax);
							status = WARNING;
						}
						if (fltappOrderTotal != flttotalOrdertotal) {
							messagesMap.put("totalOrdertotal is mismatching", "OrdeConfirmation Page value is:" + fltappOrderTotal + " and email value is:" + flttotalOrdertotal);
							status = WARNING;
						}
					}
				}
				if (!isElementPresentVerifyClick(Inbox_LNK, browser)) {
					throw new Exception("Not able to click on Inbox:");
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
	 * @Purpose - Method to Verify whether user recieved Instore Online Order
	 *          Confrimation Email
	 * @author - Siva Santhi Reddy P
	 * @Created - 26-SEP-2013
	 * @Modified By -
	 * @Modified Date --
	 ************************************************************* 
	 */

	public static void verifyInstoreConfrimationEmail(EventFiringWebDriver browser, StepBean stepBean, ScenarioBean scenarioBean, Map<String, List<String>> dataMap) {
		String status = PASS;
		String Inbox_LNK = "//*[contains(text(),'Inbox')]~XPATH";
		String EmailLink_ELM = "";
		Map<String, String> messagesMap = null;
		try {
			if (messagesMap == null) {
				messagesMap = new HashMap<String, String>();
				stepBean.setStepStatusMessages(messagesMap);
			}
			int totalRowCount = Integer.parseInt(dataMap.get("totalRowCount").get(0));
			for (int i = 0; i < totalRowCount; i++) {
				String OrderID = getTestData(dataMap.get("InputFileName").get(i), dataMap.get("SheetName").get(i), dataMap.get("RowId").get(i), "OrderID_1");
				EmailLink_ELM = "//*[contains(text(),'Thank you for placing your Online Order #" + OrderID + "')]";

				if (!isElementPresentVerifyClick(EmailLink_ELM, browser)) {
					throw new Exception("Not able to Click on the required email link for the given Order ID");
				}
				String PageText = browser.getPageSource();
				if (!PageText.contains(OrderID)) {
					messagesMap.put("Orde ID", "Not found in the Email");
					status = WARNING;
				}
				if (!isElementPresentVerifyClick(Inbox_LNK, browser)) {
					throw new Exception("Not able to click on Inbox:");
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
	 * @Purpose - Method to Verify Post Order Survey Email
	 * @author - Siva Santhi Reddy P
	 * @Created - 07-OCT-2013
	 * @Modified By -
	 * @Modified Date --
	 ************************************************************* 
	 */
	public static void verifyPostSurveyOrderEmail(EventFiringWebDriver browser, StepBean stepBean, ScenarioBean scenarioBean) {
		String status = PASS;
		String Inbox_LNK = "//*[contains(text(),'Inbox')]~XPATH";
		String PostSurveyEmail_LNK = "//span[contains(text(),'like to know what you think of Online Ordering')]~XPATH";
		String OnlineOrderWorkingForYouHeader_ELM = "//*[contains(text(),'Walgreens Online Ordering work for you?')]~XPATH";
		String UserSelectedForFeedBackSurveyMessage_ELM = "//*[contains(text(),'You have been selected to take part in a customer satisfaction')]";
		String StartSurvey_BTN = "//a[contains(text(),'Survey')]~XPATH";
		String RefillPrescriptions_LNNK = "//a[contains(text(),'Refill Prescriptions')]~XPATH";
		String PhotoPrints_LNK = "//a[contains(text(),'Photo Prints')]~XPATH";
		String MobileApps_LNK = "//a[contains(text(),'Mobile Apps')]~XPATH";
		String BalanceRewards_LNK = "//a[contains(text(),'Balance Rewards')]~XPATH";
		String Vitamins_LNK = "//a[contains(text(),'Vitamins')]~XPATH";
		String BeautyProducts_LNK = "//a[contains(text(),'Beauty Products')]~XPATH";
		String ContacLens_LNK = "//a[contains(text(),'Contact Lenses')]~xpath";
		String FindAStore_LNK = "//a[contains(text(),'Find A Store')]~XPATH";
		String CustomerServiceCenterEmail_ELM = "//*[contains(text(),'customerservice@mail2.walgreens.com')]~XPATH";
		String ShopNow_LNK = "//strong/a[contains(text(),'Shop')]";
		Map<String, String> messagesMap = null;
		try {
			if (messagesMap == null) {
				messagesMap = new HashMap<String, String>();
				stepBean.setStepStatusMessages(messagesMap);
			}
			if (!isElementPresentVerifyClick(PostSurveyEmail_LNK, browser)) {
				throw new Exception("Not able to Click on Post Survey Email Link");
			}
			String ElementsToVerify[] = { OnlineOrderWorkingForYouHeader_ELM, UserSelectedForFeedBackSurveyMessage_ELM, StartSurvey_BTN, RefillPrescriptions_LNNK, PhotoPrints_LNK, MobileApps_LNK,
					BalanceRewards_LNK, Vitamins_LNK, BeautyProducts_LNK, ContacLens_LNK, FindAStore_LNK, CustomerServiceCenterEmail_ELM };
			for (int i = 0; i < ElementsToVerify.length; i++) {
				if (!isElementPresentVerification(ElementsToVerify[i], browser)) {
					messagesMap.put(ElementsToVerify[i], "Object is Missing");
					status = WARNING;
				}
			}
			int noOfShopNowLinks = browser.findElements(By.xpath(ShopNow_LNK)).size();
			if (noOfShopNowLinks != 2) {
				messagesMap.put("Shop Now Links", "Two Shop Now Links should be displayed");
				status = WARNING;
			}
			if (!isElementPresentVerifyClick(Inbox_LNK, browser)) {
				throw new Exception("Not able to Click on Inbox Link");
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
	 * @Purpose - Method to verify Order Cancellation Email from CSC
	 * @author - Siva Santhi Reddy P
	 * @Created - 28-OCT-2013
	 * @Modified By -
	 * @Modified Date --
	 ************************************************************* 
	 */
	public static void verifyOrderCancellationEmail(EventFiringWebDriver browser, StepBean stepBean, ScenarioBean scenarioBean, Map<String, List<String>> dataMap) {
		String status = PASS;
		String MessageBody_ELM = "//*[@class='thread-body']~XPATH";
		String Inbox_LNK = "//span[contains(text(),'Inbox')]~XPATH";
		String CancelEmailSubjectLine_LNK = null, Mailcontent = null;
		String CancellationMessage = "This email confirms that your Walgreens.com Order# XXXXX was canceled, per your request.";

		Map<String, String> messagesMap = null;
		try {
			if (messagesMap == null) {
				messagesMap = new HashMap<String, String>();
				stepBean.setStepStatusMessages(messagesMap);
			}
			String OrderID = getTestData(dataMap.get("InputFileName").get(0), dataMap.get("SheetName").get(0), dataMap.get("RowId").get(0), "OrderNumber");
			CancelEmailSubjectLine_LNK = "//span[contains(text(),'Your Walgreens.com Order #" + OrderID + " Canceled Per Your Request')]~XPATH";
			CancellationMessage = CancellationMessage.replace("XXXXX", OrderID);
			if (!isElementPresentVerifyClick(CancelEmailSubjectLine_LNK, browser)) {
				throw new Exception("Not able to click on Order Cancel Email for the given number");
			}
			Mailcontent = getElementByProperty(MessageBody_ELM, browser).getText();
			System.out.println("CancellationMessage:" + CancellationMessage);
			System.out.println("Mailcontent:" + Mailcontent);
			if (!Mailcontent.contains(CancellationMessage)) {
				messagesMap.put("Order Cancelled Message in Email", "Message is not getting displayed");
				status = WARNING;
			}
			if (!isElementPresentVerifyClick(Inbox_LNK, browser)) {
				throw new Exception("Not able to Click on Inbox Link");
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
