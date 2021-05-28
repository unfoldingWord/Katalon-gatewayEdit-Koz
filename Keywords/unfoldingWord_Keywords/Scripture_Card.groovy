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

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

import internal.GlobalVariable

public class Scripture_Card {
	@Keyword
	// Get the text and reference for the scripture card whose header contains the title specified
	def getScriptureVerse(title) {
		def retCode = false
		GlobalVariable.cards_Map_Current.each { key, val ->
			if (val.contains(title)) {
				def verse = WebUI.getText(findTestObject('Card_Scripture/text_Scripture_Verse_Parmed', [('scripture_card_number') : key]))
				def reference = WebUI.getText(findTestObject('Card_Scripture/text_Scripture_Verse_Reference_Parmed', [('scripture_card_number') : key]))
				def text = verse.substring(reference.length(),verse.length())
				retCode = [reference.trim(), text]
			}
		}
		return retCode
	}

	@Keyword

	// Returns arrays containing all of the words and the words' highlight statuses of the text on a scripture card
	// Prerequisites:
	// CustomKeywords.'unfoldingWord_Keywords.Resources_Layout.getCardMap'() to populate the map of cards on the page

	// Input parameter 'cardID' can either be the number of the scripture card or unique partial text of the scripture card title (e.g. 'Leteral Text')
	def getScriptureWordsandHighlights(cardID) {

		// Get card number if input parameter is a string
		def cardNumber
		if (cardID instanceof String) {
			cardNumber = GlobalVariable.cards_Map_Current.find { it.value.contains('Literal Text') }?.key
		} else {
			cardNumber = cardID
		}

		// This is the xpath of the (parent) span that contains the (child) spans of the individual words in the scripture text
		def x_path = "/html/body/div[1]/div/main/div/div/div[${cardNumber}]/div/div/div[2]/div/span/span[2]"
		def words = []
		def highlights = []

		WebDriver driver = DriverFactory.getWebDriver()
		// verse is the parent span
		WebElement verse =  driver.findElement(By.xpath(x_path))
		// elements is an array of the child spans
		List<WebElement> elements = verse.findElements(By.tagName('span'))

		for (element in elements) {
			def word = element.text
			if(word.length() >= 1) {
				words.add(word)
				highlights.add(element.getAttribute('data-testselected'))
			}
		}
		return [words, highlights]
	}

}
