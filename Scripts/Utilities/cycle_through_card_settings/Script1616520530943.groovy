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

// Test to see that all card titles can be retrieved and card menus can be opened by use of the card map
WebUI.callTestCase(findTestCase('Components/LogIn'), [:])

retValue = CustomKeywords.'unfoldingWord_Keywords.Resources_Layout.getCardMap'()

println(retValue + 'cards were found')

GlobalVariable.cards_Map_Current.each { key, val ->
	println "number: $key title = $val"
	WebUI.click(findTestObject('Page_Main/card_3dotMenu_Parmed', [('number') : key]))
	WebUI.delay(2)
	WebUI.click(findTestObject('Card_Settings/button_Settings_Close'))
	WebUI.delay(1)
}

scrolled = false
GlobalVariable.cards_Map_Current.each { key, val ->
	if (key > 5 && !scrolled) {
		WebUI.scrollToPosition(0, 600)
		scrolled = true
	}
	WebUI.dragAndDropByOffset(findTestObject('Page_Main/resizeIcon_Parmed', [('number') : key]), -50, -50)
	WebUI.delay(1)
}

WebUI.closeBrowser()