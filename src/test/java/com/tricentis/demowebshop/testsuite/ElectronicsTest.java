package com.tricentis.demowebshop.testsuite;

import com.tricentis.demowebshop.pages.*;
import com.tricentis.demowebshop.testbase.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Instant;

public class ElectronicsTest extends TestBase {

    HomePage homePage = new HomePage();
    CellPhonesPage cellPhonesPage = new CellPhonesPage();
    ProductDetailsPage productDetailsPage = new ProductDetailsPage();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    SignInPage signInPage = new SignInPage();
    RegisterPage registerPage = new RegisterPage();
    CheckOutPage checkOutPage = new CheckOutPage();

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {
        homePage.mouseHoverOnElectronicTab();
        homePage.mouseHoverAndClickOnCellPhones();
        Assert.assertEquals(cellPhonesPage.getTitleOfSellPhonesText(), "Cell phones",
                "Cell phones text not matched!");
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() {
        homePage.mouseHoverOnElectronicTab();
        homePage.mouseHoverAndClickOnCellPhones();
        Assert.assertEquals(cellPhonesPage.getTitleOfSellPhonesText(), "Cell phones",
                "Cell phones text not matched!");

        cellPhonesPage.selectViewAsList("List");
        cellPhonesPage.clickOnYourSelectedProduct("Smartphone");

        Assert.assertEquals(productDetailsPage.getTitleOfYourSelectedProduct(),
                "Smartphone", "Smartphone text not matched!");

        Assert.assertEquals(productDetailsPage.getTotalPrice(), "100.00",
                "Price mismatch!");

        productDetailsPage.changeProductQuantity("2");
        productDetailsPage.clickOnAddToCartButton();
        Assert.assertTrue(productDetailsPage.getSuccessMessageToAddProductInCart()
                .contains("The product has been added to your shopping cart"), "Success message mismatch!");
        productDetailsPage.clickOnCrossButtonInGreenBar();
        homePage.mouseHoverOnShoppingCartOnTop();
        homePage.clickOnGoToCartButton();
        Assert.assertEquals(shoppingCartPage.getShoppingCartTitle(),
                "Shopping cart", "Cart title mismatch!");

       /* Assert.assertEquals(shoppingCartPage.getQuantityOfProduct(),
                "2", "quantity is not matched!");*/
        Assert.assertEquals(shoppingCartPage.getTotalPriceOfProduct(),
                "200.00", "Total price mismatch!");
        shoppingCartPage.clickOnCheckBoxOfTermsAndCondition();
        shoppingCartPage.clickOnCheckOutButton();
        Assert.assertEquals(signInPage.getWelcomeTextFromSignInTitle(),
                "Welcome, Please Sign In!", "Sign-in title mismatch!");

        signInPage.clickOnRegisterButton();
        Assert.assertEquals(registerPage.getRegisterPageTitle(), "Register",
                "Register title mismatch!");
        registerPage.clickOnGenderOnMail();
        registerPage.enterFirstName("Prime");
        registerPage.enterLastName("test");
        Instant timestamp = Instant.now();
        String email = "Primetest" + timestamp + "@prime.com";
        registerPage.enterEmailAddress(email.replace("-", "").replace(":", ""));
        registerPage.enterPassword("prime123");
        registerPage.enterConfirmPassword("prime123");
        registerPage.clickOnRegisterButton();
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed",
                "Register completed title mismatch!");
        registerPage.clickOnContinueButton();
        Assert.assertEquals(shoppingCartPage.getShoppingCartTitle(),
                "Shopping cart", "Cart title mismatch!");
        shoppingCartPage.clickOnCheckBoxOfTermsAndCondition();
        shoppingCartPage.clickOnCheckOutButton();

        checkOutPage.selectCountryFromDropdown("United Kingdom");
        checkOutPage.enterCityName("London");
        checkOutPage.enterShippingAddress("tets test");
        checkOutPage.enterZipCode("HA09HT");
        checkOutPage.enterPhoneNumber("856754776");
        checkOutPage.clickOnContinueBillingSaveButton();
        checkOutPage.clickOnContinueShippingSaveButton();
        checkOutPage.clickOnShippingMethodOn2ndDayAir();
        checkOutPage.clickOnContinueShippingMethodNextStep();
        checkOutPage.clickOnCreditCard();
        checkOutPage.clickOnContinuePayment();
        checkOutPage.selectCardTypFromDropdown("Visa");
        checkOutPage.enterCardholderName("Alexis Runolfsdottir");
        checkOutPage.enterCardNumber("5451628062666699");
        checkOutPage.selectMonthOfExpiry("08");
        checkOutPage.selectYearOfExpire("2025");
        checkOutPage.enterCardCode("031");
        checkOutPage.clickOnContinuePaymentInfoButton();
        Assert.assertEquals(checkOutPage.getPaymentCardType()
                , "Credit Card", "Payment method is not match!");
        Assert.assertEquals(checkOutPage.getShippingMethod(),
                "2nd Day Air", "Shipping Method is not matched!");
        Assert.assertEquals(checkOutPage.getTotalPrice(), "200.00",
                "Total price mismatch!");
        checkOutPage.clickOnConfirmButton();
        Assert.assertEquals(checkOutPage.getTitleOfThankYou(), "Thank you",
                "Thank you message mismatch!");
        Assert.assertEquals(checkOutPage.getSuccessMessageOfOrderSuccessfullyProcessed(),
                "Your order has been successfully processed!", "order successfully message not matched!");
        checkOutPage.clickOnContinueOrderCompleteButton();

        Assert.assertEquals(homePage.getMessageOfWelcomeOurStore(), "Welcome to our store",
                "Welcome message mismatch!");

        homePage.clickOnLogOutButton();
        Assert.assertTrue(homePage.getCurrentUrl().contains("https://demowebshop.tricentis.com"),
                "Url is not match!");

    }
}
