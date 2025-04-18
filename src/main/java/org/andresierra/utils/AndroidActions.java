package org.andresierra.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions {

    AndroidDriver driver;

    public AndroidActions(AndroidDriver driver) throws InterruptedException{
        this.driver = driver;
    }

    public void longPressAction(WebElement ele){
        //Long press action
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", 
            ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(), "duration", 2000));
    }

    public void scrollToEndAction(){
        //Scroll to end
        boolean canScrollMore;
        do{
        canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
            "left", 100, "top", 100, "width", 200, "height", 200,
            "direction", "down",
            "percent", 3.0
        ));}
        while(canScrollMore);
    }

    public void scrollToText(String text){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
    }

    public void swipeAction(WebElement ele, String direction){
        //Swipe action
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
            "elementId", ((RemoteWebElement)ele).getId(),
            "direction", direction,
            "percent", 0.25
        ));
    }

}
