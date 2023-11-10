package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pom_pages.AccountPagePOM;
import pom_pages.CartPOM;
import pom_pages.NavigationPOM;
import pom_pages.SearchResultsPOM;

public class TestBase {
// LOGIN INFO
//    fakelewis@2i.com
//    fakepassword@@@@1111

    protected WebDriver driver;
    protected String baseURL = "https://www.edgewordstraining.co.uk/demo-site/";

    @BeforeEach
    void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

//        HomepagePOM home = new HomepagePOM(driver);
//        home.goToAccount();
//        AccountPagePOM account = new AccountPagePOM(driver);
//        account.logInToSite("fakelewis@2i.com", "fakepassword@@@@1111");
        driver.get(baseURL);
        NavigationPOM navigation = new NavigationPOM(driver);
        AccountPagePOM account = new AccountPagePOM(driver);
        CartPOM cartInteractions = new CartPOM(driver);
        SearchResultsPOM results = new SearchResultsPOM(driver);
        navigation.acceptCookies();
        navigation.goToAccount();
        account.logInToSite("fakelewis@2i.com", "fakepassword@@@@1111");
    }

//    @AfterEach
//    void tearDown() {driver.quit();}
}
