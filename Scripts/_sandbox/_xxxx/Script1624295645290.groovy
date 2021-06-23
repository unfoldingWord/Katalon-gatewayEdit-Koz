import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

import org.openqa.selenium.remote.CommandExecutor
//import org.openqa.selenium.remote.Response

System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");

//ChromeDriver driver = new ChromeDriver();
//driver = new WebDriver();
WebDriver driver = DriverFactory.getWebDriver()

CommandExecutor executor = driver.getCommandExecutor();
		
//Set the conditions
Map<String, Object> map = new HashMap<String, Object>();
map.put("offline", false);
map.put("latency", 5);
map.put("download_throughput", 5000);
map.put("upload_throughput", 5000);

//Response response = executor.execute(new Command(driver.getSessionId(),"setNetworkConditions", ImmutableMap.of("network_conditions", ImmutableMap.copyOf(map))));

driver.get("http://google.com");