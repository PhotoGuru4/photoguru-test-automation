package concept

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
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import internal.GlobalVariable


public class ConceptKeyword {

	@Keyword
	def clickConceptMenu() {
		WebUI.click(findTestObject('Object Repository/Page_Home/btn_Concept'))
	}

	@Keyword
	def openAddConceptModal() {
		WebUI.click(findTestObject('Object Repository/Page_CreateConcept/btn_AddNewConcept'))
	}

	@Keyword
	def fillConceptInfo(String title, String categoryValue, String description) {
		WebUI.setText(findTestObject('Object Repository/Page_CreateConcept/txt_Title'), title)
		WebUI.selectOptionByValue(findTestObject('Object Repository/Page_CreateConcept/ddl_Category'), categoryValue, false)
		WebUI.setText(findTestObject('Object Repository/Page_CreateConcept/txt_Description'), description)
	}

	@Keyword
	def uploadThumbnail(String path) {
		WebUI.waitForElementPresent(
				findTestObject('Object Repository/Page_CreateConcept/ima_UploadThumbnail'),
				10
				)

		WebUI.uploadFile(findTestObject('Object Repository/Page_CreateConcept/ima_UploadThumbnail'), path)
	}

	@Keyword
	def uploadImage(String path) {
		// Bước 1: Chờ element visible
		WebUI.waitForElementVisible(findTestObject('Object Repository/Page_CreateConcept/ima_UploadImages'), 15)

		// Bước 2: Lấy WebElement
		def element = WebUiCommonHelper.findWebElement(
				findTestObject('Object Repository/Page_CreateConcept/ima_UploadImages'), 30
				)

		// Bước 3: Bật hiển thị nếu bị hidden
		WebUI.executeJavaScript("arguments[0].style.display='block';", Arrays.asList(element))

		// Bước 4: Upload file
		WebUI.uploadFile(findTestObject('Object Repository/Page_CreateConcept/ima_UploadImages'), path)
	}


	@Keyword
	def clickContinue() {
		WebUI.click(findTestObject('Object Repository/Page_CreateConcept/btn_Continue'))
	}

	// 🔥 NEW: INPUT PRICE (QUAN TRỌNG)
	@Keyword
	def inputPrice(String price = null) {
		if (price) {
			WebUI.waitForElementVisible(findTestObject('Object Repository/Page_CreateConcept/txt_Price'), 10)
			WebUI.setText(findTestObject('Object Repository/Page_CreateConcept/txt_Price'), price)
		}
	}

	@Keyword
	def selectLocation(int provinceIndex, int wardIndex) {
		WebUI.selectOptionByIndex(findTestObject('Object Repository/Page_CreateConcept/ddl_Province'), provinceIndex)
		WebUI.delay(2)
		WebUI.selectOptionByIndex(findTestObject('Object Repository/Page_CreateConcept/ddl_Ward'), wardIndex)
	}

	@Keyword
	def addLocation() {
		WebUI.click(findTestObject('Object Repository/Page_CreateConcept/btn_AddLocations'))
	}


	@Keyword
	def addBenefit(String benefit) {
		WebUI.setText(findTestObject('Object Repository/Page_CreateConcept/txt_Benefits'), benefit)
		WebUI.click(findTestObject('Object Repository/Page_CreateConcept/btn_AddBenefits'))
	}


	@Keyword
	def savePackage() {
		WebUI.click(findTestObject('Object Repository/Page_CreateConcept/btn_SavePackage'))
	}

	@Keyword
	def saveConcept() {
		WebUI.click(findTestObject('Object Repository/Page_CreateConcept/btn_SaveConcept'))
	}
}

