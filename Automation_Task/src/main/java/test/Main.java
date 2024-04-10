package test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Main {
	WebDriver driver;
	String baseUrl = "https://itassetmanagementsoftware.com/rolepermission/admin/login";

	@BeforeMethod
	public void setUp() {
		// System.setProperty("webdriver.chrome.driver", null);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	@Test(priority = 1)
	public void linkCheck() {
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, baseUrl);
	}

	@Test(priority = 2)
	public void signIn() {
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("subhamgmail.com");
		WebElement submit = driver.findElement(By.id("btnContinue"));
		submit.click();

		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement errorMsg = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='validateUserMsg']")));
		Assert.assertTrue(errorMsg.isDisplayed(), "Please enter valid username to continue");

	}

	@Test(priority = 3)
	public void ms_signin() {
		WebElement msBtn = driver.findElement(By.id("btnMsLogin"));
		msBtn.click();
		WebElement email = driver.findElement(By.id("i0116"));
		email.sendKeys("shubhamtestnew@outlook.com");
		
		WebElement next = driver
				.findElement(By.xpath("//input[@id='idSIButton9']"));
		next.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement pswd = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.name("passwd")));
		pswd.sendKeys("AutomationTest@123");
		
		WebElement signIn = driver
				.findElement(By.xpath("//button"));
		signIn.click();
		
		WebDriverWait wait1 = new WebDriverWait(driver, 5);
		WebElement cnfrm = wait1
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='acceptButton']")));
		cnfrm.click();
		
		WebDriverWait wait2 = new WebDriverWait(driver, 5);
		WebElement err1 = wait2
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='pageDescription']")));
		Assert.assertTrue(err1.isDisplayed(), "Unable to move further");
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
