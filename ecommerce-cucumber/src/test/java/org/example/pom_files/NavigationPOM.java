package org.example.pom_files;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationPOM {

    protected WebDriver driver;

    public NavigationPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Dismiss")
    WebElement dismissLink;

    @FindBy(css = "#menu-item-42 > a:nth-child(1)")
    WebElement selectHomePage;

    @FindBy(id = "woocommerce-product-search-field-0")
    WebElement searchBox;

    @FindBy(id = "menu-item-46")
    WebElement accountPage;

    @FindBy(id = "menu-item-43")
    WebElement shopPage;

    public void acceptCookies() {
        dismissLink.click();
    }

    public void searchFor(String product) {
        searchBox.sendKeys(product + Keys.ENTER);
    }

    public void goToAccount() {accountPage.click();}

    public void goToHome(){
        selectHomePage.click();
    }

    public void goToShop() {
        shopPage.click();
    }
}
