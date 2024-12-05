package org.Pages;

import org.Base.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends CommonFunctions {
    By cartItems = By.cssSelector(".cPHDOP");
    By productPrices = By.cssSelector(".product-price");
    By totalPrice = By.cssSelector(".total-price");

    public List<WebElement> getCartItems() {
        return waitForElementPresence(cartItems, 10).findElements(cartItems);
    }

    public int[] getProductPrices() {
        List<WebElement> priceElements = waitForElementPresence(productPrices, 10).findElements(productPrices);
        int[] prices = new int[priceElements.size()];
        for (int i = 0; i < priceElements.size(); i++) {
            String priceText = priceElements.get(i).getText()
                    .replace("₹", "").replace(",", "").trim();
            prices[i] = Integer.parseInt(priceText);
        }
        return prices;
    }

    public int getTotalPrice() {
        String totalPriceText = getElementText(totalPrice);
        return Integer.parseInt(totalPriceText.replace("₹", "").replace(",", "").trim());
    }


}
