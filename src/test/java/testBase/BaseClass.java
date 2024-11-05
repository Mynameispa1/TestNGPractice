package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	public HomePage hp;
	public LoginPage lp;
	public MyAccountPage myact;
	
	
	@Parameters({"browser", "os"})
	@BeforeClass(groups= {"sanity","master","regression"})
	public void setup(String br, String os) throws InterruptedException, IOException {
		
		logger=LogManager.getLogger(this.getClass());
		
		FileInputStream file = new FileInputStream("D:\\pavan\\TestNG_Practice\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			
			//os
			switch(os.toLowerCase())
			{
			case	"windows"	: cap.setPlatform(Platform.WIN11);break;
			case	"linux"		: cap.setPlatform(Platform.LINUX);break;
			case	"mac"		:cap.setPlatform(Platform.MAC);break;
			default	: System.out.println("Invalid Operating System");return;
			}
			
			
			//browser
			switch(br.toLowerCase())
			{
			case	"chrome"	: cap.setBrowserName("chrome");break;
			case	"edge"		: cap.setBrowserName("edge");break;
			case	"firefox"	: cap.setBrowserName("firefox");break;
			default	: System.out.println("Invalid Browser");return;
			
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap );
						
		}
		
		if(p.getProperty("execution_env").equals("local"))
		{
			switch(br.toLowerCase())
			{
			case	"chrome"	: driver = new ChromeDriver();break;
			case	"edge"		: driver = new EdgeDriver();break;
			case	"firefox"	: driver = new FirefoxDriver();break;
			case	"default"	: System.out.println("Invalid Browser");return;
			}
		}
		
		
		
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("appurl"));
		driver.manage().window().maximize();
	}

	@AfterClass(groups= {"sanity","master","regression"})
	public void tearDown() 
	{
		driver.quit();
	}
	
	
	public String randomString()
	{
			String geneartedString = RandomStringUtils.randomAlphabetic(5);
			
			return geneartedString;
	}
	
	public String randomNumber()
	{
		String geneartedNumber = RandomStringUtils.randomNumeric(10);
		return geneartedNumber;
		
	}
	
	public String randomAlphaNum() {
		String generatedAphaNum = RandomStringUtils.randomAlphanumeric(9);
		return generatedAphaNum;
	}
	

	//Capture Screen Shot Method we have added this step while doing Extent Report 
	public String captureScreen(String tname) {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" +timeStamp+ ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

}
