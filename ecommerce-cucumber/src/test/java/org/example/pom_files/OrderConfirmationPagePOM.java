package org.example.pom_files;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.example.utilities.UtilityLibrary;
public class OrderConfirmationPagePOM {

    protected WebDriver driver;

    protected String savedOrderNumber;

    public OrderConfirmationPagePOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getSavedOrderNumber(){
        return savedOrderNumber;
    }

    public void setSavedOrderNumber(String savedOrderNumber){
        this.savedOrderNumber = savedOrderNumber;
    }

    @FindBy(css = "#post-6 > div > div > div > ul > li.woocommerce-order-overview__order.order > strong")
    WebElement orderNumber;

    public void ascribeOrderNumber() {
//
        UtilityLibrary.waitForElementToBeVisible(driver, orderNumber, 3);
        setSavedOrderNumber(orderNumber.getText());

    }
}
