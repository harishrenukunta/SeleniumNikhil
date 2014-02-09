package com.SeleniumBasic;

import static junit.framework.Assert.*;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class DriverMethodsTest {

	@Test
	public void DriverMethodsExample() {
		
		WebDriver driver = null;
		String strUrl = "http://www.tesco.com/";
		try
		{
			driver = new FirefoxDriver();
			
			//Implicit wait of 20 seconds. 
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			//To maximize browser window
			driver.manage().window().maximize();
			
			//Navigate to site
			//driver.get(strUrl);
			driver.navigate().to(strUrl);
			
			(driver.findElement(By.cssSelector("#searchBox"))).sendKeys("Flower");
			Thread.sleep(3000);
			driver.navigate().refresh();
			
			String htmlSource = driver.getPageSource();
			System.out.println("Html : " + htmlSource);
			
			Set<String> windowHandles = driver.getWindowHandles();
			
			System.out.println("Window Handles Before clicking twitter image:" + windowHandles);
			
			//Click on twitter image on tesco home page
			WebElement imgTwitter = driver.findElement(By.cssSelector(".twitter"));
						
			imgTwitter.click();
			Thread.sleep(2000);
						
			//To retrieve handles of all browser instances
			windowHandles = driver.getWindowHandles();
			
			Object windowHandlesArray[] = windowHandles.toArray();
			
			String strPopUpWH = windowHandlesArray[windowHandlesArray.length - 1].toString();
			
			//To swith control from main browser window to pop up window
			driver.switchTo().window(strPopUpWH);
			
			String strCurrentUrl = driver.getCurrentUrl();
			System.out.println("------------------------------------------");
			System.out.println("Twitter window url:" + strCurrentUrl);
									
			WebElement txtFullName = driver.findElement(By.cssSelector("input[name='user[name]']"));//.js-initial-focus"));
			/*
			Actions actionBuilder = new Actions(driver);
			actionBuilder.click(txtFullName);
			actionBuilder.build();
			actionBuilder.perform();
			*/
			
			Thread.sleep(2000);
			txtFullName.sendKeys("Harish Renukunta");
			
			Thread.sleep(3000);
			System.out.println("Window Handles:" + windowHandles);
			driver.close();
			
			Thread.sleep(3000);
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
