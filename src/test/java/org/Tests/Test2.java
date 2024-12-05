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
                {"shoes"}
        };
    }

    @Test(dataProvider = "searchData")
    public void checkAddToCartFunctionality(String searchTerm) {
        HomePage homePage = new HomePage();
        CartPage cartPage = new CartPage();

        homePage.openURL();
        homePage.closePopup();
        homePage.productSearch(searchTerm);
        homePage.selectSorting();

        List<WebElement> products = homePage.waitForElementPresence(homePage.productList, 10).findElements(homePage.productList);
        System.out.println(products.size());
        if(products.size()>=2) {
            for (int i = 0; i < products.size(); i++) {
                homePage.selectProducts(i+1);
                homePage.addToCart();
                if (i > 3) {
                    break;
                }
            }
        }
        else{
            System.out.println(products.size()+" in list");
        }

        int[] prices = cartPage.getProductPrices();
        int expectedTotal = prices[0] + prices[1];
        int actualTotal = cartPage.getTotalPrice();

        Assert.assertEquals(prices.length, 2, "Invalid Items in Cart");
        Assert.assertEquals(actualTotal, expectedTotal, "Price not matching");
    }
}
