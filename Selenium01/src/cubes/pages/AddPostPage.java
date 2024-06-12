package cubes.pages;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.StaleElementReferenceException;

import cubes.main.URLConst;

public class AddPostPage {
	
	private WebDriver driver;
	
	private WebDriverWait wait;
	
	
	
	@FindBy (name = "post_category_id")
	private WebElement weChooseCategory;
	@FindBy (name = "title")
	private WebElement weTitle;
	@FindBy (name = "description")
	private WebElement weDescription;
	@FindBy (css = "label[for='set-as-unimportant']")
	private WebElement weNotImportant;
	@FindBy (css = "label[for='set-as-important']")
	private WebElement weImportant;
	@FindBy (xpath = "//button[text()='Save']")
	private WebElement weSave;
	@FindBy (xpath = "//a[text()='Cancel']")
	private WebElement weCancel;
	@FindBy (name = "photo")
	private WebElement weChooseFile;
	@FindBy (id = "title-error")
	private WebElement weTitleError;
	@FindBy (id = "description-error")
	private WebElement weDescriptionError;
	@FindBy (id = "tag_id[]-error")
	private WebElement weTagError;
	@FindBy (xpath = "//div[contains(text(), 'Please enter at least 20 characters.')]")
	private WebElement weInsufficientTitleError;
	@FindBy (xpath = "//div[contains(text(), 'Please enter at least 50 characters.')]")
	private WebElement weInsufficientDescriptionError;
	@FindBy (xpath = "//body")
	private WebElement weContent;
	@FindBy (xpath = "//div[contains(text(), 'The content field is required.')]")
	private WebElement weContentError;
	@FindBy (xpath = "//input[@name='tag_id[]']")
	private List<WebElement> weTags;
	
	
	public AddPostPage(WebDriver driver) {
		this.driver = driver;
		this.driver.manage().window().maximize();
		this.driver.get(URLConst.POSTS_ADD);
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		PageFactory.initElements(driver, this);	
		
	}
	
	public void openPage() {
		this.driver.get(URLConst.POSTS_ADD);
	}
	
	public void insertPostTitle(String title) {
		weTitle.clear();
		weTitle.sendKeys(title);
	}
	
	public void insertPostDescription(String description) {
		weDescription.clear();
		weDescription.sendKeys(description);
	}
	
	public void insertContent(String content) {
		weContent.sendKeys(content);
	}
	
	public void clickOnSave() {
		weSave.click();
	}
	
	public void clickOnCancel() {
		weCancel.click();
	}
	
	public boolean isTitleErrorDisplayed() {
		return weTitleError.isDisplayed();
	}
	
	public boolean isDescriptionErrorDisplayed() {
		return weDescriptionError.isDisplayed();
	}
	
	public boolean isTagsErrorDisplayd() {
		return weTagError.isDisplayed();
	}
	
	public boolean isContentErrorDisplayed() {
		try {
			return weContentError.isDisplayed();
			} catch (NoSuchElementException e) {
				return false;
			}
	}
	
	public boolean isInsufficientTitleErrorDisplayed() {
		return weInsufficientTitleError.isDisplayed();
	}
	
	public boolean isInsufficientDescriptionErrorDisplayed() {
		return weInsufficientDescriptionError.isDisplayed();
	}
	
	public String getTitleErrorText() {
		return weTitleError.getText();
	}
	
	public String getDescriptionErrorText() {
		return weDescriptionError.getText();
	}
	
	public String getTagErrorText() {
		return weTagError.getText();
	}
	
	public String getContentErrorText() {
		try {
			return weContentError.getText();
			} catch(NoSuchElementException e) {
				return "Poruka nije dobra";
			}
	}
	
	public String getInsufficientTitleErrorText() {
		return weInsufficientTitleError.getText();
	}
	
	public String getInsufficientDescriptionErrorText() {
		return weInsufficientDescriptionError.getText();
	}
	
	
	
	public void chooseCategory(WebDriver driver, String categoryName) {
		Select select = new Select(weChooseCategory);
		
		select.selectByVisibleText(categoryName);
	}
	
	public String getSelectedCategory() {
		Select select = new Select(weChooseCategory);
		WebElement selectedCategory = select.getFirstSelectedOption();
		return selectedCategory.getText();
		}
		
	
	public void chooseFile(String filePath) {
		weChooseFile.sendKeys(filePath);
	}
	
	public void clickOnImportant() {
		weImportant.click();
	}
	
	public void clickOnNotImportant() {
		weNotImportant.click();
	}
	
	public void chooseRandomTag() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.form-group div.form-check-inline input[type='checkbox'][name='tag_id[]']")));
		
		
		Random random = new Random();
		int randomIndex = random.nextInt(weTags.size());
		weTags.get(randomIndex).click();
	}
	
	public void chooseRandomTagWithRetry(int maxAttempts) {
	    for (int i = 0; i < maxAttempts; i++) {
	        try {
	        	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.form-group div.form-check-inline input[type='checkbox'][name='tag_id[]']"))); 

	            Random random = new Random();
	            int randomIndex = random.nextInt(weTags.size());
	            weTags.get(randomIndex).click();
	            return; 
	        } catch (StaleElementReferenceException e) {
	          
	           
	        }

}
	    
	
	}
	

}
