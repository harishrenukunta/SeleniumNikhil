package com.webelements;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(
					new ExpectedCondition<Boolean>(){
						
						public Boolean apply(WebDriver driver)
						{
							boolean returnValue = false;
							Select selectDropDownList3 = null;
							
							WebElement drpDownList3 = driver.findElement(By.cssSelector("select[name='DropDownList3']"));
							
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
			
			Thread.sleep(10000);
			
			
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
