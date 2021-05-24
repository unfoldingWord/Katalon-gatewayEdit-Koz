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

WebUI.callTestCase(findTestCase('Components/LogIn'), ['organization':'test_org'])

WebUI.delay(1)

buttons = ['Blue_Banners/comboBoxArrow_Book','Blue_Banners/comboBoxArrow_Book','Blue_Banners/comboBoxArrow_Chapter',
	'Blue_Banners/comboBoxArrow_Chapter','Blue_Banners/comboBoxArrow_Verse','Blue_Banners/comboBoxArrow_Verse','Blue_Banners/button_Next_Chapter',
	'Blue_Banners/button_Previous_Chapter','Blue_Banners/button_Next_Verse','Blue_Banners/button_Previous_Verse',
	'Card_tN/button_tN_Next','Card_tN/button_tN_Previous']

menus = ['Page_Main/menu_scripture_card_0',  'Page_Main/menu_scripture_card_1', 'Page_Main/menu_scripture_card_2',  'Page_Main/menu_ta_card', 
	'Page_Main/menu_tn_card',  'Page_Main/menu_tq_card', 'Page_Main/menu_twa_card',  'Page_Main/menu_twl_card']

drags = ['Page_Main/dragIcon_scripture_card_0', 'Page_Main/dragIcon_scripture_card_1', 'Page_Main/dragIcon_scripture_card_2', 'Page_Main/dragIcon_ta_card', 
	'Page_Main/dragIcon_tn_card', 'Page_Main/dragIcon_tq_card', 'Page_Main/dragIcon_twa_card', 'Page_Main/dragIcon_twl_card']
	
fieldsNewFmt = ['text_tN_N-of-N','text_tN_Book','text_tN_Chapter','text_tN_Verse','text_tN_Reference','text_tN_ID','text_tN_Occurrence',
	'text_tN_SupportReference','text_tN_Quote','text_tN_Tags','text_tN_Annotation']

fieldsOldFmt = ['text_tN_N-of-N','text_tN_Book','text_tN_Chapter','text_tN_Verse','text_tN_ID','text_tN_Occurrence',
	'text_tN_SupportReference','text_tN_OrigQuote','text_tN_GLQuote','text_tN_OccurrenceNote']

if (GlobalVariable.tNFormat == 'New') {
	fields = fieldsNewFmt
} else {
	fields = fieldsOldFmt
}

// UNCOMMENT THESE STATEMENTS TO BYPASS THE TESTING OF THE RELATED ELEMENTS
buttons = []
menus = []
drags = []
//fields = []

for (button in buttons) {
	WebUI.click(findTestObject(button))
	WebUI.delay(1)
}

for (menu in menus) {
	WebUI.click(findTestObject(menu))
	WebUI.delay(1)
	WebUI.click(findTestObject('Card_Settings/button_Settings_Close'))
	WebUI.delay(1)

}

d = 0
for (drag in drags) {
	WebUI.dragAndDropByOffset(findTestObject(drag), 20, 20)
	WebUI.delay(1)
	d ++
	if (d > 4) {
		WebUI.scrollToPosition(0, 600)
	}
}

output = []
for (field in fields) {
	fld = 'Card_tN/' + field
	output.add(field + ':' + WebUI.getText(findTestObject(fld)))
}

output.forEach({ line ->
	println(line)
})

WebUI.closeBrowser()

WebUI.callTestCase(findTestCase('Components/LogIn'), ['organization':'test_org2'])

WebUI.delay(1)

if (GlobalVariable.tNFormat == 'New') {
	fields = fieldsNewFmt
} else {
	fields = fieldsOldFmt
}

output = []
for (field in fields) {
	fld = 'Card_tN/' + field
	output.add(field + ':' + WebUI.getText(findTestObject(fld)))
}

output.forEach({ line ->
	println(line)
})

WebUI.closeBrowser()


