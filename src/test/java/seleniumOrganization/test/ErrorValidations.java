package seleniumOrganization.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumOrganization.TestComponents.BaseTest;
import seleniumOrganization.pageobjects.MyCartPage;
import seleniumOrganization.pageobjects.ProductCataloguePage;

public class ErrorValidations extends BaseTest {
	@Test
	public void loginForErrorValidation() {
		
	
		
		
		 landingPage.loginApplication("mariajacobareeckal@gmail.com","Qwerty1236@");
		AssertJUnit.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
		System.out.println(landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws InterruptedException {
        String productName = "ZARA COAT 3";
		
        landingPage.clearData();
		
		ProductCataloguePage productCatalogue = landingPage.loginApplication("mariajacobareeckal53@gmail.com","Qwerty123456@");
	
        List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	
		MyCartPage cartPage = productCatalogue.goToCartPage();
		
		

		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
	}
}
