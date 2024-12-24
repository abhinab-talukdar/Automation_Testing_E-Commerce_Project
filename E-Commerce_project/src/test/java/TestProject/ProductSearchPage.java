package TestProject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import junit.framework.Assert;


public class ProductSearchPage {
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

    @Test(dataProvider = "productSearchData")
    public void productSearch(String searchInput, String expectedMessage) {
        driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys(searchInput);
        driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();

        if (expectedMessage != null) {
            WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/p[2]"));
            String actualMessage = errorMessage.getText();
            Assert.assertEquals(actualMessage, expectedMessage);
        }
    }

    @DataProvider(name = "productSearchData")
    public Object[][] productSearchData() {
        return new Object[][] {
            // Valid product name search
            { "MacBook", null },

            // Partial product name search
            { "Mac", null },

            // Non-existent product search
            { "XYZ Laptop", "There is no product that matches the search criteria." },

            // Special characters in product name search
            { "Mac@Book", "There is no product that matches the search criteria." },

            // Multiple keywords in product name search
            { "Laptop Apple", null },

            // Similar product name search
            { "iPhone", null },

            // Non-existent product name search
            { "Flying Car", "There is no product that matches the search criteria." },

            // Auto-complete search (assuming this is the intent)
            { "Mac", null },

            // Partial data search
            { "Pho", null },

            // Empty data search
            { "", "There is no product that matches the search criteria." }
        };
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
