package Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FramesHandling {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/frames");
    }

    @Test(priority = 1)
    public void testFrame1() {
        driver.switchTo().frame("frame1");
        WebElement frame1Text = driver.findElement(By.id("sampleHeading"));
        System.out.println("Text in Frame 1: " + frame1Text.getText());
        driver.switchTo().defaultContent();
    }

    @Test(priority = 2)
    public void testFrame2() {
        driver.switchTo().frame("frame2");
        WebElement frame2Text = driver.findElement(By.id("sampleHeading"));
        System.out.println("Text in Frame 2: " + frame2Text.getText());
        driver.switchTo().defaultContent();
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
