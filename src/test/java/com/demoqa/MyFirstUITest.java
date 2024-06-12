package com.demoqa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


class MyFirstUITest {
    private static final String FORMS_CARD_TEXT = "//div[@class='card-body']/h5[text()='Forms']";
    private static final String ELEMENTS_CARD_TEXT = "//div[@class='card-body']/h5[text()='Elements']";
    private static final String WIDGETS_CARD_TEXT = "//div[@class='card-body']/h5[text()='Widgets']";
    private static final String SPAN_TEXT_RADIO_BUTTON = "//span[text()='Radio Button']";
    private static final String SPAN_TEXT_TEXT_BOX = "//span[text()='Text Box']";
    private static final String SPAN_TEXT_BUTTONS = "//span[text()='Buttons']";
    private static final String SPAN_TEXT_PROGRESSBAR = "//span[text()='Progress Bar']";
    private static final String SPAN_TEXT_TOOL_TIPS = "//span[text()='Tool Tips']";
    private static final String SPAN_TEXT_TABS = "//span[text()='Tabs']";
    private static final String BUTTON_TEXT_CLICK_ME = "//button[text()='Click Me']";
    private static final String LABEL_FOR_YES_RADIO = "//label[@for='yesRadio']";
    private static final String SCROLL_INTO_VIEW_SCRIPT = "arguments[0].scrollIntoView(true)";
    private static final String MOUSE_OVER_SCRIPT = "arguments[0].dispatchEvent(" +
            "new MouseEvent('mouseover', {'view': window, 'bubbles': true, 'cancelable': true}));";
    WebDriver driver;
    JavascriptExecutor js;

    @Test
    void clickFormsCard() {
        WebElement formsCard = driver.findElement(By.xpath(FORMS_CARD_TEXT));
        js.executeScript(SCROLL_INTO_VIEW_SCRIPT, formsCard);
        formsCard.click();
        WebElement centerDiv = driver.findElement(By.className("col-md-6"));
        String text = centerDiv.getText();
        assertEquals("Please select an item from left to start practice.", text);
    }

    @Test
    void chooseRadioButton() {
        WebElement elementsCard = driver.findElement(By.xpath(ELEMENTS_CARD_TEXT));
        js.executeScript(SCROLL_INTO_VIEW_SCRIPT, elementsCard);
        elementsCard.click();
        WebElement radioButtonMenuItem = driver.findElement(By.xpath(SPAN_TEXT_RADIO_BUTTON));
        radioButtonMenuItem.click();
        WebElement yesRadio = driver.findElement(By.xpath(LABEL_FOR_YES_RADIO));
        js.executeScript(SCROLL_INTO_VIEW_SCRIPT, yesRadio);
        yesRadio.click();
        WebElement resultSpan = driver.findElement(By.className("text-success"));
        assertEquals("Yes", resultSpan.getText());
    }

    @Test
    void inputUserName() {
        WebElement elementsCard = driver.findElement(By.xpath(ELEMENTS_CARD_TEXT));
        js.executeScript(SCROLL_INTO_VIEW_SCRIPT, elementsCard);
        elementsCard.click();
        WebElement textBoxMenuItem = driver.findElement(By.xpath(SPAN_TEXT_TEXT_BOX));
        textBoxMenuItem.click();
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
        WebElement elementsCard = driver.findElement(By.xpath(ELEMENTS_CARD_TEXT));
        js.executeScript(SCROLL_INTO_VIEW_SCRIPT, elementsCard);
        elementsCard.click();
        WebElement textBoxMenuItem = driver.findElement(By.xpath(SPAN_TEXT_TEXT_BOX));
        textBoxMenuItem.click();
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
        WebElement elementsCard = driver.findElement(By.xpath(ELEMENTS_CARD_TEXT));
        js.executeScript(SCROLL_INTO_VIEW_SCRIPT, elementsCard);
        elementsCard.click();
        WebElement buttonsMenuItem = driver.findElement(By.xpath(SPAN_TEXT_BUTTONS));
        buttonsMenuItem.click();
        WebElement clickMeButton = driver.findElement(By.xpath(BUTTON_TEXT_CLICK_ME));
        js.executeScript(SCROLL_INTO_VIEW_SCRIPT, clickMeButton);
        clickMeButton.click();
        WebElement result = driver.findElement(By.id("dynamicClickMessage"));
        assertEquals("You have done a dynamic click", result.getText());
    }

    @Test
    void clickProgressBar() {
        WebElement widgetsCard = driver.findElement(By.xpath(WIDGETS_CARD_TEXT));
        js.executeScript(SCROLL_INTO_VIEW_SCRIPT, widgetsCard);
        widgetsCard.click();
        WebElement progressBarMenuItem = driver.findElement(By.xpath(SPAN_TEXT_PROGRESSBAR));
        progressBarMenuItem.click();
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
        WebElement widgetsCard = driver.findElement(By.xpath(WIDGETS_CARD_TEXT));
        js.executeScript(SCROLL_INTO_VIEW_SCRIPT, widgetsCard);
        widgetsCard.click();
        WebElement toolTipsMenuItem = driver.findElement(By.xpath(SPAN_TEXT_TOOL_TIPS));
        toolTipsMenuItem.click();
        WebElement toolTipButton = driver.findElement(By.id("toolTipButton"));
        js.executeScript(MOUSE_OVER_SCRIPT, toolTipButton);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(1L));
        wait.until(ExpectedConditions.attributeToBe(toolTipButton, "aria-describedby", "buttonToolTip"));
        WebElement buttonToolTip = driver.findElement(By.id("buttonToolTip"));
        assertTrue(buttonToolTip.isDisplayed());
    }

    @Test
    void chooseTab() {
        WebElement widgetsCard = driver.findElement(By.xpath(WIDGETS_CARD_TEXT));
        js.executeScript(SCROLL_INTO_VIEW_SCRIPT, widgetsCard);
        widgetsCard.click();
        WebElement tabsMenuItem = driver.findElement(By.xpath(SPAN_TEXT_TABS));
        tabsMenuItem.click();
        WebElement tabUse = driver.findElement(By.id("demo-tab-use"));
        tabUse.click();
        WebElement tabPaneUse = driver.findElement(By.id("demo-tabpane-use"));
        String hidden = tabPaneUse.getAttribute("aria-hidden");
        assertFalse(Boolean.parseBoolean(hidden));
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(300));
        js = (JavascriptExecutor) driver;
        driver.get("https://demoqa.com/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
