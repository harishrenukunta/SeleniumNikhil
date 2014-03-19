package NikhilSelenium.NikhilSeleniumMavenTestNG;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import com.knockOutPages.ShoppingCart;

public class DataProviderTest {
	
  String strUrl = "http://knockoutjs.com/examples/cartEditor.html";
  WebDriver driver = null;
  ShoppingCart sc = null;
  
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(10,  TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
	  driver.get(strUrl);
	  
	  sc = PageFactory.initElements(driver, ShoppingCart.class);
  }
  
  @DataProvider(name="cartDataProvider")
  public Object[][]createCartData()
  {
	  return new Object[][]{
			  {"Motorcycles","1974 Ducati 350 Mk3 Desmo" },
			  {"Classic Cars", "1961 Chevrolet Impala"},
			  {"Classic Cars","1968 Ford Mustang"}
	  };
  }
  
  @Test(dataProvider="cartDataProvider", enabled=false)
  public void verifyAddProduct(String Category, String Product)
  {
	  try
	  {
		  //sc.addProduct("Motorcycles","1974 Ducati 350 Mk3 Desmo");
		  sc.addProduct(Category, Product);
		  
		  Thread.sleep(4000);
		  
		  assertTrue(true);
	  }
	  catch(Exception ex)
	  {
		  System.out.println("Exception:" + ex.getMessage());
		  fail("Row failed withe exception:" + ex.getMessage());
	  }
	  
	  
  }
  
  @Test(dataProvider="GetItemsToBuy", dataProviderClass=DPInAnotherClass.class)
  public void verifyAddProductDPInAnotherClass(Map<String, String>shoppingCartRow)
  {
	  try
	  {
		  //sc.addProduct("Motorcycles","1974 Ducati 350 Mk3 Desmo");
		  sc.addProduct(shoppingCartRow.get("Category"),shoppingCartRow.get("Product"));
		  
		  Thread.sleep(4000);
		  
		  assertTrue(true);
	  }
	  catch(Exception ex)
	  {
		  System.out.println("Exception:" + ex.getMessage());
		  fail("Row failed withe exception:" + ex.getMessage());
	  }
	  
	  
  }
  
  @Test(enabled=false)
  public void readExcel()
  {
	  try {
          
          //Create a workbook object from the file at specified location.
          //Change the path of the file as per the location on your computer.
          Workbook wrk1 =  Workbook.getWorkbook(new File("C:/test.xls"));
           
          //Obtain the reference to the first sheet in the workbook
          Sheet sheet1 = wrk1.getSheet(0);
           
          //Obtain reference to the Cell using getCell(int col, int row) method of sheet
          Cell colArow1 = sheet1.getCell(0, 0);
          Cell colBrow1 = sheet1.getCell(1, 0);
          Cell colArow2 = sheet1.getCell(0, 1);
           
          //Read the contents of the Cell using getContents() method, which will return
          //it as a String
          String str_colArow1 = colArow1.getContents();
          String str_colBrow1 = colBrow1.getContents();
          String str_colArow2 = colArow2.getContents();
           
          //Display the cell contents
          System.out.println("Contents of cell Col A Row 1: \""+str_colArow1 + "\"");
          System.out.println("Contents of cell Col B Row 1: \""+str_colBrow1 + "\"");
          System.out.println("Contents of cell Col A Row 2: \""+str_colArow2 + "\"");

           
      } catch (BiffException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
       

  }
  
  
  
  @AfterTest
  public void afterTest()
  {
	  driver.quit();
	  driver = null;
  }
}
