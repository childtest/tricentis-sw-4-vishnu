package com.tricentis.demowebshop.pages;

import com.tricentis.demowebshop.utility.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DesktopPage extends Utility {

    By selectOption= By.id("products-orderby");
    By productElements = By.xpath("//h2[@class='product-title']/a");

    public void sortProductByNameZtoA(String option){
        selectByVisibleTextFromDropDown(selectOption,option);
    }

    public List<WebElement> getListOfProduct(){
        return getWebElements(productElements);
    }

    public void clickOnAddToCartOfYourSelectedProduct(String name){
        clickOnElement(By.linkText(name));
    }

}
