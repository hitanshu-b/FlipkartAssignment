package org.Tests;

import org.BaseTest.CommonTests;
import org.Driver.Manager;
import org.Pages.CartPage;
import org.Pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class Test2 extends CommonTests {

    @DataProvider(name = "searchData")
    public Object[][] getData() {
        return new Object[][]{
                {"shoes"} // Search term
        };
    }

    @Test(dataProvider = "searchData")
    public void validateAddToCartFunctionality(String searchTerm) {
        HomePage homePage = new HomePage();
        CartPage cartPage = new CartPage();

        homePage.openURL();
        homePage.closePopup();
        homePage.productSearch(searchTerm);
        homePage.selectSorting();

        List<WebElement> products = homePage.waitForElementPresence(homePage.productList, 10).findElements(homePage.productList);
        if (products.size() > 1) {
            homePage.selectProducts(1);
            homePage.addToCart();
        } else {
            System.out.println("Not enough products to add to cart.");
        }

        if (products.size() > 2) {
            homePage.selectProducts(2);
            homePage.addToCart();
        } else {
            System.out.println("Only two products available, can't select the third.");
        }

        int[] prices = cartPage.getProductPrices();
        int expectedTotal = prices[0] + prices[1];
        int actualTotal = cartPage.getTotalPrice();

        Assert.assertEquals(prices.length, 2, "Invalid Items in Cart");
        Assert.assertEquals(actualTotal, expectedTotal, "Price not matching");
    }
}
