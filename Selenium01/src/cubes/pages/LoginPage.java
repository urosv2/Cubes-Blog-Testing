package cubes.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cubes.main.URLConst;

public class LoginPage {
	
	private WebDriver driver;
	
	private WebElement weEmail;
	private WebElement wePassword;
	private WebElement weSignIn;
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		this.driver.manage().window().maximize();
		this.driver.get(URLConst.LOGIN);
		
		this.weEmail = this.driver.findElement(By.name("email"));
		this.wePassword = this.driver.findElement(By.name("password"));
		this.weSignIn = this.driver.findElement(By.xpath("//button[@type='submit']"));
		
		
	}
	
	public void openPage() {
		this.driver.get(URLConst.LOGIN);
	}
	
	public void insertEmail(String email) {
		weEmail.clear();
		weEmail.sendKeys(email);
		
	}
	
	public void insertPassword(String password) {
		wePassword.clear();
		wePassword.sendKeys(password);
	}
	
	public void clickOnSignIn() {
		weSignIn.click();
	}
	
	public boolean isEmailErrorDisplayed() {
		WebElement weEmailError = driver.findElement(By.xpath("//p[@id='email-error']"));
		return weEmailError.isDisplayed();
	}
	
	public boolean isPasswordErrorDisplayed() {
		WebElement wePasswordError = driver.findElement(By.xpath("//p[@id='password-error']"));
		return wePasswordError.isDisplayed();

	}
	
	public String getEmailErrorText() {
		WebElement weEmailError = driver.findElement(By.xpath("//p[@id='email-error']"));
		return weEmailError.getText();

	}
	
	public String getPasswordErrorText() {
		WebElement wePasswordError = driver.findElement(By.xpath("//p[@id='password-error']"));
		return wePasswordError.getText();

	}
	
	
	
	public void login(String email, String password) {
		weEmail.clear();
		wePassword.clear();
		
		weEmail.sendKeys(email);
		wePassword.sendKeys(password);
		
		weSignIn.click();
	}
	
	public void loginSuccess() {
		login("kursqa@cubes.edu.rs", "cubesqa");
	}

}
