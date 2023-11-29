package pom_tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pom_pages.*;
import utilities.TestBase;

public class ProductSearchUsingPOMTest extends TestBase {

    @Test
    void testCaseOne() {
        driver.get(baseURL);
        NavigationPOM navigation = new NavigationPOM(driver);
        AccountPagePOM account = new AccountPagePOM(driver);
        CartPOM cartInteractions = new CartPOM(driver);
        SearchResultsPOM results = new SearchResultsPOM(driver);
        navigation.goToHome();
        navigation.searchFor("cap");
        results.addToCart();
        results.viewCart();
        cartInteractions.accessCouponCodeField("edgewords");
        cartInteractions.applyCouponToCart();
        cartInteractions.findSubTotal();
        cartInteractions.deductionTotal(cartInteractions.getSumAsDouble(), 15);
        cartInteractions.couponAppliedAsExpected();
        cartInteractions.applyShippingFee();
        cartInteractions.finalSum(cartInteractions.getSumAsDouble(), cartInteractions.getPostDeductionSumAsDouble(), cartInteractions.getShippingFeeAsDouble());
        navigation.goToAccount();
        account.logOutOfSite();
    }

    @Test
    void testCaseTwo() {
        driver.get(baseURL);
        NavigationPOM navigation = new NavigationPOM(driver);
        AccountPagePOM account = new AccountPagePOM(driver);
        ShopPagePOM shop = new ShopPagePOM(driver);
        CartPOM cartInteractions = new CartPOM(driver);
        CheckoutPagePOM checkout = new CheckoutPagePOM(driver);
        OrderConfirmationPagePOM orderConfirmation = new OrderConfirmationPagePOM(driver);
        navigation.goToShop();
        shop.addItemToCart();
        shop.viewCartInIcon();
        cartInteractions.goToCheckout();
        checkout.inputFirstName("Ray");
        checkout.inputLastName("Blanc");
        checkout.inputBillingCountry("France");
        checkout.inputBillingAddressNameAndNumber("9 Pourier Avinoue");
        checkout.inputBillingPostCode("75008");
        checkout.inputBillingPhone("123456789");
        checkout.inputPaymentMethodCheque();
        checkout.finaliseOrder();
        orderConfirmation.ascribeOrderNumber();
        navigation.goToAccount();
        account.viewOrders();
        account.checkOrderNumber(orderConfirmation.getSavedOrderNumber());
        account.logOutOfSite();
    }
}
