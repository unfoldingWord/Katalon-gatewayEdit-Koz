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

try {
	WebUI.navigateToUrl(GlobalVariable.url)
	open = true
} catch(BrowserNotOpenedException){
	open = false
}
	
if (!open) {
	
	WebUI.openBrowser('')
	
	if (GlobalVariable.browser == '' || GlobalVariable.browser == null || 1 == 1) {
		GlobalVariable.browser = CustomKeywords.'unfoldingWord_Keywords.GetTestingConfig.getBrowserAndVersion'()
	}
	println('Browser is ' + GlobalVariable.browser)

	WebUI.navigateToUrl(GlobalVariable.url)
}

WebUI.maximizeWindow()

GlobalVariable.version = WebUI.getText(findTestObject('Blue_Banners/text_Version'))

println('Version is ' + GlobalVariable.version)

if (binding.hasVariable('langCode')) {
	myLanguage = langCode
} else {
	myLanguage = GlobalVariable.langCode
}

if (binding.hasVariable('organization')) {
	myOrganization = organization
} else {
	myOrganization = GlobalVariable.organization
}

if (binding.hasVariable('user')) {
	myUser = user
	if (user == 'tc01') {
		myPassword = '+0+nfRaS+QU='
	} else if (user == 'tcc001') {
		myPassword = 'JY5PahTdTnI='
	}
} else {
	myUser = GlobalVariable.user1Name
	myPassword = GlobalVariable.user1Password
}

println('Logging in as ' + myUser)

WebUI.setText(findTestObject('Page_Login/input_Username'), myUser)

WebUI.setEncryptedText(findTestObject('Page_Login/input_Password'), myPassword)

WebUI.click(findTestObject('Page_Login/button_Login'))

if (WebUI.verifyElementPresent(findTestObject('Page_Account_Settings/text_Account Setup'), 3, FailureHandling.OPTIONAL)) {

	println('Selecting ' + myOrganization)
	WebUI.click(findTestObject('Page_Account_Settings/list_Organization'))
	
	WebUI.click(findTestObject('Page_Account_Settings/listOrg_Option_Parmed', [('organization') : myOrganization]))
	
	println('Selecting ' + myLanguage)
	WebUI.click(findTestObject('Page_Account_Settings/list_Language'))
	
	WebUI.click(findTestObject('Page_Account_Settings/listLang_Option_Parmed', [('langCode') : myLanguage]))
	
	WebUI.click(findTestObject('Page_Account_Settings/button_Save and Continue'))
	
	WebUI.waitForElementPresent(findTestObject('Card_Scripture/menu_Scripture_Card_0'), 5)
	
} else {
	println('Bypassing account settings page')
}

if (WebUI.verifyElementPresent(findTestObject('Card_tN/text_tN_Reference'), 1, FailureHandling.OPTIONAL)) {
	GlobalVariable.tNFormat = 'New'
} else {
	GlobalVariable.tNFormat = 'Old'
}

CustomKeywords.'unfoldingWord_Keywords.Resources_Layout.getCardMap'()

