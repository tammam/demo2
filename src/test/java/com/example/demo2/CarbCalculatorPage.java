package com.example.demo2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


// page_url = https://www.calculator.net/carbohydrate-calculator.html
public class CarbCalculatorPage {
    @FindBy(css = "input#cage")
    public WebElement ageInput;

    @FindBy(css = "input#csex1")
    public WebElement genderMaleRadioButton;

    @FindBy(css = "input#csex2")
    public WebElement genderFemaleRadioButton;

    @FindBy(css = "input#cheightmeter")
    public WebElement heightMetricInput;

    @FindBy(css = "input#cheightfeet")
    public WebElement heightFeetInput;

    @FindBy(css = "input#cheightinch")
    public WebElement heightInchInput;

    @FindBy(css = "input#ckg")
    public WebElement weightMetricInput;

    @FindBy(css = "input#cpound")
    public WebElement weightPoundInput;

    @FindBy(css = "select#cactivity")
    public Select activityDropdown;

    @FindBy(css = "div#ccsettingtitle a")
    public WebElement settingsLink;

    @FindBy(css = "div#ccsettingcontent")
    public WebElement settingsContent;

    @FindBy(css = "input#cformula1")
    public WebElement mifflinStJeorRadioButton;

    @FindBy(css = "input#cformula2")
    public WebElement katchMcArdleRadioButton;

    @FindBy(css = "input.inpct")
    public WebElement bodyFatInput;

    @FindBy(css = "input[name='x']")
    public WebElement calculateButton;

    @FindBy(css = "input[onclick='clearForm(document.calform);']")
    public WebElement clearButton;

    public CarbCalculatorPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
