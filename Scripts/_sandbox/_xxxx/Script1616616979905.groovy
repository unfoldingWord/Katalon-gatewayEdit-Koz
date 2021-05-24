import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
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

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

WebUI.callTestCase(findTestCase('Components/LogIn'), [:])

WebUI.click(findTestObject('Object Repository/Page_Main/menu_card_Parmed', [('resource') : 'tn']))

WebUI.delay(1)


xPath = "(.//*[normalize-space(text()) and normalize-space(.)='Markdown View'])[1]/following::div[7]"
 
 WebDriver driver = DriverFactory.getWebDriver()
 
 // Get the entire validator message element
 WebElement Paragraph = driver.findElement(By.xpath(xPath))
 
 // Get the individual span elements
 List elements = Paragraph.findElements(By.tagName('input'))
 
  elements.each {
	 checked = it.isSelected()
	 println(checked)
 }
 
//WebUI.click(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : 'Book']))
 
WebUI.click(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : 'Chapter']))

WebUI.delay(2)
 
 elements.each {
	 checked = it.isSelected()
	 println(checked)
 }
 

return false



columns = ['Book', 'Chapter','Verse', 'Reference','ID','Occurrence', 'SupportReference', 'Quote', 'Tags', 'Note']

names = columns

columns.each { column ->
	
	println('Testing ' + column)

	WebUI.click(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : column]))

	WebUI.delay(2)
	
	names.each { name ->
//		on = WebUI.verifyElementChecked(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : name]), 1, FailureHandling.OPTIONAL)
		if (WebUI.verifyElementChecked(findTestObject('Card_Settings/checkbox_' + name), 1, FailureHandling.OPTIONAL)) {""
			println('Column ' + name + ' is checked')
		} else {
			println('Column ' + name + ' is not checked')
		}
	}
	println('\n')
	
//	WebUI.click(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : column]))

}
return false
columns.each { name ->
//for (name in columns) {
	on = WebUI.verifyElementChecked(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : name]), 1, FailureHandling.OPTIONAL)
	println('Column ' + name + ' checked is ' + on)

	off = WebUI.verifyElementNotChecked(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : name]), 5, FailureHandling.OPTIONAL)
	println('Column ' + name + ' checked is ' + off + '\n')

	WebUI.click(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : name]))
	
	WebUI.delay(2)
	
	on = WebUI.verifyElementChecked(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : name]), 1, FailureHandling.OPTIONAL)
	println('Column ' + name + ' checked is ' + on)

	off = WebUI.verifyElementNotChecked(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : name]), 5, FailureHandling.OPTIONAL)
	println('Column ' + name + ' checked is ' + off + '\n')
}
return false
columns.each { name ->
//	for (name in columns) {
	state = WebUI.verifyElementChecked(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : name]), 1, FailureHandling.OPTIONAL)
	println('Column ' + name + ' checked is ' + state)

	WebUI.click(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : name]))
	
	state = WebUI.verifyElementChecked(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : name]), 1, FailureHandling.OPTIONAL)
	println('Column ' + name + ' checked is ' + state + '\n')
}
	
	