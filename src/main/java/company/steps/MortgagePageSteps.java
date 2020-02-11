package company.steps;

import company.pages.MortgagePage;
import io.qameta.allure.Step;

public class MortgagePageSteps {

    @Step("Сумма кредита {0}")
    public void checkAmountOfCredit(String amountOfCredit) {
        new MortgagePage().checkAmountOfCredit(amountOfCredit);
    }

    @Step("Ежемесячный платеж {0}")
    public void checkMonthlyPayment(String monthlyPayment) {
        new MortgagePage().checkMonthlyPayment(monthlyPayment);
    }

    @Step("Необходимый доход {0}")
    public void checkRequiredIncome(String requiredIncome) {
        new MortgagePage().checkRequiredIncome(requiredIncome);
    }

    @Step("Процентная ставка {0}")
    public void checkRate(String rate) {
        new MortgagePage().checkRate(rate);
    }
}
