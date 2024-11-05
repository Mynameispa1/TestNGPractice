package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_Login extends BaseClass{

	@Test(groups= {"sanity", "master"})
	public void Login() throws InterruptedException
	{
		
		logger.info("****** Login Test Started*******");
		hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		
		
		lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setpassword(p.getProperty("password"));
		lp.clickLogin();
		
		myact = new MyAccountPage(driver);
		boolean status = myact.confirmation();
		myact.clicklogout();
		Thread.sleep(3000);
		Assert.assertTrue(status);
	}
}
