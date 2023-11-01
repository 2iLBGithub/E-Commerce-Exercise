//package org.example;
//
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//
//public class Hooks {
//    //This class will be instantiated first as the @Before is needed first to run a scenario
//    private WebDriver driver;
//    private final SharedDictionary sharedDict; //Field to work with the dictionary *in this class*
//
//    public Hooks(SharedDictionary sharedDict){ //When Cucumber instantiates this class the constructor will run and picocontainer will create and inject an instance of the dictionary class
//        this.sharedDict = sharedDict; //Put the dictionary in this class's field to work with in class methods.
//    }
//
//
//    @Before
//    public void setUp(){
//        String browser = System.getProperty("BROWSER");
////String browser = "chrome";
//        if(browser==null){browser="";}
//        switch (browser){
//            case"chrome":
//                driver = new ChromeDriver();
//                break;
//            case "firefox":
//                driver = new FirefoxDriver();
//                break;
//            default:
//                System.out.printf("No or unrecognised driver specified on command line. Using Edge");
//                driver = new EdgeDriver();
//                break;
//        }
//        sharedDict.addDict("mydriver", driver);
//    }
//
////    @Before("@Firefox") //@Before can be specific to specified tags
////    public void setUpFirefox(){
////        driver = new FirefoxDriver();
////    }
//
//    @After
//    public void tearDown() throws InterruptedException {
//        Thread.sleep(3000);
//        driver.quit();
//    }
//}