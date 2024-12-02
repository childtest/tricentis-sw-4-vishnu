package com.tricentis.demowebshop.pages;

import com.tricentis.demowebshop.utility.Utility;
import org.openqa.selenium.By;

public class ComputerPage extends Utility {

    By computerTitle = By.xpath("//h1[normalize-space()='Computers']");

    public String getComputerPageTitle(){
       return getTextFromElement(computerTitle);
    }
}
