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

printAll = true

tabTest = false

closeOpen = false
	
// Array of card id's and font size objects
cards = ['0':'Scripture','1':'Scripture','2':'Scripture','tn':'Markdown','ta':'Markdown','twl':'No_Markdown','twa':'Markdown','tq':'Markdown']

orgs = ['test_org','test_org2']
//orgs = ['test_org']

orgs.each { org ->
	myOrg = orgs.indexOf(orgs)
	
	orgFail = false

	loopMax = 1
	
	loop = 1
	
	first = true
	
	while (loop <= loopMax) {
	
		println('################ LOOP ' + loop)
		
		markdowns = [:]
		
		fontSizes = [:]
		
		WebUI.callTestCase(findTestCase('Components/LogIn'), [('organization'): org])
		
		cards.each { id, font ->
			
			println('Processing ' + id + ' card')
			
			WebUI.click(findTestObject('Page_Main/menu_card_Parmed', [('resource') : id]))
			
			// Markdown View switch
			if (font == 'Markdown') {
				result = Math.abs(new Random().nextInt() % 2)
				if (result == 1) {
					newState = true
					mdState = 'On'
				} else {
					newState = false
					mdState = 'Off'
				}
				
				markdowns.put(id,newState)
				
				if (myOrg == 0) {
					markdowns0 = markdowns
				} else {
					markdowns0 = markdowns
				}
				
				println('Setting MarkdownView swith to ' + mdState)
				
				WebDriver driver = DriverFactory.getWebDriver()
				
				mdSwitch = driver.findElement(By.name('markdownView'))
				
				isState = mdSwitch.isSelected()
				
				if (isState != newState) {
					mdSwitch.click()
				}
			}
			
			// Font size
			size = Math.abs(new Random().nextInt() % 11) * 10 + 50
			
			CustomKeywords.'unfoldingWord_Keywords.Work_with_Settings_Card.setFontSize'(size,font)
			
			fontSizes.put(id,size)
			
			if (myOrg == 0) {
				fontSizes0 = fontSizes
			} else {
				fontSizes1 = fontSizes
			}
			
			if (id == 'tn') {
			
				// Get a list of the tN columns
				(columns, states, columnsMap) = CustomKeywords.'unfoldingWord_Keywords.Work_with_Settings_Card.getColumnsList'()
				
				if (first) {
					CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendInfoMessage'(org)
					msg = columns
					CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendInfoMessage'(msg)
					first = false
				}
				
				offCount = 0
				// Build a map of random states for the columns
				columns.each { name ->
					result = Math.abs(new Random().nextInt() % 2)
					if (result == 1) {
						state = true
					} else {
						state = false
						offCount ++
					}
				//	if (name.contains('Note')) {
				//		state = false
				//	}
					columnsMap.put(name, state)	
				}
				
				if (myOrg == 0) {
					columnsMap0 = columnsMap
				} else {
					columnsMap1 = columnsMap
				}
				
				msg = offCount + ' not checked -- '
				columnsMap.each { name, state ->
					pair = name + ':' + state
					msg = msg + pair + ', '
				}
				
				//CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendInfoMessage'(msg)
				
				// Set the columns to the randomly generated states
				CustomKeywords.'unfoldingWord_Keywords.Work_with_Settings_Card.setColumnStates'(columnsMap)
				println('columns set')
				
				if (closeOpen) {
					// Close and reopen the settings card
					WebUI.click(findTestObject('Object Repository/Card_Settings/button_Settings_Close'))
					
					WebUI.click(findTestObject('Object Repository/Page_Main/menu_card_Parmed', [('resource') : 'tn']))
					
					println('\n>>>>>>>>>>> Testing persistence after reopening settings card\n')
					testPersistence(columnsMap, 'reopening settings card')
				}
												
			}
		
			// Close the settings card
			WebUI.scrollToElement(findTestObject('Object Repository/Card_Settings/button_Settings_Close'), 1)
			WebUI.click(findTestObject('Object Repository/Card_Settings/button_Settings_Close'))
				
			if (tabTest) {
			
				// Prepare to open a second browser tab
				myBrowser = CustomKeywords.'unfoldingWord_Keywords.GetTestingConfig.getBrowserAndVersion'()
				
				if (GlobalVariable.systemOS.contains('Mac') && !myBrowser.contains('chrome')) {
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
				
				WebUI.callTestCase(findTestCase('Components/LogIn'), [:])
				
				WebUI.click(findTestObject('Object Repository/Page_Main/menu_card_Parmed', [('resource') : 'tn']))
				
				WebUI.delay(1)
				
				println('\n>>>>>>>>>>> Testing persistence after opening a new tab\n')
				testPersistence(columnsMap, 'opening a new tab')
			}
			
		}
		
		println(columnsMap)
		
		println(markdowns)
		
		println(fontSizes)

		// Log out and back in
		WebUI.callTestCase(findTestCase('Components/LogOut'), [:])
		
		WebUI.callTestCase(findTestCase('Components/LogIn'), [('organization'): org])

		cards.each { id, font ->
			
			println('Processing ' + id + ' card')
			
			WebUI.click(findTestObject('Page_Main/menu_card_Parmed', [('resource') : id]))
			
			println('Processing markdown switch')
			if (font == 'Markdown') {
				
				WebDriver driver = DriverFactory.getWebDriver()
				
				mdSwitch = driver.findElement(By.name('markdownView'))
				
				isState = mdSwitch.isSelected()
				
				setState = markdowns.get(id)
				
				println('Testing that MarkdownView swith is ' + setState)
				
				if (isState != setState) {
					msg = 'Test failed because the Markdown View switch on card ' + id + ' is ' + isState + ' and was set to ' + setState
					println(msg)
					CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
				}
			}
			
			println('Processing font size')
			fontSize = CustomKeywords.'unfoldingWord_Keywords.Work_with_Settings_Card.getFontSize'(font)
			
			setSize = fontSizes.get(id)
			if (fontSize != setSize) {
				msg = 'Test failed because the font size on card ' + id + ' is ' + fontSize + ' and was set to ' + setSize
				println(msg)
				CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
			}

			if (id == 'tn') {
				println('\n>>>>>>>>>>> Testing column checkboxes persistence after logout and in\n')
				testPersistence(columnsMap, 'new log in')
			}
			
			// Close the settings card
			WebUI.scrollToElement(findTestObject('Object Repository/Card_Settings/button_Settings_Close'), 1)
			WebUI.click(findTestObject('Object Repository/Card_Settings/button_Settings_Close'))
				

		}
		WebUI.closeBrowser()
		
	loop ++
	}
}

GlobalVariable.scriptRunning = false

WebUI.closeBrowser()

def testPersistence(columnsMap, test) {

	// Get a new map of tN columns
	(columns, states, columnsMapNew) = CustomKeywords.'unfoldingWord_Keywords.Work_with_Settings_Card.getColumnsList'('states')
	
	count = 0
	errCount = 0
	columnsMap.each { name, state ->
		myState = columnsMapNew.get(name)
		if (myState != state) {
			println('ERROR: The state of ' + name + ' was ' + state + ' but now is ' + myState)
			errCount ++
		}
		count ++
	}
	
	if (errCount > 0) {
		if (printAll) {
			msg = 'Test after ' + test + ' failed because ' + count + ' columns were tested and ' + errCount + ' have changed.'
			println('ERROR: ' + msg)
		} 
		msg = 'WAS - '
		columnsMap.each { name, state ->
			if (state) {
				code = 'Y'
			} else {
				code = 'N'
			}
			msg = msg + code + '   '
		}
	
		msg = msg + '   NOW - '
		columnsMapNew.each { name, state ->
			if (state) {
				code = 'Y'
			} else {
				code = 'N'
			}
			msg = msg + code + '   '
		}
		
		if (columns.contains('OccurrenceNote')) {
			if (columnsMap.get('OccurrenceNote')) {
				msg = msg + ' - - - UNEXPECTED'
			}
		} else {
			if (columnsMap.get('Reference') && columnsMap.get('Note')) {
				msg = msg + ' - - - UNEXPECTED'
			}
		}
		
		if (!orgFail) {
			if (columns.contains('OccurrenceNote')) {
				header = '      B   C   V   I   O   S   O   G   O            B   C   V   I   O   S   O   G   O'
//						  WAS - N   Y   Y   Y   Y   N   N   Y   N      NOW - Y   Y   Y   Y   Y   Y   Y   Y   Y
			} else { 
				header = '      B   C   V   R   I   O   S   Q   T   N            B   C   V   R   I   O   S   Q   T   N'
//				header = 'WAS - N   N   N   N   Y   Y   Y   Y   N   N      NOW - Y   Y   Y   Y   Y   Y   Y   Y   Y   Y'
			}
			
			CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(header)
			
			orgFail = true
		}
		
		if (loop >= loopMax) {
			msg = msg + '\n'
		}
		
		CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)
		
	}

}
