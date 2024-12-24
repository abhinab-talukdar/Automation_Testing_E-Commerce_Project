package TestProject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import junit.framework.Assert;
public class LoginPage {
	//C:\Users\Admin\git\repository\E-Commerce_project;https://github.com/abhinab-talukdar/E-Commerce_Automation-Testing-Project.git
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
	        driver.get("https://tutorialsninja.com/demo/");
	        driver.manage().window().maximize();
	        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a")).click();
	        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a")).click();
	}
	@DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
            // Valid login
            { "rk9487514@gmail.com", "Administrator123@sfs", null },
            // Invalid email
            { "rk9487514gmail.com", "Administrator123@sfs", "Warning: No match for E-Mail Address and/or Password." },
            // Incorrect password
            { "rk9487514@gmail.com", "Admin123@sfs", "Warning: No match for E-Mail Address and/or Password." },
            // Empty email
            { "", "Administrator123@sfs", "Warning: No match for E-Mail Address and/or Password." },
            // Empty password
            { "rk9487514@gmail.com", "", "Warning: No match for E-Mail Address and/or Password." },
            // Both fields empty
            { "", "", "Warning: No match for E-Mail Address and/or Password." },
            // Banned account
            { "rohitgs234@gmail.com", "rohit123@sfs", null },
            // Generic error (invalid credentials)
            { "abin9487514@gmail.com", "abin123@sfs", "Warning: No match for E-Mail Address and/or Password." }
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String email, String password, String expectedError) {
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-password")).clear();

        // Enter email and password
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();

        // If an error message is expected
        if (expectedError != null) {
            WebElement errmsg = driver.findElement(By.xpath("//*[@id=\"account-login\"]/div[1]"));
            String actualmsg = errmsg.getText();
            Assert.assertEquals(actualmsg, expectedError);
        }
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
