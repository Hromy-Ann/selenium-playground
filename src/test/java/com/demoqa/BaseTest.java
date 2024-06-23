package com.demoqa;

import com.demoqa.pages.HomePage;
import com.demoqa.pages.LeftMenuPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public abstract class BaseTest {
    WebDriver driver;
    JavascriptExecutor js;
    HomePage homePage;
    LeftMenuPage leftMenuPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(300));
        js = (JavascriptExecutor) driver;
        driver.get(ApplicationProperties.getBaseUrl());
        homePage = new HomePage(driver);
        leftMenuPage = new LeftMenuPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
