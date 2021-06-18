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
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import groovy.time.*


WebUI.callTestCase(findTestCase('Components/LogIn'), [:])

WebUI.delay(1)

reference = 'mat 1:2'

CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.setScriptureReference'(reference)

WebUI.delay(2)

// Should not be found since it's not visible
found = WebUI.verifyImagePresent(findTestObject('Card_tW_List/td_brother'),FailureHandling.OPTIONAL)

println('Image found before scrolling is ' + found)

WebUI.delay(1)

WebUI.mouseOver(findTestObject('Page_Main/menu_card_Parmed',[('resource') : 'resource_card_ta']))

WebUI.scrollToPosition(0, 900)

WebUI.delay(1)

WebUI.scrollToElement(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : 10, ('column') : 2]),1)

WebUI.delay(1)

// Don't expect it to be found since the font is non-bold and black
found = WebUI.verifyImagePresent(findTestObject('Card_tW_List/td_brother'),FailureHandling.OPTIONAL)

println('Image found before clicking is ' + found)

WebUI.delay(1)

WebUI.click(findTestObject('Card_tW_List/text_tWList_Table_Parmed', [('row') : 10, ('column') : 2]))

WebUI.delay(1)

// Expect it to be found because it should be a very close match, font is bold and blue
found = WebUI.verifyImagePresent(findTestObject('Card_tW_List/td_brother'),FailureHandling.OPTIONAL)

println('Image found after clicking is ' + found)

