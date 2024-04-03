package seleniumOrganization.test;



import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumOrganization.pageobjects.LandingPage;

public class OriginalStandAloneTest {

	public static void main(String[] args) {
		
		String productName = "ZARA COAT 3";
	
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingPage = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("mariajacobareeckal@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Qwerty123456@");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		//list of products in 1st page
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		//from list of product filter the title product we want
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
	    //from the title product selected to Clicking on Add To Cart
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//Waiting for the toaster message and loading portion
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//Clicking on Cart option
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//listing title of products inside my cart
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		//filtering(checking match) the title we want
		boolean match = cartProducts.stream().anyMatch(cproducts-> cproducts.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		//Clicking on checkout in my cart page
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//Dealing with autu suggestive dropdown
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		  
		//waiting for all the dropdown values to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		//Clicking on India
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		//Clicking on submit buttun
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
	}
		
		
		
		

	}


