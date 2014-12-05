package walgreens.ecom.batch.automation.stepdefinitions.common;

import java.util.List;
import java.util.Map;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import walgreens.ecom.batch.automation.library.common.CommonLibrary;
import walgreens.ecom.batch.automation.library.common.EmailVerificationLibrary;
import walgreens.ecom.batch.framework.common.beans.ScenarioBean;
import walgreens.ecom.batch.framework.common.beans.StepBean;
import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.table.DataTable;

public class EmailVerificationtSteps {

    @Given("^\"([^\"]*)\" Customer login to the Email Client:$")
    public void Customer_login_to_the_Email_Client(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData(arg2);
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer login to the Email Client:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    EmailVerificationLibrary.loginToEmailClient(browser, step, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @And("^\"([^\"]*)\" Customer goes to the Inbox for the email client:$")
    public void Customer_goes_to_the_Inbox_for_the_email_client(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData(arg2);
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer goes to the Inbox for the email client:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    EmailVerificationLibrary.navigateToInbox(browser, step, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @And("^\"([^\"]*)\" Customer deletes all the emails from the Inbox.$")
    public void Customer_deletes_all_the_emails_from_the_Inbox(String arg1) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;

	try {

	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer deletes all the emails from the Inbox.");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    EmailVerificationLibrary.deletingallMailsinInbox(browser, step);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @Then("^\"([^\"]*)\" Customer opens the required Email:$")
    public void Customer_opens_the_required_Email(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData(arg2);
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer opens the required Email:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    EmailVerificationLibrary.openingofAnEmail(browser, step, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @And("^\"([^\"]*)\" Customer Verifies the Loylaty Info from Email:$")
    public void Customer_Verifies_the_Loylaty_Info_from_Email(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData(arg2);
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer Verifies the Loylaty Info from Email:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    EmailVerificationLibrary.verifiesLoyaltyInfoinEmail(browser, step, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @And("^\"([^\"]*)\" Customer Archive the required Email:$")
    public void Customer_Archive_the_required_Email(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData(arg2);
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer Archive the required Email:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    EmailVerificationLibrary.archiveanEmail(browser, step, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @And("^\"([^\"]*)\" Customer validates the information on the Refill Reminders Email:$")
    public void Customer_validates_the_information_on_the_Refill_Reminders_Email(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData(arg2);
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer validates the information on the Refill Reminders Email:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    EmailVerificationLibrary.verifyInfoOnReplytoRefillEmail(browser, step, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @Then("^\"([^\"]*)\" Customer retrieves Username or changes Password from the required email:$")
    public void Customer_retrieves_Username_or_changes_Password_from_the_required_email(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData(arg2);
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer retrieves Username or changes Password from the required email:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    EmailVerificationLibrary.retrieveUserNameorChangePassword(browser, step, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @And("^\"([^\"]*)\" Customer Verifies the Express Order info from Email:$")
    public void Customer_Verifies_the_Express_Order_Info_from_Email(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData(arg2);
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer Verifies the Express Order info from Email:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    EmailVerificationLibrary.ExpressRxEmailVerification(browser, step, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @And("^\"([^\"]*)\" Customer Verifies the Fullfillment Order Complete Email:$")
    public void Customer_Verifies_the_Fullfillment_Order_Complete_Email(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData(arg2);
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer Verifies the Fullfillment Order Complete Email:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    EmailVerificationLibrary.verifyFulfillmentOrderEmail(browser, step, scenario, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @And("^\"([^\"]*)\" Customer Verifies the Instore Online Order confirmation email:$")
    public void Customer_Verifies_the_Instore_Online_Order_confirmation_email(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData(arg2);
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer Verifies the Instore Online Order confirmation email:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    EmailVerificationLibrary.verifyInstoreConfrimationEmail(browser, step, scenario, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @And("^\"([^\"]*)\" Customer Verifies Post Order Survey email:$")
    public void Customer_Verifies_Post_Order_Survey_email(String arg1) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	try {
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer Verifies Post Order Survey email:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    EmailVerificationLibrary.verifyPostSurveyOrderEmail(browser, step, scenario);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }

    @And("^\"([^\"]*)\" Customer verifies Order Cancellation email:$")
    public void Customer_verifies_Order_Cancellation_email(String arg1, DataTable arg2) {
	EventFiringWebDriver browser = null;
	ScenarioBean scenario = null;
	StepBean step = null;
	Map<String, List<String>> dataMap = null;
	try {
	    dataMap = CommonLibrary.getHorizontalData(arg2);
	    scenario = CommonLibrary.getScenario(arg1);
	    step = new StepBean();
	    step.setStepName("Customer verifies Order Cancellation email:");
	    scenario.getStepBeans().add(step);
	    browser = CommonLibrary.getBrowser(Thread.currentThread().getId());
	    EmailVerificationLibrary.verifyOrderCancellationEmail(browser, step, scenario, dataMap);
	} catch (Exception e) {
	    CommonLibrary.LogIt(e, null, step);
	} finally {
	    CommonLibrary.ReportIt(browser, scenario, step);
	}
    }
}
