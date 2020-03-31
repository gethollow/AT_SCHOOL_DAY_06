package ru.lanit.atschool.steps;

import io.cucumber.java.ru.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.lanit.atschool.webdriver.WebDriverManager;

import java.util.Map;

public class MainPageSteps {
    private WebDriver driver = WebDriverManager.getDriver();
    private String current_url;

    @Пусть("открыт браузер и введен адрес \"(.*)\"$")
    public void openedBrowserAndEnteredUrl(String url) {
        driver = WebDriverManager.getDriver();
        driver.get(url);
        System.out.println("Браузер открылся");
    }

    @И("пользователь кликнул на вкладку Категории")
    public void goToCategories() {
        driver.findElement(By.linkText("Категории")).click();
        System.out.println("Произошел переход на вкладку Категории");
    }

    @Тогда("проверим, что перешли на вкладку Категории")
    public void checkCategories() {
        current_url = driver.getCurrentUrl();
        Assert.assertEquals("https://dev.n7lanit.ru/categories/",current_url);
        System.out.println("Проверка переходна на вкладку Категории");
    }


    @И("пользоватль перешел на вкладку Пользователь")
    public void goToUsers() {
        driver.findElement(By.linkText("Пользователи")).click();
        System.out.println("Произошел переход на вкладку Пользователь");
    }

    @Тогда("проверим, что перешли на вкладку Пользователи")
    public void checkUsers() {
        current_url = driver.getCurrentUrl();
        Assert.assertEquals("https://dev.n7lanit.ru/users/active-posters/",current_url);
        System.out.println("Проверка переходна на вкладку Пользователи");
    }

    @И("найдет пользователя в поиске по нику")
    public void searchUser() {
        driver.findElement(By.xpath("//*[@id=\"user-menu-mount\"]//a/i")).click();
        driver.findElement(By.xpath("//*[@id=\"user-menu-mount\"]//input")).sendKeys("E");
        driver.findElement(By.xpath("//a[@href=\"/search/users/?q=E\"]")).click();
        driver.findElement(By.xpath("//div[@class=\"user-card-username\"]/a[text()=\"Eduard\"]")).click();
        System.out.println("Произведен поиск пользователя по имени");
    }

    @Тогда("проверим, что перешли на страницу пользователя")
    public void userEnteredCredentials() {
        current_url = driver.getCurrentUrl();
        Assert.assertEquals("https://dev.n7lanit.ru/u/eduard/7/posts/",current_url);
        System.out.println("Проверка поиска пользователя по нику");
    }


    @И("пользователь открыл форму авторизации")
    public void userEnters() {
        driver.findElement(By.xpath("//*[@id=\"user-menu-mount\"]/div/button[1]")).click();
        System.out.println("Открыта форма авторизации");
    }


    @Тогда("пользователь вводит учетные данные и вошел в систему")
    public void userEnteredCredentials(Map<String, String> params) {
        driver.findElement(By.xpath("//*[@id=\"id_username\"]")).sendKeys(params.get("login"));
        driver.findElement(By.xpath("//*[@id=\"id_password\"]")).sendKeys(params.get("password"));
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
        System.out.println("Пользователь ввел свои данные и нажал кноку Войти");
    }


    @Если("пользователь авторизован")
    public void userSucces() {
        if (current_url.equals("https://dev.n7lanit.ru/?ref=login")) {
            Assert.assertTrue(driver.findElement(By.xpath("//a[@class=\"dropdown-toggle\"][@href=\"/u/eduard/7/\"]")).isDisplayed(), "Авторизация прошла успешно");
            driver.findElement(By.xpath("//a/img[@class=\"user-avatar\"]")).click();
            driver.findElement(By.xpath("//button[text()=\"Выход\"]")).click();
            driver.switchTo().alert().accept();
            System.out.println("Проверка, что пользователь авторизировался в системе");
        }
    }

    @Если("пользователь ввел неверные данные")
    public void userFailure() {
        current_url = driver.getCurrentUrl();
        if(current_url.equals("https://dev.n7lanit.ru")) {
            Assert.assertTrue(driver.findElement(By.xpath("//button[@type=\"button\"][text()=\"Войти\"]")).isDisplayed(), "Данные введены не верно");
            driver.findElement(By.xpath("//*[@id=\"modal-mount\"]/div/div/div/button")).click();
            System.out.println("Проверка, что пользователь ввел неверные данные");
        }
    }

}
