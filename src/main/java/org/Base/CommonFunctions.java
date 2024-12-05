package org.Base;

import org.Utils.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.Driver.Manager.getDriver;

public class CommonFunctions {

    public void openURL(){
        getDriver().get(PropertyReader.readKey("url"));
    }

    public void clickElement(By by) {
        WebElement element = new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(by));
        element.click();
    }

    public void enterInput(By by, String input) {
        WebElement element = new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
        element.clear();
        element.sendKeys(input);
    }

    public String getElementText(By by) {
        WebElement element = new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
        return element.getText();
    }

    public WebElement waitForElementPresence(By by, int waitTime) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(waitTime))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

}
