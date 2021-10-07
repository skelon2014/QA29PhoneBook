package application;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {
        click(By.xpath("//a[@href='/add']"));
    }

    public void fillContactForm(Contact contact) {

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

    public void removeOneContact() {
            WebElement contact = wd.findElement(By.cssSelector(".contact-item_card__2SOIM"));
                 //   ("div[class='contact-page_leftdiv__yhyke'] div div:nth-child(1)"));
            if(contact != null) {
                contact.click();
                click(By.xpath("//button[.='Remove']"));
                System.out.println("Removed contact: " + contact.getText());
                pause(500);
            }
    }

    public void removeAllContacts() {
        while (wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size() > 0) {
            removeOneContact();
            pause(500);
        }
    }

    public int sizeOfContacts() {
        return wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
    }
}
