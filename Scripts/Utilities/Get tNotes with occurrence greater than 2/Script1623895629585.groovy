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

import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.io.File as File
import groovy.io.FileType
import org.apache.commons.io.FileUtils

// THIS SCRIPT RUNS VALIDATION ON ALL BOOKS IN THE BIBLE TO GET ALL CURRENT UNIQUE VALIDATION PRIORITY CODES
// THIS IS USEFUL TO DEVELOP AND MAINTAIN SCRIPTS THAT DO THE VALIDATION TESTING

testFiles = []
list = []
fileList = []

path = '/Users/' + GlobalVariable.pcUser + '/Downloads/en_twl/'
dirLen = path.length()

now = new Date()

fName = 'OT Occurrence greater than 2-' + now.format('MMddyyhhmmss') + '.csv'
oFile = new File('/Users/' + GlobalVariable.pcUser + '/Katalon Studio/Files/' + fName)

dir = new File(path)
dir.eachFileRecurse (FileType.FILES) { file ->
  list << file
}
///Users/cckozie/Downloads/en_tn 2/en_tn_12-2KI.tsv
///Users/cckozie/Downloads/en_twl/twl_1CH.tsv
list.each {
	p = it.path
	ext = p.substring(p.length()-3,p.length())
	if (ext == 'tsv') {
		fileList.add(p)
	}
}

fileList.sort()
fileList.forEach {
	println(it)
}

//for (def testFile in testFiles) {
fileList.each	{ def testFile ->
    println('Opening ' + testFile)
	
// Read the file into field lists
	new File(testFile).splitEachLine('\t', { def fields ->
		if (fields[0] != 'Reference') {
			
			occurrence = fields[4] as int
//			println(occurrence)
			if (occurrence > 1) {
				println(fields[0]+':'+fields[1]+':'+fields[4])
			} 
		}
		if (1 == 2) {
			newLines = []
			
			new File(myFile).eachLine({ def line ->
				newLines.add(line)
			})
		
			for (def row : (0..prioritys.size()-1) ) {
				if (!myPrioritys.contains(prioritys[row])) {
					myPrioritys.add(prioritys[row])
					errorRows.add(newLines[row])
					oFile.append(testFile + ',' + newLines[row] + '\n')
				}
			}
			
			println(myPrioritys)
			
			println(errorRows)	
		}

	})
}

GlobalVariable.scriptRunning = false

WebUI.closeBrowser()


	