package walgreens.ecom.batch.framework;

import java.util.List;
import org.apache.commons.configuration.Configuration;
import walgreens.ecom.batch.framework.common.BrowserFactory;
import walgreens.ecom.batch.framework.common.utils.CucumberUtils;
import cucumber.io.FileResourceLoader;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;

public class TestThreadRunner implements Runnable {

    Configuration config = null;
    String currentBrowserName = null;
    List<String> tagsList = null;

    /**
     * Constructor
     */
    public TestThreadRunner(List<String> pTagsList, Configuration pConfig, String runEnvironment, String runMode, String pCurrentBrowserName) {
	try {
	    currentBrowserName = pCurrentBrowserName;
	    this.tagsList = pTagsList;
	    this.config = pConfig;
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * After creating thread, this run method invoked by thread
     */
    public void run() {
	Runtime runtime = null;
	RuntimeOptions runtimeOptions = null;
	String[] command = null;
	String cucumberReportPath = null;
	try {
	    System.out.println("Scenarios List Size for Current Thread: " + this.tagsList.size());
	    for (int i = 0; i < this.tagsList.size(); i++) {
	    	//report/cucumber,staging,functional,firefox
	    	System.out.println("this.tagsList.get(i):::"+this.tagsList.get(i));
		cucumberReportPath = CucumberUtils.buildReportPath(config.getString("cucumberReportPath"), EcommTestRunner.runEnvironment, EcommTestRunner.runMode, currentBrowserName,
			this.tagsList.get(i), config);
		System.out.println("cucumberReportPath::"+cucumberReportPath);
		command = CucumberUtils.buildCommand(this.tagsList.get(i), cucumberReportPath, config);
		System.out.println("command:::;"+command);
		String strCommand = command[0] + " " + command[1] + " " + command[2] + " " + command[3] + " " + command[4] + " " + command[5] + " " + command[6];
		System.out.println("Executing the Cucumber Command: " + strCommand);
		//--glue walgreens\ecom\batch\automation\stepdefinitions src\walgreens\ecom\batch\automation\features\functional --format html: --tags @FULFILLMENT-DC-1
		BrowserFactory.setBrowserType(Thread.currentThread().getId(), currentBrowserName);
		runtimeOptions = new RuntimeOptions(command);
		runtime = new Runtime(new FileResourceLoader(), Thread.currentThread().getContextClassLoader(), runtimeOptions);
		runtime.writeStepdefsJson();
		System.out.println("runtime.exitStatus();runtime.exitStatus();runtime.exitStatus();"+runtime);
		runtime.run();
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&Exit********************************");
		runtime.exitStatus();
		
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    if ("yes".equalsIgnoreCase(EcommTestRunner.closeBrowser)) {
		System.out.println("Shutting Down: " + currentBrowserName + " with ThreadId: " + Thread.currentThread().getId());
		BrowserFactory.unLoadBrowser(Thread.currentThread().getId(), currentBrowserName);
	    }
	}
	// Thread.yield();
    }

}
