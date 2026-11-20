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
import org.openqa.selenium.support.ui.Select;

public class TransferFundsSteps {
    private final String REGISTER_URL = "https://parabank.parasoft.com/parabank/register.htm";
    private String newAccountNumber;

    //Scenario Success Transfer Funds
    @Given("User is logged in to Parabank account")
    public void userLoginToParabankAccount(){
        Hooks.driver.get(REGISTER_URL);
        String uniqueUser= "User_" + System.currentTimeMillis();

        Hooks.driver.findElement(By.id("customer.firstName")).sendKeys("User");
        Hooks.driver.findElement(By.id("customer.lastName")).sendKeys("Tester");
        Hooks.driver.findElement(By.id("customer.address.street")).sendKeys("CBD");
        Hooks.driver.findElement(By.id("customer.address.city")).sendKeys("Cibubur");
        Hooks.driver.findElement(By.id("customer.address.state")).sendKeys("West Java");
        Hooks.driver.findElement(By.id("customer.address.zipCode")).sendKeys("1743");
        Hooks.driver.findElement(By.id("customer.phoneNumber")).sendKeys("628");
        Hooks.driver.findElement(By.id("customer.ssn")).sendKeys("123");

        Hooks.driver.findElement(By.id("customer.username")).sendKeys(uniqueUser);
        Hooks.driver.findElement(By.id("customer.password")).sendKeys("SecretPass123!");
        Hooks.driver.findElement(By.id("repeatedPassword")).sendKeys("SecretPass123!");

        Hooks.driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement welcomeTitle = Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#rightPanel h1.title")));

        Assertions.assertEquals("Welcome " + uniqueUser, welcomeTitle.getText());
    }

    @When("User opens a new Savings account")
    public void userOpensSavingsAccount(){
        Hooks.driver.findElement(By.linkText("Open New Account")).click();
        Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("openAccountForm")));

        Select accountTypeDropdown = new Select(Hooks.driver.findElement(By.id("type")));
        accountTypeDropdown.selectByVisibleText("SAVINGS");

        Hooks.wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(
                By.id("fromAccountId"), By.tagName("option")
        ));

        Hooks.driver.findElement(By.cssSelector("input[value='Open New Account']")).click();

        Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("openAccountResult")));
        WebElement newAccountLink = Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("newAccountId")));
        newAccountNumber = newAccountLink.getText().trim();
    }

    @And("User navigates to Transfer Funds page")
    public void userNavigatesToTransferFundsPage(){
        Hooks.driver.findElement(By.linkText("Transfer Funds")).click();
        Hooks.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("transferForm")));

    }

    @And("User enters transfer amount {string}")
    public void userEnterTransferAmount(String amount){
        WebElement amountInput = Hooks.driver.findElement(By.id("amount"));
        amountInput.clear();
        amountInput.sendKeys(amount);
    }

    @And("User selects source account and the newly created destination account")
    public void userSelectsSourceAndNewlyCreatedDestinationAccount() {
        Hooks.wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(
                By.id("fromAccountId"), By.tagName("option")
        ));
        Hooks.wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#toAccountId option[value='" + newAccountNumber + "']")
        ));

        Select fromAccountDropdown = new Select(Hooks.driver.findElement(By.id("fromAccountId")));
        Select toAccountDropdown = new Select(Hooks.driver.findElement(By.id("toAccountId")));

        fromAccountDropdown.selectByIndex(0);
        toAccountDropdown.selectByVisibleText(newAccountNumber);
    }

    @And("User clicks Transfer button")
    public void userClicksTransferButton() {
        Hooks.driver.findElement(By.cssSelector("input[value='Transfer']")).click();

    }

    @Then("Transfer should be completed successfully to the new account")
    public void transferShouldBeCompletedSuccessfullyToTheNewAccount() {
        WebElement successMessage = Hooks.wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("#showResult h1.title")
                )
        );

        Assertions.assertEquals(
                "Transfer Complete!",
                successMessage.getText(),
                "Transfer was not completed successfully!"
        );

    }

    @Then("User should see transfer error message")
    public void userShouldSeeTransferErrorMessage() {
        WebElement errorMessage = Hooks.wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("#showError p.error")
                )
        );

        Assertions.assertEquals(
                "An internal error has occurred and has been logged.",
                errorMessage.getText(),
                "Expected transfer error message was not displayed!"
        );
    }

}
