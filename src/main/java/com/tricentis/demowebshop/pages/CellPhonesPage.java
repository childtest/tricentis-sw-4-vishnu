package com.tricentis.demowebshop.pages;

import com.tricentis.demowebshop.utility.Utility;
import org.openqa.selenium.By;

public class CellPhonesPage extends Utility {

    By cellPhoneTitle = By.xpath("//h1[normalize-space()='Cell phones']");
    By listView = By.xpath("//select[@id='products-viewmode']");

    public String getTitleOfSellPhonesText(){
        return getTextFromElement(cellPhoneTitle);
    }

    public void selectViewAsList(String option){
        selectByVisibleTextFromDropDown(listView, option);
    }

    public void clickOnYourSelectedProduct(String productName){
        clickOnElement(By.linkText(productName));
    }
}
