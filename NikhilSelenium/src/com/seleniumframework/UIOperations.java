package com.seleniumframework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UIOperations {
	
	public void check(WebElement chk)
	{
		boolean blnchecked = Boolean.parseBoolean(chk.getAttribute("checked"));
		System.out.println("Check checkbox");
		if(blnchecked == false)
		{
			chk.click();
		}
		
	}
	
	public void unCheck(WebElement chk)
	{
		System.out.println("Uncheck checkbox");
		
		boolean blnchecked = Boolean.parseBoolean(chk.getAttribute("checked"));
		if(blnchecked == true)
		{
			chk.click();
		}
		
	}
	
	public void click(WebElement we)
	{
		System.out.println("Click method");
		
		if(we.isDisplayed())
		{
			we.click();
		}
		
	}

}
