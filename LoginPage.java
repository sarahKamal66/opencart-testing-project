package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;

public class LoginPage {
    // locators
    By email_field= By.cssSelector("input[type='email']");
    By pass_field= By.cssSelector("input[type='password']");
    By login_button= By.xpath("//div[@class='text-end']//button[@type='submit']");

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enter_email(String email)
    {
        driver.findElement(email_field).sendKeys(email);
    }
    public void enter_pass(String pass)
    {
        driver.findElement(pass_field).sendKeys(pass);
    }
    public void click_login_button()
    {
        driver.findElement(login_button).click();
    }
    public void login(String email, String pass)
    {
        enter_email(email);
        enter_pass(pass);
        click_login_button();
    }

    public String getMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='content']//h1[contains(text(),'My Account')]")
        ));
        return msg.getText();
    }




}
