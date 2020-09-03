package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class newCustomerSteps {

    WebDriver driver;
    WebElement webElement;
    Select dropdown;

    @Given("As a Bank Manager I want to log in on website {string}")
    public void as_a_bank_manager_i_want_to_log_in(String url) {

        // open browser & go to Bank Manager Profile
        System.setProperty("webdriver.chrome.driver", "d://chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to(url);
        webElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[2]/div/div[1]/div[2]/button")));
        webElement.click();
    }

   @When("I create new customer with data {string}, {string} and {string}")
    public void i_create_new_customer(String firstName, String lastName, String postCode) throws InterruptedException {

        // Go to add customer
        webElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[2]/div/div[1]/button[1]")));
        webElement.click();

        // Prowvide data to the form
        webElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/div/div/form/div[1]/input")));
        webElement.sendKeys(firstName);
        driver.findElement(new By.ByXPath("/html/body/div[3]/div/div[2]/div/div[2]/div/div/form/div[2]/input")).sendKeys(lastName);
        driver.findElement(new By.ByXPath("/html/body/div[3]/div/div[2]/div/div[2]/div/div/form/div[3]/input")).sendKeys(postCode);

        // Submit form
        driver.findElement(new By.ByXPath("/html/body/div[3]/div/div[2]/div/div[2]/div/div/form/button")).click();

        // Confirm creating a new customer & take care of popup
        String createUser = "Customer added successfully with customer id :6";
        String popup = driver.switchTo().alert().getText();
        Assert.assertEquals(createUser, popup);
        driver.switchTo().alert().accept();

    }

    @When("I create new account for {string} {string} in {string}")
    public void i_create_new_account(String firstName, String lastName, String currency) throws InterruptedException {

        // Click button: Open account
        driver.findElement(new By.ByXPath("/html/body/div[3]/div/div[2]/div/div[1]/button[2]")).click();

        // Select data in dropdown
        String name = firstName + " " + lastName;
        dropdown = new Select(new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath(("//*[@id=\"userSelect\"]")))));
        dropdown.selectByVisibleText(name);

        dropdown = new Select(new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath(("//*[@id=\"currency\"]")))));
        dropdown.selectByVisibleText(currency);

        // Process creating account
        driver.findElement(new By.ByXPath("/html/body/div[3]/div/div[2]/div/div[2]/div/div/form/button")).click();

        // Confirm creating a new account & take care of popup
        String createAccount = "Account created successfully with account Number :1016";
        String popup = driver.switchTo().alert().getText();
        Assert.assertEquals(createAccount, popup);
        driver.switchTo().alert().accept();

    }

    @Then("New customer {string} {string}, post code: {string} and new account is in customers table")
    public void new_customer_and_new_account_are_in_customers_tab(String firstName, String lastName, String postCode) {

        // Go to Customers
        driver.findElement(new By.ByXPath("/html/body/div[3]/div/div[2]/div/div[1]/button[3]")).click();

        // Fill 'Search Customer' field with data of new customer
        webElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/div/form/div/div/input")));
        webElement.sendKeys(firstName);

        // Confirm creating new account with provided data
        webElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/div/div/table/tbody/tr/td[1]")));
        String firstNameWS = webElement.getText();
        Assert.assertEquals(firstName, firstNameWS);

        webElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/div/div/table/tbody/tr/td[2]")));
        String lastNameWS = webElement.getText();
        Assert.assertEquals(lastName, lastNameWS);

        webElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/div/div/table/tbody/tr/td[3]")));
        String postCodeWS = webElement.getText();
        Assert.assertEquals(postCode, postCodeWS);

        webElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div[2]/div/div[2]/div/div/table/tbody/tr/td[4]/span")));
        String accountNoWS = webElement.getText();
        Assert.assertEquals("1016", accountNoWS);

    }
}
