package com.SeleniumBasic;

import static junit.framework.Assert.*;
import com.seleniumframework.UIOperations;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebElementMethodsTest {

	static WebDriver driver = null;
	String strUrl = "http://www.google.com";
	UIOperations seleniumCore = null;
	
	@BeforeClass
	public static void BeforeClass()
	{
		System.out.println("**************Before Class************************");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	@Before
	public void SetUp()
	{
		System.out.println("Before");
		//driver = new FirefoxDriver();
		seleniumCore = new UIOperations();
		driver.navigate().to(strUrl);
	}
	
	@Test
	public void testGmailPageUI() {
		
		try
		{
			
			System.out.println("Gmail Page UI verification");
			//driver.findElement(By.linkText("Sign in")).click();
			seleniumCore.click(driver.findElement(By.linkText("Sign in")));
			
			assertTrue("Email textbox not displayed",driver.findElement(By.cssSelector("#Email")).isDisplayed());
			assertTrue("Sign In button not displayed", driver.findElement(By.cssSelector("input#signIn")).isDisplayed());
			assertTrue("Stayed Sign in", driver.findElement(By.cssSelector("#PersistentCookie")).isDisplayed());
			
			Thread.sleep(4000);
		}
		catch(Exception ex)
		{
			System.out.println("Exception:" + ex.getMessage());
			fail("Exception thrown and hence failed the testcase");
			
		}
		
	}
	
	@Test
	public void testGmailInvalidLogin() {
		
		try
		{	
			
			System.out.println("Gmail Invalid login");
			
			//driver.findElement(By.linkText("Sign in")).click();
			seleniumCore.click(driver.findElement(By.linkText("Sign in")));
			
			driver.findElement(By.cssSelector("#Email")).sendKeys("HarishRenukunta");
			driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("Harish1");
			
			Thread.sleep(3000);
			
			//driver.findElement(By.cssSelector("#PersistentCookie")).click();
			
			seleniumCore.unCheck(driver.findElement(By.cssSelector("#PersistentCookie")));
			
			Thread.sleep(3000);
			
			//driver.findElement(By.cssSelector("input#signIn")).click();
			seleniumCore.click(driver.findElement(By.cssSelector("input#signIn")));
			
			String strErrMsg = driver.findElement(By.cssSelector("#errormsg_0_Passwd")).getText();
			
			assertTrue("Error message not found", strErrMsg.contains("incorrect"));
			
			Thread.sleep(2000);
		}
		catch(Exception ex)
		{
			System.out.println("Exception:" + ex.getMessage());
			fail("Exception thrown and hence failed the testcase");
			
		}
		
	}
	
	@After
	public void TearDown()
	{
		seleniumCore = null;
		System.out.println("After");
	
	}
	
	@AfterClass
	public static void AfterClass()
	{
		System.out.println("********************After Class***********");
		driver.quit();
		driver = null;
	}

	
}
