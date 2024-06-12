package cubes.pages;


import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cubes.main.URLConst;

public class CategoryListPage {

	private WebDriver driver;
	private List<WebElement> elementsBefore;
	private List<WebElement> elementsAfter;

	@FindBy(partialLinkText = "Add new Category")
	private WebElement weAddNewCategory;
	@FindBy(xpath = "//button[contains(@class, 'btn-outline-secondary')]")
	private WebElement weChangeOrder;

	public CategoryListPage(WebDriver driver) {
		this.driver = driver;
		initializeElementsLists();
		PageFactory.initElements(driver, this);

	}

	public void openPage() {
		this.driver.get(URLConst.CATEGORIES_LIST);
	}

	public void clickOnAddNewCategory() {
		weAddNewCategory.click();
	}

	private void initializeElementsLists() {
		elementsBefore = driver.findElements(By.xpath("//strong"));
	}

	public void clickOnChangeOrderButton() {
		weChangeOrder.click();
	}

	public void clickOnCancelOrderChange() {
		WebElement weCancelOrderChange = driver
				.findElement(By.cssSelector("button.btn.btn-outline-danger[data-action='hide-order']"));
		weCancelOrderChange.click();
	}

	public void clickOnSaveOrderChange() {
		WebElement weSaveOrderChange = driver.findElement(By.cssSelector("button.btn.btn-outline-success"));
		weSaveOrderChange.click();
	}

	public void changeCategoryOrder(String name, String name2) {
		Actions actions = new Actions(driver);
		WebElement dragButton = driver.findElement(By.xpath(getChangeOrderDragButtonLocator(name)));
		WebElement dragButton2 = driver.findElement(By.xpath(getChangeOrderDragButtonLocator(name2)));
		actions.dragAndDrop(dragButton, dragButton2).perform();
	}
	
	public boolean isOrderChanged() {
		elementsAfter = driver.findElements(By.xpath("//strong"));
		
		for(int i =0; i<elementsBefore.size(); i++) {
			try {
				String textBefore = elementsBefore.get(i).getText();
				String textAfter = elementsAfter.get(i).getText();
			
			if(!textBefore.equals(textAfter)) {
				return true;
			}
		}catch (StaleElementReferenceException e) {
			return true;
			}
		}
		return false;
		
	}

	public void clickOnDeleteCategory(String name) {
		WebElement deleteButton = driver.findElement(By.xpath(getDeleteButtonLocator(name)));
		deleteButton.click();
	}

	public void clickOnEditCategory(String name) {
		WebElement updateButton = driver.findElement(By.xpath(getEditButtonLocator(name)));
		updateButton.click();
	}

	public void clickOnViewCategory(String name) {
		WebElement viewButton = driver.findElement(By.xpath(getViewButtonLocator(name)));
		viewButton.click();
		
		
		
		
		
	}
	
	public boolean viewCategoryURLContainsCategory(String name) {
		String expectedURLPart = name.toLowerCase().replace(" ", "-");
		String currentURL = driver.getCurrentUrl();
		
		return currentURL.contains(expectedURLPart);
		
	}

	public void clickOnDialogDeleteButton() {
		WebElement deleteButton = driver.findElement(By.xpath("//button[text()='Delete']"));
		deleteButton.click();
	}

	public void clickOnDialogCancelButton() {
		WebElement cancelButton = driver.findElement(By.xpath("//button[text()='Cancel']"));
	}

	public void deleteCategory(String name) {
		WebElement deleteButton = driver.findElement(By.xpath(getDeleteButtonLocator(name)));
		deleteButton.click();

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {

		}

		WebElement dialogDeleteButton = driver.findElement(By.xpath("//button[text()='Delete']"));
		dialogDeleteButton.click();

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {

		}
	}
	
	

	public int countCategoryWithName(String name) {
		List<WebElement> weCategoryList = driver.findElements(By.xpath("//strong[text()='" + name + "']"));

		return weCategoryList.size();

	}

	public boolean isCategoryWithNameInList(String name) {
		List<WebElement> weCategoryList = driver.findElements(By.xpath("//strong[text()='" + name + "']"));

		return weCategoryList.size() > 0;

	}

	public List<WebElement> getCategoryWithNameList(String name) {
		return driver.findElements(By.xpath("//strong[text()='" + name + "']"));
	}

	private String getDeleteButtonLocator(String name) {
		return "//strong[text()='" + name + "']//ancestor::tr//td[6]//button";
	}

	private String getEditButtonLocator(String name) {
		return "//strong[text()='" + name + "']//ancestor::tr//td[6]//a[2]";
	}

	private String getViewButtonLocator(String name) {
		return "//strong[text()='" + name + "']//ancestor::tr//td[6]//a[1]";
	}

	private String getChangeOrderDragButtonLocator(String name) {
		return "//strong[text()='" + name + "']//ancestor::tr//td[1]//span[1]";
	}
	
	

}
