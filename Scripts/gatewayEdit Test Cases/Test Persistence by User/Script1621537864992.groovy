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

// Test scripture references
refs = [['luk','24','53'], ['rut','3','13'], ['1jn','5','15'], ['rom','16','27'], ['neh','12,12'], ['rev','9','18']]
	
// Array of card id's and options
cards = ['0':'Scripture','1':'Scripture','2':'Scripture','tn':'Markdown','ta':'Markdown','twl':'No_Markdown','twa':'Markdown','tq':'Markdown']

orgs = ['test_org','test_org2']
//orgs = ['test_org']

users = ['tc01', 'tcc001']

for (user in users) {
//users.each { user ->

	myUser = users.indexOf(user)
		
	myOrg = myUser
	
	org = orgs[myOrg]
	
	orgFail = false

	loopMax = 1
	
	loop = 1
	
	first = true
	
	while (loop <= loopMax) {
	
		println('################ LOOP ' + loop)
		
		markdowns = [:]
		
		fontSizes = [:]
		
		WebUI.callTestCase(findTestCase('Components/LogIn'), [('user') : user, ('organization'): org])
		
		ref = Math.abs(new Random().nextInt() % (refs.size()))
		
		reference = refs[ref]
		
		CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.setScriptureReference'(reference[0], reference[1], reference[2])
		
		if (myUser == 0) {
			reference0 = reference
		} else {
			reference1 = reference
		}

		
		cards.each { id, font ->
			
			println('Processing ' + id + ' card')
			
			WebUI.click(findTestObject('Page_Main/menu_card_Parmed', [('resource') : id]))
			
			result = Math.abs(new Random().nextInt() % (refs.size()+1))
			
			
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
				
				if (myUser == 0) {
					markdowns0 = markdowns
				} else {
					markdowns1 = markdowns
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
			
			if (myUser == 0) {
				fontSizes0 = fontSizes
			} else {
				fontSizes1 = fontSizes
			}
			
			if (id == 'tn') {
			
				// Get a list of the tN columns
				(columns, states, columnsMap) = CustomKeywords.'unfoldingWord_Keywords.Work_with_Settings_Card.getColumnsList'()
				
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
					
					columnsMap.put(name, state)	
				}
				
				if (myUser == 0) {
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
					testPersistence(columnsMap, 'reopening settings card', columns)
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
				testPersistence(columnsMap, 'opening a new tab', columns)
			}
			
		}
		
		println(columnsMap)
		
		println(markdowns)
		
		println(fontSizes)

		// Log out and back in
		WebUI.callTestCase(findTestCase('Components/LogOut'), [:])
		
		msg = 'Testing ' + user + ' and ' + org + ' after logging out.'
		
		CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendInfoMessage'(msg)
		
		testByUserOrg(user, org, markdowns, fontSizes, columnsMap, columns, reference )
		
	loop ++
	}

}

// Now test for persistence after changing users and then after opening new tabs
tabs = false

done = false

while (!done) {
	users.each { user ->
		
		myUser = users.indexOf(user)
		
		myOrg = myUser
		
		org = orgs[myOrg]
			
		if (myUser == 0) {
			markdowns = markdowns0
			fontSizes = fontSizes0
			columnsMap = columnsMap0
			reference = reference0
		} else {
			markdowns = markdowns1
			fontSizes = fontSizes1
			columnsMap = columnsMap1
			reference = reference1
		}
		
		if (tabs) {
			
			done = true
			
			// Open a second browser tab
			if (myBrowser.contains('chrome')) {
				WebUI.executeJavaScript('window.open();', [])
			} else if (system.contains('Windows')) {
				WebUI.sendKeys(findTestObject('Page_tCC translationNotes/tNText_GLOutlinePoint1'), Keys.chord(Keys.CONTROL, 't'))
			}
			
			currentWindow = WebUI.getWindowIndex()
			
			//Go to new tab
			WebUI.switchToWindowIndex(currentWindow + 1)
			
			msg = 'Testing ' + user + ' and ' + org + ' in a new tab.'
			
		} else {
			
			msg = 'Testing ' + user + ' and ' + org + ' after changing users.'		
		}
		
		CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendInfoMessage'(msg)
			
		testByUserOrg(user, org, markdowns, fontSizes, columnsMap, columns, reference )
	}
	
	tabs = true
	
	myBrowser = CustomKeywords.'unfoldingWord_Keywords.GetTestingConfig.getBrowserAndVersion'()
	
	if (GlobalVariable.systemOS.contains('Mac') && !myBrowser.contains('chrome')) {
		msg = 'Bypassed testing new tabs in Firefox on Mac'
		CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendInfoMessage'(msg)
		done = true
	}
	
}		


GlobalVariable.scriptRunning = false

WebUI.closeBrowser()

def testPersistence(columnsMap, test, columns) {

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
//			msg = columns
//			CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendInfoMessage'(msg)
			
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

def testByUserOrg(user, org, markdowns, fontSizes, columnsMap, columns, reference) {
	
	WebUI.callTestCase(findTestCase('Components/LogIn'), [('user') : user, ('organization'): org])
	
	(book, chapter, verse) = CustomKeywords.'unfoldingWord_Keywords.Scripture_Card.getScriptureReference'()
	
	refNew = book + ' ' + chapter + ':' + verse
	
	refSet = reference[0] + ' ' + reference[1] + ':' + reference[2]
	
	if (refNew != refSet) {
		msg = 'Test failed because the scripture reference is ' + refNew + ' and was set to ' + refSet
		println(msg)
		CustomKeywords.'unfoldingWord_Keywords.SendMessage.SendFailMessage'(msg)

	}
	

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
			
			println('test columns = ' + columns)
			println('test columnsMap = ' + columnsMap)

			testPersistence(columnsMap, 'new log in', columns)
		}
		
		// Close the settings card
		WebUI.scrollToElement(findTestObject('Object Repository/Card_Settings/button_Settings_Close'), 1)
		WebUI.click(findTestObject('Object Repository/Card_Settings/button_Settings_Close'))
			

	}
}