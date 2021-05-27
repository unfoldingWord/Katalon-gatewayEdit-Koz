package unfoldingWordKeywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import internal.GlobalVariable

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.Keys as Keys
import groovy.io.FileType as FileType
import org.apache.commons.io.FileUtils as FileUtils

public class Browsers {
	@Keyword
	def openNewTab(def browser = '') {
		println('Opening new browser tab')

		def myBrowser
		if (browser == '') {
			myBrowser = GetTestingConfig.getBrowserAndVersion()
		} else {
			myBrowser = browser
		}

		println('Browser is ' + myBrowser)

		if (myBrowser.contains('chrome')) {
			println('execute js')
			WebUI.executeJavaScript('window.open();', [])
			
		} else if (GlobalVariable.systemOS.contains('Windows')) {
			println('sending control t')
			WebUI.sendKeys(findTestObject(null), Keys.chord(Keys.CONTROL, 't'))
//		} else {
//			println('sending command t')
//			WebUI.sendKeys(null, Keys.chord(Keys.COMMAND, 't'))
//			WebDriver driver = new FirefoxDriver()
//			driver.sendKeys(Keys.CONTROL +"t")
		} else {
//		if (1 == 2) {
			if (GlobalVariable.systemOS.contains('Windows')) {
				System.setProperty("webdriver.gecko.driver","C:\\Users\\cckoz\\Katalon\\Katalon_Studio_Windows_64-7.9.0\\configuration\\resources\\drivers\\firefox_win64\\geckodriver.exe")
			} else {
				System.setProperty("webdriver.gecko.driver","/Applications/Katalon Studio.app/Contents/Eclipse/configuration/resources/drivers/firefox_mac/geckodriver");
			}
			WebDriver driver = new FirefoxDriver()
			DriverFactory.changeWebDriver(driver)

		}

	}
}