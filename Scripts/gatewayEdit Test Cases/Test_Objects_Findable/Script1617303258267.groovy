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

dir = '/Users/cckozie/Katalon Studio/Katalon_tC_Create_Next/Object Repository/Blue_Banners/button_Next_Chapter.rs'

dir1 = '/Users/cckozie/Katalon Studio/Katalon_tC_Create_Next/Object Repository/Blue_Banners/'

baseDir = GlobalVariable.projectPath + '/Object Repository/'

path = baseDir

println(path)

folders = ['Blue_Banners']

for (folder in folders) {

myDir = baseDir + folder

	list = []
	
	println('The dir is ' + myDir)
	dir = new File(myDir)
	dir.eachFileRecurse(FileType.FILES, { def file ->
		String fileName = file
		if(fileName.contains('.rs'))
			fileName = fileName.substring(myDir.length()+1,fileName.length()-3)
			list << fileName
		})
	
//	list.each {
//		println(it)
//	}
}

//return false

WebUI.callTestCase(findTestCase('Components/LogIn'), [:])

WebUI.delay(1)

buttons = ['Blue_Banners/comboBoxArrow_Book','Blue_Banners/comboBoxArrow_Book','Blue_Banners/comboBoxArrow_Chapter',
	'Blue_Banners/comboBoxArrow_Chapter','Blue_Banners/comboBoxArrow_Verse','Blue_Banners/comboBoxArrow_Verse','Blue_Banners/button_Next_Chapter',
	'Blue_Banners/button_Previous_Chapter','Blue_Banners/button_Next_Verse','Blue_Banners/button_Previous_Verse',
	'Card_tN/button_tN_Next','Card_tN/button_tN_Previous']
	
fields = ['text_tN_N-of-N','text_tN_Book','text_tN_Chapter','text_tN_Verse','text_tN_Reference','text_tN_ID','text_tN_Occurrence',
	'text_tN_SupportReference','text_tN_Quote','text_tN_Tags','text_tN_Annotation']

for (button in buttons) {
	WebUI.click(findTestObject(button))

	WebUI.delay(1)
}

output = []
for (field in fields) {
	fld = 'Card_tN/' + field
	output.add(field + ':' + WebUI.getText(findTestObject(fld)))
}

output.forEach({ line ->
	println(line)
})


WebUI.closeBrowser()

def getObjects (myDir) {
	
	list = []
	
	println('The dir is ' + myDir)
	dir = new File(myDir)
	dir.eachFileRecurse(FileType.FILES, { def file ->
		String fileName = file
			list << fileName
		})
	return list
}

