package seleniumOrganization.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumProject.AbstractComponents.AbstractComponent;

public class ordersPage extends AbstractComponent{
	
WebDriver driver;
	
	public ordersPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> ordernames;
	
	
	
public Boolean verifyOrdersDisplay(String productName) {
		
		Boolean match = ordernames.stream().anyMatch(oproducts-> oproducts.getText().equalsIgnoreCase(productName)); 
		return match;
	
		
	}

}
