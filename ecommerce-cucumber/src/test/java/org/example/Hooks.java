package org.example;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.pom_files.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Hooks {
    //This class will be instantiated first as the @Before is needed first to run a scenario
    public Hooks(SharedDictionary sharedDict){ //When Cucumber instantiates this class the constructor will run and picocontainer will create and inject an instance of the dictionary class
        this.sharedDict = sharedDict; //Put the dictionary in this class's field to work with in class methods.
    }
    private WebDriver driver;
    private final SharedDictionary sharedDict; //Field to work with the dictionary *in this class*
    String baseUrl = "https://www.edgewordstraining.co.uk/demo-site/";
    HomePagePOM home;
    NavigationPOM navigation;
    AccountPagePOM account;
    CartPOM cartInteractions;
    SearchResultsPOM results;
    ShopPagePOM shop;
    CheckoutPagePOM checkout;
    OrderConfirmationPagePOM orderConfirmation;

    @Before
    public void setUp(){
        driver = new FirefoxDriver();
        sharedDict.addDict("driver", driver);
        driver.get(baseUrl);
        home = new HomePagePOM(driver);
        sharedDict.addDict("home", home);
        navigation = new NavigationPOM(driver);
        sharedDict.addDict("navigation", navigation);
        account = new AccountPagePOM(driver);
        sharedDict.addDict("account", account);
        cartInteractions = new CartPOM(driver);
        sharedDict.addDict("cartInteractions", cartInteractions);
        results = new SearchResultsPOM(driver);
        sharedDict.addDict("results", results);
        shop = new ShopPagePOM(driver);
        sharedDict.addDict("shop", shop);
        checkout = new CheckoutPagePOM(driver);
        sharedDict.addDict("checkout", checkout);
        orderConfirmation = new OrderConfirmationPagePOM(driver);
        sharedDict.addDict("orderConfirmation", orderConfirmation);
        home.acceptCookies();
        navigation.goToAccount();
        account.logInToSite("fakelewis@2i.com", "fakepassword@@@@1111");
    }


    @After
    public void tearDown() {
        sharedDict.clearDictionary(); // Clear shared dictionary
        navigation.goToAccount();
        account.logOutOfSite();
        if (driver != null) {
            driver.quit();
        }
    }
}