package cubes.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyWebDriver {

	private static MyWebDriver driver;
	
	private MyWebDriver() {
		
	}
	
	public static MyWebDriver getInstance() {
		
		if(driver == null) {
			driver = new MyWebDriver();
		}
		
		return driver;
	}
	
	public WebDriver getDriver(String browser) {
		
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\racunar\\Desktop\\qa\\webdriver\\chromedriver.exe");
					
			
			return new ChromeDriver();
		}
		else {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\racunar\\Desktop\\qa\\webdriver\\chromedriver.exe");
					
			
			return new ChromeDriver();
		}
	}
}
