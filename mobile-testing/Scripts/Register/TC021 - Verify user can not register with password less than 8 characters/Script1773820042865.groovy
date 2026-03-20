import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import common.CommonRegister as CommonRegister

CommonRegister.openRegisterScreen()

def data = CommonRegister.getValidData()
data.password = "123"

CustomKeywords.'auth.RegisterKeyword.register'(
	data.username,
	data.email,
	data.province,
	data.ward,
	data.password,
	data.confirm_password
)

Mobile.waitForElementPresent(
	findTestObject('Object Repository/Page_Register/msg_PasswordMinLength'),
	10
)

Mobile.verifyElementExist(findTestObject('Object Repository/Page_Register/msg_PasswordMinLength'), 5)
Mobile.verifyElementExist(findTestObject('Object Repository/Page_Register/msg_ConfirmPasswordNotMatch'), 5)

