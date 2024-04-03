package seleniumOrganization.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumOrganization.TestComponents.BaseTest;
import seleniumOrganization.pageobjects.CheckoutPage;
import seleniumOrganization.pageobjects.ConfirmationPage;
import seleniumOrganization.pageobjects.LandingPage;
import seleniumOrganization.pageobjects.MyCartPage;
import seleniumOrganization.pageobjects.ProductCataloguePage;
import seleniumOrganization.pageobjects.ordersPage;

public class StandAloneTestNew extends BaseTest{
	String productName = "ZARA COAT 3";
	
	@Test
	public void submitOrder() throws IOException {
		
	
		
		
		ProductCataloguePage productCatalogue = landingPage.loginApplication("mariajacobareeckal@gmail.com","Qwerty123456@");
	
        List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		MyCartPage cartPage = productCatalogue.goToCartPage();
		
		

		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String confirmMessage =	confirmationPage.getConfirmationMessage();
		
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		}

	
@Test(dependsOnMethods= {"submitOrder"})	
   public void orderHistoryTest() {
	
	ProductCataloguePage productCatalogue = landingPage.loginApplication("mariajacobareeckal@gmail.com","Qwerty123456@");
	ordersPage orderpAge = productCatalogue.goToOrderPage();
	Assert.assertTrue(orderpAge.verifyOrdersDisplay(productName));
	
 }
		
		

	}


