package com.luma.testsuite;

import com.luma.customlisteners.CustomListeners;
import com.luma.pages.GearPage;
import com.luma.pages.HomePage;
import com.luma.pages.OvernightDuffle;
import com.luma.pages.ShoppingCartPage;
import com.luma.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class GearTest extends BaseTest {

    HomePage homePage;
    GearPage gearPage;
    OvernightDuffle overnightDuffle;
    ShoppingCartPage shoppingCartPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        gearPage = new GearPage();
        overnightDuffle = new OvernightDuffle();
        shoppingCartPage = new ShoppingCartPage();

    }

    @Test(groups = {"sanity", "regression"})
    public void userShouldAddProductSuccessFullyToShoppingCart() throws InterruptedException {
        Thread.sleep(2000);
        homePage.mouseHoverOnGearMenu();
        homePage.clickOnBags();
        gearPage.clickOnProductGear();
        Assert.assertEquals(overnightDuffle.getOverNightDuffelText(), "Overnight Duffle");
        overnightDuffle.changeQuantityTo3("3");
        overnightDuffle.clickOnAddToCart();
        Assert.assertEquals(overnightDuffle.getAddToCartText(), "You added Overnight Duffle to your shopping cart.");
        overnightDuffle.clickOnShoppingCart();
        Assert.assertEquals(shoppingCartPage.getOverNightDuffeleText(), "Overnight Duffle");
        Assert.assertEquals(shoppingCartPage.getQty(), "3");
        Assert.assertEquals(shoppingCartPage.getProductPrice(), "$135.00");
        shoppingCartPage.changeQtyOfOverNightDuffle("5");
        shoppingCartPage.clickOnUpdateShoppingCart();
        Assert.assertEquals(shoppingCartPage.getUpdatedProductPrice(), "$225.00");


    }
}