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
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

import javax.swing.*
import groovy.io.FileType as FileType

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

blue = 'rgba(56, 173, 223, 1)'

WebUI.callTestCase(findTestCase('Components/LogIn'), [:])

WebUI.delay(1)

reference = 'Titus 1:6'

CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.setScriptureReference'(reference)

msg = ('>>>>> Testing tW for ' + reference)
CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendInfoMessage'(msg)

// Verify highlights are as expected, and tW Article is updated, when clicking on tWL rows
for (def row : (1..5)) {
	println(row)
	wordOL = WebUI.getText(findTestObject('Object Repository/Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 2]))
	msg = 'Testing clicking on ' + wordOL + ' in tWL card'
	CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendInfoMessage'(msg)
	
	WebUI.click(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 2]))
	if(!WebUI.getCSSValue(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 2]), 'color') == blue) {
		msg = 'ERROR: Word is not highlighted in blue on the tWL card'
		CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
	}
	
	wordTWL = WebUI.getText(findTestObject('Object Repository/Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 1]))
	wordTWA = WebUI.getText(findTestObject('Object Repository/Card_tW_Article/text_tWArticle_Title'))
	if (!wordTWA.contains(wordTWL)) {
		msg = ('ERROR: The the translation word on the tWL card is "' + wordTWL + '", but it is not found in the title on the tWA card, "' + wordTWA + '".')
		CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
	}
	
	check = WebUI.getText(findTestObject('Object Repository/Card_tW_Article/text_tWA_N-of-N'))
	if (check != row + ' of 5') {
		msg = ('ERROR: The navigation text on the tWA card is "' + check + '", but the row on the tWL card is "' + row + '".')
		CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
	}
	
	if (row == 1) {
		cardID = 'scripture_card_0'
		words = ['blameless']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['ἀνέγκλητος']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['someone', 'whom', 'no', 'one', 'can', 'criticize']
		occurrences = [1,1,1,1,1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
	} else if (row == 2) {
		cardID = 'scripture_card_0'
		words = ['children']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['τέκνα']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['his', 'children', 'his', 'children']
		occurrences = [1,1,2,2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
	} else if (row == 3) {
		cardID = 'scripture_card_0'
		words = ['faithful']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['πιστά']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['must', 'trust', 'in', 'God']
		occurrences = [3,1,1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
	
	} else if (row == 4) {
		cardID = 'scripture_card_0'
		words = ['accused']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['κατηγορίᾳ']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['and', 'people', 'must', 'not', 'consider']
		occurrences = [1,1,4,1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
	
	} else if (row == 5) {
		cardID = 'scripture_card_0'
		words = ['rebellion']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['ἀνυπότακτα']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['disobedient']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
	}
			
}

reference = 'Mat 1:2'

CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.setScriptureReference'(reference)

msg = ('>>>>> Testing tW for ' + reference)
CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendInfoMessage'(msg)

// Verify highlights are as expected, and tW Article is updated, when clicking on tWL rows
for (def row : (1..10)) {
	println(row)
	wordOL = WebUI.getText(findTestObject('Object Repository/Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 2]))
	msg = 'Testing clicking on ' + wordOL + ' in tWL card'
	CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendInfoMessage'(msg)
	
	WebUI.click(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 2]))
	if(!WebUI.getCSSValue(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 2]), 'color') == blue) {
		msg = 'ERROR: Word is not highlighted in blue on the tWL card'
		CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
	}
	
	wordTWL = WebUI.getText(findTestObject('Object Repository/Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 1]))
	wordTWA = WebUI.getText(findTestObject('Object Repository/Card_tW_Article/text_tWArticle_Title'))
	if (!wordTWA.contains(wordTWL)) {
		msg = ('ERROR: The the translation word on the tWL card is "' + wordTWL + '", but it is not found in the title on the tWA card, "' + wordTWA + '".')
		CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
	}
	
	check = WebUI.getText(findTestObject('Object Repository/Card_tW_Article/text_tWA_N-of-N'))
	if (check != row + ' of 10') {
		msg = ('ERROR: The navigation text on the tWA card is "' + check + '", but the row on the tWL card is "' + row + '".')
		CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
	}
	
	if (row == 1) {
		cardID = 'scripture_card_0'
		words = ['Abraham']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['Ἀβραὰμ']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['Abraham']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
	} else if (row == 2) {
		cardID = 'scripture_card_0'
		words = ['became', 'the', 'father']
		occurrences = [1,1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['ἐγέννησεν']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['was', 'the', 'father']
		occurrences = [1,1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
	} else if (row == 3) {
		cardID = 'scripture_card_0'
		words = ['of', 'Isaac']
		occurrences = [1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['Ἰσαάκ']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['of', 'Isaac']
		occurrences = [1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
	
	} else if (row == 4) {
		cardID = 'scripture_card_0'
		words = ['Isaac']
		occurrences = [2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['Ἰσαὰκ']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['Isaac']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
	
	} else if (row == 5) {
		cardID = 'scripture_card_0'
		words = ['became', 'the', 'father']
		occurrences = [2,2,2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['ἐγέννησεν']
		occurrences = [2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['was', 'the', 'father']
		occurrences = [2,2,2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
	
	} else if (row == 6) {
		cardID = 'scripture_card_0'
		words = ['of', 'Jacob']
		occurrences = [2,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['Ἰακώβ']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['of', 'Jacob']
		occurrences = [2,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
	
	} else if (row == 7) {
		cardID = 'scripture_card_0'
		words = ['Jacob']
		occurrences = [2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['Ἰακὼβ']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['Jacob']
		occurrences = [2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
	
	} else if (row == 8) {
		cardID = 'scripture_card_0'
		words = ['became', 'the', 'father']
		occurrences = [3,3,3]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['ἐγέννησεν']
		occurrences = [3]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['was', 'the', 'father']
		occurrences = [3,3,3]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
	
	} else if (row == 9) {
		cardID = 'scripture_card_0'
		words = ['of', 'Judah']
		occurrences = [3,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['Ἰούδαν']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['of', 'Judah']
		occurrences = [3,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
	
	} else if (row == 10) {
		cardID = 'scripture_card_0'
		words = ['brothers']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['ἀδελφοὺς']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['of', 'brothers']
		occurrences = [4,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
	}
			
}


GlobalVariable.scriptRunning = false

WebUI.closeBrowser()

