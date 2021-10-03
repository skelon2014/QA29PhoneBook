package application;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {
        click(By.xpath("//a[@href='/add']"));
    }

    public void fillContactForm(Contact contact) {
        //  int i = (int) (System.currentTimeMillis()/1000%3600);
        type(By.cssSelector("input[placeholder='Name']"), contact.getName());
        type(By.cssSelector("input[placeholder='Last Name']"), contact.getLastName());
        type(By.cssSelector("input[placeholder='Phone']"), contact.getPhone());
        type(By.cssSelector("input[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("input[placeholder='Address']"), contact.getAddress());
        type(By.cssSelector("input[placeholder='description']"), contact.getDescription());
    }

    public void saveContact() {
        pause(1000);
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('button b').click();");
    }

    public boolean isContactAdded(String phone) {
        List<WebElement> contacts = wd.findElements(By.xpath("//h3"));
        for (WebElement element : contacts) {
            if (element.getText().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    List<WebElement> contactsBefore = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
    int numberBefore = contactsBefore.size();


    public void removeOneContact() {
        List<WebElement> contactsBefore = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        System.out.println(contactsBefore.size());
        if (contactsBefore != null) {
          /*  contactsBefore.get(0).click();
            click(By.xpath("//button[.='Remove']"));
            pause(1000);
            System.out.println("Remove contact: " + contactsBefore.get(0).getText());*/
            WebElement contact = wd.findElement(By.cssSelector
                    ("div[class='contact-page_leftdiv__yhyke'] div div:nth-child(1)"));
          //  if(contact != null) {
                contact.click();
                click(By.xpath("//button[.='Remove']"));
                System.out.println("Removed contact: " + contact.getText());
         //   }
        }
    }

    public boolean isContactRemoved() {
        List<WebElement> contactsAfter = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        System.out.println(contactsAfter.size());
        return contactsBefore.size() - contactsAfter.size() != 0;
    }

    public void removeAllContacts() {
        while (wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size() > 0) {
            removeOneContact();
            pause(500);
        }
    }
}
