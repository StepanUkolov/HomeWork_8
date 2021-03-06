package company.steps;

import cucumber.api.java.en.When;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

/**
 * ------------ СЦЕНАРИЙ ------------
 * 1) перейти на https://www.sberbank.ru/person
 * <p>
 * 2) В верхнем меню "навестись" на Ипотека - дождаться открытия выпадающего меню
 * и выбрать "Ипотека на готовое жилье"
 * <p>
 * 3) Заполнить поля
 * • Стоимость недвижимости 5 180 000 ₽
 * • Первоначальный взнос 3 058 000 ₽
 * • Срок кредита 30 лет
 * • Снять галочку - есть зарплатная карта сбербанка
 * • дождаться появления "есть возможность подтвердить доход справкой"
 * • поставить галочку "молодая семья"
 * <p>
 * Проверить значение полей
 * Сумма кредита        2 122 000 ₽
 * Ежемесячный платеж   17 535 ₽
 * Необходимый доход    29 224 ₽
 * Процентная ставка    11% - тут ошибка (специально)
 */

public class ScenarioSteps {
    MainPageSteps mainPageSteps = new MainPageSteps();
    MortgagePageSteps mortgagePageSteps = new MortgagePageSteps();
    SendAppSteps sendAppSteps = new SendAppSteps();
    BaseSteps baseSteps = new BaseSteps();

    @Когда("^выбран пункт меню \"(.+)\"$")
    public void selectMenuItem(String itemName) {
        mainPageSteps.selectMenuItem(itemName);
    }

    @И("^выбран вид ипотеки \"(.+)\"$")
    public void selectSubMenuItem(String itemName) {
        mainPageSteps.selectSubMenuItem(itemName);
    }

    @Когда("^заполняются поля$")
    public void fillFields(DataTable testData) {
        baseSteps.goToCalculatorFrame();
        testData.asMap(String.class, String.class)
                .forEach((field, value) -> sendAppSteps.fillField((String) field, (String) value));
        baseSteps.exitCalculatorFrame();
    }

    @Когда("^устанавливаются селекторы$")
    public void setSelectors(DataTable testSelectorData) {
        BaseSteps.takeScreenshot();
        baseSteps.scrollToSelectors();
        baseSteps.goToCalculatorFrame();
        testSelectorData.asMap(String.class, String.class)
                .forEach((selector, value) -> sendAppSteps.setSelector((String) selector, (String) value));
        baseSteps.exitCalculatorFrame();
    }

    @Тогда("^проверяем значение поля Сумма кредита (.*)$")
    public void checkAmountOfCredit(String amountOfCredit) {
        baseSteps.goToCalculatorFrame();
        mortgagePageSteps.checkAmountOfCredit(amountOfCredit);
        baseSteps.exitCalculatorFrame();
    }

    @Тогда("^проверяем значение поля Ежемесячный платеж (.*)$")
    public void checkMonthlyPayment(String monthlyPayment) {
        baseSteps.goToCalculatorFrame();
        mortgagePageSteps.checkMonthlyPayment(monthlyPayment);
        baseSteps.exitCalculatorFrame();
    }

    @Тогда("^проверяем значение поля Необходимый доход (.*)$")
    public void checkRequiredIncome(String requiredIncome) {
        baseSteps.goToCalculatorFrame();
        mortgagePageSteps.checkRequiredIncome(requiredIncome);
        baseSteps.exitCalculatorFrame();
    }

    @Тогда("^проверяем значение поля Процентная ставка (.*)$")
    public void checkRate(String rate) {
        baseSteps.goToCalculatorFrame();
        mortgagePageSteps.checkRate(rate);
        baseSteps.exitCalculatorFrame();
    }

}
