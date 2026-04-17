package org.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.RegistrationPage;
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

import java.time.Duration;

public class RegistrationTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Program Files/BraveSoftware/Brave-Browser/Application/brave.exe");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://localhost/demo/index.php?route=account/register");
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @Test(priority = 0)
    public void register_with_valid_data() {
        RegistrationPage reg = new RegistrationPage(driver);

        String email = "sara@test332.com";

        reg.register("Sara", "Kamal", email,  "Test@22");

        String actual = reg.getConfirmationMessage();
        String expected = "Your Account Has Been Created!";

        Assert.assertTrue(actual.contains(expected), "registration should work with valid data");

    }

    @Test(priority = 1)
    public void register_with_invalid_data() {

        RegistrationPage reg = new RegistrationPage(driver);

        // Using the same email from previous test
        String duplicateEmail = "sara@test111.com";

        reg.register("Sara", "Kamal", duplicateEmail,  "123hjk");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement alert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".alert.alert-danger.alert-dismissible")
                )
        );

        Assert.assertTrue(alert.getText().contains("Warning: E-Mail Address is already registered!"));
        wait.until(ExpectedConditions.invisibilityOf(alert));
    }
}