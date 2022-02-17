package com.microsoft;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class GithubTest {

    private static final String REPO = "eroshenkoam/allure-example";
    private static final int ID = 0;

    @Test
    @DisplayName("Базовый тест")
    public void checkIssues () {
        open("https://github.com/");
        $(".header-search-input").click();
        $(".header-search-input").setValue("eroshenkoam/allure-example");
        $(".header-search-input").submit();
        $(linkText("eroshenkoam/allure-example")).click();
        $(partialLinkText("Issues")).click();
        $(withText("#" + ID)).should(Condition.visible);
    }
    @Test
    @DisplayName("Лямбда")
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

    @DisplayName("Listener")
    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".header-search-input").click();
        $(".header-search-input").sendKeys("eroshenkoam/allure-example");
        $(".header-search-input").submit();

        $(linkText("eroshenkoam/allure-example")).click();
        $(partialLinkText("Issues")).click();
        $(withText("#68")).should(Condition.visible);
    }

    @DisplayName("PageObject")
    @Test
    public void annotatedStepsTest() {
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPO);
        steps.openRepositoryPage(REPO);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ID);
    }

}