package org.example;

//import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreateTestDriver {
    public static void main(String[] args) {

        //Create a instance of ChromeOptions class
        ChromeOptions options = new ChromeOptions();

//Add chrome switch to disable notification - "**--disable-notifications**"
        options.addArguments("--disable-notifications");

        WebDriverManager.chromedriver().arch64().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/");

        //find the first item
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.name("search")));

        WebElement inputField = driver.findElement(By.name("search"));
        inputField.sendKeys("Acer Aspire");

        WebElement findButton = driver.findElement(By.cssSelector(".search-form__submit"));
        findButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".catalog-grid li:nth-child(3) a")));

        WebElement findResult = driver.findElement(By.cssSelector(".catalog-grid li:nth-child(3) a"));
        findResult.click();

        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".buy-button__label")));

        WebElement buyButton = driver.findElement(By.cssSelector(".buy-button__label"));
        buyButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".modal__close")));

        WebElement closeButton = driver.findElement(By.cssSelector(".modal__close"));
        closeButton.click();

        //find the second item
        wait.until(ExpectedConditions.elementToBeClickable(By.name("search")));

        WebElement inputField2 = driver.findElement(By.name("search"));
        inputField2.sendKeys("FREGGIA");

        WebElement findButton2 = driver.findElement(By.cssSelector(".search-form__submit"));
        findButton2.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".catalog-grid li:nth-child(3) a")));

        WebElement findResult2 = driver.findElement(By.cssSelector(".catalog-grid li:nth-child(3) a"));
        findResult2.click();
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);

        js.executeScript("window.scrollBy(0,250)", "");

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".buy-button__label")));

        WebElement buyButton2 = driver.findElement(By.cssSelector(".buy-button__label"));
        buyButton2.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cart-receipt__submit")));

        WebElement submitOrder = driver.findElement(By.cssSelector(".cart-receipt__submit"));
        submitOrder.click();

        //Total checkout
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".checkout-product__title")));

        List<WebElement> checkoutTotal = driver.findElements(By.cssSelector(".checkout-product__title"));
        if (checkoutTotal.size() == 2) {
            System.out.println("There are 2 items in your basket");
        } else {
            System.out.println("Test failed");
        }

        driver.quit();
    }
}