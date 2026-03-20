package common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class CommonRegister {
	static def openRegisterScreen() {
		Mobile.waitForElementPresent(
				findTestObject('Object Repository/Page_Welcome/btn_Register'),
				10
				)

		Mobile.tap(findTestObject('Object Repository/Page_Welcome/btn_Register'), 10)
	}

	static int counter = 1

	static def generateUsername() {
		String number = String.format("%02d", counter)
		int random = new Random().nextInt(100)
		return "test" + number + random
	}

	static def generateEmail() {
		String number = String.format("%02d", counter)
		int random = new Random().nextInt(100)
		counter++
		return "test" + number + random + "@gmail.com"
	}

	static def getValidData() {
		return [
			username: generateUsername(),
			email: generateEmail(),
			province: GlobalVariable.province,
			ward: GlobalVariable.ward,
			password: GlobalVariable.password,
			confirm_password: GlobalVariable.password
		]
	}
}
