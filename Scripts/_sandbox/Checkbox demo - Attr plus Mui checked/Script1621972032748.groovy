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

import javax.swing.JOptionPane;


WebUI.callTestCase(findTestCase('Components/LogIn'), [:])

WebUI.click(findTestObject('Object Repository/Page_Main/menu_card_Parmed', [('resource') : 'tn']))

WebUI.delay(1)

columns = ['Book', 'Chapter','Verse', 'Reference','ID','Occurrence', 'SupportReference', 'Quote', 'Tags', 'Note']

names = columns

found = WebUI.verifyElementPresent(findTestObject('Card_Settings/checkbox_Quote'), 1, FailureHandling.OPTIONAL)

state = WebUI.verifyElementVisible(findTestObject('Card_Settings/checkbox_Quote_Checked'), FailureHandling.OPTIONAL)

println('Found = ' + found + ' and checked is ' + state)

WebUI.click(findTestObject('Card_Settings/checkbox_Quote'))

found = WebUI.verifyElementPresent(findTestObject('Card_Settings/checkbox_Quote'), 1, FailureHandling.OPTIONAL)

state = WebUI.verifyElementVisible(findTestObject('Card_Settings/checkbox_Quote_Checked'), FailureHandling.OPTIONAL)

println('Found = ' + found + ' and checked is ' + state)

return false

done = false

while (!done) {
	displayStates(names)
}

WebUI.closeBrowser()

def displayStates(names) {
	msg = ''
	names.each { name ->
		state = WebUI.verifyElementChecked(findTestObject('Card_Settings/checkbox_' + name), 1, FailureHandling.OPTIONAL)
		msg += name + ' checked status is ' + state + '\n'
	}
	
	JOptionPane.showMessageDialog(null,
		msg,
		"Update checkboxes and click OK",
		JOptionPane.PLAIN_MESSAGE);
	
}
