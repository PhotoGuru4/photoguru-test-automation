package search

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
import com.kms.katalon.core.mobile.driver.MobileDriverFactory
import io.appium.java_client.AppiumDriver
import org.openqa.selenium.WebElement

import internal.GlobalVariable


public class SearchKeyword {

	@Keyword
	def search(String keyword) {
		TestObject txtSearch = findTestObject('Object Repository/Page_Home/txt_Search')

		Mobile.tap(txtSearch, 5)
		Mobile.clearText(txtSearch, 5)

		if (keyword == null || keyword.trim().isEmpty()) {
			return
		}

		Mobile.setText(txtSearch, keyword, 5)

		Mobile.waitForElementPresent(
				findTestObject('Object Repository/Page_Home/lst_ConceptTitle'),
				10,
				FailureHandling.STOP_ON_FAILURE
				)
	}

	private List<WebElement> getList() {
		AppiumDriver driver = MobileDriverFactory.getDriver()
		return driver.findElementsByXPath("//android.widget.TextView")
	}

	@Keyword
	def verifyHasResults() {
		List<WebElement> results = getList()
		assert results.size() > 0 : "No results found"
	}

	@Keyword
	def verifyContainsAll(String keyword) {
		List<WebElement> list = getList()

		assert list.size() > 0 : "No results"

		list.each {
			assert it.getText().toLowerCase().contains(keyword.toLowerCase()) :
			"Not contain keyword: " + it.getText()
		}
	}

	@Keyword
	def verifyNoResult() {
		Mobile.verifyElementExist(
				findTestObject('Object Repository/Page_Home/txt_NoResult'),
				10
				)
	}

	@Keyword
	def verifyPriorityTop(String keyword, int topN = 3) {
		List<WebElement> list = getList()

		assert list.size() > 0 : "No results"

		int limit = Math.min(topN, list.size())
		boolean found = false

		for (int i = 0; i < limit; i++) {
			if (list[i].getText().toLowerCase().contains(keyword.toLowerCase())) {
				found = true
				break
			}
		}

		assert found : "Keyword not in top " + topN
	}
}