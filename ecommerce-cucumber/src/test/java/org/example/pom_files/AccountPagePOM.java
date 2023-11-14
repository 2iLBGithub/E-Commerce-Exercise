package org.example.pom_files;

import org.example.utilities.UtilityLibrary;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class AccountPagePOM {

    protected WebDriver driver;

    public AccountPagePOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "username")
    WebElement selectUsername;
    @FindBy(id = "password")
    WebElement selectPassword;
    @FindBy(css = "#customer_login > div.u-column1.col-1 > form > p:nth-child(3) > button")
    WebElement loginButton;
    @FindBy(css = "li.woocommerce-MyAccount-navigation-link:nth-child(6) > a:nth-child(1)")
    WebElement logOut;
    @FindBy(css = "#post-7 > div > div > nav > ul > li.woocommerce-MyAccount-navigation-link.woocommerce-MyAccount-navigation-link--orders")
    WebElement goToOrders;
    @FindBy(css = "#post-7 > div > div > div > table > tbody > tr > td:nth-child(1)")
    List<WebElement> orderNumberCells;
    @FindBy(css = "td[data-title='Order'] a")
    WebElement orderDataCell;

    public void logOutOfSite() {
//        UtilityLibrary.waitForElementToBeVisible(driver, logOut, 3);
        logOut.click();
    }

    public void logInToSite(String username, String password) {
        UtilityLibrary.waitForElementToBeVisible(driver, selectUsername, 3);
        selectUsername.click();
        selectUsername.sendKeys(username);
        selectPassword.click();
        selectPassword.sendKeys(password);
        loginButton.click();
    }

    public void viewOrders() {
        UtilityLibrary.waitForElementToBeVisible(driver, goToOrders, 3);
        goToOrders.click();
    }

//    public void checkOrderNumber(String expectedValue) {
//        String foundValue = null;
//        System.out.println("This is " + expectedValue);
//
//        for (WebElement cell : orderNumberCells) {
//            String cellText = cell.getText().replace("#", "");
//            if (cellText.equals(expectedValue)) {
//                foundValue = cellText;
//                break;
//            }
//        }
//    }

    public String checkOrderNumber() {
        String foundValue = orderDataCell.getText();
        String sanitisedValue = foundValue.replace("#", "");
        return sanitisedValue;
    }

}
