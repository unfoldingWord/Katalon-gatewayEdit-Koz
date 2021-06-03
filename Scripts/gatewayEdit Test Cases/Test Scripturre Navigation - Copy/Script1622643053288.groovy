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
refs = ['luk 24:53', 'rut 3:13', '1jn 5:15', 'rom 16:27', 'neh 12:12', 'rev 9:18', 'act 7']

WebUI.callTestCase(findTestCase('Components/LogIn'), [:])

WebUI.delay(1)

myElement = 'Blue_Banners/input_Book'

// Dummy set reference because on Chrome the first set doesn't work
CustomKeywords.'unfoldingWord_Keywords.Copy_Text_to_Clipboard.copyText'('mat 1:1')

CustomKeywords.'unfoldingWord_Keywords.HotKeys.sendKeys'(myElement, 'paste')

WebUI.clickOffset(findTestObject(myElement), 0, -20)

/////// Test navigation by pasting reference into book combobox \\\\\\\
refs.each({ ref -> 
        
		println('ref is ' + ref)
		WebUI.click(findTestObject(myElement))
		
        CustomKeywords.'unfoldingWord_Keywords.Copy_Text_to_Clipboard.copyText'(ref)

        CustomKeywords.'unfoldingWord_Keywords.HotKeys.sendKeys'(myElement, 'paste')

		WebUI.clickOffset(findTestObject(myElement), 0, -20)
		
        (book, chapter, verse) = CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.getScriptureReference'()

		if (ref.length() > 3) {
			refBook = ref.substring(0,3)
			space = ref.indexOf(' ')
			colon = ref.indexOf(':')
			if (colon > 0) {
				refChapter = ref.substring(space + 1, colon)
				refVerse = ref.substring(colon + 1, ref.length())
			} else {
				refChapter = ref.substring(space + 1, ref.length())
				refVerse = '1'
			}
		} else {
			refBook = ref
		}

        if (book != refBook || chapter != refChapter || verse != refVerse) {
            msg = 'Test failed because the set reference is ' + ref + ' and the returned reference is ' + book + 
            ' ' + chapter + ':' + verse + '.'

            CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
        }
        
        tnBook = WebUI.getText(findTestObject('Card_tN/text_tN_Book'))

        tnChapter = WebUI.getText(findTestObject('Card_tN/text_tN_Chapter'))

        tnVerse = WebUI.getText(findTestObject('Card_tN/text_tN_Verse'))

        if (tnBook != refBook || tnChapter != refChapter || tnVerse != refVerse) {
            msg = 'Test failed because the set reference is ' + ref + ' and the tN Card reference is ' + tnBook + 
            ' ' + tnChapter + ':' + tnVerse + '.'

            CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
		}
    })

/////// Test navigation by clicking on navigation chevrons \\\\\\\
startRef = 'rom 3:4'
expectedRef = ['']

CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.setScriptureReference'(startRef)
GlobalVariable.scriptRunning = false

WebUI.closeBrowser()

