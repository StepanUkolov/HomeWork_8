package company.steps;

import company.pages.MainPage;
import io.qameta.allure.Step;

public class MainPageSteps {

    @Step("Выбран пункт меню {0}")
    public void selectMenuItem(String itemName) {
        new MainPage().selectMenuItem(itemName);
    }

    @Step("Выбран вид ипотеки {0}")
    public void selectSubMenuItem(String itemName) {
        new MainPage().selectSubMenuItem(itemName);
    }
}
