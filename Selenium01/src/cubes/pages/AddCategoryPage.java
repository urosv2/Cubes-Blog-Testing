package cubes.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cubes.main.URLConst;
import cubes.main.Utils;

public class AddCategoryPage {
	
	private WebDriver driver;
	
	@FindBy (name = "name")
	private WebElement weCategoryName;
	@FindBy (name = "description")
	private WebElement weCategoryDescription;
	@FindBy (xpath = "//button[text()='Save']")
	private WebElement weSave;
	@FindBy (xpath = "//a[text()='Cancel']")
	private WebElement weCancel;
	@FindBy (id = "name-error")
	private WebElement weNameError;
	@FindBy(xpath = "//div[contains(text(), 'The name has already been taken.')]")
	private WebElement weExistingNameError;
	@FindBy (id = "description-error")
	private WebElement weDescriptionError;
	@FindBy(xpath = "//div[contains(text(), 'The description must be at least 50 characters')]")
	private WebElement weInsufficientDescriptionError;
	
	public AddCategoryPage(WebDriver driver) {
		this.driver = driver;
		this.driver.manage().window().maximize();
		this.driver.get(URLConst.CATEGORIES_ADD);
		
		PageFactory.initElements(driver, this);
		
	}
	
	public void openPage() {
		this.driver.get(URLConst.CATEGORIES_ADD);
	}
	
	public void insertCategoryName(String categoryName) {
		weCategoryName.clear();
		weCategoryName.sendKeys(categoryName);
		
	}
	
	public void insertCategoryDescription(String categoryDescription) {
		weCategoryDescription.clear();
		weCategoryDescription.sendKeys(categoryDescription);
	}
	
	public void clickOnSave() {
		weSave.click();
		
	}
	
	public void clickOnCancel() {
		weCancel.click();
	}
	
	public boolean isNameErrorDisplayed() {
		return weNameError.isDisplayed();
	}
	
	public boolean isDescriptionErrorDisplayed() {
		return weDescriptionError.isDisplayed();
	}
	
	public String getNameErrorText() {
		return weNameError.getText();
	}
	
	public String getDescriptionErrorText() {
		return weDescriptionError.getText();
	}
	
	public String getCategoryNameText() {
		return weCategoryName.getAttribute("value");
	}
	
	public boolean isInsufficientDescriptionErrorDisplayed() {
		
		try {
		return weInsufficientDescriptionError.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public String getInsufficientDescriptionErrorText() {
		
		try {
		return weInsufficientDescriptionError.getText();
		} catch(NoSuchElementException e) {
			return "Poruka nije dobra";
		}
	}
	
	public WebElement getInsufficientDescriptionError() {
        return weInsufficientDescriptionError;
    }
	
	public boolean isExistingNameErrorDisplayed() {
		return weExistingNameError.isDisplayed();
	}
	
	public String getExistingNameErrorText() {
		return weExistingNameError.getText();
	}
	
	public WebElement getExistingNameError() {
		return weExistingNameError;
	}
	
	public void addNewCategory(String name, String description) {
		insertCategoryName(name);
		insertCategoryDescription(description);
		clickOnSave();
		
	}
	
	public void addNewCategory() {
		String tempCategoryName = Utils.getRandomCategoryName();
		String randomDescription = Utils.getRandomCategoryDescription();
		addNewCategory(tempCategoryName, randomDescription);
	}
}
