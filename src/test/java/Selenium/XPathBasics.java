package Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class XPathBasics {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void openFacebook() {
        driver.get("https://www.facebook.com/");
        Assert.assertTrue(driver.getTitle().contains("Facebook"), "Facebook page did not load");
    }

    @Test(priority = 2)
    public void openSignupPage() throws InterruptedException {
        WebElement signupLink = driver.findElement(By.xpath("//a[text()='Create new account']"));
        signupLink.click();
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void enterUserDetails() {
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Aryan");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Dutta");
        driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("duttaryan2002@gmail.com");
        driver.findElement(By.xpath("//input[@name='reg_passwd__']")).sendKeys("SecurePass123");
    }

    @Test(priority = 4)
    public void selectBirthDate() {
        Select day = new Select(driver.findElement(By.xpath("//select[@id='day']")));
        day.selectByValue("25");

        Select month = new Select(driver.findElement(By.xpath("//select[@id='month']")));
        month.selectByValue("6");

        Select year = new Select(driver.findElement(By.xpath("//select[@id='year']")));
        year.selectByValue("2002");
    }

    @Test(priority = 5)
    public void selectGender() {
        WebElement maleRadioBtn = driver.findElement(By.xpath("//input[@value='2']"));
        maleRadioBtn.click();
        Assert.assertTrue(maleRadioBtn.isSelected(), "Male option is not selected");
    }

    @Test(priority = 6)
    public void takeScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destination = new File("C:\\Users\\dutta\\OneDrive\\Pictures\\selenium ss\\facebook_signup.png");
        FileUtils.copyFile(screenshot, destination);
        System.out.println("Screenshot saved at: " + destination.getAbsolutePath());
        Assert.assertTrue(destination.exists(), "Screenshot was not saved successfully");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
