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

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.url)

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

user = GlobalVariable.user1Name
println('Logging in as ' + user)

WebUI.setText(findTestObject('Page_Login/input_Username'), user)

WebUI.setEncryptedText(findTestObject('Page_Login/input_Password'), GlobalVariable.user1Password)

WebUI.click(findTestObject('Page_Login/button_Login'))

println('Selecting ' + myOrganization)
WebUI.click(findTestObject('Page_Account_Settings/list_Organization'))

WebUI.click(findTestObject('Page_Account_Settings/listOrg_Option_Parmed', [('organization') : myOrganization]))

println('Selecting ' + myLanguage)
WebUI.click(findTestObject('Page_Account_Settings/list_Language'))

WebUI.click(findTestObject('Page_Account_Settings/listLang_Option_Parmed', [('langCode') : myLanguage]))

WebUI.click(findTestObject('Page_Account_Settings/button_Save and Continue'))

WebUI.waitForElementPresent(findTestObject('Card_Scripture/menu_Scripture_Card_0'), 5)

if (WebUI.verifyElementPresent(findTestObject('Card_tN/text_tN_Reference'), 1, FailureHandling.OPTIONAL)) {
	GlobalVariable.tNFormat = 'New'
} else {
	GlobalVariable.tNFormat = 'Old'
}

