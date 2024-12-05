package org.Driver;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Objects;

public class Manager {

    private static final ThreadLocal<WebDriver> dr =new ThreadLocal<>();

    public static void setDriver(WebDriver driverRef) {
        dr.set(driverRef);
    }

    public static WebDriver getDriver(){
        return dr.get();
    }

    public static void unload(){
        dr.remove();
    }

    @BeforeMethod
    @Description("Initialize the Browser")
    public static void init(){
        if(Objects.isNull(Manager.getDriver())){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito,--start-maximized");
            WebDriver driver = new ChromeDriver(options);
            setDriver(driver);
        }
    }

    @AfterMethod
    @Description("Close the Browser")
    public static void down(){
        if(Objects.nonNull(Manager.getDriver())){
            getDriver().quit();
            unload();
        }
    }
}
