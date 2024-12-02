package com.tricentis.demowebshop.testbase;

import com.tricentis.demowebshop.propertyreader.PropertyReader;
import com.tricentis.demowebshop.utility.Utility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase extends Utility {

    String browser = PropertyReader.getInstance().getProperty("browser");

    @BeforeMethod
    public void setUpBrowser() {
        selectBrowser(browser);
    }

    @AfterMethod
    public void tearDown() {
        closeBrowser();
    }
}
