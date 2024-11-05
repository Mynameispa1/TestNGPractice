package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public WebDriver driver;
	
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	@FindBy(xpath="//h2[normalize-space()=\"My Account\"]")
	WebElement linkdiplayed;
	
	@FindBy(xpath="//a[@class=\"list-group-item\"][normalize-space()=\"Logout\"]")
	WebElement linklogout;
	
	
	
	
	public boolean confirmation() {
		try {
		return(linkdiplayed.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clicklogout()
	{
		linklogout.click();
	}
}


