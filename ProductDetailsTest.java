package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.LoginPage;
import org.example.ProductDetails;
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

import java.time.Duration;

public class ProductDetailsTest {
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
    @Test
    public void viewDetailsForAProduct() throws InterruptedException
    {
        Thread.sleep(5000);
        driver.navigate().to("http://localhost/demo/");

        ProductDetails pro=new ProductDetails(driver);

        pro.get_product_details("Canon EOS 5D");

        String actual = pro.getProductName();
        String expected = "Canon EOS 5D";

        Assert.assertEquals(actual, expected);
    }
    @Test
    public void compareProduct() throws InterruptedException {

        Thread.sleep(5000);
        driver.navigate().to("http://localhost/demo/");

        ProductDetails pro=new ProductDetails(driver);
        pro.get_product_details("iPhone");

        pro.compare("iPhone");


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='alert alert-success alert-dismissible']")
                )
        );
        Assert.assertTrue(alert.getText().contains("Success: You have added iPhone to your product comparison!"));
        wait.until(ExpectedConditions.invisibilityOf(alert));
    }

    @Test
    public void addToWishList() throws InterruptedException
    {

        Thread.sleep(5000);
        driver.navigate().to("http://localhost/demo/");


        ProductDetails pro=new ProductDetails(driver);
        pro.get_product_details("iPhone");

        pro.wishList("iPhone");



        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alert1 = wait1.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='alert alert-success alert-dismissible']")
                )
        );
        Assert.assertTrue(alert1.getText().contains("Success: You have added iPhone to your wish list!"));
        wait1.until(ExpectedConditions.invisibilityOf(alert1));
    }
}