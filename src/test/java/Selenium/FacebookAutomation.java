package Selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
public class FacebookAutomation {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        System.out.println("Browser Opened");
    }

    @Test(priority = 1)
    public void openLoginPage() {
        driver.get("https://www.facebook.com/");
        System.out.println("Opened Login Page");
        System.out.println("Title: " + driver.getTitle());
        System.out.println("URL: " + driver.getCurrentUrl());
    }

    @Test(priority = 2)
    public void maximizeWindow() {
        driver.manage().window().maximize();
        System.out.println("Window Maximized");
    }

    @Test(priority = 3)
    public void loginToFacebook() throws InterruptedException {
        WebElement email = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("pass"));
        WebElement loginButton = driver.findElement(By.name("login"));

        email.sendKeys("duttaryan2002@gmail.com");
        password.sendKeys("duttaryan@2002");
        loginButton.click();

        Thread.sleep(3000);
        System.out.println("Logged Into Facebook");
        System.out.println("Title: " + driver.getTitle());
        System.out.println("URL: " + driver.getCurrentUrl());
    }

    @Test(priority = 4)
    public void navigateBackToLogin() throws InterruptedException {
        driver.navigate().back();
        Thread.sleep(2000);
        System.out.println("Navigated Back to Login Page");
        System.out.println("Title: " + driver.getTitle());
        System.out.println("URL: " + driver.getCurrentUrl());
    }

    @Test(priority = 5)
    public void openSignUpPage() throws IOException, InterruptedException {
        driver.get("https://www.facebook.com/r.php");
        Thread.sleep(2000);
        System.out.println("Opened Sign-Up Page");
        System.out.println("Title: " + driver.getTitle());
        System.out.println("URL: " + driver.getCurrentUrl());
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage image = ImageIO.read(screenshot);
        File outputFile = new File("C:\\Users\\dutta\\OneDrive\\Pictures\\Screenshots\\Assignment\\signup_screenshot.jpg");
        ImageIO.write(image, "jpg", outputFile);
        System.out.println("Screenshot saved at: " + outputFile.getAbsolutePath());
    }
    @Test(priority = 6)
    public void minimizeWindow() {
        driver.manage().window().minimize();
        System.out.println("Window Minimized");
    }
    @AfterClass
    public void teardown() {
        driver.quit();
        System.out.println("Browser Closed");
    }
    //xpath basic, mouse(via location) and keyboard
    //find about relative locator
}

