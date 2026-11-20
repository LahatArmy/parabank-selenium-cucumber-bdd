package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginSteps {
    private final String LOGIN_URL = "https://parabank.parasoft.com/parabank/index.htm";

    @Given("User is on the Parabank page")
    public void userIsOnTheParabankPage(){
        Hooks.driver.get(LOGIN_URL);
        Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginPanel")));
    }

    //Login and Logout Scenario
    @When("User fills Login forms")
    public void userFillsLoginForms(){
        Hooks.driver.findElement(By.name("username")).sendKeys("john");
        Hooks.driver.findElement(By.name("password")).sendKeys("demo");
    }

    @Then("User redirected to Accounts Overview")
    public void userRedirectedAccountsOverview(){
        Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("showOverview")));
    }

    @When("User clicks Logout")
    public void userClickLogout(){
        Hooks.driver.findElement(By.linkText("Log Out")).click();
    }

    @Then("User back to Parabank page")
    public void userBackToParabankPage(){
        Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginPanel")));
    }

    //Failed login - Invalid Password
    @When("User fills Login forms with wrong password")
    public void userFillsLoginFormsWithWrongPassword(){
        Hooks.driver.findElement(By.name("username")).sendKeys("john");
        Hooks.driver.findElement(By.name("password")).sendKeys("notdemo");
    }

    @Then("User should see login error message")
    public void userSeeLoginErrorMessage(){
        Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#rightPanel p.error")));
    }

    //Running click login button
    @And("User clicks Login button")
    public void userClickLoginButton(){
        Hooks.driver.findElement(By.cssSelector("input[value='Log In']")).click();
    }

}
