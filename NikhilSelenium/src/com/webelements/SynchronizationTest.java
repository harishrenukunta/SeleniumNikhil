package com.webelements;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.seleniumframework.WaitForElement;
import com.seleniumframework.WaitForElementInDropDown;

public class SynchronizationTest {

	String strUrl = "https://dev-webapps.wsu.edu/ais/utilities/ajaxsamples/";
	WebDriver driver = null;
	
	@Before
	public void SetUp()
	{
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	@Test
	@Ignore
	public void AjaxDropDown()
	{
		try
		{
			driver.get(strUrl);
			
			//Click on 'View DropDownList AJAX sample
			WebElement btnAjaxDropDown = driver.findElement(By.cssSelector("#Button5"));
			btnAjaxDropDown.click();
			
			System.out.println("About to select a value from 1st drop down");
			WebElement drpDownList2 = driver.findElement(By.cssSelector("#DropDownList2"));
			
			if(drpDownList2.isDisplayed())
			{
				Select selectDropDownList2 = new Select(drpDownList2);
				selectDropDownList2.selectByVisibleText("BLAW");
			}
			
			Thread.sleep(5000);
			//Select a value from another drop down
			System.out.println("Time : " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(
					new ExpectedCondition<Boolean>(){
						
						public Boolean apply(WebDriver driver)
						{
							boolean returnValue = false;
							Select selectDropDownList3 = null;
							
							WebElement drpDownList3 = driver.findElement(By.cssSelector("select[name='DropDownList3']"));
							
							System.out.println("Time : " + System.currentTimeMillis());
							
							if(drpDownList3.isDisplayed())
							{
								selectDropDownList3 = new Select(drpDownList3);
								
								List<WebElement> Options = selectDropDownList3.getOptions();
								
								for(WebElement Option:Options)
								{
									if(Option.getText().equalsIgnoreCase("410"))
									{
										returnValue = true;
									}
									
								}
							}
							
							
							
							return returnValue;
						}
					}
					
			);
			
			//Select 410 from Dropdown3
			WebElement drpDownList3 = driver.findElement(By.cssSelector("select[name='DropDownList3']"));
			
			if(drpDownList3.isDisplayed())
			{
				Select selectDropDownList2 = new Select(drpDownList3);
				selectDropDownList2.selectByVisibleText("410");
			}
			
			Thread.sleep(5000);
			
			//Select a value from third drop down
			//DropDownList4
			WebElement drpDownList4 = driver.findElement(By.cssSelector("select[name='DropDownList4']"));
			
			wait.until(
					new ExpectedCondition<Boolean>(){
						public Boolean apply(WebDriver driver)
						{
							boolean returnValue = false;
							Select selectDropDownList4 = null;
							
							WebElement drpDownList4 = driver.findElement(By.cssSelector("select[name='DropDownList4']"));
							
							System.out.println("Time : " + System.currentTimeMillis());
							
							if(drpDownList4.isDisplayed())
							{
								selectDropDownList4 = new Select(drpDownList4);
								
								List<WebElement> Options = selectDropDownList4.getOptions();
								
								for(WebElement Option:Options)
								{
									if(Option.getText().equalsIgnoreCase("7"))
									{
										returnValue = true;
									}
									
								}
							}
							return returnValue;
						}
					}
					
			);
			
			if(drpDownList4.isDisplayed())
			{
				Select selectDropDownList4 = new Select(drpDownList4);
				selectDropDownList4.selectByVisibleText("7");
			}
			
			System.out.println("Item from 3rd dropdown selected");
			Thread.sleep(3000);
			
			WebElement label = driver.findElement(By.cssSelector("#Label2"));
			String strMsg = label.getText();
			String strMsgToBeVerified = " BLAW 410 Section 7";
			assertTrue(strMsgToBeVerified + " not found",strMsg.contains("BLAW 410 Section 7"));
			
			
			
		}
		catch(Exception ex)
		{
			System.out.println("-------------------------------------------------------");
			System.out.println("Time : " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
			System.out.println("Exception:" + ex.getMessage());
			fail("Testcase failed because of exception");
		}
		
		
	}
	
	@Test
	@Ignore
	public void AjaxDropDown2()
	{
		try
		{
			driver.get(strUrl);
			
			//Click on 'View DropDownList AJAX sample
			WebElement btnAjaxDropDown = driver.findElement(By.cssSelector("#Button5"));
			btnAjaxDropDown.click();
			
			System.out.println("About to select a value from 1st drop down");
			WebElement drpDownList2 = driver.findElement(By.cssSelector("#DropDownList2"));
			
			if(drpDownList2.isDisplayed())
			{
				Select selectDropDownList2 = new Select(drpDownList2);
				selectDropDownList2.selectByVisibleText("BLAW");
			}
			
			Thread.sleep(5000);
			//Select a value from another drop down
			System.out.println("Time : " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
			
			WebDriverWait wait = new WebDriverWait(driver, 10);
			
			System.out.println("About to execute custom expeced condition");
			WebElement drpDownList3 = driver.findElement(By.cssSelector("select[name='DropDownList3']"));
			wait.until(new WaitForElementInDropDown(drpDownList3, "410"));
			System.out.println("Completed execution custom expeced condition");
			
			
			//Select 410 from Dropdown3
			if(drpDownList3.isDisplayed())
			{
				Select selectDropDownList2 = new Select(drpDownList3);
				selectDropDownList2.selectByVisibleText("410");
			}
			
			Thread.sleep(5000);
			
			//Select a value from third drop down
			//DropDownList4
			WebElement drpDownList4 = driver.findElement(By.cssSelector("select[name='DropDownList4']"));
			
			WaitForElementInDropDown waitElement = new WaitForElementInDropDown(drpDownList4, "7");
			wait.until(waitElement);
			
			if(drpDownList4.isDisplayed())
			{
				Select selectDropDownList4 = new Select(drpDownList4);
				selectDropDownList4.selectByVisibleText("7");
			}
			
			System.out.println("Item from 3rd dropdown selected");
			Thread.sleep(3000);
			
			WebElement label = driver.findElement(By.cssSelector("#Label2"));
			String strMsg = label.getText();
			String strMsgToBeVerified = " BLAW 410 Section 7";
			assertTrue(strMsgToBeVerified + " not found",strMsg.contains("BLAW 410 Section 7"));
			
			
			
		}
		catch(Exception ex)
		{
			System.out.println("-------------------------------------------------------");
			System.out.println("Time : " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
			System.out.println("Exception:" + ex.getMessage());
			fail("Testcase failed because of exception");
		}
		
		
	}
	
	@Test
	@Ignore
	public void AjaxDropDown3()
	{
		try
		{
			driver.get(strUrl);
			
			//Click on 'View DropDownList AJAX sample
			WebElement btnAjaxDropDown = driver.findElement(By.cssSelector("#Button5"));
			btnAjaxDropDown.click();
			
			System.out.println("About to select a value from 1st drop down");
			WebElement drpDownList2 = driver.findElement(By.cssSelector("#DropDownList2"));
			
			if(drpDownList2.isDisplayed())
			{
				Select selectDropDownList2 = new Select(drpDownList2);
				selectDropDownList2.selectByVisibleText("BLAW");
			}
			
			Thread.sleep(5000);
			
			WebDriverWait wait = new WebDriverWait(driver, 10);

			//Wait for an object to display and enable
			wait.until(new WaitForElement(By.cssSelector("select[name='DropDownList3']")));
			
			WebElement drpDownList3 = driver.findElement(By.cssSelector("select[name='DropDownList3']"));
			wait.until(new WaitForElementInDropDown(drpDownList3, "410"));
			
			
			//Select 410 from Dropdown3
			if(drpDownList3.isDisplayed())
			{
				Select selectDropDownList2 = new Select(drpDownList3);
				selectDropDownList2.selectByVisibleText("410");
			}
			
			Thread.sleep(5000);
			
			//Select a value from third drop down
			//DropDownList4
			WebElement drpDownList4 = driver.findElement(By.cssSelector("select[name='DropDownList4']"));
			
			WaitForElementInDropDown waitElement = new WaitForElementInDropDown(drpDownList4, "7");
			wait.until(waitElement);
			
			if(drpDownList4.isDisplayed())
			{
				Select selectDropDownList4 = new Select(drpDownList4);
				selectDropDownList4.selectByVisibleText("7");
			}
			
			System.out.println("Item from 3rd dropdown selected");
			Thread.sleep(3000);
			
			WebElement label = driver.findElement(By.cssSelector("#Label2"));
			String strMsg = label.getText();
			String strMsgToBeVerified = " BLAW 410 Section 7";
			assertTrue(strMsgToBeVerified + " not found",strMsg.contains("BLAW 410 Section 7"));
			
			
			
		}
		catch(Exception ex)
		{			
			System.out.println("Exception:" + ex.getMessage());
			fail("Testcase failed because of exception");
		}
		
		
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
		driver = null;
	}

}
