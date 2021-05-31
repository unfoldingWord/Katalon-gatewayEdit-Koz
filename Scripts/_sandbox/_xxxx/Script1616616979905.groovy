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

//refs = ['luk':['15','53'], 'rut':['3','13'], '1JN':['5','15'], 'Romans':['16':'27'], 'neh':['12,12'], 'rev':['9','19']]
refs = [['luk', '24', '53'], ['rut', '3', '13'], ['1jn', '5', '15'], ['Romans', '16', '27'], ['neh', '12', '12'], ['rev'
        , '9', '18']]

WebUI.callTestCase(findTestCase('Components/LogIn'), [:])

ref = Math.abs(new Random().nextInt() % (refs.size()))

refs.each {reference ->

//reference = refs[3]

CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.setScriptureReference'(reference[0], reference[1], reference[2])

}

println(reference)

refSet = reference[0] + ' ' + reference[1] + ':' + reference[2]

println(refSet)

return false
refs.each({ def ref ->
        CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.setScriptureReference'(ref[0], ref[1], ref[2])

        sleep(2000)
    })

return false

min = 25

max = 0

lists = []

i = 0

while (i < 20) {
    lists.add(i)

    i++
}

//for (list in lists) {
lists.each({ def list ->
        result = Math.abs(new Random().nextInt() % refs.size())

        if (result < min) {
            min = result
        }
        
        if (result > max) {
            max = result
        }
        
        println('result is ' + result)

        ref = (refs[result])

        myRef = (((((ref[0]) + ' ') + (ref[1])) + ':') + (ref[2]))

        println(myRef)
    })

println((min + ':') + max)

WebUI.sendKeys(findTestObject(null), Keys.chord(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE))

def setScriptureReference(def book, def chapter, def verse) {
	if (book != '' && book != null) {
		//			WebUI.click(findTestObject('Blue_Banners/input_Book'))
		WebUI.click(findTestObject('Blue_Banners/comboBoxArrow_Book'))
		WebUI.click(findTestObject('Blue_Banners/list_Book_Parmed', [('book'):book]))
	}

	if (chapter != '' && chapter != null) {
		//			WebUI.click(findTestObject('Blue_Banners/input_Chapter'))
		WebUI.click(findTestObject('Blue_Banners/comboBoxArrow_Chapter'))
		WebUI.click(findTestObject('Blue_Banners/list_Chapter_Parmed', [('chapter'):chapter]))
	}

	if (verse != '' && verse != null) {
		//			WebUI.click(findTestObject('Blue_Banners/input_Verse'))
		WebUI.click(findTestObject('Blue_Banners/comboBoxArrow_Verse'))
		WebUI.click(findTestObject('Blue_Banners/list_Verse_Parmed', [('verse'):verse]))
	}
}

