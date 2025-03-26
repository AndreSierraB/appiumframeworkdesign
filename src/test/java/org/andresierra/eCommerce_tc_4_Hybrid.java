package org.andresierra;

import org.andresierra.pageObjects.android.CartPage;
import org.andresierra.pageObjects.android.ProductCatalog;
import org.testng.Assert;
import org.testng.annotations.Test;

public class eCommerce_tc_4_Hybrid extends BaseTest
{
    @Test
	public void FillForm() throws InterruptedException
	{
        formPage.setNameField("Andrea Sierra");
        formPage.setGender("female");
		formPage.setCountrySelection("Argentina");
        ProductCatalog productCatalog = formPage.submitForm();
		
        productCatalog.addItemCartByIndex(0);
        productCatalog.addItemCartByIndex(0);
        CartPage cartPage = productCatalog.goToCartPage();

        Thread.sleep(2000);
        double productSum = cartPage.getProductSum();
        double displayFormatedSum = cartPage.getTotalAmountDisplayed();
        Assert.assertEquals(productSum, displayFormatedSum, 0.0);

        cartPage.acceptTermsConditions();
        cartPage.submitOrder();
        Thread.sleep(4000);
	}

}
