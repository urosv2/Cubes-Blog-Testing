package cubes.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import cubes.main.URLConst;

public class PostListPage {
	
	private WebDriver driver;
	
	@FindBy (partialLinkText = "Add new Post")
	private WebElement weAddNewPost;
	@FindBy (name = "title")
	private WebElement weSearchByTitle;
	@FindBy (xpath = "//span[contains(@class,'select2-selection__rendered') and text()='--Choose Author --']")
	private WebElement weChooseAuthor;
	@FindBy (xpath = "//span[contains(@class,'select2-selection__rendered') and text()='--Choose Category --']")
	private WebElement weChooseCategory;
	@FindBy (name = "important")
	private WebElement weImportant;
	@FindBy (name = "status")
	private WebElement weStatus;
	@FindBy (css = "ul.select2-selection__rendered > li.select2-search.select2-search--inline > input.select2-search__field")
	private WebElement weWithTag;
	
	public PostListPage(WebDriver driver) {
		this.driver = driver;
		this.driver.manage().window().maximize();
		this.driver.get(URLConst.POSTS_LIST);
		
		PageFactory.initElements(driver, this);
	}
	
	public void openPage() {
		this.driver.get(URLConst.POSTS_LIST);
	}
	
	public void clickOnAddNewPost() {
		weAddNewPost.click();
	}
	
	public int countPostsWithTitle(String title) {
		List<WebElement> wePostsList = driver.findElements(By.xpath("//tr[.//td[5][contains(text(), '" + title + "')]]"));
		
		return wePostsList.size();
	}
	
	public int countPostsWithCategory(String category) {
		List<WebElement> wePostsList = driver.findElements(By.xpath("//tr[.//td[7][contains(text(),'"+category+"')]]"));
		
		return wePostsList.size();
	}
	
	public int countPostsWithAuthor(String author) {
		List<WebElement> wePostsList = driver.findElements(By.xpath("//tr[.//td[6][contains(text(),'"+author+"')]]"));
		
		return wePostsList.size();
	}
	
	public int countPostsWithImportant(String text) {
		List<WebElement> wePostsList = driver.findElements(By.xpath("//tr[.//td[3][contains(text(),'"+text+"')]]"));
		return wePostsList.size();
	}
	
	public int countPostsWithStatus(String text) {
		List<WebElement> wePostsList = driver.findElements(By.xpath("//tr[.//td[4][contains(text(),'"+text+"')]]"));
		return wePostsList.size();
	}
	
	public int countPostsWithTag(String text) {
		List<WebElement> wePostsList = driver.findElements(By.xpath("//tr[.//td[8][contains(text(),'"+text+"')]]"));
		
		return wePostsList.size();
	}
	
	public void select25Entries() {
		WebElement entries = driver.findElement(By.name("entities-list-table_length"));
		
		Select select = new Select(entries);
		select.selectByValue("25");
	}
	
	
	
	public void searchByTitle(String postTitle) {
		weSearchByTitle.clear();
		weSearchByTitle.sendKeys(postTitle);
	}
	
	public void searchByAuthor(String author) {
		weChooseAuthor.click();
		
		driver.findElement(By.cssSelector("input.select2-search__field")).sendKeys(author);
		
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).perform();
	}
	
	public void searchByCategory(String category) {
		weChooseCategory.click();
		
		driver.findElement(By.cssSelector("input.select2-search__field")).sendKeys(category);
		
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).perform();
	}
	
	public void searchByImportant() {
		Select select = new Select(weImportant);
		select.selectByVisibleText("yes");
	}
	
	public void searchByNotImportant() {
		Select select = new Select(weImportant);
		select.selectByVisibleText("no");
	}
	
	public void searchByStatusEnabled() {
		Select select = new Select(weStatus);
		select.selectByVisibleText("enabled");
	}
	
	public void searchByStatusDisabled() {
		Select select = new Select(weStatus);
		select.selectByVisibleText("disabled");
	}
	
	public void clickOnViewPost(String title) {
		WebElement viewButton = driver.findElement(By.xpath(getViewButtonLocator(title)));
		viewButton.click();
	}
	
	public void clickOnEditPost(String title) {
		WebElement editButton = driver.findElement(By.xpath(getEditButtonLocator(title)));
		editButton.click();
	}
	
	public void clickOnDeletePost(String title) {
		WebElement deleteButton = driver.findElement(By.xpath(getDeleteButtonLocator(title)));
	}
	
	
	
	public boolean viewPostURLContainsTitle(String title) {
		String expectedURLPart = title.toLowerCase().replace(" ", "-");
		String currentURL = driver.getCurrentUrl();
		
		return currentURL.contains(expectedURLPart);
	}
	
	
	
	public String searchByTag() {
		weWithTag.click();
		List<WebElement> tags = driver.findElements(By.cssSelector(".select2-results__option"));
		Random random = new Random();
		int randomIndex = random.nextInt(tags.size());
		
		WebElement randomTag = tags.get(randomIndex);
		String selectedTag = randomTag.getText();
		randomTag.click();
		
		return selectedTag;
	}
	
	public int countPosts() {
		List<WebElement> posts = driver.findElements(By.xpath("//div[@class='card-body']//table[@id='entities-list-table']//tbody//tr"));
		
		return posts.size();
	}
	
	private String getViewButtonLocator(String title) {
		return "//tr[td[5][normalize-space()='" + title + "']]/td[1]/div/a[1]";
	}
	
	private String getEditButtonLocator(String title) {
		return "//tr[td[5][normalize-space()='" + title + "']]/td[1]/div/a[2]";
	}
	
	private String getDeleteButtonLocator(String title) {
		return "//tr[td[5][normalize-space()='" + title + "']]/td[1]/div/button[1]";
	}

}

