package org.Pages;

import org.Base.CommonFunctions;
import org.Driver.Manager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.Driver.Manager.getDriver;

public class HomePage extends CommonFunctions {

    By popup = By.xpath("//span[@class='_30XB9F']");
    By searchBar = By.name("q");
    By searchButton = By.xpath("//button[@type='submit']");
    By sortOption = By.xpath("//div[text()='Price -- Low to High']");
    By price = By.cssSelector("._30jeq3");
    public By productList = By.cssSelector("._75nlfW");
    By addToCart = By.xpath("//button[text()='Add to cart']");

    // Closing the popup
    public void closePopup(){
        try{
            clickElement(popup);
        } catch (Exception e){
            System.out.println("Popup is not displayed");
        }
    }

    public void productSearch(String product){
        enterInput(searchBar, product);
        WebElement searchBtnElement = getDriver().findElement(searchButton);
        Actions actions = new Actions(getDriver());
        actions.moveToElement(searchBtnElement).click().perform();
    }

    public void selectSorting(){
        clickElement(sortOption);
    }

    public void selectProducts(int index){
        waitForElementPresence(productList,10).findElements(productList).get(index).click();
    }

    public void addToCart(){
        clickElement(addToCart);
    }

    public int[] getPrices(){
        List<WebElement> pricingElement = getDriver().findElements(price);
        int[] prices = new int[pricingElement.size()];

        for(int i=0; i< pricingElement.size(); i++){
            String priceValue = pricingElement.get(i).getText();
            priceValue = priceValue.replace("₹", "").replace(",", "").trim();
            prices[i] = Integer.parseInt(priceValue);
        }
        return prices;
    }

}
