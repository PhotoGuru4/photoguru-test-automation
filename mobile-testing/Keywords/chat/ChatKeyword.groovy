package chat

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

class ChatKeyword {

	def clickLatestBookNow() {

		// Scroll xuống cuối (quan trọng cho chat)
		Mobile.swipe(500, 1500, 500, 300)

		TestObject bookBtn = new TestObject()
		bookBtn.addProperty("xpath", ConditionType.EQUALS,
				"(//android.view.ViewGroup[@content-desc='Book now'])[last()]"
				)

		Mobile.waitForElementPresent(bookBtn, 15)
		Mobile.tap(bookBtn, 10)
	}
}