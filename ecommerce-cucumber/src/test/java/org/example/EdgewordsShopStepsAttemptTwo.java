package org.example;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pom_files.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EdgewordsShopStepsAttemptTwo {

    WebDriver driver;
    String baseUrl = "https://www.edgewordstraining.co.uk/demo-site/";
    NavigationPOM navgiation;
    AccountPagePOM account;
    CartPOM cartInteractions;
    SearchResultsPOM results;
    ShopPagePOM shop;
    CheckoutPagePOM checkout;
    OrderConfirmationPagePOM orderConfirmation;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        navgiation = new NavigationPOM(driver);
        account = new AccountPagePOM(driver);
        cartInteractions = new CartPOM(driver);
        results = new SearchResultsPOM(driver);
        shop = new ShopPagePOM(driver);
        checkout = new CheckoutPagePOM(driver);
        orderConfirmation = new OrderConfirmationPagePOM(driver);
        navgiation.acceptCookies();
        navgiation.goToAccount();
        account.logInToSite("fakelewis@2i.com", "fakepassword@@@@1111");
    }

    @After
    public void tearDown() {
        navgiation.goToAccount();
        account.logOutOfSite();
        driver.quit();
    }

    // ONE

    @Given("I can access the home page")
    public void i_can_access_the_home_page() {
        navgiation.goToHome();
    }

    @When("I add an item to my cart")
    public void i_add_an_item_to_my_cart() {
        navgiation.searchFor("cap");
        results.addToCart();
    }

    @When("I apply a coupon")
    public void i_apply_a_coupon() {
        results.viewCart();
        cartInteractions.accessCouponCodeField("edgewords");
        cartInteractions.applyCouponToCart();
    }

    @Then("I can see {int}% has been deducted")
    public void i_can_see_has_been_deducted(Integer int1) {
        cartInteractions.findSubTotal();
        cartInteractions.deductionTotal(cartInteractions.getSumAsDouble(), 15);
        cartInteractions.couponAppliedAsExpected();
        cartInteractions.applyShippingFee();
        cartInteractions.finalSum(cartInteractions.getSumAsDouble(), cartInteractions.getPostDeductionSumAsDouble(), cartInteractions.getShippingFeeAsDouble());
    }

    // TWO

    @Given("I have access to the shop page")
    public void i_have_access_to_the_shop_page() {
        navgiation.goToShop();
    }

    @When("I add an item of clothing to my cart")
    public void i_add_an_item_of_clothing_to_my_cart() {
        shop.addItemToCart();
    }

    @When("I proceed through the purchasing process, entering {string}, {string}, {string}, {string}, {string}, {string}")
    public void i_proceed_through_the_purchasing_process_entering(String firstName, String lastName, String country, String address, String postCode, String phone) {
        shop.viewCartInIcon();
        cartInteractions.goToCheckout();
        checkout.inputFirstName(firstName);
        checkout.inputLastName(lastName);
        checkout.inputBillingCountry(country);
        checkout.inputBillingAddressNameAndNumber(address);
        checkout.inputBillingPostCode(postCode);
        checkout.inputBillingPhone(phone);
        checkout.inputPaymentMethodCheque();
        checkout.finaliseOrder();
    }

    @Then("I should see my order number in the accounts page")
    public void i_should_see_my_order_number_in_the_accounts_page() {
        orderConfirmation.ascribeOrderNumber();
        navgiation.goToAccount();
        account.viewOrders();
        account.checkOrderNumber(orderConfirmation.getSavedOrderNumber());
    }



}
