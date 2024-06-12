package cubes.test.posts;

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
import cubes.pages.AddPostPage;
import cubes.pages.CategoryListPage;
import cubes.pages.LoginPage;
import cubes.pages.PostListPage;

class AddPostsTest {

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
		driver.get(URLConst.POSTS_LIST);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void tc24() {
		AddPostPage addPostPage = new AddPostPage(driver);

		addPostPage.clickOnSave();

		assertEquals(addPostPage.isTitleErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.isDescriptionErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.isTagsErrorDisplayd(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.isContentErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.getTitleErrorText(), "This field is required.", "Poruka nije dobra, proveriti tekst");
		assertEquals(addPostPage.getDescriptionErrorText(), "This field is required.",
				"Poruka nije dobra, proveriti tekst");
		assertEquals(addPostPage.getTagErrorText(), "This field is required.", "Poruka nije dobra proveriti tekst");
		assertEquals(addPostPage.getContentErrorText(), "The content field is required.",
				"Poruka nije dobra, proveriti tekst");
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_ADD, "URL nije dobar");
	}

	@Test
	void tc25() {
		AddPostPage addPostPage = new AddPostPage(driver);

		addPostPage.insertPostTitle("");
		addPostPage.insertPostDescription("");
		addPostPage.insertContent("");
		addPostPage.clickOnSave();

		assertEquals(addPostPage.isTitleErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.isDescriptionErrorDisplayed(), true, "Poruka nije prikazana");

		assertEquals(addPostPage.isContentErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.getTitleErrorText(), "This field is required.", "Poruka nije dobra, proveriti tekst");
		assertEquals(addPostPage.getDescriptionErrorText(), "This field is required.",
				"Poruka nije dobra, proveriti tekst");
		assertEquals(addPostPage.getTagErrorText(), "This field is required.", "Poruka nije dobra proveriti tekst");
		assertEquals(addPostPage.getContentErrorText(), "The content field is required.",
				"Poruka nije dobra, proveriti tekst");
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_ADD, "URL nije dobar");

	}

	@Test
	void tc26() {
		AddPostPage addPostPage = new AddPostPage(driver);

		String randomDescription = Utils.getRandomCategoryDescription();

		addPostPage.insertPostTitle("");
		addPostPage.insertPostDescription(randomDescription);
		addPostPage.chooseRandomTag();
		addPostPage.insertContent("a");
		addPostPage.clickOnSave();

		assertEquals(addPostPage.isTitleErrorDisplayed(), true, "Poruka nije prikazana");

		assertEquals(addPostPage.getTitleErrorText(), "This field is required.", "Poruka nije dobra, proveriti tekst");

		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_ADD, "URL nije dobar");

	}

	@Test
	void tc27() {
		AddPostPage addPostPage = new AddPostPage(driver);

		addPostPage.insertPostTitle("");
		addPostPage.insertPostDescription("a");
		addPostPage.chooseRandomTag();
		addPostPage.insertContent("a");
		addPostPage.clickOnSave();

		assertEquals(addPostPage.isTitleErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.getTitleErrorText(), "This field is required.", "Poruka nije dobra, proveriti tekst");
		assertEquals(addPostPage.isDescriptionErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.getDescriptionErrorText(), "Please enter at least 50 characters.",
				"Poruka nije dobra proveriti tekst");
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_ADD, "URL nije dobar");
	}

	@Test
	void tc28() {
		AddPostPage addPostPage = new AddPostPage(driver);

		addPostPage.insertPostTitle("a");
		addPostPage.insertPostDescription("a");
		addPostPage.chooseRandomTag();
		addPostPage.insertContent("a");
		addPostPage.clickOnSave();

		assertEquals(addPostPage.isTitleErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.getTitleErrorText(), "Please enter at least 20 characters.",
				"Poruka nije dobra, proveriti tekst");
		assertEquals(addPostPage.isDescriptionErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.getDescriptionErrorText(), "Please enter at least 50 characters.",
				"Poruka nije dobra proveriti tekst");
		assertFalse(addPostPage.isContentErrorDisplayed(), "Poruka je prikazana");

		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_ADD, "URL nije dobar");

	}

	@Test
	void tc29() {
		AddPostPage addPostPage = new AddPostPage(driver);

		String tempPostTitle = Utils.getRandomTitle();

		addPostPage.insertPostTitle(tempPostTitle);
		addPostPage.insertPostDescription("a");

		addPostPage.chooseRandomTagWithRetry(3);
		addPostPage.insertContent("a");
		addPostPage.clickOnSave();

		assertEquals(addPostPage.isDescriptionErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.getDescriptionErrorText(), "Please enter at least 50 characters.",
				"Poruka nije dobra proveriti tekst");

		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_ADD, "URl nije dobar");

	}

	@Test
	void tc30() {
		AddPostPage addPostPage = new AddPostPage(driver);

		String tempPostTitle = Utils.getRandomTitle();
		String randomDescription = Utils.getRandomCategoryDescription();

		addPostPage.insertPostTitle(tempPostTitle);
		addPostPage.insertPostDescription(randomDescription);

		addPostPage.insertContent("a");

		addPostPage.clickOnSave();

		assertEquals(addPostPage.isTagsErrorDisplayd(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.getTagErrorText(), "This field is required.", "Poruka nije dobra proveriti tekst");
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_ADD, "URl nije dobar");

	}

	@Test
	void tc31() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();

		AddPostPage addPostPage = new AddPostPage(driver);

		addPostPage.chooseCategory(driver, tempCategoryName);
		addPostPage.insertPostTitle("");
		addPostPage.insertPostDescription("");

		addPostPage.insertContent("");
		addPostPage.clickOnSave();

		assertEquals(addPostPage.isTitleErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.isDescriptionErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.isTagsErrorDisplayd(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.isContentErrorDisplayed(),true, "Poruka nije prikazana");
		assertEquals(addPostPage.getContentErrorText(),"The content field is required.", "Poruka nije dobra proveriti tekst");

		assertEquals(addPostPage.getTitleErrorText(), "This field is required.", "Poruka nije dobra, proveriti tekst");
		assertEquals(addPostPage.getDescriptionErrorText(), "This field is required.",
				"Poruka nije dobra, proveriti tekst");
		assertEquals(addPostPage.getTagErrorText(), "This field is required.", "Poruka nije dobra proveriti tekst");

		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_ADD, "URL nije dobar");
		assertEquals(addPostPage.getSelectedCategory(), tempCategoryName);

		CategoryListPage categoryListPage = new CategoryListPage(driver);
		categoryListPage.openPage();
		categoryListPage.deleteCategory(tempCategoryName);

	}

	@Test
	void tc32() {
		AddPostPage addPostPage = new AddPostPage(driver);

		addPostPage.insertPostTitle("");
		addPostPage.insertPostDescription("");
		addPostPage.chooseFile("C:\\Users\\racunar\\Desktop\\joegoldberg.PNG");

		addPostPage.insertContent("");
		addPostPage.clickOnSave();

		assertEquals(addPostPage.isTitleErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.isDescriptionErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.isTagsErrorDisplayd(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.isContentErrorDisplayed(),true, "Poruka nije prikazana");
		assertEquals(addPostPage.getContentErrorText(),"The content field is required.", "Poruka nije dobra proveriti tekst");

		assertEquals(addPostPage.getTitleErrorText(), "This field is required.", "Poruka nije dobra, proveriti tekst");
		assertEquals(addPostPage.getDescriptionErrorText(), "This field is required.",
				"Poruka nije dobra, proveriti tekst");
		assertEquals(addPostPage.getTagErrorText(), "This field is required.", "Poruka nije dobra proveriti tekst");

		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_ADD, "URL nije dobar");

	}

	@Test
	void tc33() {

		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();

		AddPostPage addPostPage = new AddPostPage(driver);

		addPostPage.chooseCategory(driver, tempCategoryName);
		addPostPage.insertPostTitle("");
		addPostPage.insertPostDescription("");
		addPostPage.chooseFile("C:\\Users\\racunar\\Desktop\\joegoldberg.PNG");

		addPostPage.insertContent("");
		addPostPage.clickOnSave();

		assertEquals(addPostPage.isTitleErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.isDescriptionErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.isTagsErrorDisplayd(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.isContentErrorDisplayed(),true, "Poruka nije prikazana");
		assertEquals(addPostPage.getContentErrorText(),"The content field is required.", "Poruka nije dobra proveriti tekst");

		assertEquals(addPostPage.getTitleErrorText(), "This field is required.", "Poruka nije dobra, proveriti tekst");
		assertEquals(addPostPage.getDescriptionErrorText(), "This field is required.",
				"Poruka nije dobra, proveriti tekst");
		assertEquals(addPostPage.getTagErrorText(), "This field is required.", "Poruka nije dobra proveriti tekst");

		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_ADD, "URL nije dobar");
		assertEquals(addPostPage.getSelectedCategory(), tempCategoryName);

		CategoryListPage categoryListPage = new CategoryListPage(driver);
		categoryListPage.openPage();
		categoryListPage.deleteCategory(tempCategoryName);

	}

	@Test
	void tc34() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();

		AddPostPage addPostPage = new AddPostPage(driver);

		String randomDescription2 = Utils.getRandomCategoryDescription();

		addPostPage.chooseCategory(driver, tempCategoryName);
		addPostPage.insertPostTitle("");
		addPostPage.insertPostDescription(randomDescription2);
		addPostPage.chooseFile("C:\\Users\\racunar\\Desktop\\joegoldberg.PNG");
		addPostPage.chooseRandomTag();

		addPostPage.insertContent("a");
		addPostPage.clickOnSave();

		assertEquals(addPostPage.isTitleErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.getTitleErrorText(), "This field is required.", "Poruka nije dobra, proveriti tekst");
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_ADD, "URL nije dobar");
		assertEquals(addPostPage.getSelectedCategory(), tempCategoryName);

		CategoryListPage categoryListPage = new CategoryListPage(driver);
		categoryListPage.openPage();
		categoryListPage.deleteCategory(tempCategoryName);
	}

	@Test
	void tc35() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();

		AddPostPage addPostPage = new AddPostPage(driver);

		String tempPostTitle = Utils.getRandomTitle();

		addPostPage.chooseCategory(driver, tempCategoryName);
		addPostPage.insertPostTitle(tempPostTitle);
		addPostPage.insertPostDescription("");
		addPostPage.chooseFile("C:\\Users\\racunar\\Desktop\\joegoldberg.PNG");
		addPostPage.chooseRandomTag();

		addPostPage.insertContent("a");
		addPostPage.clickOnSave();

		assertEquals(addPostPage.isDescriptionErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.getDescriptionErrorText(), "This field is required.",
				"Poruka nije dobra, proveriti tekst");
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_ADD, "URL nije dobar");
		assertEquals(addPostPage.getSelectedCategory(), tempCategoryName);

		CategoryListPage categoryListPage = new CategoryListPage(driver);
		categoryListPage.openPage();
		categoryListPage.deleteCategory(tempCategoryName);

	}
	
	@Test
	void tc36() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();
		
		AddPostPage addPostPage = new AddPostPage(driver);
		
		String tempPostTitle = Utils.getRandomTitle();
		String randomDescription2 = Utils.getRandomCategoryDescription();
		
		addPostPage.chooseCategory(driver, tempCategoryName);
		addPostPage.insertPostTitle(tempPostTitle);
		addPostPage.insertPostDescription(randomDescription2);
		addPostPage.chooseFile("C:\\Users\\racunar\\Desktop\\joegoldberg.PNG");
		

		addPostPage.insertContent("a");
		addPostPage.clickOnSave();
		
		assertEquals(addPostPage.isTagsErrorDisplayd(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.getTagErrorText(), "This field is required.", "Poruka nije dobra proveriti tekst");
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_ADD, "URL nije dobar");
		assertEquals(addPostPage.getSelectedCategory(), tempCategoryName);
		
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		categoryListPage.openPage();
		categoryListPage.deleteCategory(tempCategoryName);
		

		
	}
	
	@Test
	void tc37() {
		AddPostPage addPostPage = new AddPostPage(driver);
		
		addPostPage.insertPostTitle("");
		addPostPage.insertPostDescription("");
		addPostPage.insertContent("");
		addPostPage.clickOnCancel();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_LIST, "URL nije dobar");
	}
	
	@Test
	void tc38() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();
		
		AddPostPage addPostPage = new AddPostPage(driver);
		
		String tempPostTitle = Utils.getRandomTitle();
		String randomDescription2 = Utils.getRandomCategoryDescription();
		
		addPostPage.chooseCategory(driver, tempCategoryName);
		addPostPage.insertPostTitle(tempPostTitle);
		addPostPage.insertPostDescription(randomDescription2);
		addPostPage.chooseRandomTag();
		addPostPage.chooseFile("C:\\Users\\racunar\\Desktop\\joegoldberg.PNG");
		

		addPostPage.insertContent("a");
		addPostPage.clickOnCancel();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_LIST,"URL nije dobar");
		
		PostListPage postListPage = new PostListPage(driver);
		
		int countPosts = postListPage.countPostsWithTitle(tempPostTitle);
		assertEquals(countPosts,0, "Post je dodat");
		
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		categoryListPage.openPage();
		categoryListPage.deleteCategory(tempCategoryName);
		
	}
	
	@Test
	void tc39() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();
		
		AddPostPage addPostPage = new AddPostPage(driver);
		
		String tempPostTitle = Utils.getRandomTitle();
		String randomDescription2 = Utils.getRandomCategoryDescription();
		
		addPostPage.chooseCategory(driver, tempCategoryName);
		addPostPage.insertPostTitle(tempPostTitle);
		addPostPage.insertPostDescription(randomDescription2);
		addPostPage.chooseRandomTag();
		addPostPage.chooseFile("C:\\Users\\racunar\\Desktop\\joegoldberg.PNG");
		

		addPostPage.insertContent("");
		addPostPage.clickOnSave();
		
		assertEquals(addPostPage.isContentErrorDisplayed(), true, "Poruka nije prikazana");
		assertEquals(addPostPage.getContentErrorText(),"The content field is required.");
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_ADD, "URL nije dobar");
		
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		categoryListPage.openPage();
		categoryListPage.deleteCategory(tempCategoryName);
		
		
		
	}
	// u sledecim test case-evima se desava bag da se post ne pojavljuje u listi iako je kreiran tako da je moguce da provere nisu dobro odradjene
	@Test
	void tc40() {
		
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();
		
        AddPostPage addPostPage = new AddPostPage(driver);
		
		String tempPostTitle = Utils.getRandomTitle();
		String randomDescription2 = Utils.getRandomCategoryDescription();
		
		
		addPostPage.insertPostTitle(tempPostTitle);
		addPostPage.insertPostDescription(randomDescription2);
		addPostPage.chooseRandomTag();
		addPostPage.chooseFile("C:\\\\Users\\\\racunar\\\\Desktop\\\\joegoldberg.PNG");
		

		addPostPage.insertContent("a");
		addPostPage.clickOnSave();
		
		
		
		PostListPage postListPage = new PostListPage(driver);
		int countPostsTitle = postListPage.countPostsWithTitle(tempPostTitle);
		int countPostsCategory = postListPage.countPostsWithCategory(tempCategoryName);
		
		assertEquals(countPostsTitle, 1, "Post se ne nalazi u listi");
		assertEquals(countPostsCategory,0,"Kategorija je dodata");
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_LIST, "URL nije dobar");
		
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		categoryListPage.openPage();
		categoryListPage.deleteCategory(tempCategoryName);
		
		
		
		
	}
	
	@Test
	void tc41() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();
		
        AddPostPage addPostPage = new AddPostPage(driver);
		
		String tempPostTitle = Utils.getRandomTitle();
		String randomDescription2 = Utils.getRandomCategoryDescription();
		
		
		addPostPage.insertPostTitle(tempPostTitle);
		addPostPage.insertPostDescription(randomDescription2);
		addPostPage.chooseRandomTag();
		
		

		addPostPage.insertContent("a");
		addPostPage.clickOnSave();
		
		PostListPage postListPage = new PostListPage(driver);
		int countPostsTitle = postListPage.countPostsWithTitle(tempPostTitle);
		assertEquals(countPostsTitle, 1, "Post se ne nalazi u listi");
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_LIST, "URL nije dobar");
		
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		categoryListPage.openPage();
		categoryListPage.deleteCategory(tempCategoryName);
	}
	
	@Test 
	void tc42() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();
		
        AddPostPage addPostPage = new AddPostPage(driver);
		
		String tempPostTitle = Utils.getRandomTitle();
		String randomDescription2 = Utils.getRandomCategoryDescription();
		
		addPostPage.chooseCategory(driver, tempCategoryName);
		addPostPage.insertPostTitle(tempPostTitle);
		addPostPage.insertPostDescription(randomDescription2);
		addPostPage.clickOnImportant();
		addPostPage.chooseRandomTag();
		addPostPage.chooseFile("C:\\\\Users\\\\racunar\\\\Desktop\\\\joegoldberg.PNG");
		
		

		addPostPage.insertContent("a");
		addPostPage.clickOnSave();
		
		
		PostListPage postListPage = new PostListPage(driver);
		
		postListPage.searchByImportant();
		int countPostsTitle = postListPage.countPostsWithTitle(tempPostTitle);
		assertEquals(countPostsTitle, 1, "Post se ne nalazi u listi");
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_LIST,"URL nije dobar");
		
		
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		categoryListPage.openPage();
		categoryListPage.deleteCategory(tempCategoryName);
		
		
	}
	
	@Test
	void tc43() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();
		
        AddPostPage addPostPage = new AddPostPage(driver);
		
		String tempPostTitle = Utils.getRandomTitle();
		String randomDescription2 = Utils.getRandomCategoryDescription();
		
		addPostPage.chooseCategory(driver, tempCategoryName);
		addPostPage.insertPostTitle(tempPostTitle);
		addPostPage.insertPostDescription(randomDescription2);
		addPostPage.clickOnNotImportant();
		addPostPage.chooseRandomTag();
		addPostPage.chooseFile("C:\\\\Users\\\\racunar\\\\Desktop\\\\joegoldberg.PNG");
		
		

		addPostPage.insertContent("a");
		addPostPage.clickOnSave();
		
		
		PostListPage postListPage = new PostListPage(driver);
		
		postListPage.searchByNotImportant();
		int countPostsTitle = postListPage.countPostsWithTitle(tempPostTitle);
		assertEquals(countPostsTitle, 1, "Post se ne nalazi u listi");
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_LIST,"URL nije dobar");
		
		
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		categoryListPage.openPage();
		categoryListPage.deleteCategory(tempCategoryName);
		
		
	}
	
	@Test
	void tc44() {
		PostListPage postListPage = new PostListPage(driver);
		
		postListPage.clickOnAddNewPost();
		
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_ADD, "URL nije dobar");
		
	
	}
	
	@Test
	void tc45() {
		 AddPostPage addPostPage = new AddPostPage(driver);
			
			String tempPostTitle = Utils.getRandomTitle();
			String randomDescription2 = Utils.getRandomCategoryDescription();
			
			
			addPostPage.insertPostTitle(tempPostTitle);
			addPostPage.insertPostDescription(randomDescription2);
			
			addPostPage.chooseRandomTag();
			addPostPage.chooseFile("C:\\\\Users\\\\racunar\\\\Desktop\\\\joegoldberg.PNG");
			
			

			addPostPage.insertContent("a");
			addPostPage.clickOnSave();
			
			PostListPage postListPage = new PostListPage(driver);
			postListPage.searchByTitle(tempPostTitle);
			int numberOfPosts = postListPage.countPosts();
			int countPostsTitle = postListPage.countPostsWithTitle(tempPostTitle);
			assertEquals(driver.getCurrentUrl(), URLConst.POSTS_LIST,"URL nije dobar");
			
			
			assertEquals(numberOfPosts,1,"Ima vise postova");
			assertEquals(countPostsTitle, 1, "Post se ne nalazi u listi");
			
	}
	
	@Test
	void tc46() {
		PostListPage postsListPage = new PostListPage(driver);
		
		postsListPage.searchByTitle("fasfasdhfa;s");
		
		int numberOfPosts = postsListPage.countPosts();
		assertEquals(numberOfPosts, 0, "Ponudjeni su postovi koji ne sadrze title");
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_LIST, "URL nije dobar");
	}
	
	@Test
	void tc47() {
		PostListPage postListPage = new PostListPage(driver);
		postListPage.searchByAuthor("Polaznik Kursa");
		int countPostsAuthor = postListPage.countPostsWithAuthor("Polaznik Kursa");
		
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_LIST,"URL nije dobar");
		assertTrue(countPostsAuthor >0, "Nisu ponudjeni postovi sa autorom");
	}
	
	@Test
	void tc48() {
		AddCategoryPage addCategoryPage = new AddCategoryPage(driver);

		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();

		addCategoryPage.insertCategoryName(tempCategoryName);
		addCategoryPage.insertCategoryDescription(randomDescription);
		addCategoryPage.clickOnSave();
		
        AddPostPage addPostPage = new AddPostPage(driver);
		
		String tempPostTitle = Utils.getRandomTitle();
		String randomDescription2 = Utils.getRandomCategoryDescription();
		
		
		addPostPage.insertPostTitle(tempPostTitle);
		addPostPage.insertPostDescription(randomDescription2);
		addPostPage.chooseRandomTag();
		
		

		addPostPage.insertContent("a");
		addPostPage.clickOnSave();
		
		PostListPage postListPage = new PostListPage(driver);
		postListPage.searchByCategory(tempCategoryName);
		int countPosts = postListPage.countPostsWithCategory(tempCategoryName);
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_LIST,"URL nije dobar");
		assertEquals(countPosts,1,"Nema posta sa nasom kategorijom");
		
		CategoryListPage categoryListPage = new CategoryListPage(driver);
		categoryListPage.openPage();
		categoryListPage.deleteCategory(tempCategoryName);
		
		
		
	}
	
	@Test
	void tc49() {
		PostListPage postListPage = new PostListPage(driver);
		postListPage.searchByImportant();
		
		int countPosts = postListPage.countPostsWithImportant("yes");
		
		assertTrue(countPosts > 0,"Nije ponudjen important post");
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_LIST,"URL nije dobar");
	}
	
	@Test
	void tc50() {
		PostListPage postListPage = new PostListPage(driver);
		postListPage.searchByImportant();
		
		int countPosts = postListPage.countPostsWithImportant("no");
		
		assertTrue(countPosts > 0,"Nije ponudjen important post");
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_LIST,"URL nije dobar");
	}
	
	@Test
	void tc51() {
		PostListPage postListPage = new PostListPage(driver);
		postListPage.searchByStatusEnabled();
		
		int countPosts = postListPage.countPostsWithStatus("enabled");
		
		assertTrue(countPosts > 0,"Nije ponudjen enabled post");
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_LIST,"URL nije dobar");
	}
	
	@Test
	void tc52() {
		PostListPage postListPage = new PostListPage(driver);
		postListPage.searchByStatusEnabled();
		
		int countPosts = postListPage.countPostsWithStatus("disabled");
		
		assertTrue(countPosts > 0,"Nije ponudjen disabled post");
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_LIST,"URL nije dobar");
	}
	
	@Test
	void tc53() {
		PostListPage postListPage = new PostListPage(driver);
		String selectedTag = postListPage.searchByTag();
		
		int countPosts = postListPage.countPostsWithTag(selectedTag);
		
		assertEquals(driver.getCurrentUrl(), URLConst.POSTS_LIST,"URL nije dobar");
		assertTrue(countPosts>0,"Nije ponudjen post sa ovim tagom");
		
		
	}
	
	@Test
	void tc54() {
		PostListPage postListPage = new PostListPage(driver);
		postListPage.select25Entries();
		
		int countPosts = postListPage.countPosts();
		
		assertTrue(26>countPosts,"Ima vise od 25 elemenata");
		assertTrue(countPosts>24,"Ima manje od 25 elemenata");
	}
	
	@Test
	void tc55() {
		String currentTab = driver.getWindowHandle();
		
		  AddPostPage addPostPage = new AddPostPage(driver);
		  
		  Set<String> allTabs = driver.getWindowHandles();
			
			String tempPostTitle = Utils.getRandomTitle();
			String randomDescription2 = Utils.getRandomCategoryDescription();
			
			
			addPostPage.insertPostTitle(tempPostTitle);
			addPostPage.insertPostDescription(randomDescription2);
			addPostPage.chooseRandomTag();
			
			

			addPostPage.insertContent("a");
			addPostPage.clickOnSave();
			
			PostListPage postListPage = new PostListPage(driver);
			postListPage.clickOnViewPost(tempPostTitle);
			
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
		        assertTrue(postListPage.viewPostURLContainsTitle(tempPostTitle),"URL nije dobar");}
		    driver.close();
		    
		    driver.switchTo().window(currentTab);
	}
	
	@Test
	void tc56() {
		 AddPostPage addPostPage = new AddPostPage(driver);
		  
		 
			
			String tempPostTitle = Utils.getRandomTitle();
			String randomDescription2 = Utils.getRandomCategoryDescription();
			
			
			addPostPage.insertPostTitle(tempPostTitle);
			addPostPage.insertPostDescription(randomDescription2);
			addPostPage.chooseRandomTag();
			
			

			addPostPage.insertContent("a");
			addPostPage.clickOnSave();
			
			PostListPage postListPage = new PostListPage(driver);
			postListPage.clickOnEditPost(tempPostTitle);
			
			
	}
	
	@Test
	
	void tc57() {
		
		AddPostPage addPostPage = new AddPostPage(driver);
		  
		 
		
		String tempPostTitle = Utils.getRandomTitle();
		String randomDescription2 = Utils.getRandomCategoryDescription();
		
		
		addPostPage.insertPostTitle(tempPostTitle);
		addPostPage.insertPostDescription(randomDescription2);
		addPostPage.chooseRandomTag();
		
		

		addPostPage.insertContent("a");
		addPostPage.clickOnSave();
		
		PostListPage postListPage = new PostListPage(driver);
		// ostale test casove nisam stigao da uradim 
	}
	
	
	

}
