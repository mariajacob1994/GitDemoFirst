package seleniumOrganization.TestComponents;

import org.testng.annotations.AfterMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumOrganization.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver; 
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {
		
		
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//seleniumOrganization//resources//Globaldata.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
		    driver = new ChromeDriver();
			
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
			System.setProperty("webdriver.gecko.driver", "browser path");
			
		}
		else if(browserName.equalsIgnoreCase("edge")){
			 driver = new EdgeDriver();
			System.setProperty("webdriver.edge.driver", "browser path");
			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;
		
		
		}
	@BeforeMethod
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}

	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
