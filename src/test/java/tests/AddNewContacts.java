package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class AddNewContacts extends TestBase {
    @BeforeMethod
    public void preCondition() {
        if (app.userHelper().isNoLogined()) {
            app.userHelper().login(new User().withEmail("kselon+4@bk.ru").withPassword("Qwerty$4"));
        }
    }

    @Test(invocationCount = 5)
    public void AddNewContact() {
        int i = (int) (System.currentTimeMillis() / 1000 % 3600);

        Contact contact = Contact.builder()
                .name("Masha" + i)
                .lastName("Rasputina")
                .phone("12345678" + i)
                .email("masha" + i + "@bk.ru")
                .address("Rehovot, Herzl " + i)
                .description("Friend").build();
        app.contactHelper().openContactForm();
        app.contactHelper().fillContactForm(contact);
        app.contactHelper().saveContact();
        Assert.assertTrue(app.contactHelper().isContactAdded(contact.getPhone()));
    }
    @AfterMethod
    public void postCondition(){
        app.userHelper().logout();
    }
}

