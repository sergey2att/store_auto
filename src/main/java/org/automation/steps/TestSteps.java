package org.automation.steps;

import io.cucumber.java.en.Given;
import org.automation.utils.OSUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TestSteps {

    @Given("^Start browser$")
    public void startBrowser() throws IOException {
        int chromeVersion = OSUtils.getChromeBrowserVersion();
        String driverPath = Objects.requireNonNull(TestSteps.class.getClassLoader().getResource("drivers")).getPath() + "/macos/chrome/x64_" + chromeVersion;
        if (Files.isRegularFile(Paths.get(driverPath))) {
            System.setProperty("webdriver.chrome.driver", driverPath);
        } else {
            throw new RuntimeException("Unable to find chrome driver by path: " + driverPath);
        }



//Initiating your chromedriver
        WebDriver driver=new ChromeDriver();

//Applied wait time
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//maximize window
        driver.manage().window().maximize();

//open browser with desried URL
        driver.get("https://www.google.com");

//closing the browser
        driver.close();
    }
}
