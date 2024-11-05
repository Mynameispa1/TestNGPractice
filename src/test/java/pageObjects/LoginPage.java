package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public WebDriver driver;
	
	//Constructor
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locator
	
	@FindBy(xpath="//input[@id=\"input-email\"]")
	WebElement linkemail;
	
	@FindBy(xpath="//input[@id=\"input-password\"]")
	WebElement linkpassword;
	
	@FindBy(xpath="//input[@value=\"Login\"]")
	WebElement linkLogin;
	
	
	
	
	//Action Methods
	
	public void setEmail(String email) {
		linkemail.sendKeys(email);
	}
	
	public void setpassword(String password) {
		linkpassword.sendKeys(password);
	}
	
	public void clickLogin() {
		linkLogin.click();
	}
	
	

	
	
}
