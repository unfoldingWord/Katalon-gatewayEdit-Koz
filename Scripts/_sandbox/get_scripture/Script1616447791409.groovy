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
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

WebUI.callTestCase(findTestCase('Components/LogIn'), [:])

retValue = CustomKeywords.'unfoldingWordKeywords.Resources_Layout.getCardMap'()

WebUI.delay(5)

(ref, text) = CustomKeywords.'unfoldingWordKeywords.Scripture_Card.getScriptureVerse'('Literal Text')

println('return value is ' + ref + ':' + text)

//(words, highlights) = CustomKeywords.'unfoldingWordKeywords.Scripture_Card.getScriptureWordsandHighlights'(1)
(words, highlights) = CustomKeywords.'unfoldingWordKeywords.Scripture_Card.getScriptureWordsandHighlights'('Literal Text')



for (def n : (0..words.size()-1)) {
	count = n + 1
	println(count + ':' + words[n] + ':' + highlights[n])
}


WebUI.closeBrowser()

