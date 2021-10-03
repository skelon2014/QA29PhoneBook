package application;

import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserHelper extends HelperBase {


    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public boolean isLogged() {
        return wd.findElements(By.xpath("//button[.='Sign Out']")).size() > 0;
    }

    public void logout() {
        click(By.xpath("//button[.='Sign Out']"));
    }

    public void openLoginForm() {
        click(By.xpath("//a[@href='/login']"));
    }

    public void fillLoginForm(User user) {
        type(By.xpath("//input[@placeholder='Email']"), user.getEmail());
        type(By.xpath("//input[@placeholder='Password']"), user.getPassword());

    }

    public void submitLogin() {
        click(By.xpath("//button[.=' Login']"));
    }


    public void alert() {
        new WebDriverWait(wd, 10).until(ExpectedConditions.alertIsPresent());
        wd.switchTo().alert().accept();
    }

    public boolean isNoLogined() {
        return wd.findElements(By.xpath("//a[.='LOGIN']")).size() > 0;
    }

    public void sumitRegistration() {
        click(By.xpath("//button[.=' Registration']"));
    }

    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        submitLogin();
        pause(1000);
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(wd, 15).until(ExpectedConditions.alertIsPresent());
        //System.out.println(alert.getText());
        alert.accept();//didmiss//sendKeys(text)//getText()

        if (alert == null) {
            return false;
        } else {
            return true;
        }

    }


}
