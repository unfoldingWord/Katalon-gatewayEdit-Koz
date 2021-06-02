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
import org.openqa.selenium.Keys as Keys

// Test pasting scripture references
refs = [['luk', '24', '53'], ['rut', '3', '13'], ['1jn', '5', '15'], ['rom', '16', '27'], ['neh', '12', '12'], ['rev', '9'
        , '18'], ['act', '7']]

WebUI.callTestCase(findTestCase('Components/LogIn'), [:])

WebUI.delay(1)

myElement = 'Blue_Banners/input_Book'

// Dummy set reference because on Chrome the first set doesn't work
CustomKeywords.'unfoldingWord_Keywords.Copy_Text_to_Clipboard.copyText'('mat 1:1')

CustomKeywords.'unfoldingWord_Keywords.HotKeys.sendKeys'(myElement, 'paste')

WebUI.clickOffset(findTestObject(myElement), 0, -20)

refs.each({ 
        ref = (((it[0]) + ' ') + (it[1]))

        println('size is ' + it.size())

        if (it.size() == 3) {
            println((('[' + (it[2])) + ':') + (it[2]).length())
        }
        
        if ((it.size() == 3) && ((it[2]).length() > 0)) {
            ref = ((ref + ':') + (it[2]))
        }
        
		println('ref is ' + ref)
		WebUI.click(findTestObject(myElement))
		
        CustomKeywords.'unfoldingWord_Keywords.Copy_Text_to_Clipboard.copyText'(ref)

        CustomKeywords.'unfoldingWord_Keywords.HotKeys.sendKeys'(myElement, 'paste')

		WebUI.clickOffset(findTestObject(myElement), 0, -20)
		
        (book, chapter, verse) = CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.getScriptureReference'()

        // If no verse is specified or verse us blank, verse should be set to verse 1
        if (it.size() < 3) {
            it.add('1')
        } else if ((it[2]).length() == 0) {
            (it[2]) = '1'
        }
        
        if (((book != (it[0])) || (chapter != (it[1]))) || (verse != (it[2]))) {
            msg = (((((((('Test failed because the set reference is ' + ref) + ' and the returned reference is ') + book) + 
            ' ') + chapter) + ':') + verse) + '.')

            CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
        }
        
        tnBook = WebUI.getText(findTestObject('Card_tN/text_tN_Book'))

        tnChapter = WebUI.getText(findTestObject('Card_tN/text_tN_Chapter'))

        tnVerse = WebUI.getText(findTestObject('Card_tN/text_tN_Verse'))

        if (((tnBook != (it[0])) || (tnChapter != (it[1]))) || (tnVerse != (it[2]))) {
            msg = (((((((('Test failed because the set reference is ' + ref) + ' and the tN Card reference is ') + tnBook) + 
            ' ') + tnChapter) + ':') + tnVerse) + '.')

            CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
		}
    })

GlobalVariable.scriptRunning = false

WebUI.closeBrowser()

