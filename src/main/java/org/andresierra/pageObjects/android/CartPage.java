package org.andresierra.pageObjects.android;

import java.util.List;

import org.andresierra.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{

    AndroidDriver driver;

    public CartPage(AndroidDriver driver) throws InterruptedException {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy (id = "com.androidsample.generalstore:id/toolbar_title")
    private WebElement pageTittle;
    
    @AndroidFindBy (id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productList;

    @AndroidFindBy (id = "com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmount;

    @AndroidFindBy (id = "com.androidsample.generalstore:id/termsButton")
    private WebElement terms;

    @AndroidFindBy (id = "android:id/button1")
    private WebElement acceptButton;

    @AndroidFindBy (className = "android.widget.CheckBox")
    private WebElement checkBox;
    
    @AndroidFindBy (id = "com.androidsample.generalstore:id/btnProceed")
    private WebElement proceed;

    public List<WebElement> getProducLis(){
        return productList;
    }

    public double getProductSum(){
        int count = productList.size();
        double totalSum = 0.0;
        for(int i =0; i< count; i++)
        {
            String amountString = productList.get(i).getText();
            double price = getFormattedAmount(amountString);
            totalSum = totalSum + price;  //160.97 + 120 =280.97
                
        }
        return totalSum;
    }

    public double getTotalAmountDisplayed (){
        return getFormattedAmount(totalAmount.getText());
    }

    public double getFormattedAmount (String amount){
        double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    public void acceptTermsConditions(){
        longPressAction(terms);
        acceptButton.click();
    }

    public void submitOrder(){
        checkBox.click();
        proceed.click();
    }

}
