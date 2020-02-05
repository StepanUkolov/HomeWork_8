package company.steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;

import java.util.List;
import java.util.Map;

/** ------------ СЦЕНАРИЙ ------------
 * 1) перейти на https://www.sberbank.ru/person
 *
 * 2) В верхнем меню "навестись" на Ипотека - дождаться открытия выпадающего меню
 * и выбрать "Ипотека на готовое жилье"
 *
 * 3) Заполнить поля
 * • Стоимость недвижимости 5 180 000 ₽
 * • Первоначальный взнос 3 058 000 ₽
 * • Срок кредита 30 лет
 * • Снять галочку - есть зарплатная карта сбербанка
 * • дождаться появления "есть возможность подтвердить доход справкой"
 * • поставить галочку "молодая семья"
 *
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

    @Дано("^в меню доступны пункты$")
    public void вМенюДоступныПункты(DataTable arg) {
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        System.out.println(table.get(0).get("Название"));
        System.out.println(table.get(1).get("Название"));
        System.out.println(table.get(2).get("Название"));
    }

    @When("^выбран пункт меню \"(.+)\"$")
    public void selectMenuItem(String itemName){
        mainPageSteps.selectMenuItem(itemName);
    }

    @When("^выбран вид ипотеки \"(.+)\"$")
    public void selectSubMenuItem(String itemName){
        mainPageSteps.selectSubMenuItem(itemName);
    }

    @Когда("^заполняются поля$")
    public void fillFields(DataTable testData){
        baseSteps.goToCalculatorFrame();
        testData.asMap(String.class, String.class)
                .forEach((field, value) -> sendAppSteps.fillField(field, value));
        baseSteps.exitCalculatorFrame();
    }

    @Когда("^устанавливаются селекторы$")
    public void setSelectors(DataTable testSelectorData){
        baseSteps.scrollToSelectors();
        baseSteps.goToCalculatorFrame();
        testSelectorData.asMap(String.class, String.class)
                .forEach((selector, value) -> sendAppSteps.setSelector(selector, value));
        baseSteps.exitCalculatorFrame();
    }

    @Тогда("^проверяем значение поля Сумма кредита (.*)$")
    public void checkAmountOfCredit(String amountOfCredit){
        baseSteps.goToCalculatorFrame();
        mortgagePageSteps.checkAmountOfCredit(amountOfCredit);
        baseSteps.exitCalculatorFrame();
    }

    @Тогда("^проверяем значение поля Ежемесячный платеж (.*)$")
    public void checkMonthlyPayment(String monthlyPayment){
        baseSteps.goToCalculatorFrame();
        mortgagePageSteps.checkMonthlyPayment(monthlyPayment);
        baseSteps.exitCalculatorFrame();
    }

    @Тогда("^проверяем значение поля Необходимый доход (.*)$")
    public void checkRequiredIncome(String requiredIncome){
        baseSteps.goToCalculatorFrame();
        mortgagePageSteps.checkRequiredIncome(requiredIncome);
        baseSteps.exitCalculatorFrame();
    }

    @Тогда("^проверяем значение поля Процентная ставка (.*)$")
    public void checkRate(String rate){
        baseSteps.goToCalculatorFrame();
        mortgagePageSteps.checkRate(rate);
        baseSteps.exitCalculatorFrame();
    }

}
