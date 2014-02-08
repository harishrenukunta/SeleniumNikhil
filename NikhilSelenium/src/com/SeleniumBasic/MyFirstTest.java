package com.SeleniumBasic;

import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


import static junit.framework.Assert.*;

public class MyFirstTest {

	@Test
	@Ignore
	public void ApplyAsdaCC() {
		
		WebDriver driver = null;
		String strUrl = "https://credit-cardapply.asda.com/apply/applyjourney.html?promotionalCode=&brandCode=asda&responseCode=ASD030&empty_guid=true";
		String strBrowserTitle = "";
		try
		{
			driver = new FirefoxDriver();
			
			driver.get(strUrl);
			
			strBrowserTitle = driver.getTitle();
			
			assertEquals("Title do not match with expected", "Asda Apply Journey-Invalid", strBrowserTitle);
			
			Thread.sleep(4000);
			
			
		}
		catch(Exception ex)
		{
			
			System.out.println("Exception thrown:" + ex.getMessage());
		}
		finally
		{
			driver.quit();
		}
	}
	
	@Test
	public void verifyApplyNowPage()
	{
		WebDriver driver = null;
		String strUrl = "http://money.asda.com/credit-cards/";
		try
		{
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			driver.get(strUrl);
			
			WebElement lnkApplyNow = driver.findElement(By.linkText("Apply now"));
			
			WebElement chkIAgree = null;
			
			if(lnkApplyNow.isDisplayed())
			{
				lnkApplyNow.click();
				
				chkIAgree = driver.findElement(By.cssSelector("input[name='confirmEligibility']"));//By.cssSelector("#confirmEligibility"));
				chkIAgree.click();
				
				WebElement btnApplyNow = driver.findElement(By.cssSelector("form.interstitial-form input.button-primary"));//By.cssSelector(".button-primary"));
				Thread.sleep(3000);
				if(btnApplyNow.isEnabled())
				{
					btnApplyNow.click();
					System.out.println("Click on Apply now");
				}
				
				WebElement btnNext = driver.findElement(By.cssSelector(".btn"));
				assertNotNull("Next button not found", btnNext);
			}
			else
			{
				System.out.println("Apply now not found");
			}
			
			Thread.sleep(4000);
		}
		catch(Exception ex)
		{
			System.out.println("Exception thrown:" + ex.getMessage());
		}
		finally
		{
			driver.quit();
		}
	}

}
