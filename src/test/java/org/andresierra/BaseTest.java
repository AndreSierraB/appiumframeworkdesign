package org.andresierra;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.io.File;
import java.net.URI;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.time.Duration;

import org.andresierra.pageObjects.android.FormPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public FormPage formPage;
    
    @BeforeClass
    public void ConfigureAppium() throws URISyntaxException, MalformedURLException, InterruptedException{
        // Start Appium server with increased startup timeout
        service = new AppiumServiceBuilder()
        .withAppiumJS(new File("/Users/andreasierra/.nvm/versions/node/v22.14.0/lib/node_modules/appium/build/lib/main.js"))
        .withIPAddress("127.0.0.1")
        .usingPort(4723)
        /*.withArgument(() -> "--session-override")
        .withArgument(() -> "--log-level", "error")
        .withArgument(() -> "--command-timeout", "60") // Aumentar el tiempo de espera a 60 segundos
        .withArgument(() -> "--startup-timeout", "60000") // Aumentar el tiempo de espera de inicio a 60 segundos*/
        .build();
        service.start();
        
        //Configure the Android driver
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("andrephonemedium");
        options.setChromedriverExecutable("//usr//local//bin//GoogleChromeforTesting.app");
        //options.setApp("/Users/andreasierra/github/first-appium/src/test/java/resources/ApiDemos-debug.apk");
        options.setApp("/Users/andreasierra/github/first-appium/src/test/java/resources/General-Store.apk");
        //Android driver
        //Appium code -> Appium server --> Mobile
        driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        formPage = new FormPage(driver);
    }

    @AfterClass
    public void StopAppium(){
        // Stop Appium server
        // Quit the driver
        driver.quit();
        service.stop();
    }
}