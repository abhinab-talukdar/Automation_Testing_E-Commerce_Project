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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RegistrationPage {
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
		driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a")).click();
	}
	@DataProvider(name="userData")
	public Object[][] createUserData()
	{
		return new Object[][] {
			 {"Abhinab", "Talukdar", "rk9487514@gmail.com", "8134076829", "Administrator123@sfs", "Administrator123@sfs", true, "Continue", null},
	            {"Abhinab", "Talukdar", "invalidemail.com", "8134076829", "Administrator123@sfs", "Administrator123@sfs", true, "Continue", "Invalid email address!"},
	            {"Abhinab", "Talukdar", "rohit23@gmail.com", "8134076829", "Administrator123@sfs", "Admin123@sfs", true, "Continue", "Password confirmation does not match password!"},
	            {"", "Talukdar", "umesh45@gmail.com", "8134076829", "Administrator123@sfs", "Administrator123@sfs", true, "Continue", "First Name must be between 1 and 32 characters"},
	            {"Abhinab", "Talukdar", "iman87@gmail.com", "8134076829", "weak", "weak", true, "Continue", null},
	            {"Abhinab", "Talukdar", "amal456@gmail.com", "12345", "Admin123@sfs", "Admin123@sfs", true, "Continue", null},
	            {"Abhinab", "Talukdar", "bbbfgb@gmail.com", "8134076829", "Admin123@sfs", "Admin123@sfs", false, "Continue", "You must agree to the Privacy Policy!"},
	            {"Abhin@b", "Talukdar", "rk9487514@gmail.com", "8134076829", "Admin123@sfs", "Admin123@sfs", true, "Continue", null},
	            {"Abhi", "Kumar", "rk9487514@gmail.com", "7063058743", "Admin123@sfs", "Admin123@sfs", true, "Continue", "E-Mail Address is already registered!"}
		};
	}
	@Test(dataProvider="userData")
	 public void registrationTest(String firstName, String lastName, String email, String telephone, String password, String confirmPassword, boolean agree, String submitButton, String expectedMessage) {
        driver.findElement(By.id("input-firstname")).sendKeys(firstName);
        driver.findElement(By.id("input-lastname")).sendKeys(lastName);
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-telephone")).sendKeys(telephone);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.id("input-confirm")).sendKeys(confirmPassword);
        if (agree) {
            driver.findElement(By.name("agree")).click();
        }

        driver.findElement(By.xpath("//input[@value='" + submitButton + "']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        if (expectedMessage != null) {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + expectedMessage + "')]")));
            Assert.assertTrue(errorMessage.getText().contains(expectedMessage));
        } else {
            wait.until(ExpectedConditions.urlContains("account/success"));
        }
	}
	 @AfterClass
		public void tearDown()
		{
			driver.quit();
		}
}
