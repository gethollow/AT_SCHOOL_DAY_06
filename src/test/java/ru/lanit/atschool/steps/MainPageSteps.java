package ru.lanit.atschool.steps;

import io.cucumber.java.ru.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.lanit.atschool.webdriver.WebDriverManager;

import java.util.Map;

public class MainPageSteps {
    WebDriver driver = WebDriverManager.getDriver();

    @Пусть("открыт браузер и введен адрес \"(.*)\"$")
    public void openedBrowserAndEnteredUrl(String url) {
        driver = WebDriverManager.getDriver();
        driver.get(url);
    }

    @И("пользователь открыл форму авторизации")
    public void userEnters() {
        driver.findElement(By.xpath("//*[@id=\"user-menu-mount\"]/div/button[1]")).click();
    }


    @Тогда("пользователь вводит учетные данные")
    public void userEnteredCredentials(Map<String, String> params) {
        driver.findElement(By.xpath("//*[@id=\"id_username\"]")).sendKeys(params.get("login"));
        driver.findElement(By.xpath("//*[@id=\"id_password\"]")).sendKeys(params.get("password"));
    }


    @Если("пользователь \"(.*)\" авторизован")
    public void userSucces(String username) {
        driver.findElement(By.xpath(""));
    }


}
