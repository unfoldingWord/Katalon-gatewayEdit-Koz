package x_Archive

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
import org.openqa.selenium.interactions.Actions as Actions


import org.openqa.selenium.Keys as Keys


import internal.GlobalVariable

public class Scripture_Card {
	// RENAME TO SIMPLY "Scripture"
	// ADD getScriptureReference()
	// ADD setScriptureReference()

	@Keyword
	// Get the scripture reference from the navigator bar
	def getScriptureReference() {
		def book = WebUI.getAttribute(findTestObject('Blue_Banners/input_Book'),'value')
		def chapter = WebUI.getAttribute(findTestObject('Blue_Banners/input_Chapter'),'value')
		def verse = WebUI.getAttribute(findTestObject('Blue_Banners/input_Verse'),'value')
		def reference = book + ' ' + chapter + ':' + verse

		return [
			book,
			chapter,
			verse,
			reference
		]
	}


	@Keyword
	// Set the scripture reference in the navigator bar by using the comboboxes
	// If the book parameter is actually a full reference, use that. Otherwise use book, chapter, verse
	def setScriptureReference(def myBook, def chapter = '', def verse = '') {
		def book
		if (myBook.length() > 3) {
			book = myBook.substring(0,3)
			def space = myBook.indexOf(' ')
			def colon = myBook.indexOf(':')
			if (colon > 0) {
				chapter = myBook.substring(space + 1, colon)
				verse = myBook.substring(colon + 1, myBook.length())
			} else {
				chapter = myBook.substring(space + 1, myBook.length())
				verse = ''
			}
		} else {
			book = myBook
		}

		if (book != '' && book != null) {
			WebUI.click(findTestObject('Blue_Banners/comboBoxArrow_Book'))
			WebUI.click(findTestObject('Blue_Banners/list_Book_Parmed', [('book'):book]))
		}

		if (chapter != '' && chapter != null) {
			WebUI.click(findTestObject('Blue_Banners/comboBoxArrow_Chapter'))
			WebUI.click(findTestObject('Blue_Banners/list_Chapter_Parmed', [('chapter'):chapter]))
		}

		if (verse != '' && verse != null) {
			WebUI.click(findTestObject('Blue_Banners/comboBoxArrow_Verse'))
			WebUI.click(findTestObject('Blue_Banners/list_Verse_Parmed', [('verse'):verse]))
		}
	}


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
	def getScriptureWordsandHighlights(def cardID) {

		def msg
		// Get card number if input parameter is a string
		def x_path // This is the xpath of the (parent) span that contains the (child) spans of the individual words in the scripture text
		if (cardID instanceof String) { // cardID is the id attribute of the scripture card
			x_path = "//div[@id='${cardID}']/div[3]/div/span/span[2]"
			msg = 'card id ' + cardID
		} else { // cardID is the card number of the scripture card
			int cardNumber = cardID
			x_path = "/html/body/div[1]/div/main/div/div/div[1]/div/div/div[3]/div/span/span[2]"
			msg = 'card number ' + cardID
		}
		println('Retrieving text from ' + msg)

		def words = []
		def highlights = []
		//	List<WebElement>  = []
		List<WebElement> myElements = []

		WebDriver driver = DriverFactory.getWebDriver()
		Actions actions = new Actions(driver)
		// verse is the parent span
		WebElement verse =  driver.findElement(By.xpath(x_path))
		println(verse.getText() + ' is the text on ' + msg)
		// elements is an array of the child spans
		//		List<WebElement> elements = verse.findElements(By.tagName('span'))
		List<WebElement> elements = verse.findElements(By.tagName('span'))
		println(elements.size() + ' elements were found on ' + msg)
		def lastWord = ''
		for (element in elements) {
			def word = element.text
			if(word.length() >= 1 && word != lastWord) {
				words.add(word)
				highlights.add(element.getAttribute('data-testselected'))
				//				TestObject nextWord = WebUI.convertWebElementToTestObject(element)
				//				highlights.add(WebUI.getAttribute(nextWord), 'data-testselected')
				myElements.add(element)
				lastWord = word
			}
		}
		return [
			words,
			highlights,
			myElements
		]
	}

}
