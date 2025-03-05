package Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class WebTableTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/webtables");
    }

    @Test(priority = 1)
    public void testTotalElementCount() {
        List<WebElement> allCells = driver.findElements(By.xpath("//div[@class='rt-td']"));
        System.out.println("Total number of elements (cells) in the table: " + allCells.size());
    }

    @Test(priority = 2)
    public void testColumnCount() {
        List<WebElement> columns = driver.findElements(By.xpath("//div[@class='rt-thead -header']//div[@class='rt-resizable-header-content']"));
        System.out.println("Total number of columns in the table: " + columns.size());
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
