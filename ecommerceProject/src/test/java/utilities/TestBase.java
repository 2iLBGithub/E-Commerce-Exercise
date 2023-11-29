package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pom_pages.AccountPagePOM;
import pom_pages.NavigationPOM;

public class TestBase {

    public WebDriver driver;
    public String baseURL = "https://www.edgewordstraining.co.uk/demo-site/";

    @BeforeEach
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(baseURL);
        NavigationPOM navigation = new NavigationPOM(driver);
        AccountPagePOM account = new AccountPagePOM(driver);
        navigation.acceptCookies();
        navigation.goToAccount();
        account.logInToSite("fakelewis@2i.com", "fakepassword@@@@1111");
    }

    @AfterEach
    public void tearDown() {driver.quit();}
}
