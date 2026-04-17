package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {

    WebDriver driver;

    // Locators
    By fname = By.id("input-firstname");
    By lname = By.id("input-lastname");
    By email = By.id("input-email");
    By password = By.cssSelector("input[type='password']");
    By privacyPolicy = By.cssSelector("input[name='agree']");
    By continueBtn = By.xpath("//div[@class='text-end']//button[@type='submit']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterFirstName(String first) {
        driver.findElement(fname).sendKeys(first);
    }

    public void enterLastName(String last) {
        driver.findElement(lname).sendKeys(last);
    }

    public void enterEmail(String mail) {
        driver.findElement(email).sendKeys(mail);
    }

    public void enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    public void acceptPrivacyPolicy() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(privacyPolicy));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(privacyPolicy));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(privacyPolicy));
    }

    public void clickContinue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(continueBtn));


        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }


    public void register(String first, String last, String mail, String pass) {
        enterFirstName(first);
        enterLastName(last);
        enterEmail(mail);
        enterPassword(pass);
        acceptPrivacyPolicy();
        clickContinue();
    }

    public String getConfirmationMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(),'Your Account Has Been Created')]")
        ));
        return successMsg.getText();
    }
}