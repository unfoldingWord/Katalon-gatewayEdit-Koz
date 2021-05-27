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

import groovy.time.*

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory


WebUI.callTestCase(findTestCase('Components/LogIn'), [:])

WebUI.click(findTestObject('Object Repository/Page_Main/menu_card_Parmed', [('resource') : 'tn']))

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : 'Book']))

WebUI.click(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : 'Chapter']))

timeStart = new Date()

(columns, states, map) = CustomKeywords.'unfoldingWordKeywords.Work_with_Settings_Card.getColumnsList'('states')

timeEnd = new Date()

seconds = (timeEnd.getTime() - timeStart.getTime())/1000

println(seconds)

println(columns)
println(states)
println(map)
//columns.each {
//	println(it)
//}

WebUI.closeBrowser()

return false

pcts = [50,60,70,80,90,100,110,120,130,140,150]

pcts.each { pct ->
	
	CustomKeywords.'unfoldingWordKeywords.Work_with_Settings_Card.setFontSize'(pct)
	
	value = CustomKeywords.'unfoldingWordKeywords.Work_with_Settings_Card.getFontSize'()
	
	println('Font size is ' + value)
		
}

WebUI.closeBrowser()

return false

pct = 140

diff = (pct - min)

sliderWidth = WebUI.getElementWidth(findTestObject('Object Repository/Card_Settings/slider_Font_Size'))

offset = sliderWidth - sliderWidth/2 + diff/100

WebUI.clickOffset(findTestObject('Card_Settings/slider_Font_Size'), offset, 0)

return false

WebUI.verifyElementPresent(findTestObject('Card_Settings/input_Font_Size'), 1)

pct = WebUI.getAttribute(findTestObject('Card_Settings/input_Font_Size'), "value")

sliderWidth = WebUI.getElementWidth(findTestObject('Object Repository/Card_Settings/slider_Font_Size'))

pct = 0.7

offset = sliderWidth * (pct - 0.5)

WebUI.clickOffset(findTestObject('Card_Settings/slider_Font_Size'), offset, 0)

WebUI.delay(5)

pct = 0.2

offset = sliderWidth * (pct - 0.5)

WebUI.clickOffset(findTestObject('Card_Settings/slider_Font_Size'), offset, 0)

done = true

while (!done) {
	pct = WebUI.getAttribute(findTestObject('Card_Settings/input_Font_Size'), "value")

	JOptionPane.showMessageDialog(null,
		pct,
		"Update checkboxes and click OK",
		JOptionPane.PLAIN_MESSAGE);
}
return false


if (1 == 2) {
state = WebUI.verifyElementChecked(findTestObject('Card_Settings/switch_Markdown_View'), 1, FailureHandling.OPTIONAL)
println(state)

	WebUI.click(findTestObject('Card_Settings/switch_Markdown_View'))
	state = WebUI.verifyElementChecked(findTestObject('Card_Settings/switch_Markdown_View'), 1, FailureHandling.OPTIONAL)
	println(state)
	
	WebUI.click(findTestObject('Card_Settings/switch_Markdown_View'))
	state = WebUI.verifyElementChecked(findTestObject('Card_Settings/switch_Markdown_View'), 1, FailureHandling.OPTIONAL)
	println(state)
	
	return false
}

columns = ['Book', 'Chapter','Verse', 'Reference','ID','Occurrence', 'SupportReference', 'Quote', 'Tags', 'Note']

names = columns

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
	
	state = WebUI.verifyElementChecked(findTestObject('Card_Settings/switch_Markdown_View'), 1, FailureHandling.OPTIONAL)
	msg += 'markdown switch' + ' checked status is ' + state + '\n'
	
	
	JOptionPane.showMessageDialog(null,
		msg,
		"Update checkboxes and click OK",
		JOptionPane.PLAIN_MESSAGE);
	
}

def getColumnsList() {
	
		def tObj = findTestObject('Card_Settings/div_Column_Checkboxes')
		println "${tObj.findPropertyValue('xpath')}"
		def xPath = ${tObj.findPropertyValue('xpath')}
		println(xPath)
	}

