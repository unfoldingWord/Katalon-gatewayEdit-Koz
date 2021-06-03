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
import java.awt.Robot

// TEST VARIOUS FORMS OF SCRIPTURE NAVIGATION AND THE TOOLTIPS

refs = ['luk 24:53', 'rut 3:13', '1jn 5:15', 'rom 16:27', 'neh 12:12', 'rev 9:18', 'act 7']

WebUI.callTestCase(findTestCase('Components/LogIn'), [:])

WebUI.delay(1)

myElement = 'Blue_Banners/input_Book'

// Dummy paste the reference because on Chrome the first set doesn't work
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
		
        (book, chapter, verse, reference) = CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.getScriptureReference'()

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

        if (book != refBook || chapter != refChapter || verse != refVerse ) {
            msg = 'Test failed because the PASTED reference is ' + ref + ' and the returned reference is ' + book + 
            ' ' + chapter + ':' + verse + '.'

            CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
        }
        
        tnBook = WebUI.getText(findTestObject('Card_tN/text_tN_Book'))

        tnChapter = WebUI.getText(findTestObject('Card_tN/text_tN_Chapter'))

        tnVerse = WebUI.getText(findTestObject('Card_tN/text_tN_Verse'))

        if (tnBook != refBook || tnChapter != refChapter || tnVerse != refVerse) {
            msg = 'Test failed because the PASTED reference is ' + ref + ' and the tN Card reference is ' + tnBook + 
            ' ' + tnChapter + ':' + tnVerse + '.'

            CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
		}
    })

/////// Test navigation by setting book, chapter, verse \\\\\\\
refs.each { ref ->

	CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.setScriptureReference'(ref)

	(book, chapter, verse, reference ) = CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.getScriptureReference'()
	
	if (ref.indexOf(':') < 0) {
		ref = ref + ':1'
	}
	
	if (reference != ref) {
		msg = 'Test failed because the SET reference is ' + ref + ' and the actual reference is ' + reference + '.'
		CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
	}
}


/////// Test navigation by clicking on navigation chevrons \\\\\\\
refs = ['rom 3:5','rom 3:6','rom 4:1','rom 5:1','rom 4:25', 'rom 4:24','rom 3:1','rom 2:1','rom 1:1','act 28:31','act 28:30','act 27:1' ]

startRef = 'rom 3:4'

actions = ['Next_Verse','Next_Verse','Next_Chapter','Next_Chapter','Previous_Verse','Previous_Verse','Previous_Chapter','Previous_Chapter',
  	'Previous_Chapter','Previous_Verse','Previous_Verse','Previous_Chapter']

CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.setScriptureReference'(startRef)

(book, chapter, verse, reference ) = CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.getScriptureReference'()

if (reference != startRef) {
	msg = 'Test failed because the STARTING reference is ' + startRef + ', but the actual reference is ' + reference +
	'.\n Bypassing the tests for navigation by clicking on navigation chevrons.'
	CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
	
} else {

	i = 0
	actions.each { action ->
		
		WebUI.click(findTestObject('Blue_Banners/button_' + action))
		
		(book, chapter, verse, reference ) = CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.getScriptureReference'()
		
		if (reference != refs[i]) {
			msg = 'Test failed because the EXPECTED reference is ' + refs[i] + ' and the actual reference is ' + reference + '.'
			CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
		}
		
		i ++
	}
}

/////// Test to see that the navigation bar icons have tooltips \\\\\\\
// Katalon cannot detect the actual tooltip text, so it looks for the 'title' attributes
chevrons = ['Previous_Chapter','Previous_Verse','Next_Chapter','Next_Verse']

arrows = ['Book','Chapter','Verse']

Robot robot = new Robot()

myY = 170 // y coordinate of center of top blue bar

WebUI.callTestCase(findTestCase('Components/LogIn'), [:])

WebUI.delay(1)

chevrons = ['Previous_Chapter','Previous_Verse','Next_Verse','Next_Chapter']

arrows = ['Book','Chapter','Verse']

//for (chevron in chevrons) {
chevrons.each { chevron ->
	tooltip = chevron.replace('_', ' ')
	
	tooltip = tooltip.toLowerCase()
	
	tooltip = tooltip.capitalize()
	
	myX = WebUI.getElementLeftPosition(findTestObject('Blue_Banners/button_' + chevron)) + 20
	
	robot.mouseMove(myX, myY) // Move the physical curson onto the navigation bar icon
	
	WebUI.mouseOver(findTestObject('Blue_Banners/button_' + chevron))
	
	title = WebUI.getAttribute(findTestObject('Blue_Banners/button_' + chevron), 'title')
	
	if (title != tooltip) {
		msg = 'Test failed because the ' + chevron + ' tooltip was not found as the element title.'
		println(msg)
	}
	
	WebUI.delay(3)
}

tooltip = 'open'
//for (arrow in arrows) {
arrows.each { arrow ->
	myX = WebUI.getElementLeftPosition(findTestObject('Blue_Banners/comboBoxArrow_' + arrow)) + 20
	
	robot.mouseMove(myX, myY)
	
	WebUI.mouseOver(findTestObject('Blue_Banners/comboBoxArrow_' + arrow))
	
	title = WebUI.getAttribute(findTestObject('Blue_Banners/comboBoxArrow_' + arrow), 'title')
	
	if (title != tooltip) {
		msg = 'Test failed because the ' + arrow + ' tooltip was not found as the element title.'
		println(msg)
	}
	
	WebUI.delay(3)
}

GlobalVariable.scriptRunning = false

WebUI.closeBrowser()

