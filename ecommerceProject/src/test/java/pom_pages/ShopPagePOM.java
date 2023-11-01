package pom_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.UtilityLibrary;

public class ShopPagePOM {

    protected WebDriver driver;

    public ShopPagePOM(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#main > ul > li.product.type-product.post-27.status-publish.first.instock.product_cat-accessories.has-post-thumbnail.sale.shipping-taxable.purchasable.product-type-simple > a.button.product_type_simple.add_to_cart_button.ajax_add_to_cart")
    WebElement addBeanieHatToCart;

    @FindBy(css = "#main > ul > li.product.type-product.post-27.status-publish.first.instock.product_cat-accessories.has-post-thumbnail.sale.shipping-taxable.purchasable.product-type-simple > a.added_to_cart.wc-forward")
    WebElement viewCartPopUp;

    public void addItemToCart() {
        UtilityLibrary.waitForElementToBeClickable(driver, addBeanieHatToCart, 3);
        addBeanieHatToCart.click();
    }

    public void viewCartInIcon() {
        UtilityLibrary.waitForElementToBeClickable(driver, viewCartPopUp, 3);
        viewCartPopUp.click();
    }
}
