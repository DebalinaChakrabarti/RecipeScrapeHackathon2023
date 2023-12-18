package managers;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import enums.BrowserType;
import utilities.ConfigReader;

import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private BrowserType browserType;
    private static ConfigReader configReader = new ConfigReader();

    public DriverManager() {
        browserType = configReader.getBrowserType();
    }

    public WebDriver getDriver() {
        if (driver.get() == null) {
            switch (browserType) {

                case FIREFOX:
                    driver.set(new FirefoxDriver());
                    break;

                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless"); // Run Chrome in headless mode
                    chromeOptions.addArguments("--disable-popup-blocking"); // Disable popup blocking
                    chromeOptions.addArguments("--disable-notifications"); // Disable popup blocking
                    chromeOptions.addArguments("--disable-extensions"); // Disable popup blocking
                    chromeOptions.addArguments("--blink-settings=imagesEnabled=false");
                    driver.set(new ChromeDriver(chromeOptions));
                    break;

                case EDGE:
                    driver.set(new EdgeDriver());
                    break;
            }
        }
        driver.get().manage().deleteAllCookies();
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
        driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
        driver.get().manage().window().maximize();
        
        return driver.get();
    }

   
}
