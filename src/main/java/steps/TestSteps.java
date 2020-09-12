package steps;

import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TestSteps {

    @Given("^Start browser$")
    public void startBrowser() {
        //setting the driver executable
        System.setProperty("webdriver.chrome.driver", Objects.requireNonNull(TestSteps.class.getClassLoader().getResource("drivers")).getPath() + "/macos/chrome/x64_85");

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
