package unfoldingWord_Keywords
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
//import internal.GlobalVariable as GlobalVariable



class Resources_Layout {

	@Keyword
	// Load the Global Variable "cards_Map_Current" (Will contain the number and title of all cards on the page)
	def getCardMap() {
		def retCode
		if (WebUI.verifyElementPresent(findTestObject('Page_Main/cards_Header_Parmed', [('number') : 1]), 1, FailureHandling.OPTIONAL)) {
			//			cardExists = true
			def n = 1
			while (WebUI.verifyElementPresent(findTestObject('Page_Main/cards_Header_Parmed', [('number') : n]), 1, FailureHandling.OPTIONAL)) {
				def title = (WebUI.getText(findTestObject('Page_Main/cards_Header_Parmed', [('number') : n]), FailureHandling.OPTIONAL))
				GlobalVariable.cards_Map_Current.putAt(n, title)
				//				println(n + ' : ' + title)
				retCode = true
				n ++
			}
			retCode = n - 1
		} else {
			println('ERROR: Failed to find the first resource card')
			retCode = 0
		}
		return retCode
	}

}