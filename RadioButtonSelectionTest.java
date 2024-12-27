package testcases;


import java.time.Duration;

import org.openqa.selenium.By;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

public class RadioButtonSelectionTest {

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
        wait.until(driver -> driver.toString().equals("complete"));
    }

    @Test
    public void testRadioButtonSelection() {
        // Step 1: Set up WebDriver and navigate to the URL
        setupDriver();
        navigateToURL("https://lambdatest.com/selenium-playground/");

        // Step 2: Perform an explicit wait until the "Radio Buttons Demo" link is clickable
        WebElement radioButtonsDemoLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Radio buttons Demo"))
        );
        radioButtonsDemoLink.click();

        // Step 3: Select the "Female" radio button under the "Click on button to get the selected value"
        WebElement femaleRadioButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Female']"))
        );
        femaleRadioButton.click();

        // Step 4: Click on the "Get Value" button
        WebElement getValueButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("buttoncheck"))
        );
        getValueButton.click();

        // Step 5: Read the printed message
        WebElement messageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("radiobutton"))
        );
        String message = messageElement.getText();

        // Step 6: Validate that the message is as expected
        String expectedMessage = "Radio button 'Female' is checked";
        assertEquals(expectedMessage, message, "The printed message does not match the expected one.");

        // Close the browser
        driver.quit();
    }
}
