import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword as Keyword
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
//import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
//import org.openqa.selenium.WebDriver as WebDriver
//import javax.swing.*
//import org.openqa.selenium.Keys as Keys

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

WebUI.callTestCase(findTestCase('Components/LogIn'), [:])

cards = getCardMap()

println(cards)

GlobalVariable.cards_Map_Current.each { id, title ->
	println(id + ':' + title)
}

GlobalVariable.cards_Map_ID.each { num, id  ->
	println(num + ':' + id)
}

def getCardMap() {
	def retCode
	if (WebUI.verifyElementPresent(findTestObject('Page_Main/cards_Header_Parmed', [('number') : 1]), 1, FailureHandling.OPTIONAL)) {
		//			cardExists = true
		def n = 1
		while (WebUI.verifyElementPresent(findTestObject('Page_Main/cards_Header_Parmed', [('number') : n]), 1, FailureHandling.OPTIONAL)) {
			def title = (WebUI.getText(findTestObject('Page_Main/cards_Header_Parmed', [('number') : n]), FailureHandling.OPTIONAL))
			def id = (WebUI.getAttribute(findTestObject('Page_Main/card_Full_Parmed', [('number') : n]), 'id', FailureHandling.OPTIONAL))
			GlobalVariable.cards_Map_Current.putAt(n, title)
			GlobalVariable.cards_Map_ID.putAt(n, id)
			//				println(n + ' : ' + title)
			retCode = true
			n ++
		}
		retCode = n - 1
	} else {
		println('ERROR: Failed to find the first resource card')
		retCode = 0
	}
	return retCode
}
