package testcases;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class WindowPopupModalTest {

    private WebDriver driver;
    private WebDriverWait wait;

    // Method to set up WebDriver and navigate to the URL
    private void setupDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // Optional: remove this if you want to see the browser UI.
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void navigateToURL(String url) {
        driver.get(url);
        wait.until(driver-> ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));
    }

    @Test
    public void testWindowPopupModal() {
        // Step 1: Set up WebDriver and navigate to the URL
        setupDriver();
        navigateToURL("https://lambdatest.com/selenium-playground/");

        // Step 2: Click the "Window Popup Modal" link
        WebElement windowPopupModalLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Window Popup Modal"))
        );
        windowPopupModalLink.click();

        // Step 3: Click the "Follow On Twitter" button to trigger the popup
        WebElement followOnTwitterButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://twitter.com/lambdatest']"))
        );
        followOnTwitterButton.click();

        // Step 4: Handle multiple windows and switch to the new window
        String originalWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Step 5: Validate that the new window is opened (by checking the title)
        String currentWindowTitle = driver.getTitle();
        assertTrue(currentWindowTitle.contains("Lambdatest (@lambdatest)"), "The new window title is incorrect!");

        // Step 6: Close all windows with a single command
        driver.quit();
    }
}
