package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private static final String FORMS_CARD_TEXT = "//div[@class='card-body']/h5[text()='Forms']";
    private static final String ELEMENTS_CARD_TEXT = "//div[@class='card-body']/h5[text()='Elements']";
    private static final String WIDGETS_CARD_TEXT = "//div[@class='card-body']/h5[text()='Widgets']";
    private static final String FRAME_CARD_TEXT = "//div[@class='card-body']/h5[text()='Alerts, Frame & Windows']";
    private static final String SCROLL_INTO_VIEW_SCRIPT = "arguments[0].scrollIntoView(true)";

    private final WebDriver driver;
    private final JavascriptExecutor js;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    public void goToForms() {
        findAndClick(FORMS_CARD_TEXT);
    }

    public void goToElements() {
        findAndClick(ELEMENTS_CARD_TEXT);
    }

    public void goToWidgets() {
        findAndClick(WIDGETS_CARD_TEXT);
    }

    public void goToFrame() {
        findAndClick(FRAME_CARD_TEXT);
    }

    private void findAndClick(String xPath) {
        WebElement formsCard = driver.findElement(By.xpath(xPath));
        js.executeScript(SCROLL_INTO_VIEW_SCRIPT, formsCard);
        formsCard.click();
    }
}
