package keywords.common

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import internal.GlobalVariable

public class CommonKeywords {

	/**
	 * Open browser with URL
	 * @param url website URL
	 */
	def openBrowserAndNavigate(String url) {
		WebUI.openBrowser('')
		WebUI.maximizeWindow()
		WebUI.navigateToUrl(url)
		WebUI.delay(2)
	}

	/**
	 * Wait for page to load completely
	 */
	def waitForPageReady() {
		WebUI.delay(1)
		WebUI.waitForPageLoad(10)
	}

	/**
	 * Close browser
	 */
	def closeBrowser() {
		WebUI.closeBrowser()
	}

	/**
	 * Verify element present with timeout
	 * @param testObject object to verify
	 * @param timeout timeout in seconds
	 */
	def verifyElementPresent(TestObject testObject, int timeout = 10) {
		WebUI.waitForElementPresent(testObject, timeout)
		WebUI.verifyElementPresent(testObject, timeout)
	}

	/**
	 * Click with wait
	 * @param testObject object to click
	 */
	def safeClick(TestObject testObject) {
		WebUI.waitForElementClickable(testObject, 10)
		WebUI.click(testObject)
		WebUI.delay(1)
	}

	/**
	 * Input text with clear first
	 * @param testObject input field
	 * @param text text to input
	 */
	def safeInputText(TestObject testObject, String text) {
		WebUI.waitForElementVisible(testObject, 10)
		WebUI.clearText(testObject)
		WebUI.setText(testObject, text)
	}
}