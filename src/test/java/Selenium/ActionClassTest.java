package Selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ActionClassTest {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/buttons");
        actions = new Actions(driver);
    }

    @Test(priority = 1)
    public void testMoveToElement() {
        driver.get("https://demoqa.com/menu");
        WebElement menuItem = driver.findElement(By.xpath("//a[text()='Main Item 2']"));
        actions.moveToElement(menuItem).perform();
        System.out.println("Moved to element: Main Item 2");
    }

    @Test(priority = 2)
    public void testContextClick() {
        driver.get("https://demoqa.com/buttons");
        WebElement rightClickButton = driver.findElement(By.id("rightClickBtn"));
        actions.contextClick(rightClickButton).perform();
        System.out.println("Right-clicked on button");
    }

    @Test(priority = 3)
    public void testDragAndDrop() {
        driver.get("https://demoqa.com/droppable");
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        actions.dragAndDrop(source, target).perform();
        System.out.println("Dragged and dropped successfully");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
