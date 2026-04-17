package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetails {

    By productTitle = By.xpath("//div[@class='col-sm']//h1");
    By compareIcon=By.xpath("(//div[@class='btn-group']/button)[2]");
    By wishIcon=By.xpath("(//div[@class='btn-group']/button)[1]");
    WebDriver driver;

    public ProductDetails(WebDriver driver) {
        this.driver = driver;
    }

    public void get_product_details(String product_name)
    {
        WebElement ele = driver.findElement(By.xpath("//a[normalize-space()='" + product_name + "']"));


        ((JavascriptExecutor) driver).executeScript(
                "var rect = arguments[0].getBoundingClientRect();" +
                        "window.scrollBy(0, rect.top - 100);", ele
        );


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(ele));

        try {

            ele.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
        }

    }
    public String getProductName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle));

        return title.getText();
    }
    public void wishList(String proName)
    {
        get_product_details(proName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement wish = wait.until(ExpectedConditions.visibilityOfElementLocated(wishIcon));
        wish.click();


    }
    public void compare(String proName)
    {
        get_product_details(proName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement comp = wait.until(ExpectedConditions.visibilityOfElementLocated(compareIcon));
        comp.click();


    }


}