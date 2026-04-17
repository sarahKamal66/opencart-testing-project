package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.LoginPage;
import org.example.ShoppingCart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class ShoppingCartTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Program Files/BraveSoftware/Brave-Browser/Application/brave.exe");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();


        driver.get("http://localhost/demo/index.php?route=account/login&language=en-gb");
        LoginPage user=new LoginPage(driver);
        user.login("sara@test00000.com","Test@000000");

    }
    @Test(priority = 2)
    public void check_emptyCart() throws InterruptedException
    {
        Thread.sleep(5000);
        driver.navigate().to("http://localhost/demo/");


        ShoppingCart cart=new ShoppingCart(driver);
        cart.navigate_to_cart();

        String actual =cart.get_message();
        String expected="Your shopping cart is empty!";

        SoftAssert soft=new SoftAssert();
        soft.assertTrue(actual.contains(expected));
        soft.assertAll();
    }
    @Test(priority = 0)
    public void addToCart() throws InterruptedException
    {
        Thread.sleep(5000);
        driver.navigate().to("http://localhost/demo/");

        ShoppingCart cart=new ShoppingCart(driver);
        cart.addToCart("iPhone");


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='alert alert-success alert-dismissible']")
                )
        );
        Assert.assertTrue(alert.getText().contains("Success: You have added iPhone to your shopping cart!"));
        wait.until(ExpectedConditions.invisibilityOf(alert));



        String result=cart.getCartItem();
        Assert.assertTrue(result.contains("iPhone"));
    }
    @Test(priority = 1)
    public void removeFromCart() throws InterruptedException
    {
        Thread.sleep(5000);
        driver.navigate().to("http://localhost/demo/");


        ShoppingCart cart = new ShoppingCart(driver);
        cart.navigate_to_cart();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        cart.removeItem2();

        Assert.assertTrue(cart.get_message().contains("Your shopping cart is empty!"));

    }
}