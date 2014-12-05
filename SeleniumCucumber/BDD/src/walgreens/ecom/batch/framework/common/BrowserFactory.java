/* 
 ****************************************************************
 * @Purpose       - Driver Class to set the different web drivers
 * @author        - Imran/Ram
 * @Created       - 09 Nov 2011
 * @Modified By   -  
 * @Modified Date - 
 ****************************************************************
 */

package walgreens.ecom.batch.framework.common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.iphone.IPhoneDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import walgreens.ecom.batch.automation.library.common.CommonLibrary;
import walgreens.ecom.batch.framework.EcommTestRunner;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserFactory {

	public static final String FIRE_FOX = "fireFox";
	public static final String IOS_DEVICE_BROWSER = "iosDeviceBrowser";
	public static final String INTERNET_EXPLORER = "internetExplorer";
	public static final String CHROME = "chrome";
	public static Map<String, EventFiringWebDriver> fireFoxBrowserMap = null;
	public static Map<String, String> browserTypeMap = null;
	public static Map<String, EventFiringWebDriver> ieBrowserMap = null;
	public static Map<String, EventFiringWebDriver> chromeBrowserMap = null;
	public static Map<String, EventFiringWebDriver> iosDeviceBrowserMap = null;

	// RAM 13-JAN-14 - User agent related strings
	public static String target_device = null;
	public static String useragent_host_browser = null;
	public static String device_configuration = null;
	public static String resolution_x = null;
	public static String resolution_y = null;
	public static String usergent_string = null;
	public static String breakpoint_category = null;
	public static String[] arr_device_configuration = null;
	public static String browserVersion = null;

	public static EventFiringWebDriver getBrowser(long threadId) {
		EventFiringWebDriver eventFiringWebDriver = null;
		String tagThreadId = Long.toString(threadId);
		try {
			System.out.println("@@getBrowsergetBrowsergetBrowsergetBrowsergetBrowsergetBrowser@@@");
			if (fireFoxBrowserMap != null && fireFoxBrowserMap.get(tagThreadId) != null) {
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$getBrowser");
				eventFiringWebDriver = (EventFiringWebDriver) fireFoxBrowserMap.get(tagThreadId);
			} else if (ieBrowserMap != null && ieBrowserMap.get(tagThreadId) != null) {
				eventFiringWebDriver = (EventFiringWebDriver) ieBrowserMap.get(tagThreadId);
			} else if (chromeBrowserMap != null && chromeBrowserMap.get(tagThreadId) != null) {
				eventFiringWebDriver = (EventFiringWebDriver) chromeBrowserMap.get(tagThreadId);
			}else if (iosDeviceBrowserMap != null && iosDeviceBrowserMap.get(tagThreadId) != null) {
				eventFiringWebDriver = (EventFiringWebDriver) iosDeviceBrowserMap.get(tagThreadId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eventFiringWebDriver;
	}

	public static EventFiringWebDriver getCSCBrowser(long threadId) {
		EventFiringWebDriver eventFiringWebDriver = null;
		String pThreadid = Long.toString(threadId);
		String cscThreadId = threadId + "-CSC";
		try {
			if (BrowserFactory.FIRE_FOX.equals(browserTypeMap.get(pThreadid))) {
				eventFiringWebDriver = (EventFiringWebDriver) fireFoxBrowserMap.get(cscThreadId);
			} else if (BrowserFactory.INTERNET_EXPLORER.equals(browserTypeMap.get(pThreadid))) {
				eventFiringWebDriver = (EventFiringWebDriver) ieBrowserMap.get(cscThreadId);
			} else if (BrowserFactory.IOS_DEVICE_BROWSER.equals(browserTypeMap.get(pThreadid))) {
				eventFiringWebDriver = (EventFiringWebDriver) iosDeviceBrowserMap.get(cscThreadId);
			}else if (BrowserFactory.CHROME.equals(browserTypeMap.get(pThreadid))) {
				eventFiringWebDriver = (EventFiringWebDriver) chromeBrowserMap.get(cscThreadId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eventFiringWebDriver;
	}

	public static EventFiringWebDriver getNewBrowser(long threadId) {
		EventFiringWebDriver eventFiringWebDriver = null;
		String tagThreadId = Long.toString(threadId);
		try {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~getNewBrowser"+tagThreadId);
			eventFiringWebDriver = getBrowser(threadId);
			if (eventFiringWebDriver != null) {
				return eventFiringWebDriver;
			}

			if (BrowserFactory.FIRE_FOX.equals(browserTypeMap.get(tagThreadId))) {
				if (fireFoxBrowserMap == null) {
					fireFoxBrowserMap = new HashMap<String, EventFiringWebDriver>();
				}

				// RAM - 11/15/13 - Pick the FF binary based on the installation
				// directory, if coming from VDI (Q: or V:) or C:
				if ((StringUtils.containsIgnoreCase(System.getProperty("user.dir"), "Q") || StringUtils.containsIgnoreCase(System.getProperty("user.dir"), "V"))
						&& EcommTestRunner.runfromVDI.equalsIgnoreCase("Yes")) {
					System.setProperty("webdriver.firefox.bin", System.getProperty("user.dir").split(":")[0] + ":" + EcommTestRunner.firefoxBinaryPath);
				}

				FirefoxProfile profile = new FirefoxProfile();
				profile.setEnableNativeEvents(true);

				if (EcommTestRunner.device.equalsIgnoreCase("DESKTOP")){
					WebDriver dummyDriver = new FirefoxDriver(profile);
					Capabilities caps = ((RemoteWebDriver) dummyDriver).getCapabilities();
					browserVersion = caps.getVersion();
					dummyDriver.quit();
				}

				// RAM 13-JAN-14 - Fetch the corresponding device details and
				// set the user agent string
				target_device = EcommTestRunner.device;
				useragent_host_browser = FIRE_FOX;
				device_configuration = CommonLibrary.getDeviceConfiguration(System.getProperty("user.dir") + "/config/automation/device_configuration", "device_configuration", target_device,
						useragent_host_browser);
//				arr_device_configuration = device_configuration.split("~");
//				if (target_device.equalsIgnoreCase("DESKTOP")) {
//					profile.setPreference("general.useragent.override", arr_device_configuration[0].replace("VERSION", browserVersion));
//				} else {
//					profile.setPreference("general.useragent.override", arr_device_configuration[0]);
//				}

				WebDriver webDriver = new FirefoxDriver();
				webDriver.manage().deleteAllCookies();

				// Initialize EventFiringWebdriver
				// ********************************

				eventFiringWebDriver = new EventFiringWebDriver(webDriver);
				eventFiringWebDriver.manage().timeouts().implicitlyWait(EcommTestRunner.config.getLong("timeOut"), TimeUnit.MILLISECONDS);
				eventFiringWebDriver.get(EcommTestRunner.config.getString("walgreensURL"));

				if (EcommTestRunner.device.equalsIgnoreCase("DESKTOP")) {
					if (EcommTestRunner.maximizeBrowser.equalsIgnoreCase("Yes"))
						eventFiringWebDriver.manage().window().maximize();
				} else {
					eventFiringWebDriver.manage().window().setSize(new Dimension(Integer.parseInt(arr_device_configuration[1]), Integer.parseInt(arr_device_configuration[2])));

				}
				fireFoxBrowserMap.put(tagThreadId, eventFiringWebDriver);
			} else if (BrowserFactory.INTERNET_EXPLORER.equals(browserTypeMap.get(tagThreadId))) {
				if (ieBrowserMap == null) {
					ieBrowserMap = new HashMap<String, EventFiringWebDriver>();
				}

				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + File.separator + "Drivers" + File.separator + "IEDriverServer.exe");
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("ignoreProtectedModeSettings", true);
				WebDriver webDriver = new InternetExplorerDriver(capabilities);
				webDriver.manage().deleteAllCookies();

				// Initialize EventFiringWebdriver
				// ********************************

				eventFiringWebDriver = new EventFiringWebDriver(webDriver);
				eventFiringWebDriver.manage().timeouts().implicitlyWait(EcommTestRunner.config.getLong("timeOut"), TimeUnit.MILLISECONDS);
				eventFiringWebDriver.get(EcommTestRunner.config.getString("walgreensURL"));
				if (EcommTestRunner.maximizeBrowser.equalsIgnoreCase("Yes")) {
					eventFiringWebDriver.manage().window().maximize();
				}
				ieBrowserMap.put(tagThreadId, eventFiringWebDriver);
			}
				else if (BrowserFactory.IOS_DEVICE_BROWSER.equals(browserTypeMap.get(tagThreadId))) {
					if (iosDeviceBrowserMap == null) {
						iosDeviceBrowserMap = new HashMap<String, EventFiringWebDriver>();
					}
					System.out.println("Entering into IWebdriver");
					WebDriver webDriver = new IPhoneDriver();
					
					// Initialize EventFiringWebdriver
					// ********************************

					eventFiringWebDriver = new EventFiringWebDriver(webDriver);
					eventFiringWebDriver.manage().timeouts().implicitlyWait(EcommTestRunner.config.getLong("timeOut"), TimeUnit.MILLISECONDS);
					eventFiringWebDriver.get(EcommTestRunner.config.getString("walgreensURL"));
					iosDeviceBrowserMap.put(tagThreadId, eventFiringWebDriver);

			} else if (BrowserFactory.CHROME.equals(browserTypeMap.get(tagThreadId))) {
				if (chromeBrowserMap == null) {
					chromeBrowserMap = new HashMap<String, EventFiringWebDriver>();
				}

				// RAM 01-03-2013 - Made changes for Chrome
				// *****************************************

				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/" + EcommTestRunner.driverPath + "chromedriver.exe");
				WebDriver webDriver = new ChromeDriver();
				webDriver.manage().deleteAllCookies();

				// Initialize EventFiringWebdriver
				// ********************************
				eventFiringWebDriver = new EventFiringWebDriver(webDriver);
				eventFiringWebDriver.manage().timeouts().implicitlyWait(EcommTestRunner.config.getLong("timeOut"), TimeUnit.MILLISECONDS);
				eventFiringWebDriver.get(EcommTestRunner.config.getString("walgreensURL"));
				if (EcommTestRunner.maximizeBrowser.equalsIgnoreCase("Yes")) {
					eventFiringWebDriver.manage().window().maximize();
				}
				chromeBrowserMap.put(tagThreadId, eventFiringWebDriver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eventFiringWebDriver;
	}

	public static void setBrowserType(long threadId, String browserType) {
		if (browserTypeMap == null) {
			browserTypeMap = new HashMap<String, String>();
		}
		browserTypeMap.put(Long.toString(threadId), browserType);
	}

	public static void setCSCBrowser(EventFiringWebDriver eventFiringWebDriver, long threadId) {
		String pThreadid = Long.toString(threadId);
		String cscThreadId = threadId + "-CSC";
		try {
			if (BrowserFactory.FIRE_FOX.equals(browserTypeMap.get(pThreadid))) {
				fireFoxBrowserMap.put(cscThreadId, eventFiringWebDriver);
			} else if (BrowserFactory.INTERNET_EXPLORER.equals(browserTypeMap.get(pThreadid))) {
				ieBrowserMap.put(cscThreadId, eventFiringWebDriver);
			} else if (BrowserFactory.CHROME.equals(browserTypeMap.get(pThreadid))) {
				chromeBrowserMap.put(cscThreadId, eventFiringWebDriver);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void unLoadBrowser(long threadId, String browserType) {
		System.out.println("unLoadBrowLoadBrowserunLoadBrowserunLoadBrowserunLoadBrowserunLoadBrowserunLoaserunLoadBrowser&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*(^$%$%$##^^^^^^^#$#$#$#$#$#$#$#$#$#$#$#$%");
		EventFiringWebDriver browser = null;
		String tagThreadId = Long.toString(threadId);
		try {
			if (BrowserFactory.FIRE_FOX.equals(browserType)) {
				if (fireFoxBrowserMap != null && fireFoxBrowserMap.get(tagThreadId) != null) {
					browser = fireFoxBrowserMap.get(tagThreadId);
					System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*(^$%$%$##^^^^^^^#$#$#$#$#$#$#$#$#$#$#$#$%");
					browser.quit();
				}
			}
			if (BrowserFactory.CHROME.equals(browserType)) {
				if (chromeBrowserMap != null && chromeBrowserMap.get(tagThreadId) != null) {
					browser = chromeBrowserMap.get(tagThreadId);
					browser.quit();
				}
			}
			if (BrowserFactory.IOS_DEVICE_BROWSER.equals(browserType)) {
				if (iosDeviceBrowserMap != null && iosDeviceBrowserMap.get(tagThreadId) != null) {
					browser = iosDeviceBrowserMap.get(tagThreadId);
					browser.quit();
				}
			}
			if (BrowserFactory.INTERNET_EXPLORER.equals(browserType)) {
				if (ieBrowserMap != null && ieBrowserMap.get(tagThreadId) != null) {
					browser = ieBrowserMap.get(tagThreadId);
					browser.quit();
				}
			}

		} catch (Exception e) {
			EcommTestRunner.LogIt(e, null, null);
		} finally {

			// FireFox
			System.out.println("finallyfinallyfinunLoadBrowserunLoadBrowserunLoadBrowserunLoadBrowserunLoadBrowserunLoadBrowserunLoadBrowserallyfinallyfinallyfinallyfinallyfinallyfinallyfinallyfinallyfinallyfinallyfinally");
			if (fireFoxBrowserMap != null && fireFoxBrowserMap.get(tagThreadId) != null) {
				System.out.println("fireFoxBrowserMap.put(tagThreadId, null);fireFoxBrowserMap.put(tagThreadId, null);fireFoxBrowserMap.put(tagThreadId, null);");
				fireFoxBrowserMap.put(tagThreadId, null);
			}

			// Chrome
			if (chromeBrowserMap != null && chromeBrowserMap.get(tagThreadId) != null) {
				chromeBrowserMap.put(tagThreadId, null);
			}

			// Internet Explorer
			if (ieBrowserMap != null && ieBrowserMap.get(tagThreadId) != null) {
				ieBrowserMap.put(tagThreadId, null);
			}
			if (iosDeviceBrowserMap != null && iosDeviceBrowserMap.get(tagThreadId) != null) {
				iosDeviceBrowserMap.put(tagThreadId, null);
			}
		}
	}

}
