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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class ProductFilter {
	
	WebDriver driver;
	@BeforeMethod
	 @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
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
    	driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a")).click();
		driver.findElement(By.id("input-email")).sendKeys("rk9487514@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Administrator123@sfs");
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();
	}
	@Test
	public void validProductName()
	{
		driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys("Monitors");
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		driver.close();
		}
	@Test
	public void BrandFilter()
	{
		driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys("Apple");
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
	}
	@Test
	public void FilterPriceHighToLow()
	{
		driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys("MacBook");
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		WebElement dropdown=driver.findElement(By.xpath("//*[@id=\"input-sort\"]"));
		dropdown.click();
		Select select=new Select(dropdown);
		select.selectByIndex(4);
		driver.close();
	}
	@Test
	public void FilterPriceLowToHigh()
	{
		driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys("MacBook");
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		WebElement dropdown=driver.findElement(By.xpath("//*[@id=\"input-sort\"]"));
		dropdown.click();
		Select select=new Select(dropdown);
		select.selectByIndex(3);
		driver.close();
	}
	@Test
	public void HighestRating()
	{
		driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys("MacBook");
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		WebElement dropdown=driver.findElement(By.xpath("//*[@id=\"input-sort\"]"));
		dropdown.click();
		Select select=new Select(dropdown);
		select.selectByIndex(5);
		driver.close();
	}
	@Test
	public void LowestRating()
	{
		driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys("MacBook");
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		WebElement dropdown=driver.findElement(By.xpath("//*[@id=\"input-sort\"]"));
		dropdown.click();
		Select select=new Select(dropdown);
		select.selectByIndex(6);
		driver.close();
	}
	@Test
	public void ModelAtoZ()
	{
		driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys("MacBook");
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		WebElement dropdown=driver.findElement(By.xpath("//*[@id=\"input-sort\"]"));
		dropdown.click();
		Select select=new Select(dropdown);
		select.selectByIndex(7);
		driver.close();
	}
	@Test
	public void ModelZtoA()
	{
		driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys("MacBook");
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		WebElement dropdown=driver.findElement(By.xpath("//*[@id=\"input-sort\"]"));
		dropdown.click();
		Select select=new Select(dropdown);
		select.selectByIndex(8);
		driver.close();
	}	
	@Test
	public void NameAtoZ()
	{
		driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys("MacBook");
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		WebElement dropdown=driver.findElement(By.xpath("//*[@id=\"input-sort\"]"));
		dropdown.click();
		Select select=new Select(dropdown);
		select.selectByIndex(1);
		driver.close();
	}
	@Test
	public void NameZtoA()
	{
		driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys("MacBook");
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		WebElement dropdown=driver.findElement(By.xpath("//*[@id=\"input-sort\"]"));
		dropdown.click();
		Select select=new Select(dropdown);
		select.selectByIndex(2);
		driver.close();
	}
	@Test
	public void MultipleFilters() throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys("MacBook");
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		WebElement dropdown1=driver.findElement(By.xpath("//*[@id=\"input-sort\"]"));
		dropdown1.click();
		Select select1=new Select(dropdown1);
		select1.selectByIndex(4);
		Thread.sleep(Duration.ofSeconds(10));
		WebElement dropdown2=driver.findElement(By.xpath("//*[@id=\"input-sort\"]"));
		dropdown2.click();
		Select select2=new Select(dropdown2);
		select2.selectByIndex(5);
		driver.close();
	}
	@Test
	public void NonExistentBrand()
	{
		driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys("Lenovo");
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		WebElement errmsg=driver.findElement(By.xpath("//*[@id=\"content\"]/p[2]"));
		String actualmsg=errmsg.getText();
		String expectedmsg="There is no product that matches the search criteria.";
		Assert.assertEquals(actualmsg,expectedmsg);
		driver.close();
		
	}

}
