package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class LoginTest
{
    WebDriver driver;
    @BeforeMethod
    public void setup() {
        // Setup ChromeDriver (Brave uses Chromium, so this works)
        WebDriverManager.chromedriver().setup();

        // Tell Selenium to use Brave browser
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Program Files/BraveSoftware/Brave-Browser/Application/brave.exe");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://localhost/demo/index.php?route=account/login&language=en-gb");
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @Test(priority = 0)
    public void login_with_valid()
    {
        LoginPage obj=new LoginPage(driver);

        obj.login("sara@test00000.com", "Test@000000");
        String actual= obj.getMessage();
        String expected = "My Account";
        Assert.assertEquals(actual, expected, "Login did not succeed!");
    }
    @Test(priority = 1)
    public void login_with_invalid()
    {
        LoginPage obj=new LoginPage(driver);
        obj.login("nanauudu@test.com","788#hhKll");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".alert.alert-danger.alert-dismissible")
                )
        );

        Assert.assertTrue(alert.getText().contains(" No match for E-Mail Address and/or Password."));
        wait.until(ExpectedConditions.invisibilityOf(alert));
    }

}