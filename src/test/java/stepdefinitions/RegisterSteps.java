package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterSteps {

    private final String REGISTER_URL = "https://parabank.parasoft.com/parabank/register.htm";
    private String dynamicUsername;


    @Given("User is on the Parabank registration page")
    public void userIsOnTheParabankRegistrationPage(){
        Hooks.driver.get(REGISTER_URL);
        Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customerForm")));
    }

    //Success Register Scenario
    @When("User fills registration form")
    public void userFillsRegistrationForm(){
        dynamicUsername = "User_" + System.currentTimeMillis();

        Hooks.driver.findElement(By.id("customer.firstName")).sendKeys("User");
        Hooks.driver.findElement(By.id("customer.lastName")).sendKeys("Tester");
        Hooks.driver.findElement(By.id("customer.address.street")).sendKeys("CBD");
        Hooks.driver.findElement(By.id("customer.address.city")).sendKeys("Cibubur");
        Hooks.driver.findElement(By.id("customer.address.state")).sendKeys("West Java");
        Hooks.driver.findElement(By.id("customer.address.zipCode")).sendKeys("1743");
        Hooks.driver.findElement(By.id("customer.phoneNumber")).sendKeys("628");
        Hooks.driver.findElement(By.id("customer.ssn")).sendKeys("123");

        Hooks.driver.findElement(By.id("customer.username")).sendKeys(dynamicUsername);
        Hooks.driver.findElement(By.id("customer.password")).sendKeys("SecretPass123!");
        Hooks.driver.findElement(By.id("repeatedPassword")).sendKeys("SecretPass123!");
    }

    @Then("User redirected to welcome page")
    public void userRedirectedToWelcomePage(){
        WebElement welcomeTitle = Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#rightPanel h1.title")));

        Assertions.assertEquals("Welcome " + dynamicUsername, welcomeTitle.getText());
    }

    //Failed Register - Mismatch Password Scenario
    @When("User fills registration form with mismatched password")
    public void userFillsMismatchedPassword(){
        dynamicUsername = "User_" + System.currentTimeMillis();

        Hooks.driver.findElement(By.id("customer.firstName")).sendKeys("User");
        Hooks.driver.findElement(By.id("customer.lastName")).sendKeys("Tester");
        Hooks.driver.findElement(By.id("customer.address.street")).sendKeys("CBD");
        Hooks.driver.findElement(By.id("customer.address.city")).sendKeys("Cibubur");
        Hooks.driver.findElement(By.id("customer.address.state")).sendKeys("West Java");
        Hooks.driver.findElement(By.id("customer.address.zipCode")).sendKeys("1743");
        Hooks.driver.findElement(By.id("customer.phoneNumber")).sendKeys("628");
        Hooks.driver.findElement(By.id("customer.ssn")).sendKeys("123");

        Hooks.driver.findElement(By.id("customer.username")).sendKeys(dynamicUsername);
        Hooks.driver.findElement(By.id("customer.password")).sendKeys("SecretPass123!");
        Hooks.driver.findElement(By.id("repeatedPassword")).sendKeys("DiffPass123!");
    }

    @Then("User should see error message")
    public void userSeeErrorMessage(){
        Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("repeatedPassword.errors")));
    }


    //Running all scenario together
    @And("User clicks Register button")
    public void userClickRegisterButton(){
        Hooks.driver.findElement(By.cssSelector("input[value='Register']")).click();
    }
}
