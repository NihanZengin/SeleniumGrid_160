package manage;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManage {

    static WebDriver driver;
    DesiredCapabilities capabilities = new DesiredCapabilities();


    public WebDriver setUpChromeDriver(){

        capabilities.setPlatform(Platform.WINDOWS);
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("136.0.7103.49"); //Buraya kendi bilgisayarimizdaki chrome versiyonunu yazmaliyiz.

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.merge(capabilities); //olusturdugumuz capabilities ayarlarini chromeOptions icine birlestiriyoruz

        try {
            driver = new RemoteWebDriver(new URL("http://192.168.1.176:4444"), chromeOptions);
            //Artik burda kendi olusturdugumuz chromeOptions objesinin adini yaziyoruz.
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        System.out.println("*/ Remote Chrome Driver /*");

        return driver;
    }

    //138.0.1
    public WebDriver setUpFirefoxDriver(){

        capabilities.setPlatform(Platform.ANY);
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("138.0.1");//Buraya kendi bilgisayarimizdaki firefox versiyonunu yazmaliyiz.

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.merge(capabilities);

        try {
            driver = new RemoteWebDriver(new URL("http://192.168.1.176:4444"), firefoxOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        System.out.println("*/ Remote Firefox Driver /*");

        return driver;
    }
}
