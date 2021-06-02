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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import javax.swing.*
import org.openqa.selenium.Keys as Keys

refs = [['luk', '24', '53'], ['rut', '3', '13'], ['1jn', '5', '15'], ['Romans', '16', '27'], ['neh', '12', '12'], ['rev'
        , '9', '18']]
refs = [['luk', '24', '53']]

myElement = 'Page_Login/input_Username'

WebUI.openBrowser('https://gateway-edit.netlify.app/')

CustomKeywords.'unfoldingWord_Keywords.Copy_Text_to_Clipboard.copyText'('tc01')

WebUI.click(findTestObject(myElement))

CustomKeywords.'unfoldingWord_Keywords.HotKeys.sendKeys'(myElement, 'paste')

WebUI.click(findTestObject(myElement))

CustomKeywords.'unfoldingWord_Keywords.HotKeys.sendKeys'(myElement, 'all')

CustomKeywords.'unfoldingWord_Keywords.Copy_Text_to_Clipboard.copyText'('')

CustomKeywords.'unfoldingWord_Keywords.HotKeys.sendKeys'(myElement, 'paste')

CustomKeywords.'unfoldingWord_Keywords.Copy_Text_to_Clipboard.copyText'('tcc001')

WebUI.click(findTestObject(myElement))

CustomKeywords.'unfoldingWord_Keywords.HotKeys.sendKeys'(myElement, 'paste')

WebUI.delay(10)

WebUI.closeBrowser()
