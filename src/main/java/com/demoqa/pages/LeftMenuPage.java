package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeftMenuPage {
    private static final String SPAN_TEXT_RADIO_BUTTON = "//span[text()='Radio Button']";
    private static final String SPAN_TEXT_TEXT_BOX = "//span[text()='Text Box']";
    private static final String SPAN_TEXT_BUTTONS = "//span[text()='Buttons']";
    private static final String SPAN_TEXT_PROGRESSBAR = "//span[text()='Progress Bar']";
    private static final String SPAN_TEXT_TOOL_TIPS = "//span[text()='Tool Tips']";
    private static final String SPAN_TEXT_TABS = "//span[text()='Tabs']";
    private static final String SPAN_TEXT_FRAMES = "//span[text()='Frames']";

    private final WebDriver driver;

    public LeftMenuPage(WebDriver driver) {
        this.driver = driver;
    }

    public void chooseRadioButton() {
        findAndClick(SPAN_TEXT_RADIO_BUTTON);
    }

    public void chooseTextBox() {
        findAndClick(SPAN_TEXT_TEXT_BOX);
    }

    public void chooseButtons() {
        findAndClick(SPAN_TEXT_BUTTONS);
    }

    public void chooseProgressbar() {
        findAndClick(SPAN_TEXT_PROGRESSBAR);
    }

    public void chooseToolTips() {
        findAndClick(SPAN_TEXT_TOOL_TIPS);
    }

    public void chooseTabs() {
        findAndClick(SPAN_TEXT_TABS);
    }

    public void chooseFrames() {
        findAndClick(SPAN_TEXT_FRAMES);
    }

    private void findAndClick(String xPath) {
        WebElement formsCard = driver.findElement(By.xpath(xPath));
        formsCard.click();
    }
}
