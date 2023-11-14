package org.example.pom_files;

import java.math.BigDecimal;
import org.example.utilities.UtilityLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPOM {

    protected WebDriver driver;
    protected BigDecimal sumAsBigDecimal;
    protected BigDecimal postDeductionSumAsBigDecimal;
    protected BigDecimal shippingFeeAsBigDecimal;
    protected BigDecimal finalFeeAsBigDecimal;

    public CartPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public BigDecimal getSumAsBigDecimal() {
        return sumAsBigDecimal;
    }

    public BigDecimal getPostDeductionSumAsBigDecimal() {
        return postDeductionSumAsBigDecimal;
    }

    public BigDecimal getShippingFeeAsBigDecimal() {
        return shippingFeeAsBigDecimal;
    }

    public BigDecimal getFinalFeeAsBigDecimal() {
        return finalFeeAsBigDecimal;
    }

    public void setSumAsBigDecimal(BigDecimal sumAsBigDecimal) {
        this.sumAsBigDecimal = sumAsBigDecimal;
    }

    public void setPostDeductionSumAsBigDecimal(BigDecimal postDeductionSumAsBigDecimal) {
        this.postDeductionSumAsBigDecimal = postDeductionSumAsBigDecimal;
    }

    public void setShippingFeeAsBigDecimal(BigDecimal shippingFeeAsBigDecimal) {
        this.shippingFeeAsBigDecimal = shippingFeeAsBigDecimal;
    }

    public void setFinalFeeAsBigDecimal(BigDecimal finalFeeAsBigDecimal) {
        this.finalFeeAsBigDecimal = finalFeeAsBigDecimal;
    }

    @FindBy(name = "coupon_code")
    WebElement couponCodeField;

    @FindBy(name = "apply_coupon")
    WebElement applyCoupon;

    @FindBy(css = "#post-5 > div > div > div.cart-collaterals > div > table > tbody > tr.cart-subtotal > td > span > bdi")
    WebElement checkSubtotal;

    @FindBy(css = "#post-5 > div > div > div.cart-collaterals > div > table > tbody > tr.cart-discount.coupon-edgewords > td > span")
    WebElement checkDeduction;

    @FindBy(css = "#shipping_method > li > label > span > bdi")
    WebElement checkShippingFee;

    @FindBy(css = "#post-5 > div > div > div.cart-collaterals > div > table > tbody > tr.order-total > td > strong")
    WebElement checkFinalFee;

    @FindBy(css = "#post-5 > div > div > div.cart-collaterals > div > div > a")
    WebElement checkOut;

    public void findSubTotal() {
        String rawValue = checkSubtotal.getText();
        rawValue = rawValue.replaceAll("[^0-9.]", "");
        BigDecimal valueAsBigDecimal = new BigDecimal(rawValue);
        setSumAsBigDecimal(valueAsBigDecimal);
    }

    public BigDecimal getCouponDeduction() {
        UtilityLibrary.waitForElementToBeVisible(driver, checkDeduction, 3);
        String rawValue = checkDeduction.getText();
        rawValue = rawValue.replaceAll("[^0-9.]", "");
        return new BigDecimal(rawValue);
    }

    public void accessCouponCodeField(String coupon) {
        UtilityLibrary.waitForElementToBeClickable(driver, couponCodeField, 3);
        couponCodeField.click();
        couponCodeField.sendKeys(coupon);
    }

    public void applyCouponToCart() {
        UtilityLibrary.waitForElementToBeClickable(driver, applyCoupon, 3);
        applyCoupon.click();
    }

    public void applyShippingFee() {
        UtilityLibrary.waitForElementToBeVisible(driver, checkShippingFee, 3);
        String rawValue = checkShippingFee.getText();
        rawValue = rawValue.replaceAll("[^0-9.]", "");
        BigDecimal valueAsBigDecimal = new BigDecimal(rawValue);
        setShippingFeeAsBigDecimal(valueAsBigDecimal);
    }

    public BigDecimal getFinalSum() {
        UtilityLibrary.waitForElementToBeVisible(driver, checkFinalFee, 3);
        String rawValue = checkFinalFee.getText();
        rawValue = rawValue.replaceAll("[^0-9.]", "");
        return new BigDecimal(rawValue);
    }

    public void goToCheckout() {
        UtilityLibrary.waitForElementToBeClickable(driver, checkOut, 3);
        checkOut.click();
    }

}
