package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UtilityLibrary {

    public static void waitForElementToBeClickable(WebDriver driver, WebElement locator, int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public static void waitForElementToBeInvisible(WebDriver driver, By by, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

}
