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

// ================= PRECONDITION =================
Mobile.callTestCase(
	findTestCase('Test Cases/Login/TC008 - Verify customer can Login successfully with valid account'),
	[:],
	FailureHandling.STOP_ON_FAILURE
)

// ================= TEST DATA =================
String keyword = "hihi"  // Non-existent keyword

// ================= TEST STEPS =================
// Step 1: Search with non-existent keyword
CustomKeywords.'search.SearchKeyword.search'(keyword)

// Step 2: Verify no results message appears
CustomKeywords.'search.SearchKeyword.verifyNoResultMessage'()

// Step 3: Verify no result items displayed
CustomKeywords.'search.SearchKeyword.verifyNoResultsFound'()

