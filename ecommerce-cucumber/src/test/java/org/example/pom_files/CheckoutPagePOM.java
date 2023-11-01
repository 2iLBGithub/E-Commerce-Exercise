package org.example.pom_files;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.example.utilities.UtilityLibrary;
public class CheckoutPagePOM {

    protected WebDriver driver;

    public CheckoutPagePOM(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "billing_first_name")
    WebElement getFirstName;

    @FindBy(id = "billing_last_name")
    WebElement getLastName;

    @FindBy(css = "#select2-billing_country-container")
    WebElement getBillingCountry;

    @FindBy(css = "body > span > span > span.select2-search.select2-search--dropdown > input")
    WebElement getBillingCountryInput;

    @FindBy(id = "billing_address_1")
    WebElement getBillingAddressStreetAndNumber;

    @FindBy(id = "billing_postcode")
    WebElement getBillingPostCode;

    @FindBy(id = "billing_phone")
    WebElement getBillingPhone;

    @FindBy(css = "#payment > ul > li.wc_payment_method.payment_method_cheque > label")
    WebElement paymentMethodCheque;

    @FindBy(id = "place_order")
    WebElement placeOrder;

//    @FindBy(css = "blockUI blockOverlay")
//    WebElement obstruction;

    public void inputFirstName(String firstName) {
        UtilityLibrary.waitForElementToBeClickable(driver, getFirstName, 3);
        getFirstName.click();
        getFirstName.clear();
        getFirstName.sendKeys(firstName);
    }

    public void inputLastName(String lastName) {
        UtilityLibrary.waitForElementToBeClickable(driver, getLastName, 3);
        getLastName.click();
        getLastName.clear();
        getLastName.sendKeys(lastName);
    }

    public void inputBillingCountry(String countryName){
        UtilityLibrary.waitForElementToBeClickable(driver, getBillingCountry, 3);
        getBillingCountry.click();
        UtilityLibrary.waitForElementToBeClickable(driver, getBillingCountryInput, 3);
        getBillingCountryInput.click();
        getBillingCountryInput.sendKeys(countryName, Keys.ENTER);
    }

    public void inputBillingAddressNameAndNumber(String address){
        UtilityLibrary.waitForElementToBeClickable(driver, getBillingAddressStreetAndNumber, 3);
        getBillingAddressStreetAndNumber.click();
        getBillingAddressStreetAndNumber.clear();
        getBillingAddressStreetAndNumber.sendKeys(address);
    }

    public void inputBillingPostCode(String postcode){
        UtilityLibrary.waitForElementToBeClickable(driver, getBillingPostCode, 3);
        getBillingPostCode.click();
        getBillingPostCode.clear();
        getBillingPostCode.sendKeys(postcode);
    }

    public void inputBillingPhone(String phoneNumber){
        UtilityLibrary.waitForElementToBeClickable(driver, getBillingPhone, 3);
        getBillingPhone.click();
        getBillingPhone.clear();
        getBillingPhone.sendKeys(phoneNumber);
    }

    public void inputPaymentMethodCheque() {
        UtilityLibrary.waitForElementToBeInvisible(driver, By.cssSelector(".blockUI.blockOverlay"), 10);
        UtilityLibrary.waitForElementToBeClickable(driver, paymentMethodCheque, 3);
        paymentMethodCheque.click();
    }

    public void finaliseOrder() {
        UtilityLibrary.waitForElementToBeInvisible(driver, By.cssSelector(".blockUI.blockOverlay"), 10);
        UtilityLibrary.waitForElementToBeVisible(driver, placeOrder, 3);
        UtilityLibrary.waitForElementToBeClickable(driver, placeOrder, 3);
        placeOrder.click();
    }
}
