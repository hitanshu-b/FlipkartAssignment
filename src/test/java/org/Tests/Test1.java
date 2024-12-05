package org.Tests;

import org.BaseTest.CommonTests;
import org.Pages.HomePage;
import org.Utils.PropertyReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test1 extends CommonTests {

    @DataProvider(name = "searchData")
    public Object[][] getData(){
        return new Object[][]{
                {"shoes"}
        };
    }

    @Test(dataProvider = "searchData")
    public void checkSort(String itemSearch){
        HomePage homepage = new HomePage();

        homepage.openURL();
        homepage.closePopup();
        homepage.productSearch(itemSearch);
        homepage.selectSorting();

        int[] price = homepage.getPrices();
        for(int i=1; i< price.length; i++){
            Assert.assertTrue(price[i-1]<=price[i], "Not in Low to High");
        }
    }
}
