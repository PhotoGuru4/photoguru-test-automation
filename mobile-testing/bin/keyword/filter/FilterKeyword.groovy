package filter

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

public class FilterKeyword {
	def selectProvince(String province) {

		Mobile.tap(findTestObject('Object Repository/Page_Home/ddl_Province'), 5)

		Mobile.tap(
				findTestObject('Object Repository/Page_Home/opt_ProvinceDynamic', [
					('province') : province
				]),
				5
				)
	}

	def selectWard(String ward) {

		Mobile.tap(findTestObject('Object Repository/Page_Home/ddl_Ward'), 5)

		Mobile.tap(
				findTestObject('Object Repository/Page_Home/opt_WardDynamic', [
					('ward') : ward
				]),
				5
				)
	}

	// Filter full
	def filterByLocation(String province, String ward) {

		if(province != null && province != "") {
			selectProvince(province)
		}

		if(ward != null && ward != "") {
			selectWard(ward)
		}
	}
}
