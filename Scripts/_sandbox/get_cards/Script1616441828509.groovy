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

WebUI.callTestCase(findTestCase('Components/LogIn'), [:])

WebUI.delay(5)

retValue = CustomKeywords.'unfoldingWordKeywords.Resources_Layout.getCardMap'()
//retValue = getCardMap()

println('return value is ' + retValue)

//retValue = CustomKeywords.'unfoldingWordKeywords.Resources_Layout.getCardMap1'()

//println('return value is ' + retValue)

GlobalVariable.cards_Map_Current.each { key, val ->
	println "number: $key title = $val"
	if (1 == 2) {
		WebUI.click(findTestObject('Page_Main/cards_3dotMenu_Parmed', [('number') : key]))
		WebUI.delay(2)
	//	WebUI.clickOffset(findTestObject('Blue_Banners/text_Application_Name', 0, 0))
		WebUI.clickOffset(findTestObject('Blue_Banners/text_Application_Name'), 0, 0)
		WebUI.delay(2)
		card = GlobalVariable.cards_Map_Current.find { it.value.contains('Literal Text') }?.key
		println(card)
	}
	
}
WebUI.closeBrowser()

def getCardMap() {
	def retCode
	int n
	if (WebUI.verifyElementPresent(findTestObject('Page_Main/cards_Header_Parmed', [('number') : 1]), 1, FailureHandling.OPTIONAL)) {
	//			cardExists = true
		for (n = 1; n < 30; n++) {
			if (WebUI.verifyElementPresent(findTestObject('Page_Main/cards_Header_Parmed', [('number') : n]), 1, FailureHandling.OPTIONAL)) {
				def title = (WebUI.getText(findTestObject('Page_Main/cards_Header_Parmed', [('number') : n]), FailureHandling.OPTIONAL))
				GlobalVariable.cards_Map_Current.putAt(n, title)
				//				println(n + ' : ' + title)
				retCode = true
			} else {
				break
			}
		retCode = n - 1
		}
	} else {
		println('ERROR: Failed to find the first resource card')
		retCode = 0
	}
	return retCode
}
