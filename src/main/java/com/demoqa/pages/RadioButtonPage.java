package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RadioButtonPage {
    private static final String LABEL_FOR_YES_RADIO = "//label[@for='yesRadio']";
    private static final String SCROLL_INTO_VIEW_SCRIPT = "arguments[0].scrollIntoView(true)";

    private final WebDriver driver;
    private final JavascriptExecutor js;

    public RadioButtonPage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    public void clickYesRadio() {
        WebElement yesRadio = driver.findElement(By.xpath(LABEL_FOR_YES_RADIO));
        js.executeScript(SCROLL_INTO_VIEW_SCRIPT, yesRadio);
        yesRadio.click();
    }

    public String getSuccessText() {
        WebElement resultSpan = driver.findElement(By.className("text-success"));
        return resultSpan.getText();
    }
}
