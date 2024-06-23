package com.demoqa;

import com.demoqa.pages.RadioButtonPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


class MyFirstUITest extends BaseTest {
    private static final String BUTTON_TEXT_CLICK_ME = "//button[text()='Click Me']";
    private static final String SCROLL_INTO_VIEW_SCRIPT = "arguments[0].scrollIntoView(true)";

    @Test
    void clickFormsCard() {
        homePage.goToForms();
        WebElement centerDiv = driver.findElement(By.className("col-md-6"));
        String text = centerDiv.getText();
        assertEquals("Please select an item from left to start practice.", text);
    }

    @Test
    void chooseRadioButton() {
        homePage.goToElements();
        leftMenuPage.chooseRadioButton();
        RadioButtonPage page = new RadioButtonPage(driver);
        page.clickYesRadio();
        String actualText = page.getSuccessText();
        assertEquals("Yes", actualText);
    }

    @Test
    void inputUserName() {
        homePage.goToElements();
        leftMenuPage.chooseTextBox();
        WebElement userNameInput = driver.findElement(By.id("userName"));
        String inputText = "user name";
        userNameInput.sendKeys(inputText);
        WebElement submitButton = driver.findElement(By.id("submit"));
        js.executeScript(SCROLL_INTO_VIEW_SCRIPT, submitButton);
        submitButton.click();
        WebElement nameParagraph = driver.findElement(By.id("name"));
        assertEquals("Name:" + inputText, nameParagraph.getText());
    }

    @Test
    void inputUserEmail() {
        homePage.goToForms();
        leftMenuPage.chooseTextBox();
        WebElement userEmailInput = driver.findElement(By.id("userEmail"));
        String inputText = "a@g.com";
        userEmailInput.sendKeys(inputText);
        WebElement submitButton = driver.findElement(By.id("submit"));
        js.executeScript(SCROLL_INTO_VIEW_SCRIPT, submitButton);
        submitButton.click();
        WebElement emailParagraph = driver.findElement(By.id("email"));
        assertEquals("Email:" + inputText, emailParagraph.getText());
    }

    @Test
    void clickMeButton() {
        homePage.goToElements();
        leftMenuPage.chooseButtons();
        WebElement clickMeButton = driver.findElement(By.xpath(BUTTON_TEXT_CLICK_ME));
        js.executeScript(SCROLL_INTO_VIEW_SCRIPT, clickMeButton);
        clickMeButton.click();
        WebElement result = driver.findElement(By.id("dynamicClickMessage"));
        assertEquals("You have done a dynamic click", result.getText());
    }

    @Test
    void clickProgressBar() {
        homePage.goToWidgets();
        leftMenuPage.chooseProgressbar();
        WebElement start = driver.findElement(By.id("startStopButton"));
        js.executeScript(SCROLL_INTO_VIEW_SCRIPT, start);
        start.click();
        WebElement progressBarContainer = driver.findElement(By.id("progressBar"));
        WebElement progressBarValue = progressBarContainer.findElement(By.tagName("div"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        wait.until(d -> progressBarValue.getAttribute("aria-valuenow").equals("100"));
        WebElement resetButton = driver.findElement(By.id("resetButton"));
        assertTrue(resetButton.isDisplayed());
    }

    @Test
    void hoverTheButton() {
        homePage.goToWidgets();
        leftMenuPage.chooseToolTips();
        WebElement toolTipButton = driver.findElement(By.id("toolTipButton"));
        new Actions(driver)
                .pause(Duration.ofSeconds(3L))
                .scrollToElement(toolTipButton)
                .scrollByAmount(0, 150)
                .moveToElement(toolTipButton)
                .perform();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(1L));
        wait.until(ExpectedConditions.attributeToBe(toolTipButton, "aria-describedby", "buttonToolTip"));
        WebElement buttonToolTip = driver.findElement(By.id("buttonToolTip"));
        assertTrue(buttonToolTip.isDisplayed());
    }

    @Test
    void chooseTab() {
        homePage.goToWidgets();
        leftMenuPage.chooseTabs();
        WebElement tabUse = driver.findElement(By.id("demo-tab-use"));
        tabUse.click();
        WebElement tabPaneUse = driver.findElement(By.id("demo-tabpane-use"));
        String hidden = tabPaneUse.getAttribute("aria-hidden");
        assertFalse(Boolean.parseBoolean(hidden));
    }

    @Test
    void checkIFrameText() {
        homePage.goToFrame();
        leftMenuPage.chooseFrames();
        WebElement iFrame = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(iFrame);
        WebElement inTheFrame = driver.findElement(By.id("sampleHeading"));
        assertEquals("This is a sample page", inTheFrame.getText());
    }
}
