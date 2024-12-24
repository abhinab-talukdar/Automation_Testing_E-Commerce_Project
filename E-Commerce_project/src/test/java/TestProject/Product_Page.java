package TestProject;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import junit.framework.Assert;


public class Product_Page {

	WebDriver driver;
	@BeforeMethod
	@Parameters("browser")
	public void setUp(@Optional("chrome") String browser)
	{
		if (browser.equalsIgnoreCase("chrome")) {
			 ChromeOptions options = new ChromeOptions();
		        driver = new ChromeDriver(options);
			}else if (browser.equalsIgnoreCase("firefox")) {
				FirefoxOptions options=new FirefoxOptions();
				driver=new FirefoxDriver(options);
			}
			else if(browser.equalsIgnoreCase("edge")) {
				EdgeOptions options=new EdgeOptions();
				driver=new EdgeDriver(options);
			}
		
	
		ChromeOptions options=new ChromeOptions();
		driver=new ChromeDriver(options);
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a")).click();
		driver.findElement(By.id("input-email")).sendKeys("rk9487514@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Administrator123@sfs");
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();
		driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys("MacBook");
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
	}
	@Test
	public void testProductDetails()
	{
		WebElement productLink=driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]/div/div[2]/div[1]/h4/a"));
		productLink.click();
		//Wait for the product page to load
		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		//Find product name, price, availability and description on the page
		WebElement productNameElement=driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/h1"));
		WebElement productPriceElement=driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/ul[2]/li[1]/h2"));
		WebElement ProductAvailabilityElement=driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/ul[1]/li[4]"));
		WebElement ProductDescriptionElement=driver.findElement(By.cssSelector("div#tab-description"));
        
        //Retrieve the text values
        String productName=productNameElement.getText();
        String productPrice=productPriceElement.getText();
        String productAvailability=ProductAvailabilityElement.getText();
        String productDescription=ProductDescriptionElement.getText();
        
		//Test Assertions
        Assert.assertTrue(productNameElement.isDisplayed());
        Assert.assertEquals(productName, "MacBook Air");
        Assert.assertTrue(productPriceElement.isDisplayed());
        Assert.assertEquals(productPrice, "$1,202.00");
        Assert.assertTrue(ProductAvailabilityElement.isDisplayed());
        Assert.assertEquals(productAvailability, "Availability:Out Of Stock");
        Assert.assertTrue(ProductDescriptionElement.isDisplayed());
        Assert.assertEquals(productDescription,"MacBook Air is ultrathin, ultraportable, and ultra unlike anything else. But you don’t lose inches and pounds overnight."
        		+ " It’s the result of rethinking conventions. Of multiple wireless innovations. And of breakthrough design. With MacBook Air, mobile computing suddenly has a new standard.");

		System.out.println("Product name:"+productName);
		System.out.println("Product price:"+productPrice);
		System.out.println("Product Availability:"+productAvailability);
		System.out.println("Product Description:"+productDescription);
	}
	@Test
	public void testMultipleProductDetails()
	{
		for (int i=0;i<10;i++)
		{
			testProductDetails();
		}
	}
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	
}
