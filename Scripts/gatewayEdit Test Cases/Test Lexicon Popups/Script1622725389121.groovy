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
import org.openqa.selenium.interactions.Actions as Actions
import org.openqa.selenium.Keys as Keys

refs = ['tit 2:6', 'rut 1:10']

//scriptures = ['en_ulb', 'hi_glt', 'el-x-koine_ugnt', 'hbo_uhb']
scriptureCards = ['scripture_card_0', 'scripture_card_1', 'scripture_card_2']

WebUI.callTestCase(findTestCase('Components/LogIn'), [:])

WebUI.delay(1)

for (def ref : refs) {
    CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.setScriptureReference'(ref)

	olTitle = WebUI.getText(findTestObject('Object Repository/Page_Main/text_Card_Title_Parmed-ID', [('cardID') : scriptureCards[1]]))
	
	for (card in scriptureCards) {
            testForPopups(ref, card)
    }

	if (olTitle.contains('Greek')) {
		setScriptures(['el-x-koine_ugnt', 'ru_rlob', 'te_glt'])
	} else {
		setScriptures(['hi_glt', 'hbo_uhb', 'hbo_uhb'])
	}
		
	for (card in scriptureCards) {
            testForPopups(ref, card)
    }
	
	last = refs[refs.size()-1]
	if (ref != last) {
		WebUI.closeBrowser()
	
		WebUI.callTestCase(findTestCase('Components/LogIn'), [:])
	
		WebUI.delay(1)
	}

}

GlobalVariable.scriptRunning = false

WebUI.closeBrowser() 

def testForPopups(ref, card) {
	
	println('Testing for lexicon popups in ' + ref + ' on ' + card)
	
	title = WebUI.getText(findTestObject('Object Repository/Page_Main/text_Card_Title_Parmed-ID', [('cardID') : card]))
	
	if (title.contains('Greek') || title.contains('Hebrew')) {
		ol = true
	} else {
		ol = false
	}
	
	println('Getting words on ' + card)
    (words, highlights, elements) = CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.getScriptureWordsandHighlights'(card)
	
	words.each {
		println(it)
	}
	
	elements.each {
		println(it)
	}

    WebDriver driver = DriverFactory.getWebDriver()

    Actions actions = new Actions(driver)

    TestObject nextWord = WebUI.convertWebElementToTestObject(elements[1])

    WebUI.waitForElementPresent(nextWord, 5)

    actions.moveToElement(elements[0], -20, -20).perform() // Need to do a dummy first move to get the first hover to work

    i = 1

    elements.each{ def element ->
        if (!(ol)) {
            actions.doubleClick(element).perform()
        }
        
        actions.moveToElement(element).perform()

        if (ol) {
            if (!(WebUI.verifyTextPresent('lemma', false, FailureHandling.OPTIONAL)) && !(WebUI.verifyTextPresent('strong', 
                false, FailureHandling.OPTIONAL))) {
                word = element.getText()

                msg = ((((('Test failed because lexicon popup was not displayed on original language card when hovering over word ' + 
                i) + ', ') + word) + ' in ') + ref)

                println(msg)

                CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
            }
        } else {
            if (!(WebUI.verifyTextNotPresent('lemma', false, FailureHandling.OPTIONAL)) || !(WebUI.verifyTextNotPresent(
                'strong', false, FailureHandling.OPTIONAL))) {
                word = element.getText()

                msg = ((((('Test failed because lexicon popup was displayed on gateway language card when hovering over word ' + 
                i) + ', ') + word) + ' in ') + ref)

                println(msg)

                CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
            }
        }
        
        i++
    }
	
	last = elements.size() -1
	actions.click(elements[last]).perform()
}

def setScriptures(def scriptures) {
    baseURL = 'https://git.door43.org/Door43-Catalog/'	

    for (def i : (0..2)) {
        WebUI.click(findTestObject('Page_Main/menu_card_Parmed', [('resource') : scriptureCards[i]]))

        WebUI.click(findTestObject('Card_Settings/combobox_Version_or_URL'))

        WebUI.setText(findTestObject('Card_Settings/combobox_Version_or_URL'), baseURL + (scriptures[i]))

		WebUI.sendKeys(findTestObject('Card_Settings/combobox_Version_or_URL'), Keys.chord(Keys.ENTER))
		
		WebUI.click(findTestObject('Object Repository/Card_Settings/button_Settings_Close'))
		
		WebUI.delay(1)
    }
}

