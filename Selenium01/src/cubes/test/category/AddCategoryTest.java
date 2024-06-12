package cubes.test.category;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cubes.driver.MyWebDriver;
import cubes.main.URLConst;
import cubes.main.Utils;
import cubes.pages.AddCategoryPage;
import cubes.pages.CategoryListPage;
import cubes.pages.LoginPage;

class AddCategoryTest {

	private static WebDriver driver;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		driver = MyWebDriver.getInstance().getDriver("chrome");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginSuccess();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		driver.get(URLConst.CATEGORIES_LIST);
	}

	@AfterEach
	void tearDown() throws Exception {

	}

	@Test
	void tc01() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		addCategoryPage.clickOnSave();

		assertEquals(addCategoryPage.isNameErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addCategoryPage.isDescriptionErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addCategoryPage.getNameErrorText(), "This field is required.",
				"Poruka nije dobra, proveriti tekst");
		assertEquals(addCategoryPage.getDescriptionErrorText(), "This field is required.",
				"Poruka nije dobra, proveriti tekst");
		assertEquals(driver.getCurrentUrl(), URLConst.CATEGORIES_ADD, "URL nije dobar");
	}

	@Test
	void tc02() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		addCategoryPage.insertCategoryName("");
		addCategoryPage.insertCategoryDescription("");
		addCategoryPage.clickOnSave();

		assertEquals(addCategoryPage.isNameErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addCategoryPage.isDescriptionErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addCategoryPage.getNameErrorText(), "This field is required.",
				"Poruka nije dobra, proveriti tekst");
		assertEquals(addCategoryPage.getDescriptionErrorText(), "This field is required.",
				"Poruka nije dobra, proveriti tekst");
		assertEquals(driver.getCurrentUrl(), URLConst.CATEGORIES_ADD, "URL nije dobar");
	}

	@Test
	void tc03() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName("");
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();

		assertEquals(addCategoryPage.isNameErrorDisplayed(), true, "Poruka nije prikazana");

		assertEquals(addCategoryPage.getNameErrorText(), "This field is required.",
				"Poruka nije dobra, proveriti tekst");

		assertEquals(driver.getCurrentUrl(), URLConst.CATEGORIES_ADD, "URL nije dobar");

	}

	@Test
	void tc04() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		addCategoryPage.insertCategoryName("");
		addCategoryPage.insertCategoryDescription("a");
		addCategoryPage.clickOnSave();

		assertEquals(addCategoryPage.isNameErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addCategoryPage.isInsufficientDescriptionErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addCategoryPage.getNameErrorText(), "This field is required.",
				"Poruka nije dobra, proveriti tekst");
		assertEquals(addCategoryPage.getInsufficientDescriptionErrorText(),
				"The description must be at least 50 characters.", "Poruka nije dobra, proveriti tekst");
		assertEquals(driver.getCurrentUrl(), URLConst.CATEGORIES_ADD, "URL nije dobar");

	}

	@Test
	void tc05() {

		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		addCategoryPage.insertCategoryName("");
		addCategoryPage.insertCategoryDescription("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		addCategoryPage.clickOnSave();

		assertEquals(addCategoryPage.isNameErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addCategoryPage.isInsufficientDescriptionErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addCategoryPage.getNameErrorText(), "This field is required.",
				"Poruka nije dobra, proveriti tekst");
		assertEquals(addCategoryPage.getInsufficientDescriptionErrorText(),
				"The description must be at least 50 characters.", "Poruka nije dobra, proveriti tekst");
		assertEquals(driver.getCurrentUrl(), URLConst.CATEGORIES_ADD, "URL nije dobar");
	}

	@Test
	void tc06() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription("a");
		addCategoryPage.clickOnSave();

		assertEquals(addCategoryPage.isInsufficientDescriptionErrorDisplayed(), true, "Poruka nije prikazana");

		assertEquals(addCategoryPage.getInsufficientDescriptionErrorText(),
				"The description must be at least 50 characters.", "Poruka nije dobra, proveriti tekst");
		assertEquals(driver.getCurrentUrl(), URLConst.CATEGORIES_ADD, "URL nije dobar");

	}

	@Test
	void tc07() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		addCategoryPage.clickOnSave();

		assertEquals(addCategoryPage.isInsufficientDescriptionErrorDisplayed(), true, "Poruka nije prikazana");

		assertEquals(addCategoryPage.getInsufficientDescriptionErrorText(),
				"The description must be at least 50 characters.", "Poruka nije dobra, proveriti tekst");
		assertEquals(driver.getCurrentUrl(), URLConst.CATEGORIES_ADD, "URL nije dobar");

	}

	@Test
	void tc08() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		addCategoryPage.insertCategoryName("");
		addCategoryPage.insertCategoryDescription("");
		addCategoryPage.clickOnCancel();

		assertEquals(driver.getCurrentUrl(), URLConst.CATEGORIES_LIST, "URL nije dobar");

	}

	@Test
	void tc09() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnCancel();

		assertEquals(driver.getCurrentUrl(), URLConst.CATEGORIES_LIST, "URL nije dobar");

		CategoryListPage categoryListPage = new CategoryListPage(driver);
		assertEquals(categoryListPage.countCategoryWithName(tempCategoryName), 0, "Kategorija se pojavljuje");

	}

	@Test
	void tc10() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();

		addCategoryPage.openPage();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();

		assertEquals(driver.getCurrentUrl(), URLConst.CATEGORIES_ADD, "URL nije dobar");
		assertEquals(addCategoryPage.isExistingNameErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addCategoryPage.getExistingNameErrorText(), "The name has already been taken.",
				"Poruka nije dobra, proveriti tekst");
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		categoryListPage.openPage();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

		}

		categoryListPage.deleteCategory(tempCategoryName);

	}

	@Test
	void tc11() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();

		assertEquals(driver.getCurrentUrl(), URLConst.CATEGORIES_LIST, "URL nije dobar");

		CategoryListPage categoryListPage = new CategoryListPage(driver);
		int countCategories = categoryListPage.countCategoryWithName(tempCategoryName);

		assertEquals(countCategories, 1, "Kategorija se ne nalazi u listi ili ih ima vise");

		categoryListPage.deleteCategory(tempCategoryName);
	}

	@Test
	void tc12() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);

		categoryListPage.clickOnAddNewCategory();

		assertEquals(driver.getCurrentUrl(), URLConst.CATEGORIES_ADD, "URL nije dobar");
	}

	@Test
	void tc13() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);

		categoryListPage.clickOnChangeOrderButton();
		categoryListPage.clickOnCancelOrderChange();

		assertEquals(driver.getCurrentUrl(), URLConst.CATEGORIES_LIST, "URL nije dobar");
	}

	@Test
	void tc14() {
		CategoryListPage categoryListPage = new CategoryListPage(driver);

		categoryListPage.clickOnChangeOrderButton();
		categoryListPage.clickOnSaveOrderChange();

		assertEquals(driver.getCurrentUrl(), URLConst.CATEGORIES_LIST, "URL nije dobar");
	}

	@Test
	void tc15() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();

		addCategoryPage.openPage();

		String tempCategoryName2 = Utils.getRandomCategoryName();
		String randomDescription2 = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName2);
		addCategoryPage.insertCategoryDescription(randomDescription2);
		addCategoryPage.clickOnSave();

		CategoryListPage categoryListPage = new CategoryListPage(driver);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

		}

		categoryListPage.clickOnChangeOrderButton();

		categoryListPage.changeCategoryOrder(tempCategoryName2, tempCategoryName);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

		}

		categoryListPage.clickOnSaveOrderChange();

		boolean isOrderChanged = categoryListPage.isOrderChanged();

		assertTrue(isOrderChanged, "Redosled kategorija se nije promenio");
		assertEquals(driver.getCurrentUrl(), URLConst.CATEGORIES_LIST, "URL nije dobar");

	}

	@Test
	void tc16() {
		
		String currentTab = driver.getWindowHandle();
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);
		
		Set<String> allTabs = driver.getWindowHandles();
	
	String tempCategoryName = Utils.getRandomCategoryName();
	String randomDescription = Utils.getRandomCategoryDescription();
	
	addCategoryPage.insertCategoryName(tempCategoryName);
	addCategoryPage.insertCategoryDescription(randomDescription);
	addCategoryPage.clickOnSave();
	
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		
		categoryListPage.clickOnViewCategory(tempCategoryName);
		
		Set<String> allTabsAfterClick = driver.getWindowHandles();
		
		String newTab = "";
	    for (String tab : allTabsAfterClick) {
	        if (!allTabs.contains(tab)) {
	            newTab = tab;
	            break;
	        }
	    }
	    
	    if (!newTab.isEmpty()) {
	        driver.switchTo().window(newTab);
		
	assertTrue(categoryListPage.viewCategoryURLContainsCategory(tempCategoryName), "URL nije dobar");}
	    
	    driver.close();
	    
	    driver.switchTo().window(currentTab);
		
		

	}

	@Test
	void tc17() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();

		CategoryListPage categoryListPage = new CategoryListPage(driver);

		categoryListPage.clickOnEditCategory(tempCategoryName);

		assertEquals(tempCategoryName, addCategoryPage.getCategoryNameText(), "Naziv kategorije nije dobar");

	}

	@Test
	void tc18() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();

		CategoryListPage categoryListPage = new CategoryListPage(driver);

		categoryListPage.clickOnEditCategory(tempCategoryName);

		assertEquals(tempCategoryName, addCategoryPage.getCategoryNameText(), "Naziv kategorije nije dobar");

		String tempCategoryNameEdited = Utils.getRandomCategoryName();

		addCategoryPage.insertCategoryName(tempCategoryNameEdited);

		addCategoryPage.clickOnCancel();

		assertEquals(driver.getCurrentUrl(), URLConst.CATEGORIES_LIST, "URL niije dobar");

		int countCategories = categoryListPage.countCategoryWithName(tempCategoryNameEdited);
		assertEquals(countCategories, 0, "Kategorija je editovana");

		categoryListPage.deleteCategory(tempCategoryName);
	}

	@Test
	void tc19() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();

		CategoryListPage categoryListPage = new CategoryListPage(driver);

		categoryListPage.clickOnEditCategory(tempCategoryName);

		assertEquals(tempCategoryName, addCategoryPage.getCategoryNameText(), "Naziv kategorije nije dobar");
		
		addCategoryPage.insertCategoryName("");
		addCategoryPage.clickOnSave();
		
	
		
		assertEquals(addCategoryPage.isNameErrorDisplayed(),true,"Poruka nije prikazana");
		assertEquals(addCategoryPage.getNameErrorText(),"This field is required.","Poruka nije dobra, proveriti tekst");
		
		categoryListPage.openPage();
		categoryListPage.deleteCategory(tempCategoryName);

	}
	
	@Test
	void tc20() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();

		CategoryListPage categoryListPage = new CategoryListPage(driver);

		categoryListPage.clickOnEditCategory(tempCategoryName);

		assertEquals(tempCategoryName, addCategoryPage.getCategoryNameText(), "Naziv kategorije nije dobar");
		
		addCategoryPage.insertCategoryDescription("");
		addCategoryPage.clickOnSave();
		
		assertEquals(addCategoryPage.isDescriptionErrorDisplayed(),true,"Poruka nije prikazana");
		assertEquals(addCategoryPage.getDescriptionErrorText(),"This field is required.","Poruka nije dobra, proveriti tekst");
		
		categoryListPage.openPage();
		categoryListPage.deleteCategory(tempCategoryName);
		
		
		
	
	}
	
	@Test
	void tc21() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();

		CategoryListPage categoryListPage = new CategoryListPage(driver);

		categoryListPage.clickOnEditCategory(tempCategoryName);

		assertEquals(tempCategoryName, addCategoryPage.getCategoryNameText(), "Naziv kategorije nije dobar");
		
		addCategoryPage.insertCategoryDescription("a");
		addCategoryPage.clickOnSave();
		
		assertEquals(addCategoryPage.isInsufficientDescriptionErrorDisplayed(),true,"Poruka nije prikazana");
		assertEquals(addCategoryPage.getInsufficientDescriptionErrorText(),"The description must be at least 50 characters.","Poruka nije dobra, proveriti tekst");
		
		categoryListPage.openPage();
		categoryListPage.deleteCategory(tempCategoryName);
		
		
		
	
	}
	
	@Test
	void tc22() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);
		
		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();
		
		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();
		
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		
		categoryListPage.clickOnDeleteCategory(tempCategoryName);
		categoryListPage.clickOnDialogCancelButton();
		
		assertEquals(driver.getCurrentUrl(), URLConst.CATEGORIES_LIST,"URL nije dobar");
		
		assertTrue(categoryListPage.isCategoryWithNameInList(tempCategoryName), "Kategorija je izbrisana");

	}
	@Test
	void tc23() {
		
	
	AddCategoryPage addCategoryPage = new AddCategoryPage(driver);
	
	String tempCategoryName = Utils.getRandomCategoryName();
	String randomDescription = Utils.getRandomCategoryDescription();
	
	addCategoryPage.insertCategoryName(tempCategoryName);
	addCategoryPage.insertCategoryDescription(randomDescription);
	addCategoryPage.clickOnSave();
	
	CategoryListPage categoryListPage = new CategoryListPage(driver);
	
	categoryListPage.clickOnDeleteCategory(tempCategoryName);
	categoryListPage.clickOnDialogDeleteButton();
	
	assertEquals(driver.getCurrentUrl(), URLConst.CATEGORIES_LIST,"URL nije dobar");
	
	int countCategories = categoryListPage.countCategoryWithName(tempCategoryName);
	assertEquals(countCategories,0,"Kategorija nije izbrisana");
	
	}

}
