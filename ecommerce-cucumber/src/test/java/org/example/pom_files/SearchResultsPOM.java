package org.example.pom_files;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.example.utilities.UtilityLibrary;
public class SearchResultsPOM {

    protected WebDriver driver;

    public SearchResultsPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "add-to-cart")
    WebElement addToCartButton;

    @FindBy(linkText = "View cart")
    WebElement viewCartLink;
//CONCERNING USAGE BELOW, TEST PROPERLY NEXT THING
    public void addToCart() {
        UtilityLibrary.waitForElementToBeClickable(driver, addToCartButton, 3);
        addToCartButton.click();
    }

    public void viewCart() {
        UtilityLibrary.waitForElementToBeClickable(driver, viewCartLink, 3);
        viewCartLink.click();
    }

}
