package com.webelements;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.seleniumframework.UIOperations;

public class DropDownSelectTest {

	static WebDriver driver = null;
	String strUrl;
	static UIOperations seleniumCore = null;
	
	@BeforeClass
	public static void InitializeClass()
	{
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		seleniumCore = new UIOperations();
	}
	@Test
	public void IllustrateSelectClass() 
	{
		strUrl = "http://knockoutjs.com/examples/";
		
		try
		{
			//Navigate to Url
			driver.get(strUrl);
			
			//Click on 'Control Types' link
			WebElement lnkControlTypes = driver.findElement(By.linkText("Control types"));
			seleniumCore.click(lnkControlTypes);
			
			//Check if drop down is displayed
			//WebElement drpDropDownList = driver.findElement(By.cssSelector("select[data-bind='options: optionValues, value: selectedOptionValue']"));
			//WebElement drpDropDownList = driver.findElement(By.cssSelector("select[data-bind*='selectedOptionValue']"));  //similar to contains
			WebElement drpDropDownList = driver.findElement(By.cssSelector("select[data-bind^='options']")); //attribute value starts with options
			if(!drpDropDownList.isDisplayed())
			{
				fail("Drop down is not displayed. I cannot proceed with testcase execution");
			}
			
			//Read items in drop down
			Select selectDropDownList = new Select(drpDropDownList);
			
			List<WebElement> objDropDownOptions = selectDropDownList.getOptions();
			
			int intItemsCount = objDropDownOptions.size();
			
			System.out.println("Items Count: " + intItemsCount);
			String strItemText;
			List<String> objDropDownTexts = new ArrayList<String>();
			/*
			for(int i = 0; i < intItemsCount; i++)
			{
				strItemText = objDropDownOptions.get(i).getText();
				System.out.println("Item text:" + strItemText);
				objDropDownTexts.add(strItemText);
			}
			*/
			for(WebElement objOption:objDropDownOptions)
			{
				strItemText = objOption.getText();
				System.out.println("Item text:" + strItemText);
				objDropDownTexts.add(strItemText);
			}
			
			//Select Alpha
			Thread.sleep(3000);
			selectDropDownList.selectByVisibleText("Alpha");
			Thread.sleep(3000);
			System.out.println("Selected Value 1 :" + selectDropDownList.getFirstSelectedOption().getText());
			
			selectDropDownList.selectByIndex(2);
			Thread.sleep(3000);
			System.out.println("Selected Value 2:" + selectDropDownList.getAllSelectedOptions().get(0).getText());
			
			System.out.println("Text Collection:" + objDropDownTexts);
			assertTrue("No item found with text Beta-invalid", objDropDownTexts.contains("Beta-invalid"));
		}
		catch(Exception ex)
		{
			System.out.println("Exception : " + ex.getMessage());
			fail("Exception thrown : " + ex.getMessage());
		}
	}
	
	@AfterClass
	public static void TearDownClass()
	{
		driver.quit();
		driver = null;
		seleniumCore = null;
	}

}
