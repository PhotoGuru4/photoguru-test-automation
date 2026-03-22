package concept

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

class ConceptKeyword {

	def selectConceptByName(String name) {

		TestObject concept = new TestObject()
		concept.addProperty("xpath", ConditionType.EQUALS,
				"(//android.view.ViewGroup[.//android.widget.TextView[contains(@text,'" + name + "')]])[1]"
				)

		Mobile.waitForElementPresent(concept, 15)
		Mobile.tap(concept, 10)
	}

	def clickChatButton() {
		Mobile.waitForElementPresent(findTestObject('Object Repository/Page_ConceptDetail/btn_Chat'), 10)
		Mobile.tap(findTestObject('Object Repository/Page_ConceptDetail/btn_Chat'), 10)
	}
}