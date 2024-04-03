package seleniumOrganization.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumProject.AbstractComponents.AbstractComponent;

public class MyCartPage extends AbstractComponent{

	WebDriver driver;
	
	public MyCartPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProds;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	
	public Boolean verifyProductDisplay(String productName) {
		
		Boolean match = cartProds.stream().anyMatch(cproducts-> cproducts.getText().equalsIgnoreCase(productName)); 
		return match;
	
		
	}
	
	public CheckoutPage goToCheckOut() {
		
		checkOut.click();
		return new CheckoutPage(driver);
		
	}

}
