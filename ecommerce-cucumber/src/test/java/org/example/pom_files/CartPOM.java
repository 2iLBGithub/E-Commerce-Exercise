package org.example.pom_files;

import org.example.utilities.UtilityLibrary;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.example.utilities.UtilityLibrary.*;


import static org.hamcrest.Matchers.equalTo;


public class CartPOM {

    protected WebDriver driver;
    protected double sumAsDouble;
    protected double postDeductionSumAsDouble;
    protected double shippingFeeAsDouble;
    protected double finalFeeAsDouble;

    public CartPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public double getSumAsDouble() {
        return sumAsDouble;
    }

    public double getPostDeductionSumAsDouble() {
        return postDeductionSumAsDouble;
    }

    public double getShippingFeeAsDouble() {
        return shippingFeeAsDouble;
    }

    public double getFinalFeeAsDouble() {
        return finalFeeAsDouble;
    }

    public void setSumAsDouble(double sumAsDouble) {
        this.sumAsDouble = sumAsDouble;
    }

    public void setPostDeductionSumAsDouble(double postDeductionSumAsDouble) {
        this.postDeductionSumAsDouble = postDeductionSumAsDouble;
    }

    public void setShippingFeeAsDouble(double shippingFeeAsDouble) {
        this.shippingFeeAsDouble = shippingFeeAsDouble;
    }

    public void setFinalFeeAsDouble(double finalFeeAsDouble) {
        this.finalFeeAsDouble = finalFeeAsDouble;
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
        double valueAsDouble = Double.parseDouble(rawValue);
        setSumAsDouble(valueAsDouble);
    }

    public void couponAppliedAsExpected() {
        UtilityLibrary.waitForElementToBeVisible(driver, checkDeduction, 3);
        String rawValue = checkDeduction.getText();
        rawValue = rawValue.replaceAll("[^0-9.]", "");
        double valueAsDouble = Double.parseDouble(rawValue);
        MatcherAssert.assertThat(postDeductionSumAsDouble, equalTo(valueAsDouble));
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

    public void deductionTotal(double sumAsDoubleParameterOne, int deductionPercentageParameterTwo) {
        double result = (sumAsDoubleParameterOne / 100) * deductionPercentageParameterTwo;
        setPostDeductionSumAsDouble(result);
    }

    public void applyShippingFee() {
        UtilityLibrary.waitForElementToBeVisible(driver, checkShippingFee, 3);
        String rawValue = checkShippingFee.getText();
        rawValue = rawValue.replaceAll("[^0-9.]", "");
        double valueAsDouble = Double.parseDouble(rawValue);
        setShippingFeeAsDouble(valueAsDouble);
    }

    public void finalSum(double initialSum, double deductedSum, double shippingFee) {
        double result = (initialSum - deductedSum) + shippingFeeAsDouble;
        setFinalFeeAsDouble(result);
        UtilityLibrary.waitForElementToBeVisible(driver, checkFinalFee, 3);
        String rawValue = checkFinalFee.getText();
        rawValue = rawValue.replaceAll("[^0-9.]", "");
        double valueAsDouble = Double.parseDouble(rawValue);
        MatcherAssert.assertThat(finalFeeAsDouble, Matchers.closeTo(valueAsDouble, 0.1));
    }

    public void goToCheckout() {
        UtilityLibrary.waitForElementToBeClickable(driver, checkOut, 3);
        checkOut.click();
    }

}
