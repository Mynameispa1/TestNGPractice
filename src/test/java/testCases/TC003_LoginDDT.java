package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {


	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void LoginDDT(String email, String password, String exp) {
		logger.info("*****Started TC003_LoginDDT Testcase****");
		try {
		hp = new HomePage(driver);

		hp.clickMyAccount();
		hp.clickLogin();

		// Login page
		lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setpassword(password);
		lp.clickLogin();

		// MyAccountPage
		
		logger.info("*****Validation******");
		myact = new MyAccountPage(driver);
		boolean status = myact.confirmation();

		if (exp.equalsIgnoreCase("Valid")) {

			if (status == true) {
				myact.clicklogout();
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid")) {
			if (status == true) {
				myact.clicklogout();
				Assert.assertTrue(false);
			}
			
			else {
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e){
			Assert.fail();
		}
		
		logger.info("*****Finished TC003_LoginDDT Testcase****");

	}

}
