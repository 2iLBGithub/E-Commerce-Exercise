In the two directories can be found two projects, one using Cucumber/Gherkin for behaviour driven development and the other using Selenium WebDriver. Both access the same fake e-commerce website and run end-to-end tests against it.

For this project to run you will need IntelliJ, Java (11+) and Maven.

In order to trial these please clone the repository first and then go into each project's directory in the order you wish to run them and first access the POM file. You may need to hit the Maven button in order to install everything there, or type 'mvn clean install' to the terminal. 

For 'ecommerceProject', please access the following directories in turn: src > test > java > pom_tests > ProductSearchUsingPOM

For 'ecommerce-cucmber', please access the following directories in turn: src > test > resources > org.example > edgewordsShopSecondAttempt.feature

In both of these files you should find the ability to run the tests within. 
