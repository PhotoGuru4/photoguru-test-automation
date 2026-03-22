package booking

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

class BookingKeyword {

	def selectPackage(String packageName) {

		TestObject pkg = new TestObject()
		pkg.addProperty("xpath", ConditionType.EQUALS,
				"//android.widget.TextView[@text='" + packageName + "']"
				)

		Mobile.waitForElementPresent(pkg, 10)
		Mobile.tap(pkg, 10)
	}

	def selectDate(String date) {

		TestObject d = new TestObject()
		d.addProperty("xpath", ConditionType.EQUALS,
				"//android.widget.TextView[@text='" + date + "']"
				)

		Mobile.tap(d, 10)
	}

	def selectTime(String time) {

		TestObject t = new TestObject()
		t.addProperty("xpath", ConditionType.EQUALS,
				"//android.widget.TextView[@text='" + time + "']"
				)

		Mobile.tap(t, 10)
	}

	def confirmBooking() {
		Mobile.tap(findTestObject('Object Repository/Page_Booking/btn_Confirm'), 10)
	}

	def verifyBookingSuccess() {

		Mobile.waitForElementPresent(findTestObject('Object Repository/Page_Booking/lbl_SuccessMessage'), 15)
		Mobile.verifyElementExist(findTestObject('Object Repository/Page_Booking/lbl_SuccessMessage'), 10)
	}
}