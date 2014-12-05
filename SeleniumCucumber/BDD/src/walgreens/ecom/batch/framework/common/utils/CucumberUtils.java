package walgreens.ecom.batch.framework.common.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;

import walgreens.ecom.batch.framework.EcommTestRunner;

public class CucumberUtils {

    public static String[] buildCommand(String tags, String cucumberReportPath, Configuration config) {
	String[] command = new String[7];
	String runMode = null;
	String stepDefPath = null;
	String featureFilePath = null;
	String format = null;

	if (StringUtils.isNotBlank(tags)) {
	    runMode = EcommTestRunner.runMode;
	    System.out.println("CucumberUtils  runMode:::"+runMode);
	    stepDefPath = config.getString("stepDefPath");
	    System.out.println("CucumberUtils stepDefPath "+stepDefPath);
	    //walgreens\\ecom\\batch\\automation\\stepdefinitions
	    featureFilePath = config.getString("featureFilePath") + File.separator + "features" + File.separator + runMode;
	    System.out.println("CucumberUtils featureFilePath "+featureFilePath);
//src\\walgreens\\ecom\\batch\\automation/feature/functional
	   format = "html:" + cucumberReportPath;
	    //format = "html:";
	    System.out.println("CucumberUtils format::"+format);

	    if (EcommTestRunner.enableCucumberReport.equalsIgnoreCase("yes")) {
		command[0] = "--glue";
		command[1] = stepDefPath;
		command[2] = featureFilePath;
		command[3] = "--format";
		command[4] = format;
		command[5] = "--tags";
		command[6] = tags;
	    } else {
		command[0] = "--glue";
		command[1] = stepDefPath;
		command[2] = featureFilePath;
		command[3] = "--format";
		command[4] = "html:";
		command[5] = "--tags";
		command[6] = tags;
	    }
	} else {
	    System.out.println("Cannot run the application: Flow Type is not specified");
	    return null;
	}
	return command;
    }

    public static String buildReportPath(String reportPath, String runEnvironment, String runMode, String browserName, String tags, Configuration config) throws Exception {
	String finalReportPath = reportPath + File.separator + runEnvironment + File.separator + runMode + File.separator + EcommTestRunner.currentDateTime + File.separator + tags + File.separator
		+ browserName;
	//report/cucumber/staginf/functional/date/scenarios/firefox
	System.out.println("finalReportPath:::"+finalReportPath);
	File dir = new File(finalReportPath);
	boolean exists = dir.exists();

	if (!exists) {
	    exists = dir.mkdirs();
	}

	if (!exists) {
	    finalReportPath = null;
	}
	return finalReportPath;
    }

    public static List<String> getTagsList(String tags) throws Exception {
	String[] tagsArray = null;
	if (tags.contains("~")) {
	    tagsArray = StringUtils.split(tags, "~");
	} else {
	    tagsArray = StringUtils.split(tags, ",");
	}
	List<String> tagsList = Arrays.asList(tagsArray);
	System.out.println("tagsList:::"+tagsList);
	return tagsList;
    }

    public static String getCurrentDateTime() throws Exception {
	Calendar cal = Calendar.getInstance();
	Date currentDate = cal.getTime();
	// RAM (1/14/13) Added  AM/PM to Time
	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss-a");
	String currentDateTime = sdf.format(currentDate);
	return currentDateTime;
    }

    public static String getCurrentDate() throws Exception {
	Calendar cal = Calendar.getInstance();
	Date currentDate = cal.getTime();
	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
	String currentDateTime = sdf.format(currentDate);
	return currentDateTime;
    }

    @SuppressWarnings("unchecked")
    public static List<String>[] splitTheList(List<String> data, int pNumUnits) {
    	System.out.println("@@@splitTheList@@@data.size()::"+data.size());
    	System.out.println("@@@splitTheList@@@pNumUnits::"+pNumUnits);
	if (pNumUnits >= data.size()) {
	    return new List[] { 
	    		data
	    		};
	}

	List<String>[] results = null;
	Iterator<String> iter = data.iterator();

	if ((data.size() % pNumUnits) > 0) {
	    results = new List[(data.size() / pNumUnits) + 1];
	    System.out.println("@@@splitTheList@@@@results:::"+results);
	} else {
	    results = new List[data.size() / pNumUnits];
	}

	for (int i = 0; i < results.length; i++) {
		System.out.println("@@@results.length::"+results.length);
	    results[i] = new ArrayList<String>();
	}

	int count = 0;

	while (iter.hasNext())
	    results[count++ / pNumUnits].add(iter.next());

	return results;
    }

}
