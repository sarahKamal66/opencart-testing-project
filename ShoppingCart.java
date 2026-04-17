package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class ShoppingCart
{
    WebDriver driver;

    public ShoppingCart(WebDriver driver) {
        this.driver = driver;
    }

    // locators

    By cart_button=By.xpath("//a[@title='Shopping Cart']");
    By message=By.xpath("//p[contains(text(),'Your shopping cart is empty')]");
    By add_to_cartBtn=By.xpath("//div[@class='input-group']//button[@type='submit']");
    By cartItems=By.xpath("//div[@class='dropdown d-grid']//button[@type='button']");
    By itemName=By.xpath("(//table[@class='table table-striped mb-2']//a)[2]");
    By removeBtn2=By.xpath("//table[@class='table table-bordered']//a[@class='btn btn-danger']");

    public void navigate_to_cart()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartBtn = wait.until(
                ExpectedConditions.elementToBeClickable(cart_button)
        );
        cartBtn.click();
    }
    public String get_message()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(message));
        return msg.getText();
    }
    public void addToCart(String product)
    {
        ProductDetails pro = new ProductDetails(driver);
        pro.get_product_details(product);


        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait2.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//div[@class='col-sm']//h1"), product
        ));

        WebElement addBtn = wait2.until(ExpectedConditions.visibilityOfElementLocated(add_to_cartBtn));
        addBtn.click();
    }
    public String getCartItem()
    {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cart = wait1.until(ExpectedConditions.visibilityOfElementLocated(cartItems));
        cart.click();

        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement item = wait2.until(ExpectedConditions.visibilityOfElementLocated(itemName));
        return item.getText();

    }
    public void removeItem2()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement remove = wait.until(ExpectedConditions.visibilityOfElementLocated(removeBtn2));
        remove.click();
    }


}