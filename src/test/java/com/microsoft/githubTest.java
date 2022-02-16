package com.microsoft;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class githubTest {

    String REPO = "eroshenkoam/allure-example";


    @Test
    @DisplayName("Видиен issues")

    public void ChekInfoXbox () {
        open("https://github.com/");
        $(".header-search-input").click();
        $(".header-search-input").setValue("eroshenkoam/allure-example");
        $(".header-search-input").submit();
        $(linkText("eroshenkoam/allure-example")).click();
        $(partialLinkText("Issues")).click();
        $(withText("#0")).should(Condition.visible);
    }
    @Test
    @DisplayName("Лямда тест")

    public void labmdaTest() {
        step("Открываем главную", () -> {
            open("https://github.com/");
        });
        step("Ищем репо" + REPO, () -> {
            $(".header-search-input").click();
            $(".header-search-input").setValue(REPO);
            $(".header-search-input").submit();
        });
        step("Переходим в репо" + REPO, () -> {
            $(linkText(REPO)).click();
        });
        step("Открываем issues", () -> {
            $(partialLinkText("Issues")).click();
        });
        step("Убедиться,что есть номер Issues", () -> {
            Allure.addAttachment("Page source", "text/html", WebDriverRunner.source(), "html");
            $(withText("#0")).should(Condition.visible);

        });

    }
}