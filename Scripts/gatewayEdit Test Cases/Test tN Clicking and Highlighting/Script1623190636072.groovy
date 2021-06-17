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

scriptureCards = ['scripture_card_0', 'scripture_card_1', 'scripture_card_2']

orgs = ['test_org','test_org2']

first = true

for (org in orgs) {
//orgs.each { org ->
	msg = '>>>>>>>>> Testing with ' + org
	CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendInfoMessage'(msg)

	if (org == 'test_org') {
		quote = 'Quote'
	} else if (org == 'test_org2'){
		quote = 'OrigQuote'
	}
	
	if (!first) {
		WebUI.click(findTestObject('Blue_Banners/button_Open_Drawer'))
		WebUI.click(findTestObject('Drawer/button_Account-Settings'))
		WebUI.click(findTestObject('Page_Account_Settings/list_Organization'))	
		WebUI.click(findTestObject('Page_Account_Settings/listOrg_Option_Parmed', [('organization') : org]))
	} else {
		first = false		
	}
	
	WebUI.callTestCase(findTestCase('Components/LogIn'), [('organization'): org])
	
	WebUI.delay(1)
	
	reference = 'Titus 1:6'
	
	CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.setScriptureReference'(reference)
	
	//Testing issue #90 and #177
	// Verify highlights are as expected when clicking on tN card chevrons
	
	check = '2 of 5'
	retCode = nextCheck(reference, check, quote)
	if (retCode) {
		cardID = 'scripture_card_0'
		words = ['if', 'anyone', 'is', 'blameless']
		occurrences = [1,1,1,1]	
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['εἴ', 'τίς', 'ἐστιν', 'ἀνέγκλητος']
		occurrences = [1,1,1,1]		
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)//		testForHighlighting(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['Now', 'every', 'elder', 'must', 'be', 'someone', 'whom', 'no', 'one', 'can', 'criticize']
		occurrences = [1,1,1,1,1,1,1,1,1,1,1]	
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
	}
		
	check = '3 of 5'
	retCode = nextCheck(reference, check, quote)
	if (retCode) {
		cardID = 'scripture_card_0'
		words = ['blameless']
		occurrences = [1]	
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['ἀνέγκλητος']
		occurrences = [1]		
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)//		testForHighlighting(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['someone', 'whom', 'no', 'one', 'can', 'criticize']
		occurrences = [1,1,1,1,1,1]	
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
	}
		
	check = '4 of 5'
	retCode = nextCheck(reference, check, quote)
	if (retCode) {
		cardID = 'scripture_card_0'
		words = ['the', 'husband', 'of', 'one', 'wife']
		occurrences = [1,1,1,1,1]	
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['μιᾶς', 'γυναικὸς', 'ἀνήρ']
		occurrences = [1,1,1]		
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)//		testForHighlighting(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['He', 'must', 'also', 'have', 'just', 'one', 'wife']
		occurrences = [1,2,1,1,1,2,1]	
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
	}
		
	check = '5 of 5'
	retCode = nextCheck(reference, check, quote)
	if (retCode) {
		cardID = 'scripture_card_0'
		words = ['faithful', 'children']
		occurrences = [1,1]	
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = ['τέκνα', 'πιστά']
		occurrences = [1,1]		
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)//		testForHighlighting(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = ['his', 'children', 'must', 'trust', 'in', 'God', 'his', 'children' ]
		occurrences = [1,1,3,1,1,1,2,2]	
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
	}
		
	check = '1 of 5'
	retCode = nextCheck(reference, check, quote)
	if (retCode) {
		cardID = 'scripture_card_0'
		words = []
		occurrences = []	
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
		
		cardID = 'scripture_card_1'
		words = []
		occurrences = []		
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)//		testForHighlighting(cardID, words, occurrences)
		
		cardID = 'scripture_card_2'
		words = []
		occurrences = []	
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card_NEW.testForHighlighting'(cardID, words, occurrences)
	}
				
}

GlobalVariable.scriptRunning = false

WebUI.closeBrowser()

def nextCheck(reference, check, quote) {
	msg = 'Testing ' + reference + ' check ' + check
	CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendInfoMessage'(msg)
	WebUI.click(findTestObject('Object Repository/Card_tN/button_tN_Next'))

	if (WebUI.getText(findTestObject('Object Repository/Card_tN/text_tN_N-of-N')) != check) {
		msg = 'ERROR: ' + check + ' text is not present on tN card'
		CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
		retCode = false
	} else {
		retCode = true
		if(!WebUI.getCSSValue(findTestObject('Card_tN/text_tN_' + quote), 'color') == blue) {
			msg = 'ERROR: Quote is not highlighted in blue on the tN card'
			CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
		}
	}
	
	return retCode
}
