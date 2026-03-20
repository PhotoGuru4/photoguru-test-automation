package auth

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

public class RegisterKeyword {
	@Keyword
	def register = { String username, String email, String province, String ward, String password, String confirm_password ->

		Mobile.setText(findTestObject('Object Repository/Page_Register/txt_Username'), username, 5)

		Mobile.setText(findTestObject('Object Repository/Page_Register/txt_Email'), email, 5)

		// Province
		Mobile.tap(findTestObject('Object Repository/Page_Register/ddl_Rrovince'), 5)
		//Mobile.scrollToText(province)
		Mobile.tap(
				findTestObject('Object Repository/Page_Register/opt_ProvinceDynamic', [
					('province') : province
				]),
				5
				)

		// Ward
		Mobile.tap(findTestObject('Object Repository/Page_Register/ddl_Ward'), 5)
		//Mobile.scrollToText(ward)
		Mobile.tap(
				findTestObject('Object Repository/Page_Register/opt_WardDynamic', [
					('ward') : ward
				]),
				5
				)

		Mobile.setText(findTestObject('Object Repository/Page_Register/txt_Password'), password, 5)

		Mobile.scrollToText("Confirm Password")
		Mobile.setText(findTestObject('Object Repository/Page_Register/txt_ConfirmPassword'), confirm_password, 5)

		Mobile.scrollToText("Register")
		Mobile.tap(findTestObject('Object Repository/Page_Register/btn_Register'), 10)
	}
}