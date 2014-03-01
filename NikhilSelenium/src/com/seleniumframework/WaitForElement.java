package com.seleniumframework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;


public class WaitForElement implements ExpectedCondition<WebElement> {
	
	By by;
	
	
	public WaitForElement(By by)
	{
		this.by = by;
	
	}

	@Override
	public WebElement apply(WebDriver driver) {
		// TODO Auto-generated method stub
		WebElement returnElement = null;
				
		WebElement we = driver.findElement(by);
		
		if(we.isDisplayed() && we.isEnabled())
		{
			returnElement = we;
			
			
		}
		
		return returnElement;
	}
	
	

}
