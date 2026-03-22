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
import auth.LoginKeyword
import concept.ConceptKeyword

def login = new LoginKeyword()
def concept = new ConceptKeyword()

String url = GlobalVariable.baseUrl
String email = GlobalVariable.email
String password = GlobalVariable.password
String thumbnailPath = System.getProperty("user.dir") + "/Include/testdata/images/img1.jpg"
String imagePath = System.getProperty("user.dir") + "/Include/testdata/images/img2.jpg"


login.openLoginPage(url)
login.login(email, password)

// ACTION
concept.clickConceptMenu()

WebUI.verifyElementText(findTestObject('Object Repository/Page_CreateConcept/h3_ConceptTilte'), 'Portfolio Management')

concept.openAddConceptModal()

WebUI.verifyElementText(findTestObject('Object Repository/Page_CreateConcept/p_AddNewConcept'), 'Add New Concept')


concept.fillConceptInfo(
	'Family',
	'30',
	'Test description'
)

concept.uploadThumbnail(thumbnailPath)
concept.uploadImage(imagePath)

concept.clickContinue()

concept.inputPrice("100")

concept.selectLocation(1, 1)
concept.addLocation()

concept.addBenefit('Test benefit')

concept.savePackage()
concept.saveConcept()

WebUI.verifyElementText(findTestObject('Object Repository/Page_CreateConcept/h3_ConceptTilte'), 'Portfolio Management')

