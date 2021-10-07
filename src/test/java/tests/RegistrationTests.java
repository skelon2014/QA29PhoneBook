package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (app.userHelper().isLogged()) {
            app.userHelper().logout();
        }
    }
    @Test(groups = {"web"})
    public void registrationTestPositive(){
        app.userHelper().pause(1000);
        int i = (int) (System.currentTimeMillis()/3600%1000);
        User user = new User()
                .withEmail("skelon"+i+"@bk.ru")
                .withPassword("Qwerty$4");
        app.userHelper().openLoginForm();
        app.userHelper().fillLoginForm(user);;
        app.userHelper().sumitRegistration();
        String message = app.userHelper().getText(By.xpath("//h1[.=' No Contacts here!']"));
        Assert.assertEquals(message, "No Contacts here!");
        app.userHelper().logout();
    }

    @Test
    public void registrationTestNegativeWithWrongPassword(){
        int i = (int) (System.currentTimeMillis()/3600%1000);
        User user = new User()
                .withEmail("skelon"+i+"@bk.ru")
                .withPassword("Qwerty04");
        app.userHelper().pause(1000);
        app.userHelper().openLoginForm();
        app.userHelper().fillLoginForm(user);;
        app.userHelper().sumitRegistration();
    //    app.userHelper().alert();
        Assert.assertTrue(app.userHelper().isAlertPresent());
    }
    @AfterMethod
    public void postCondition(){
       if(!app.userHelper().isNoLogined()){
            app.userHelper().logout();
        }
    }
}
