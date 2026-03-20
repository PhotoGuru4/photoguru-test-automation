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

public class CommonLogin {

	static def openLoginScreen() {
		Mobile.waitForElementPresent(
				findTestObject('Object Repository/Page_Welcome/btn_Login'),
				10
				)

		Mobile.tap(findTestObject('Object Repository/Page_Welcome/btn_Login'), 10)
	}

	static def getValidData() {
		return [
			email: GlobalVariable.email,
			password: GlobalVariable.password
		]
	}

	static def getInvalidPasswordData() {
		return [
			email: GlobalVariable.email,
			password: "password102"
		]
	}

	static def getEmptyData() {
		return [
			email: "",
			password: ""
		]
	}
}

