package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hooks {
    public static WebDriver driver;
    public static WebDriverWait wait;
    private static final int TIMEOUT_IN_SECONDS = 10;

    @Before
    public void setUp(){
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);

        ChromeOptions options = new ChromeOptions();

        boolean isCI = Boolean.parseBoolean(
                System.getenv().getOrDefault("CI", "false")
        );

        if (isCI) {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_IN_SECONDS));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() && driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure_Screenshot");
        }

        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }
}
