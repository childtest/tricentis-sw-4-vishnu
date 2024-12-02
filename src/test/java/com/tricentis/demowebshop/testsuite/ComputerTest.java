package com.tricentis.demowebshop.testsuite;

import com.tricentis.demowebshop.pages.*;
import com.tricentis.demowebshop.testbase.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComputerTest extends TestBase {

    HomePage homePage = new HomePage();
    DesktopPage desktopPage = new DesktopPage();
    ProductDetailsPage productDetailsPage = new ProductDetailsPage();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    SignInPage signInPage = new SignInPage();
    CheckOutPage checkOutPage = new CheckOutPage();

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {

        homePage.clickOnComputerInTopMenu("Computers");
        homePage.clickOnComputerInTopMenu("Desktops");
        desktopPage.sortProductByNameZtoA("Name: Z to A");

        //  Verify the products are arranged in descending order
        List<String> actualProductNames = new ArrayList<>();
        for (WebElement product : desktopPage.getListOfProduct()) {
            actualProductNames.add(product.getText());
        }

        // Create a sorted copy of the product names in descending order
        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        Collections.sort(expectedProductNames, Collections.reverseOrder());

        // Assert that the product names are sorted correctly
        Assert.assertEquals(actualProductNames, expectedProductNames, "Products are not sorted in descending order!");

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessfully() {

        homePage.clickOnComputerInTopMenu("Computers");
        homePage.clickOnComputerInTopMenu("Desktops");
        desktopPage.sortProductByNameZtoA("Name: A to Z");
        desktopPage.clickOnAddToCartOfYourSelectedProduct("Build your own computer");
        Assert.assertEquals(productDetailsPage.getTitleOfYourSelectedProduct(),
                "Build your own computer"
                , "Product title mismatch!");
        productDetailsPage.selectProcessorInDropDown("2.2 GHz Intel Pentium Dual-Core E2200");
        productDetailsPage.selectRamInDropDown("8GB [+60.00]");
        productDetailsPage.clickOnHDDRadioButton400GB();
        productDetailsPage.clickOnOSRadioButtonWindow10();
        productDetailsPage.clickOnCheckboxMicrosoftOffice();
        productDetailsPage.clickOnCheckboxTotalCommander();
        Assert.assertEquals(productDetailsPage.getTotalPrice(), "1200.00"
                , "Total Price mismatch!");
        productDetailsPage.clickOnAddToCartButton();
        Assert.assertTrue(productDetailsPage.getSuccessMessageToAddProductInCart()
                .contains("The product has been added to your shopping cart"), "Success message mismatch!");
        productDetailsPage.clickOnCrossButtonInGreenBar();
        homePage.mouseHoverOnShoppingCartOnTop();
        homePage.clickOnGoToCartButton();
        Assert.assertEquals(shoppingCartPage.getShoppingCartTitle(),
                "Shopping cart", "Cart title mismatch!");
        shoppingCartPage.changeQuantityOfProduct("2");
        shoppingCartPage.clickOnUpdateShoppingCartButton();
        Assert.assertEquals(shoppingCartPage.getTotalPriceOfProduct(),
                "2950.00", "Total price mismatch!");
        shoppingCartPage.clickOnCheckBoxOfTermsAndCondition();
        shoppingCartPage.clickOnCheckOutButton();
        Assert.assertEquals(signInPage.getWelcomeTextFromSignInTitle(),
                "Welcome, Please Sign In!", "Sign-in title mismatch!");
        signInPage.clickSignInAsAGuestButton();

        checkOutPage.enterFirstName("prime");
        checkOutPage.enterLastName("Test");
        checkOutPage.enterEmailAddress("primetest113@test.com");
        checkOutPage.selectCountryFromDropdown("United Kingdom");
        checkOutPage.enterCityName("London");
        checkOutPage.enterShippingAddress("tets test");
        checkOutPage.enterZipCode("HA09HT");
        checkOutPage.enterPhoneNumber("856754776");
        checkOutPage.clickOnContinueBillingSaveButton();
        checkOutPage.clickOnContinueShippingSaveButton();
        checkOutPage.clickOnShippingMethodOnNextDayAir();
        checkOutPage.clickOnContinueShippingMethodNextStep();
        checkOutPage.clickOnCreditCard();
        checkOutPage.clickOnContinuePayment();
        checkOutPage.selectCardTypFromDropdown("Master card");
        checkOutPage.enterCardholderName("Alexis Runolfsdottir");
        checkOutPage.enterCardNumber("5451628062666699");
        checkOutPage.selectMonthOfExpiry("08");
        checkOutPage.selectYearOfExpire("2025");
        checkOutPage.enterCardCode("031");
        checkOutPage.clickOnContinuePaymentInfoButton();
        Assert.assertEquals(checkOutPage.getPaymentCardType()
                , "Credit Card", "Payment method is not match!");
        Assert.assertEquals(checkOutPage.getShippingMethod(),
                "Next Day Air", "Shipping Method is not matched!");
        Assert.assertEquals(checkOutPage.getTotalPrice(), "2950.00",
                "Total price mismatch!");
        checkOutPage.clickOnConfirmButton();
        Assert.assertEquals(checkOutPage.getTitleOfThankYou(), "Thank you",
                "Thank you message mismatch!");
        Assert.assertEquals(checkOutPage.getSuccessMessageOfOrderSuccessfullyProcessed(),
                "Your order has been successfully processed!", "order successfully message not matched!");
        checkOutPage.clickOnContinueOrderCompleteButton();

        Assert.assertEquals(homePage.getMessageOfWelcomeOurStore(), "Welcome to our store",
                "Welcome message mismatch!");
    }

}
