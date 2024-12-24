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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import junit.framework.Assert;
public class ShoppingCart {
	WebDriver driver;
	@BeforeMethod
	@Parameters("browser")
	public void setUp(@Optional("chrome")String browser) {
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
		driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys("MacBook");
		driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
		WebElement productLink=driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]/div/div[2]/div[1]/h4/a"));
		productLink.click();
	}
	@Test(priority=1)
	public void testAddProductToCart() {
		 WebElement addToCartButton = driver.findElement(By.id("button-cart"));
	        addToCartButton.click();
	       driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).click();
	       WebElement cart=driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[1]/table/tbody/tr/td[3]"));
	       String quantity=cart.getText();
	       Assert.assertTrue(cart.isDisplayed());
	       Assert.assertEquals(quantity, "x1");
	       System.out.println("Quantity:"+quantity);
	}
	@Test(priority=2)
	public void testAddMultipleQuantities() {
		WebElement qtyInputField=driver.findElement(By.id("input-quantity"));
		qtyInputField.clear();
		qtyInputField.sendKeys("3");
		 WebElement addToCartButton = driver.findElement(By.id("button-cart"));
	        addToCartButton.click();
	        driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).click();
		       WebElement cart=driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[1]/table/tbody/tr/td[3]"));
		       String quantity=cart.getText();
		       Assert.assertTrue(cart.isDisplayed());
		       Assert.assertEquals(quantity, "x3");
		       System.out.println("Quantity:"+quantity);
	        
	}
	@Test(priority=3)
	public void testRemoveProductFromCart() throws InterruptedException {
		 driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).click();
		 driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[1]/table/tbody/tr/td[5]/button")).click();
		 Thread.sleep(Duration.ofSeconds(10));
		WebElement msg=driver.findElement(By.xpath("/html/body/header/div/div/div[3]/div/ul/li/p"));
		String display=msg.getText();
		System.out.println(display);
		Assert.assertEquals(display, "Your shopping cart is empty!");
	}
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
