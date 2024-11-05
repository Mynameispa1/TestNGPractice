package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	public HomePage hp;
	public AccountRegistrationPage act;

	

	@Test(groups= {"regression", "master"})
	public void verify_account_registration()
	{
		logger.info("******verify_account_registration Strated ****");
		hp = new HomePage(driver);
		logger.info("***** Calling Methods from HomePage*****");
		hp.clickMyAccount();
		hp.clickRegister();
		
		String password = randomAlphaNum();
		logger.info("***** Calling Methods from AccountRegistration from HomePage*****");
		act = new AccountRegistrationPage(driver);
		act.setFirtName(randomString().toUpperCase());
		act.setLastName(randomString().toUpperCase());
		act.setEmail(randomString()+"@gmail.com");
		act.setTelephone(randomNumber());
		act.setPassword(password);
		act.setConfirmPassword(password);
		act.clickAgree();
		act.clickContinue();

		String cnf = act.getConfirmationMsg();
		if (cnf.equals("Your Account Has Been Created!")) {
			logger.info("******verify_account_registration Finished ****");
			Assert.assertTrue(true);
		}
		
		else {
			logger.info("******verify_account_registration failed ****");
			Assert.fail();
		}
	}

	
}
