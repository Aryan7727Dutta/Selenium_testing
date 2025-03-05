package Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class ListBoxTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/select-menu");
    }

    @Test(priority = 1)
    public void testSelectOptions() {
        WebElement dropdown = driver.findElement(By.id("oldSelectMenu"));
        Select select = new Select(dropdown);

        select.selectByIndex(2);
        System.out.println("Selected by index: " + select.getFirstSelectedOption().getText());

        select.selectByValue("4");
        System.out.println("Selected by value: " + select.getFirstSelectedOption().getText());
    }

    @Test(priority = 2)
    public void testDeselectOptions() {
        WebElement multiSelect = driver.findElement(By.id("cars"));
        Select select = new Select(multiSelect);

        if (select.isMultiple()) {
            select.selectByIndex(1);
            select.selectByValue("volvo");

            select.deselectByIndex(1);
            select.deselectByValue("volvo");
            System.out.println("Deselected options successfully.");
        }
    }

    @Test(priority = 3)
    public void testIsMultiple() {
        WebElement multiSelect = driver.findElement(By.id("cars"));
        Select select = new Select(multiSelect);
        System.out.println("Is multi-select: " + select.isMultiple());
    }

    @Test(priority = 4)
    public void testGetAllOptions() {
        WebElement dropdown = driver.findElement(By.id("oldSelectMenu"));
        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();

        System.out.println("Available options:");
        for (WebElement option : options) {
            System.out.println(option.getText());
        }
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
