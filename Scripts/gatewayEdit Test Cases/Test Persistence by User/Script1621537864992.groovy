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

import javax.swing.*
import groovy.io.FileType as FileType

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

WebUI.callTestCase(findTestCase('Components/LogIn'), [:])

WebUI.click(findTestObject('Object Repository/Page_Main/menu_card_Parmed', [('resource') : 'tn']))

WebUI.delay(1)

columns = getColumns()

names = []
columns.each { line, name ->
	println(line + ':' + name)
	names.add(name)
}

offs = ['Book', 'Verse', 'ID', 'SupportReference', 'Tags']
for (name in offs) {
	WebUI.click(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : name]))
}

WebUI.delay(2)

states = getColumnStates(names)

states.each { name, state ->
	println('Column ' + name + ' checked is ' + state)
}

/////////////////
myBrowser = CustomKeywords.'unfoldingWord_Keywords.GetTestingConfig.getBrowserAndVersion'()

if (system.contains('Mac') && !myBrowser.contains('chrome')) {
	println('Bypassed testing multiple sessions in Firefox on Mac')
	return false
}
// Open a second browser tab
if (myBrowser.contains('chrome')) {
	WebUI.executeJavaScript('window.open();', [])
} else if (system.contains('Windows')) {
	WebUI.sendKeys(findTestObject('Page_tCC translationNotes/tNText_GLOutlinePoint1'), Keys.chord(Keys.CONTROL, 't'))
}

currentWindow = WebUI.getWindowIndex()

//Go to new tab
WebUI.switchToWindowIndex(currentWindow + 1)

// Navigate to tCC
WebUI.navigateToUrl(GlobalVariable.url)

/////////////////////

GlobalVariable.scriptRunning = false

//WebUI.closeBrowser()

def getColumns() {
	xPath = "(.//*[normalize-space(text()) and normalize-space(.)='Markdown View'])[1]/following::div[7]"
	
	WebDriver driver = DriverFactory.getWebDriver()
	
	// Get the entire validator message element
	WebElement Paragraph = driver.findElement(By.xpath(xPath))
	
	// Get the individual span elements
	List elements = Paragraph.findElements(By.tagName('span'))
	
	columns = [:]
	n = 0
	elements.each {
		name = it.getText()
		if (name.length() > 1 && name != 'Show Columns') {
			columns.put(n, name)
			n ++
		}
	}
	return columns
}

def getColumnStates(names) {
	states = [:]
	names.each { name ->
		state = WebUI.verifyElementChecked(findTestObject('Object Repository/Card_Settings/checkbox_Parmed', [('name') : name]), 1, FailureHandling.OPTIONAL)
		println('Column ' + name + ' checked is ' + state)
		
		states.put(name, state)
	}
	return states
}
