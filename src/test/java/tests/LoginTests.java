package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(app.userHelper().isLogged()){
            app.userHelper().logout();
        }
    }
    @Test
    public void loginTestPositive(){
        User user = new User()
                .withEmail("skelon+4@bk.ru")
                .withPassword("Qwerty$4");
        app.userHelper().openLoginForm();
        app.userHelper().fillLoginForm(user);
        app.userHelper().submitLogin();
        String message = app.userHelper().getText(By.xpath("//a[.='ADD']"));
        Assert.assertEquals(message, "ADD");
    }
    @Test
    public void loginTestNegativeWithWrongPassword(){
        User user = new User()
                .withEmail("skelon+4@bk.ru")
                .withPassword("Qwerty04");
        app.userHelper().openLoginForm();
        app.userHelper().fillLoginForm(user);
        app.userHelper().submitLogin();
        app.userHelper().alert();
        Assert.assertTrue(app.userHelper().isNoLogined());

    }
    @AfterMethod
    public void postCondition(){
        if(!app.userHelper().isNoLogined()){
           app.userHelper().logout();
        }
    }


}
