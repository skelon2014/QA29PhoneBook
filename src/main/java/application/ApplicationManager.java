package application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    EventFiringWebDriver wd;
    String browser;
    UserHelper userHelper;
    ContactHelper contactHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init(){
        if (browser.equals(BrowserType.CHROME)) {
            wd = new EventFiringWebDriver(new ChromeDriver());
        }else if (browser.equals(BrowserType.FIREFOX)) {
            wd = new EventFiringWebDriver(new FirefoxDriver());
        }else if(browser.equals((BrowserType.EDGE))){
            wd = new EventFiringWebDriver(new EdgeDriver());
        }

        wd.navigate().to("https://contacts-app.tobbymarshall815.vercel.app/home).");
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        userHelper = new UserHelper(wd);
        contactHelper = new ContactHelper(wd);
    }

    public void stop(){
        wd.quit();
    }
    public UserHelper userHelper() {
        return userHelper;
    }

    public ContactHelper contactHelper() {
        return contactHelper;
    }
}
