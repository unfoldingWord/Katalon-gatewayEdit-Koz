package unfoldingWord_Keywords

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

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

public class SAVED_Work_with_Settings_Card {
	@Keyword
	def getFontSize() {
		println('Getting font size')

		def retValue = false
		if (WebUI.verifyElementPresent(findTestObject('Card_Settings/span_Font_Size'), 1, FailureHandling.OPTIONAL)) {

			def pctStr = WebUI.getAttribute(findTestObject('Card_Settings/span_Font_Size'), "aria-valuenow")

			retValue = Integer.parseInt(pctStr)
		}

		return retValue
	}

	@Keyword
	def setFontSize(int value) {
		println('Setting font size to ' + value)

		def retValue = false

		def minStr = WebUI.getAttribute(findTestObject('Card_Settings/span_Font_Size'), "aria-valuemin")

		def min = Integer.parseInt(minStr)

		def maxStr = WebUI.getAttribute(findTestObject('Card_Settings/span_Font_Size'), "aria-valuemax")

		def max = Integer.parseInt(maxStr)

		def sliderWidth = WebUI.getElementWidth(findTestObject('Card_Settings/slider_Font_Size'))

		def diff = (value - min)/100

		int offset = sliderWidth * (diff - 0.5)

		WebUI.clickOffset(findTestObject('Card_Settings/slider_Font_Size'), offset, 0)
	}

	@Keyword
	def getColumnsList() {
		println('Getting columns list')

		def xPath = '/html/body/div[2]/div[3]/div/div/div[3]/div[3]/div/div'

		WebDriver driver = DriverFactory.getWebDriver()

		// Get the entire validator message element
		def WebElement Paragraph = driver.findElement(By.xpath(xPath))

		// Get the individual span elements
		List elements = Paragraph.findElements(By.tagName('span'))

		def columnsMap = [:]
		def columns = []
		//		def n = 0
		elements.each {
			def name = it.getText()
			if (name.length() > 1 ) { // && name != 'Show Columns') {
				columns.add(name)
				//				columnsMap.put(n, name)
				//				n ++
			}
		}
		return columns

	}

	@Keyword
	def getColumnStates() {
		println('Getting column states')

	}




}