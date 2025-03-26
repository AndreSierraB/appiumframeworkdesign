package org.andresierra.pageObjects.android;

import org.andresierra.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions{

    AndroidDriver driver;

    public FormPage(AndroidDriver driver) throws InterruptedException{
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Andrea Sierra");
    @AndroidFindBy (id="com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    //driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
    @AndroidFindBy (xpath="//android.widget.RadioButton[@text='Female']")
    private WebElement femaleOption;

    @AndroidFindBy (xpath="//android.widget.RadioButton[@text='Male']")
    private WebElement maleOption;

    @AndroidFindBy (id="android:id/text1")
    private WebElement countryDropDown;

    @AndroidFindBy (id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;

    public void setNameField(String name){
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }
    
    public void setGender (String gender){
        if (gender.contains("female")){
            femaleOption.click();            
        }else{
            maleOption.click();
        }
    }

    public void setCountrySelection (String countryName){
        countryDropDown.click();
        scrollToText(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
    }

    public ProductCatalog submitForm() throws InterruptedException{
        shopButton.click();
        return new ProductCatalog(driver);
    }

}
