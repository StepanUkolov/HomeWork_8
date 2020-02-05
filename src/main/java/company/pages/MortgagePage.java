package company.pages;

import company.steps.BaseSteps;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MortgagePage extends BasePageObject {

    public MortgagePage() {
        PageFactory.initElements(BaseSteps.getDriver(), this);
    }



    @FindBy(xpath = "//span[@data-test-id='amountOfCredit']")
    private WebElement amountOfCredit;

    @FindBy(xpath = "//span[@data-test-id='monthlyPayment']")
    private WebElement monthlyPayment;

    @FindBy(xpath = "//span[@data-test-id='requiredIncome']")
    private WebElement requiredIncome;

    @FindBy(xpath = "//span[@data-test-id='rate']")
    private WebElement rate;



    public void checkAmountOfCredit(String amountOfCredit) {
        Assert.assertEquals(
                String.format("Сумма кредита [%s]. Ожидалось - [%s]", this.amountOfCredit.getText(), amountOfCredit),
                amountOfCredit, this.amountOfCredit.getText());
    }

    public void checkMonthlyPayment(String monthlyPayment) {
        Assert.assertEquals(
                String.format("Ежемесячный платеж [%s]. Ожидалось - [%s]", this.monthlyPayment.getText(), monthlyPayment),
                monthlyPayment, this.monthlyPayment.getText());
    }

    public void checkRequiredIncome(String requiredIncome) {
        Assert.assertEquals(
                String.format("Необходимый доход [%s]. Ожидалось - [%s]", this.requiredIncome.getText(), requiredIncome),
                requiredIncome, this.requiredIncome.getText());
    }

    public void checkRate(String rate) {
        Assert.assertEquals(
                String.format("Процентная ставка [%s]. Ожидалось - [%s]", this.rate.getText(), rate),
                rate, this.rate.getText());
    }
}
