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

public class Work_with_Settings_Card {
	@Keyword
	// Returns the font size setting from the open settings card, or 'false' if the font size element could not be found
	def getFontSize(font) {
		println('Getting font size')

		def retCode = false
		println('verifying element present Card_Settings/span_Font_Size-' + font)
		if (WebUI.verifyElementPresent(findTestObject('Card_Settings/span_Font_Size-' + font), 1, FailureHandling.OPTIONAL)) {

			println('getting aria-valuenow from Card_Settings/span_Font_Size-' + font)
			def pctStr = WebUI.getAttribute(findTestObject('Card_Settings/span_Font_Size-' + font), "aria-valuenow")

			retCode = Integer.parseInt(pctStr)
		}

		return retCode
	}

	@Keyword
	// Sets the font size to the specified value (%) on the open settings card. Return 'false' if an invalid value was specified.
	def setFontSize(int value, font) {
		println('Setting font size to ' + value)

		def retCode = false

		def minStr = WebUI.getAttribute(findTestObject('Card_Settings/span_Font_Size-' + font), "aria-valuemin")

		def min = Integer.parseInt(minStr)

		def maxStr = WebUI.getAttribute(findTestObject('Card_Settings/span_Font_Size-' + font), "aria-valuemax")

		def max = Integer.parseInt(maxStr)

		if (min <= value && value <= max) {

			def sliderWidth = WebUI.getElementWidth(findTestObject('Card_Settings/slider_Font_Size-' + font))

			def diff = (value - min)/100

			int offset = sliderWidth * (diff - 0.5)

			WebUI.clickOffset(findTestObject('Card_Settings/slider_Font_Size-' + font), offset, 0)

			retCode = true

		} else {

			retCode = false
		}

		return retCode

	}

	@Keyword
	// Returns a list of columns on the open settings card.
	// Also return a list of states and map of [column:state] if 'states' is passed in
	def getColumnsList(def option = '') {

		option = option.toLowerCase()

		def getStates
		def msg = ''
		if (option.contains('state')) {
			getStates = true
			msg = ' including states'
		}

		println('Getting columns list' + msg)

		def xPath = '/html/body/div[2]/div[3]/div/div/div[3]/div[3]/div/div'

		WebDriver driver = DriverFactory.getWebDriver()

		// Get the entire columns div
		def WebElement Paragraph = driver.findElement(By.xpath(xPath))

		// Get the individual span elements
		List elements = Paragraph.findElements(By.tagName('span'))

		def columnsMap = [:]
		def columns = []
		def states = []
		elements.each {
			def name = it.getText()
			if (name.length() > 1 ) {
				columns.add(name)
				if (getStates) {
					def checkbox = driver.findElement(By.name(name))
					def state = checkbox.isSelected()
					states.add(state)
					columnsMap.put(name, state)
				}
			}
		}
		return [columns, states, columnsMap]

	}

	@Keyword
	// Sets checkbox states according to the passed in map
	def setColumnStates(def columnsMap) {

		println('Setting columns states per map')

		def xPath = '/html/body/div[2]/div[3]/div/div/div[3]/div[3]/div/div'

		WebDriver driver = DriverFactory.getWebDriver()

		// Get the entire columns div
		def WebElement Paragraph = driver.findElement(By.xpath(xPath))

		// Get the individual span elements
		List elements = Paragraph.findElements(By.tagName('span'))

		columnsMap.each { name, value ->
			def checkbox = driver.findElement(By.name(name))
			def state = checkbox.isSelected()
			if (state != value) {
				checkbox.click()
			}
		}
		println('states are set')

	}



}