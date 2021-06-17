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
	// Load the Global Variable "cards_Map_ID" (Will contain the title and id of all cards on the page)
	def getCardMap() {
		def retCode
		if (WebUI.verifyElementPresent(findTestObject('Page_Main/card_Header_Parmed', [('number') : 1]), 1, FailureHandling.OPTIONAL)) {
			//			cardExists = true
			WebDriver driver = DriverFactory.getWebDriver()
			def x_path = "//*[@class = 'sc-bdnxRM jvCTkj jss31']"
			List<WebElement> cards =  driver.findElements(By.xpath(x_path))

			for (card in cards) {
				def id = card.getAttribute('id')
				def text = card.getText()
				List myText = text.split( '\n' )
				def title = myText[0]
				GlobalVariable.cards_Map_ID.putAt(title, id)
			}
			retCode = true

		} else {
			println('ERROR: Failed to find the first resource card')
			retCode = false
		}
		return retCode
	}


	@Keyword
	// Get the ID of any cards containing the text parameter in the title of the card
	def getCardIDsByTitle(text) {

		def cardIDs = []
		GlobalVariable.cards_Map_ID.each{ key, value ->
			if (key.contains(text)) {
				cardIDs.add(value)
			}
		}
		return cardIDs
	}

	@Keyword
	// Get the ID of any cards containing the text parameter in the title of the card
	def static getCardTitleByID(cardID) {

		def title = GlobalVariable.cards_Map_ID.find{ it.value == cardID }?.key
		return title
	}

}