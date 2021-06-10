package WithoutPOMTestCase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class DependsOnMethodsDemo {

	WebDriver driver = null;
	
	/**
	 * This test case will login in http://demo.guru99.com/V4/
	 * Verify login page title as guru99 bank
	 * Login to application
	 * Verify the home page using Dashboard message
	 * @throws InterruptedException 
	 */
	
	
	@Test
	public void test_Home_Page_Appear_Correct() throws InterruptedException{
		
		System.out.println("in inti()");
		// System Property for Chrome Driver   
		System.setProperty("webdriver.chrome.driver", ".\\server\\chromedriver.exe");  

		// Instantiate a ChromeDriver class.     
		driver=new ChromeDriver(); 
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/V4/");
		
		//Find user name and fill user name
		driver.findElement(By.name("uid")).sendKeys("mgr123");
		
		//find password and fill it
		driver.findElement(By.name("password")).sendKeys("mgr!23");
		//click login button
		
		driver.findElement(By.name("btnLogin")).click();
		
		Thread.sleep(3000);
		
		String homeText = driver.findElement(By.xpath("//h2[.='Guru99 Bank']")).getText();
		
		System.out.println("===========>>1 "+ homeText);
		//verify login success
		Assert.assertTrue(homeText.toLowerCase().contains("guru99 bank"));
		
		System.out.println("===========>> 2 "+ homeText);
		Assert.assertEquals(homeText, "Guru99 Bank");
		
		Assert.assertTrue(true);
		//Assert.assertTrue(false);
	}
	
	
	@Test(dependsOnMethods = { "test_Home_Page_Appear_Correct" })
	public void verify_MangerID() {
		
		String mngrId = driver.findElement(By.xpath("//tr[@class='heading3']/td")).getText();
		Assert.assertTrue(mngrId.toLowerCase().contains("manger id"));
	}

}
