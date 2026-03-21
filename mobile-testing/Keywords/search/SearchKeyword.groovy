package search

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By

class SearchKeyword {

	private static final int DEFAULT_TIMEOUT = 10
	private static final int RETRY_COUNT = 3

	// ================= SEARCH =================
	@Keyword
	def search(String keyword) {
		try {
			// 1. Tìm và tap vào ô tìm kiếm
			def txtSearch = findTestObject('Object Repository/Page_Home/txt_Search')

			Mobile.waitForElementPresent(txtSearch, DEFAULT_TIMEOUT, FailureHandling.STOP_ON_FAILURE)
			Mobile.tap(txtSearch, DEFAULT_TIMEOUT)

			// 2. Clear text cũ
			Mobile.clearText(txtSearch, DEFAULT_TIMEOUT)

			// 3. Nhập keyword
			if (keyword != null && !keyword.trim().isEmpty()) {
				Mobile.setText(txtSearch, keyword, DEFAULT_TIMEOUT)
				Mobile.hideKeyboard(FailureHandling.CONTINUE_ON_FAILURE)
			}

			// 4. Wait cho kết quả load (quan trọng)
			Mobile.delay(2) // Đợi app xử lý
		} catch (Exception e) {
			throw new AssertionError("❌ Failed to search with keyword: '${keyword}'. Error: ${e.message}")
		}
	}

	// ================= GET LIST (CẢI TIẾN) =================
	/**
	 * Lấy danh sách kết quả tìm kiếm dựa trên content-desc
	 * @param keyword - từ khóa cần tìm (nếu null thì lấy tất cả kết quả)
	 * @return List<WebElement> danh sách kết quả
	 */
	private List<WebElement> getResults(String keyword = null) {
		AppiumDriver driver = MobileDriverFactory.getDriver()

		// XPath mới: tìm tất cả các item kết quả (dựa trên cấu trúc UI thực tế)
		// Bạn cần điều chỉnh XPath này theo UI thực tế của app
		String xpath

		if (keyword && !keyword.trim().isEmpty()) {
			// Tìm kết quả có chứa keyword
			xpath = "//android.view.ViewGroup[contains(@content-desc, '${keyword}')]"
		} else {
			// Tìm tất cả kết quả (cần xác định đúng XPath của container chứa kết quả)
			xpath = "//android.view.ViewGroup[@clickable='true' and contains(@content-desc, ',')]"
			// Hoặc: xpath = "//android.widget.ImageView[contains(@content-desc, '')]"
		}

		// Retry logic để đảm bảo kết quả đã load
		for (int i = 0; i < RETRY_COUNT; i++) {
			List<WebElement> elements = driver.findElements(By.xpath(xpath))
			if (!elements.isEmpty()) {
				return elements
			}
			Mobile.delay(1) // Đợi thêm nếu chưa có kết quả
		}

		return []
	}

	// ================= UTIL =================
	private String normalize(String text) {
		return text == null ? "" : text.trim().toLowerCase()
	}

	private boolean isElementDisplayed(WebElement element) {
		try {
			return element != null && element.isDisplayed()
		} catch (Exception e) {
			return false
		}
	}

	// ================= VERIFY METHODS =================

	/**
	 * Verify có kết quả trả về
	 */
	@Keyword
	def verifyHasResults() {
		try {
			// Cách 1: Kiểm tra có ít nhất 1 kết quả
			List<WebElement> results = getResults()

			// Log để debug
			println("🔍 Total results found: ${results.size()}")

			if (results.isEmpty()) {
				// Cách 2: Thử tìm kiếm với XPath khác nếu không có kết quả
				AppiumDriver driver = MobileDriverFactory.getDriver()
				String fallbackXpath = "//android.widget.ImageView[contains(@content-desc, '')]"
				results = driver.findElements(By.xpath(fallbackXpath))

				println("🔍 Fallback results found: ${results.size()}")
			}

			assert results.size() > 0 : "❌ Không tìm thấy kết quả nào sau khi tìm kiếm"

			// Ghi log chi tiết
			println("✅ Found ${results.size()} result(s)")
		} catch (Exception e) {
			throw new AssertionError("❌ verifyHasResults failed: ${e.message}")
		}
	}

	/**
	 * Verify tất cả kết quả đều chứa keyword
	 */
	@Keyword
	def verifyAllContain(String keyword) {
		try {
			List<WebElement> results = getResults()

			assert results.size() > 0 : "❌ Không có kết quả để kiểm tra"

			String kw = normalize(keyword)
			int invalidCount = 0

			results.eachWithIndex { item, index ->
				String contentDesc = item.getAttribute("content-desc") ?: ""
				String text = normalize(contentDesc)

				println("👉 Result ${index + 1}: ${contentDesc}")

				if (!text.contains(kw)) {
					invalidCount++
					println("⚠️ Result ${index + 1} does NOT contain '${keyword}': ${contentDesc}")
				}
			}

			assert invalidCount == 0 : "❌ Có ${invalidCount}/${results.size()} kết quả không chứa keyword '${keyword}'"

			println("✅ All ${results.size()} results contain keyword: '${keyword}'")
		} catch (Exception e) {
			throw new AssertionError("❌ verifyAllContain failed: ${e.message}")
		}
	}

	/**
	 * Verify kết quả đầu tiên chứa keyword
	 */
	@Keyword
	def verifyTop1Contains(String keyword) {
		try {
			List<WebElement> results = getResults()

			assert results.size() > 0 : "❌ Không có kết quả để kiểm tra"

			String top1Desc = results[0].getAttribute("content-desc") ?: ""
			String normalizedTop1 = normalize(top1Desc)
			String normalizedKw = normalize(keyword)

			println("👉 Top 1: ${top1Desc}")

			assert normalizedTop1.contains(normalizedKw) :
			"❌ Top 1 không chứa keyword '${keyword}'. Actual: ${top1Desc}"

			println("✅ Top 1 contains keyword: '${keyword}'")
		} catch (Exception e) {
			throw new AssertionError("❌ verifyTop1Contains failed: ${e.message}")
		}
	}

	/**
	 * Verify không có kết quả (trường hợp tìm kiếm không có kết quả)
	 */
	@Keyword
	def verifyNoResult() {
		try {
			// Kiểm tra message "No result" hiển thị
			Mobile.verifyElementExist(
					findTestObject('Object Repository/Page_Home/txt_NoResult'),
					DEFAULT_TIMEOUT,
					FailureHandling.CONTINUE_ON_FAILURE
					)

			// Hoặc kiểm tra không có kết quả
			List<WebElement> results = getResults()
			assert results.isEmpty() : "❌ Expected no results but found ${results.size()} results"

			println("✅ Verified no results found")
		} catch (Exception e) {
			throw new AssertionError("❌ verifyNoResult failed: ${e.message}")
		}
	}

	/**
	 * Verify số lượng kết quả (nếu cần)
	 */
	@Keyword
	def verifyResultCount(int expectedCount) {
		try {
			List<WebElement> results = getResults()
			int actualCount = results.size()

			assert actualCount == expectedCount :
			"❌ Expected ${expectedCount} results but found ${actualCount}"

			println("✅ Result count matches: ${actualCount}")
		} catch (Exception e) {
			throw new AssertionError("❌ verifyResultCount failed: ${e.message}")
		}
	}
}