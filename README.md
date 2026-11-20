# Parabank E2E Test Automation (Selenium + Cucumber BDD)

Automated end-to-end UI testing framework for **Parabank** built with **Java**, **Selenium 4**, **Cucumber BDD**, and **JUnit 5**, integrated with **GitHub Actions** for continuous testing.

## Tech Stack & Architecture
- **Language:** Java 27+
- **Automation Engine:** Selenium WebDriver 4 (with Selenium Manager)
- **BDD Framework:** Cucumber 7 (Gherkin)
- **Test Runner:** JUnit 5 Platform Suite
- **Build Tool:** Maven
- **CI/CD:** GitHub Actions (Headless Chrome + HTML Report Artifacts)

## Covered Test Scenarios
1. **User Registration (`Register.feature`)**: Valid dynamic user registration & password mismatch validation.
2. **Authentication (`Login.feature`)**: End-to-end login/logout flow & invalid credential error handling.
3. **Banking Transaction (`TransferFunds.feature`)**: Chained E2E flow (Register -> Open New Savings Account -> Transfer Funds to Newly Created Account) & empty amount error validation.

## How to Run Locally
```bash
# Run with visible browser
mvn test

# Run in headless mode
mvn test -Dheadless=true