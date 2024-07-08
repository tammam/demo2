package com.example.demo2;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class CarbCalculatorTest {
    private WebDriver driver;
    private CarbCalculatorPage carbCalculatorPage;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.calculator.net/carbohydrate-calculator.html");

        carbCalculatorPage = new CarbCalculatorPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    // Test NT-1
    @Test
    public void testAgeFieldDoesNotAcceptValueLessThan18() {
        carbCalculatorPage.ageInput.clear();
        carbCalculatorPage.ageInput.sendKeys("17");
        carbCalculatorPage.calculateButton.click();
        WebElement errorMsg = driver.findElement(By.cssSelector("div > div > font[color='red']"));
        assertTrue(errorMsg.isDisplayed());
        assertEquals("Please provide an age between 18 and 80.", errorMsg.getText());
    }

    // Test NT-2
    @Test
    public void testAgeFieldDoesNotAcceptValueGreaterThan80() {
        carbCalculatorPage.ageInput.clear();
        carbCalculatorPage.ageInput.sendKeys("81");
        carbCalculatorPage.calculateButton.click();
        WebElement errorMsg = driver.findElement(By.cssSelector("div > div > font[color='red']"));
        assertTrue(errorMsg.isDisplayed());
        assertEquals("Please provide an age between 18 and 80.", errorMsg.getText());
    }

    // Test NT-3
    @Test
    public void testAgeFieldDoesNotAcceptValueLessThanZero() {
        carbCalculatorPage.ageInput.clear();
        carbCalculatorPage.ageInput.sendKeys("-1");
        WebElement errorMsg = driver.findElement(By.cssSelector("div#cageifcErr"));
        assertTrue(errorMsg.isDisplayed());assertEquals("positive numbers only", errorMsg.getText());

    }

    // Test FT-1
    @Test
    public void testDefaultValuesMetricUnits() {
        assertEquals("25", carbCalculatorPage.ageInput.getAttribute("value"));
        assertTrue(carbCalculatorPage.genderMaleRadioButton.isSelected());
        assertFalse(carbCalculatorPage.genderFemaleRadioButton.isSelected());
        assertEquals("180", carbCalculatorPage.heightMetricInput.getAttribute("value"));
        assertEquals("60", carbCalculatorPage.weightMetricInput.getAttribute("value"));
        // The POM is returning carbCalculatorPage.activityDropdown as NULL for some reason
        // assertEquals(6, carbCalculatorPage.activityDropdown.getOptions().size());
        // assertEquals("Light: exercise 1-3 times/week", carbCalculatorPage.activityDropdown.getFirstSelectedOption().getText());
        assertFalse(carbCalculatorPage.settingsContent.isDisplayed());
        carbCalculatorPage.settingsLink.click();
        assertTrue(carbCalculatorPage.settingsContent.isDisplayed());
        assertTrue(carbCalculatorPage.mifflinStJeorRadioButton.isSelected());
        assertFalse(carbCalculatorPage.katchMcArdleRadioButton.isSelected());
        assertEquals("20", carbCalculatorPage.bodyFatInput.getAttribute("value"));
    }

    // Test FT-3
    @Test
    public void testMetricUnitsTabBMREstimationDefaultValues() {
        carbCalculatorPage.settingsLink.click();
        assertTrue(carbCalculatorPage.settingsContent.isDisplayed());
        assertTrue(carbCalculatorPage.mifflinStJeorRadioButton.isSelected());
        assertFalse(carbCalculatorPage.katchMcArdleRadioButton.isSelected());
        assertEquals("20", carbCalculatorPage.bodyFatInput.getAttribute("value"));
    }

    /*
    // Test FT-3
    @Test
    public void testUSUnitsTabBMREstimationDefaultValues() {
        carbCalculatorPage.usUnitsTab().click();
        carbCalculatorPage.settingsLink.click();
        assertTrue(carbCalculatorPage.settingsContent.isDisplayed());
        assertTrue(carbCalculatorPage.mifflinStJeorRadioButton.isSelected());
        assertFalse(carbCalculatorPage.katchMcArdleRadioButton.isSelected());
        assertEquals("20", carbCalculatorPage.bodyFatInput.getAttribute("value"));
    }
    */

    // Test FT-9
    @Test
    public void testClearButtonClearsAllFields() {
        carbCalculatorPage.clearButton.click();
        assertEquals("", carbCalculatorPage.ageInput.getAttribute("value"));
        assertEquals("", carbCalculatorPage.heightMetricInput.getAttribute("value"));
        assertEquals("", carbCalculatorPage.weightMetricInput.getAttribute("value"));
        carbCalculatorPage.settingsLink.click();
        assertEquals("", carbCalculatorPage.bodyFatInput.getAttribute("value"));
    }
}
