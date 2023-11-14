package org.example;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pom_files.*;
import org.example.utilities.pomUtilities.CartUtilityLibrary;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.math.BigDecimal;

public class EdgewordsShopStepsAttemptTwo {

    private SharedDictionary sharedDict;

    public EdgewordsShopStepsAttemptTwo(SharedDictionary sharedDict) {
        this.sharedDict = sharedDict;
    }

//    NavigationPOM navigation = (NavigationPOM) sharedDict.readDict("navigation");
//    SearchResultsPOM results = (SearchResultsPOM) sharedDict.readDict("results");
//    AccountPagePOM account = (AccountPagePOM) sharedDict.readDict("account");
//    CartPOM cartInteractions = (CartPOM) sharedDict.readDict("cartInteractions");
//    ShopPagePOM shop = (ShopPagePOM) sharedDict.readDict("shop");
//    CheckoutPagePOM checkout = (CheckoutPagePOM) sharedDict.readDict("checkout");
//    OrderConfirmationPagePOM orderConfirmation = (OrderConfirmationPagePOM) sharedDict.readDict("orderConfirmation");

//    private SharedDictionary sharedDict = new SharedDictionary();
//    WebDriver driver;
//    String baseUrl = "https://www.edgewordstraining.co.uk/demo-site/";
//    NavigationPOM navgiation;
//    AccountPagePOM account;
//    CartPOM cartInteractions;
//    SearchResultsPOM results;
//    ShopPagePOM shop;
//    CheckoutPagePOM checkout;
//    OrderConfirmationPagePOM orderConfirmation;
//

//    @Before
//    public void setUp() {
//        driver = new FirefoxDriver();
//        driver.get(baseUrl);
//        navgiation = new NavigationPOM(driver);
//        account = new AccountPagePOM(driver);
//        cartInteractions = new CartPOM(driver);
//        results = new SearchResultsPOM(driver);
//        shop = new ShopPagePOM(driver);
//        checkout = new CheckoutPagePOM(driver);
//        orderConfirmation = new OrderConfirmationPagePOM(driver);
//        navgiation.acceptCookies();
//        navgiation.goToAccount();
//        account.logInToSite("fakelewis@2i.com", "fakepassword@@@@1111");
//    }
//
//    @After
//    public void tearDown() {
//        sharedDict.clearDictionary();
//        navgiation.goToAccount();
//        account.logOutOfSite();
//        driver.quit();
//    }

    // ONE

    @Given("I can access the home page")
    public void i_can_access_the_home_page() {
        NavigationPOM navigation = (NavigationPOM) sharedDict.readDict("navigation");
        navigation.goToHome();
    }

    @When("I add an {string} to my cart")
    public void i_add_an_item_to_my_cart(String item) {
        NavigationPOM navigation = (NavigationPOM) sharedDict.readDict("navigation");
        SearchResultsPOM results = (SearchResultsPOM) sharedDict.readDict("results");
        navigation.searchFor(item);
        results.addToCart();
    }

    @When("I apply a coupon")
    public void i_apply_a_coupon() {
        SearchResultsPOM results = (SearchResultsPOM) sharedDict.readDict("results");
        CartPOM cartInteractions = (CartPOM) sharedDict.readDict("cartInteractions");
        results.viewCart();
        cartInteractions.accessCouponCodeField("edgewords");
        cartInteractions.applyCouponToCart();
    }

    @Then("I can see {int}% has been deducted")
    public void i_can_see_has_been_deducted(Integer int1) {
        CartPOM cartInteractions = (CartPOM) sharedDict.readDict("cartInteractions");
        cartInteractions.findSubTotal();
        BigDecimal subtotal = cartInteractions.getSumAsBigDecimal();
        BigDecimal expectedDeduction = CartUtilityLibrary.calculateDeductionTotal(subtotal, int1);
        cartInteractions.applyShippingFee();
        BigDecimal shippingFee = cartInteractions.getShippingFeeAsBigDecimal();
        BigDecimal expectedFinalSum = CartUtilityLibrary.calculateFinalSum(subtotal, expectedDeduction, shippingFee);
        BigDecimal actualDeduction = cartInteractions.getCouponDeduction();
        BigDecimal actualFinalSum = cartInteractions.getFinalSum();
        MatcherAssert.assertThat(actualDeduction, Matchers.comparesEqualTo(expectedDeduction));
        MatcherAssert.assertThat(actualFinalSum, Matchers.comparesEqualTo(expectedFinalSum));
    }
    // TWO

    @Given("I have access to the shop page")
    public void i_have_access_to_the_shop_page() {
        NavigationPOM navigation = (NavigationPOM) sharedDict.readDict("navigation");
        navigation.goToShop();
    }

    @When("I add an {string} of clothing to my cart")
    public void i_add_an_item_of_clothing_to_my_cart(String item) {
        ShopPagePOM shop = (ShopPagePOM) sharedDict.readDict("shop");
        shop.addItemToCartParameterised(item);
        shop.addItemToCart();
    }

    @When("I proceed through the purchasing process, entering {string}, {string}, {string}, {string}, {string}, {string}")
    public void i_proceed_through_the_purchasing_process_entering(String firstName, String lastName, String country, String address, String postCode, String phone) {
        CartPOM cartInteractions = (CartPOM) sharedDict.readDict("cartInteractions");
        ShopPagePOM shop = (ShopPagePOM) sharedDict.readDict("shop");
        CheckoutPagePOM checkout = (CheckoutPagePOM) sharedDict.readDict("checkout");
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
        NavigationPOM navigation = (NavigationPOM) sharedDict.readDict("navigation");
        AccountPagePOM account = (AccountPagePOM) sharedDict.readDict("account");
        OrderConfirmationPagePOM orderConfirmation = (OrderConfirmationPagePOM) sharedDict.readDict("orderConfirmation");
        String orderNumber = orderConfirmation.getOrderNumber();
        sharedDict.addDict("orderNumber", orderNumber);
        navigation.goToAccount();
        account.viewOrders();
        String foundOrderNumber = account.checkOrderNumber();
        String savedOrderNumber = (String) sharedDict.readDict("orderNumber");
        MatcherAssert.assertThat(foundOrderNumber, Matchers.equalTo(savedOrderNumber));
    }


}
