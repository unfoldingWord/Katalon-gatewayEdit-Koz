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

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.SelectorMethod


// Click on tWL links and verify the highlights are as expected, and tW Article is updated correctly

writeFlag = false

WebUI.callTestCase(findTestCase('Components/LogIn'), [('organization') : 'unfoldingWord'])

WebUI.delay(1)

reference = 'Titus 1:6'

CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.setScriptureReference'(reference)

msg = ('>>>>> Testing tW for ' + reference)
CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendInfoMessage'(msg)

rows = getRowCount()

for (def row : (1..rows)) {
	
	checkTexts(row, rows)
	
	olOccurrence = WebUI.getText(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 3])) as int
	olWord = WebUI.getText(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 2]))
	
	if (row == 1) {
		cardID = 'scripture_card_0'
		words = ['blameless']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['someone', 'whom', 'no', 'one', 'can', 'criticize']
		occurrences = [1,1,1,1,1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
	} else if (row == 2) {
		cardID = 'scripture_card_0'
		words = ['children']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['his', 'children', 'his', 'children']
		occurrences = [1,1,2,2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
	} else if (row == 3) {
		cardID = 'scripture_card_0'
		words = ['faithful']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['must', 'trust', 'in', 'God']
		occurrences = [3,1,1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	
	} else if (row == 4) {
		cardID = 'scripture_card_0'
		words = ['accused']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['and', 'people', 'must', 'not', 'consider']
		occurrences = [1,1,4,1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	
	} else if (row == 5) {
		cardID = 'scripture_card_0'
		words = ['rebellion']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['disobedient']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	}
			
}

reference = 'Mat 1:2'

CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.setScriptureReference'(reference)

msg = ('>>>>> Testing tW for ' + reference)
CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendInfoMessage'(msg)

rows = getRowCount()

for (def row : (1..rows)) {
	
	checkTexts(row, rows)

	olOccurrence = WebUI.getText(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 3])) as int
	olWord = WebUI.getText(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 2]))
	
	if (row == 1) {
		cardID = 'scripture_card_0'
		words = ['Abraham']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['Abraham']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
	} else if (row == 2) {
		cardID = 'scripture_card_0'
		words = ['became', 'the', 'father']
		occurrences = [1,1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['was', 'the', 'father']
		occurrences = [1,1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
	} else if (row == 3) {
		cardID = 'scripture_card_0'
		words = ['of', 'Isaac']
		occurrences = [1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['of', 'Isaac']
		occurrences = [1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	
	} else if (row == 4) {
		cardID = 'scripture_card_0'
		words = ['Isaac']
		occurrences = [2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['Isaac']
		occurrences = [2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	
	} else if (row == 5) {
		cardID = 'scripture_card_0'
		words = ['became', 'the', 'father']
		occurrences = [2,2,2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['was', 'the', 'father']
		occurrences = [2,2,2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	
	} else if (row == 6) {
		cardID = 'scripture_card_0'
		words = ['of', 'Jacob']
		occurrences = [2,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['of', 'Jacob']
		occurrences = [2,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	
	} else if (row == 7) {
		cardID = 'scripture_card_0'
		words = ['Jacob']
		occurrences = [2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['Jacob']
		occurrences = [2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	
	} else if (row == 8) {
		cardID = 'scripture_card_0'
		words = ['became', 'the', 'father']
		occurrences = [3,3,3]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['was', 'the', 'father']
		occurrences = [3,3,3]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	
	} else if (row == 9) {
		cardID = 'scripture_card_0'
		words = ['of', 'Judah']
		occurrences = [3,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['of', 'Judah']
		occurrences = [3,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	
	} else if (row == 10) {
		cardID = 'scripture_card_0'
		words = ['brothers']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['of', 'brothers']
		occurrences = [4,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	}
	
}
	
reference = 'Exodus 4:6'

CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.setScriptureReference'(reference)

msg = ('>>>>> Testing tW for ' + reference)
CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendInfoMessage'(msg)

rows = getRowCount()

for (def row : (1..rows)) {
	
	checkTexts(row, rows)

	olOccurrence = WebUI.getText(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 3])) as int
	olWord = WebUI.getText(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 2]))
	
	if (row == 1) {
		cardID = 'scripture_card_0'
		words = ['Yahweh']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		wordOL = WebUI.getText(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 2]))
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['Yahweh']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
	} else if (row == 2) {
		cardID = 'scripture_card_0'
		words = ['your', 'hand']
		occurrences = [1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['your', 'hand']
		occurrences = [1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
	} else if (row == 3) {
		cardID = 'scripture_card_0'
		words = ['his', 'hand']
		occurrences = [1,2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['his', 'hand']
		occurrences = [1,2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	
	} else if (row == 4) {
		cardID = 'scripture_card_0'
		words = ['his', 'hand']
		occurrences = [3,3]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		wordOL = WebUI.getText(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 2]))
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['it']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	
	}
}
 
reference = 'Exodus 12:16'

CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.setScriptureReference'(reference)

msg = ('>>>>> Testing tW for ' + reference)
CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendInfoMessage'(msg)

rows = getRowCount()

for (def row : (1..rows)) {
	
	checkTexts(row, rows)

	olOccurrence = WebUI.getText(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 3])) as int
	olWord = WebUI.getText(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 2]))
	
	if (row == 1) {
		cardID = 'scripture_card_0'
		words = ['And', 'on','day']
		occurrences = [1,1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		wordOL = WebUI.getText(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 2]))
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['On', 'the', 'first', 'day', 'of', 'that', 'week']
		occurrences = [1,1,1,1,1,1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
	} else if (row == 2) {
		cardID = 'scripture_card_0'
		words = ['an', 'assembly', 'of']
		occurrences = [1,1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['a', 'meeting', 'do', 'the', 'same', 'thing']
		occurrences = [1,1,1,2,1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
	} else if (row == 3) {
		cardID = 'scripture_card_0'
		words = ['holiness']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['holy']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	
	} else if (row == 4) {
		cardID = 'scripture_card_0'
		words = ['and', 'on', 'day']
		occurrences = [2,2,2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		wordOL = WebUI.getText(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 2]))
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['on', 'the', 'seventh', 'day']
		occurrences = [2,3,1,2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	
	} else if (row == 5) {
		cardID = 'scripture_card_0'
		words = ['an', 'assembly', 'of']
		occurrences = [2,2,2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['a', 'meeting', 'do', 'the', 'same', 'thing']
		occurrences = [1,1,1,2,1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	
	} else if (row == 6) {
		cardID = 'scripture_card_0'
		words = ['holiness']
		occurrences = [2]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['holy']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	
	} else if (row == 7) {
		cardID = 'scripture_card_0'
		words = ['work']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['People', 'must', 'work']
		occurrences = [1,3,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	
	} else if (row == 8) {
		cardID = 'scripture_card_0'
		words = ['person']
		occurrences = [1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_1'
		words = [olWord]
		occurrences = [olOccurrence]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
		
		cardID = 'scripture_card_2'
		words = ['food', 'to', 'eat']
		occurrences = [1,1,1]
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.testForHighlighting'(cardID, words, occurrences, writeFlag)
	
	}
}

// Testing issue #147
reference = 'mat 1:2'

CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.setScriptureReference'(reference)

WebUI.delay(2)

WebUI.mouseOver(findTestObject('Page_Main/menu_card_Parmed',[('resource') : 'resource_card_ta']))

WebUI.scrollToPosition(0, 900)

WebUI.delay(1)

WebUI.scrollToElement(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : 10, ('column') : 2]),1)

WebUI.delay(1)

WebUI.click(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : 10, ('column') : 2]))

WebUI.delay(1)

found = WebUI.verifyImagePresent(findTestObject('Card_tW_List/td_brother'),FailureHandling.OPTIONAL)

msg = 'ERROR: The clicked on tWL is not visible after being clicked, issue #147.'
CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)

GlobalVariable.scriptRunning = false

WebUI.closeBrowser()

def checkTexts(row, count) {
	blue = 'rgba(56, 173, 223, 1)'

	wordOL = WebUI.getText(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 2]))
	msg = 'Testing clicking on row ' + row + ', "' + wordOL + '" in tWL card'
	CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendInfoMessage'(msg)
	
	WebUI.click(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 2]))
	if(!WebUI.getCSSValue(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 2]), 'color') == blue) {
		msg = 'ERROR: Word is not highlighted in blue on the tWL card'
		CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
	}
	
	wordTWL = WebUI.getText(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : row, ('column') : 1]))
	if (wordTWL == 'biblicaltimeday') {
		wordTWL = 'day'
	}
	lWordTWL = wordTWL.toLowerCase()
	wordTWA = WebUI.getText(findTestObject('Card_tW_Article/text_tWArticle_Title'))
	lWordTWA = wordTWA.toLowerCase()
	
	if (!lWordTWA.contains(lWordTWL)) {
		msg = ('ERROR: The the translation word on the tWL card is "' + wordTWL + '", but it is not found in the title on the tWA card, "' + wordTWA + '".')
		CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
	}
	
	check = WebUI.getText(findTestObject('Card_tW_Article/text_tWA_N-of-N'))
	if (check != row + ' of ' + count) {
		msg = ('ERROR: The navigation text on the tWA card is "' + check + '", but the row on the tWL card is "' + row + '".')
		CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
	}

}

def getRowCount() {
	WebUI.waitForElementPresent(findTestObject('Object Repository/Card_tW_List/table_tWL'),5)

	xPath = findTestObject('Card_tW_List/table_tWL').getSelectorCollection().get(SelectorMethod.XPATH)
	println('xPath is ' + xPath)
	WebDriver driver = DriverFactory.getWebDriver()
	WebElement Table = driver.findElement(By.xpath(xPath))
	List<WebElement> rows = Table.findElements(By.tagName('tr'))
	rowCount = rows.size()
	
	return rowCount

}