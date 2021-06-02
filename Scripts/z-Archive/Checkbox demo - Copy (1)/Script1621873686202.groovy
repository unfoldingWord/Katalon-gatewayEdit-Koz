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

displayStates(names)

for (int i : (0..3)) {
	
	println('Testing ' + columns[i])

	WebUI.uncheck(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : columns[i]]))

	displayStates(names)	
}

for (int i : (0..3)) {

	WebUI.check(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : columns[i]]))

}

for (int i = 0; i<5; i+=2) {
	WebUI.uncheck(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : columns[i]]))
	
		displayStates(names)
}

WebUI.closeBrowser()

def displayStates(names) {
	msg = ''
	names.each { name ->
		state = WebUI.verifyElementChecked(findTestObject('Card_Settings/checkbox_' + name), 1, FailureHandling.OPTIONAL)
		if (state) {
			msg += name + ' is checked\n'
		} else {
			msg += name + ' is NOT checked\n'
		}
	}
	
	JOptionPane.showMessageDialog(null,
		msg,
		"Verify Checked",
		JOptionPane.PLAIN_MESSAGE);
	
	msg = ''
	names.each { name ->
		state = WebUI.verifyElementNotChecked(findTestObject('Card_Settings/checkbox_' + name), 1, FailureHandling.OPTIONAL)
		if (!state) {
			msg += name + ' is checked\n'
		} else {
			msg += name + ' is NOT checked\n'
		}
	}
	
	JOptionPane.showMessageDialog(null,
		msg,
		"Verify Unhecked",
		JOptionPane.PLAIN_MESSAGE);

}