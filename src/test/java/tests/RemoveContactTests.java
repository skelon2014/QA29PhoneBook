package tests;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        if (app.userHelper().isNoLogined()) {
            app.userHelper().login(new User().withEmail("kselon+4@bk.ru").withPassword("Qwerty$4"));
        }
    }

    @Test
    public void remove1OneContact() {
        app.contactHelper().removeOneContact();
        Assert.assertTrue(app.contactHelper().isContactRemoved());
    }

    @Test
    public void removeAllContacts() {
        app.contactHelper().removeAllContacts();
        String message = app.userHelper().getText(By.xpath("//h1[.=' No Contacts here!']"));
        Assert.assertEquals(message, "No Contacts here!");
    }


}
