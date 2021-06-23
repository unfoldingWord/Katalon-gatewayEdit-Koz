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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.chrome.ChromeDriver


//import org.openqa.selenium.webdriver.common.desired_capabilities.DesiredCapabilities
//from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
//selenium.webdriver.common.desired_capabilities.DesiredCapabilities[source]

//WebUI.openBrowser('google.com')

//WebDriver driver = DriverFactory.getWebDriver()

System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
WebDriver driver = new ChromeDriver()

//driver.set_network_conditions(
//	{offline=false}) //,
//	latency=5,  //# additional latency (ms)
//	download_throughput=5 * 1024,  //# maximal throughput
//	upload_throughput=5 * 1024)  //# maximal throughput

//System.setProperty("webdriver.gecko.driver","/Applications/Katalon Studio.app/Contents/Eclipse/configuration/resources/drivers/firefox_mac/geckodriver");

//System.setProperty("webdriver.chrome.driver","/Applications/Katalon Studio.app/Contents/Eclipse/configuration/resources/drivers/chromedriver_mac/chromedriver")
//driver = new ChromeDriver();
driver.get("https://google.com");
driver.set_network_conditions(
//	offline=false) //,
"latency": 0, "throughput": 0, "offline": true)

//driver.set_network_conditions(offline=True)

//WebUI.openBrowser('google.com')

