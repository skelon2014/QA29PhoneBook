package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase {
    @BeforeClass(alwaysRun = true)
    public void preCondition() {
        if (app.userHelper().isNoLogined()) {
            app.userHelper().login(new User().withEmail("kselon+4@bk.ru").withPassword("Qwerty$4"));
        }
    }

    @Test(priority = 1,groups = {"web","one"})
    public void removeOneContact() {
        int sizeBefore = app.contactHelper().sizeOfContacts();
        app.contactHelper().removeOneContact();
        int sizeAfter = app.contactHelper().sizeOfContacts();
        Assert.assertTrue(sizeBefore - sizeAfter ==1);
    }

   // @Test(dependsOnMethods = {"removeOneContact"})
    @Test(dependsOnGroups = {"web"},alwaysRun = true)
    public void removeAllContacts() {
        app.contactHelper().removeAllContacts();
        String message = app.userHelper().getText(By.xpath("//h1[.=' No Contacts here!']"));
        Assert.assertEquals(message, "No Contacts here!");
    }
    @AfterClass
    public void postCondition(){
        app.userHelper().logout();
    }
}
