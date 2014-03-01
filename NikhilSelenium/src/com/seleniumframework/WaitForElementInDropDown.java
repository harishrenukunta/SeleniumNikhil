package com.seleniumframework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;


public class WaitForElementInDropDown implements ExpectedCondition<Boolean> {
	
	WebElement we;
	String strItemValue = "";
	
	public WaitForElementInDropDown(WebElement we, String strItem)
	{
		this.we = we;
		strItemValue = strItem;
	}

	@Override
	public Boolean apply(WebDriver driver) {
		// TODO Auto-generated method stub
		boolean returnValue = false;
		Select selectDropDownList = null;
		
		//WebElement drpDownList3 = driver.findElement(By.cssSelector("select[name='DropDownList3']"));
		
		if(we.isDisplayed())
		{
			selectDropDownList = new Select(we);
			
			List<WebElement> Options = selectDropDownList.getOptions();
			
			for(WebElement Option:Options)
			{
				if(Option.getText().equalsIgnoreCase(strItemValue))
				{
					returnValue = true;
				}
				
			}
		}
		
		return returnValue;
	}
	
	

}
