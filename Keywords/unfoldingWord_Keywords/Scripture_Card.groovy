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
//import org.openqa.selenium.interactions.Actions as Actions

import org.openqa.selenium.Keys as Keys
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.io.File as File

import internal.GlobalVariable

public class Scripture_Card {
	// RENAME TO SIMPLY "Scripture"

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

	def testForHighlighting(def cardID, List words, List occurrences, write = false) {

		def now = new Date()
		def myFile = cardID
		//nFormat = ('MMddyyhhmmss')
		def fName = (((('highlights_' + myFile) + '-') + now.format('MMddyyhhmmss')) + '.txt')
		String logsDir = '/Users/' + GlobalVariable.pcUser + GlobalVariable.logsDir
		def oFile = new File(logsDir + '/' + fName)

		List myWords
		List myHighlights
		List myElements
		def msg

		(myWords, myHighlights, myElements) = getScriptureWordsandHighlights(cardID)

		def title = Resources_Layout.getCardTitleByID(cardID)

		def ol = testForOriginalLanguage(cardID)

		if (write) {
			oFile.append(title)
			oFile.append(myWords)
			oFile.append(myHighlights)
		}

		if (myWords.size() > 0 && myHighlights.size() > 0) {

			List shouldBeHighlighted = []
			def i = 0
			//	for (def word in words) {	// Words that should be highlighted
			words.each { word ->
				if (!ol) {
					word = word.toLowerCase()
				}
				List indexes = myWords.findIndexValues {
					it == word
				}
				//			println(word + ':' + indexes)
				Integer occurrence = occurrences[i]
				//			println(occurrence)
				Integer index = (indexes[occurrence-1])	// Occurrence number converted to array index
				//			println(index)
				shouldBeHighlighted.add(index)
				i ++
			}
			//		println(shouldBeHighlighted)

			def errorCount = 0
			i = 0
			def ii
			//	for (word in myWords) {
			myWords.each { word ->
				Boolean errFlag = false
				ii = i + 1
				def highlight = Boolean.valueOf(myHighlights[i])
				if(i in shouldBeHighlighted) {
					if (highlight == false) {
						msg = 'Word number ' + ii + ', "' + word + '" should be hightlighted in ' + title + ' but it is not.'
						errorCount ++
						errFlag = true
					} else {
						msg = 'Word number ' + ii + ', "' + word + '" is correctly hightlighted in ' + title + '.'
					}
				} else {
					if (highlight == true) {
						msg = 'Word number ' + ii + ', "' + word + '" should not be hightlighted in ' + title + ' but it is.'
						errorCount ++
						errFlag = true
					} else {
						msg = 'Word number ' + ii + ', "' + word + '" is correctly not hightlighted in ' + title + '.'
					}
				}

				println(msg)
				if (write) {
					oFile.append(msg)
				}

				if (errFlag) {
					SendMessage.SendFailMessage(msg)
				}

				i ++
			}
		} else {
			msg = '=======> ERROR:  (Issue 236) Could not check for highlights because no word spans were found in the ' + title
			println(msg)
			if (write) {
				oFile.append(msg)
			}
			SendMessage.SendFailMessage(msg)
		}
	}


	@Keyword

	// Returns arrays containing all of the words, the words' highlight statuses, and selenium elements of the text on a scripture card
	// Prerequisites:
	// CustomKeywords.'unfoldingWord_Keywords.Resources_Layout.getCardMap'() to populate the map of cards on the page

	// Input parameter 'cardID' can either be the number of the scripture card or unique partial text of the scripture card title (e.g. 'Leteral Text')
	def getScriptureWordsandHighlights(def cardID) {

		def msg
		def x_path // This is the xpath of the (parent) span that contains the (child) spans of the individual words in the scripture text
		x_path = "//div[@id='${cardID}']/div[3]/div/span/span[2]"
		println('Retrieving text from ' + cardID)

		def words = []
		List highlights = []
		//	List<WebElement>  = []
		List<WebElement> myElements = []

		WebDriver driver = DriverFactory.getWebDriver()
		//		Actions actions = new Actions(driver)

		// verse is the parent span
		WebElement verse =  driver.findElement(By.xpath(x_path))
		println(verse.getText() + ' is the text on ' + cardID)

		// elements is an array of the child spans
		List<WebElement> elements = verse.findElements(By.tagName('span'))
		println(elements.size() + ' elements were found on ' + cardID)



		def lastWord = ''

		def ol = testForOriginalLanguage(cardID)

		if (ol) {
			println('Original Language card')
			for (element in elements) {
				def word = element.text.replaceAll("\\p{P}+", "")
				if(word.length() >= 1) { // && i < elements.size()/2 ) {
					def highlighted = element.getAttribute('data-testselected')
					//					def hBool = Boolean.valueOf(highlighted)
					if (highlighted != null) {
						words.add(word)
						highlights.add(highlighted)
					}
				}
			}

		} else {
			println('Not an original language card')
			for (element in elements) {
				def word = element.text.replaceAll("\\p{P}+", "")
				if(word.length() >= 1 ) {
					words.add(word.toLowerCase())
					highlights.add(element.getAttribute('data-testselected'))
				}
			}
		}

		return [
			words,
			highlights,
			myElements
		]
	}


	@Keyword

	// Returns true if the selected scripture pane is an original language, else returns false
	def testForOriginalLanguage(def cardID) {
		def retCode
		def myKey = GlobalVariable.cards_Map_ID.find{ it.value == cardID }?.key
		if (myKey.contains('Greek') || myKey.contains('Hebrew')) {
			retCode = true
		} else {
			retCode = false
		}

		return retCode
	}


}
