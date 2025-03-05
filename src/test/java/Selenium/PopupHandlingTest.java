package Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.time.Duration;
import java.util.Set;

public class PopupHandlingTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void testJavaScriptAlertPopup() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.id("alertButton"));
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "You clicked a button");
        alert.accept();
    }

    @Test(priority = 2)
    public void testJavaScriptConfirmPopup() {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.id("confirmButton")).click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    @Test(priority = 3)
    public void testJavaScriptPromptPopup() {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.id("promtButton")).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Test Input");
        alert.accept();
    }

    @Test(priority = 4)
    public void testHiddenDivisionPopup() {
        driver.get("https://www.w3schools.com/howto/howto_js_popup.asp");
        driver.findElement(By.xpath("//button[text()='Try it']")).click();
        WebElement popup = driver.findElement(By.className("w3-modal"));
        Assert.assertTrue(popup.isDisplayed());
    }

    @Test(priority = 5)
    public void testFileUpload() {
        driver.get("https://demoqa.com/upload-download");
        WebElement uploadElement = driver.findElement(By.id("uploadFile"));
        File file = new File("C:\\Users\\dutta\\Downloads\\Selenium Notes.pdf");
        uploadElement.sendKeys(file.getAbsolutePath());
        Assert.assertTrue(driver.findElement(By.id("uploadedFilePath")).getText().contains("Selenium Notes.pdf"));
    }

    @Test(priority = 6)
    public void testFileDownload() {
        driver.get("https://demoqa.com/upload-download");
        driver.findElement(By.id("downloadButton")).click();
        // Add verification logic based on your download location
    }

    @Test(priority = 7)
    public void testChildBrowserPopup() {
        driver.get("https://demoqa.com/browser-windows");
        driver.findElement(By.id("windowButton")).click();
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("sample"));
        driver.close();
        driver.switchTo().window(parentWindow);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
