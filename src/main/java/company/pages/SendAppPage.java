package company.pages;

import company.steps.BaseSteps;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SendAppPage extends BasePageObject {

    public SendAppPage() {
        PageFactory.initElements(BaseSteps.getDriver(), this);
    }

    @FindBy(xpath = "//div[text()='Стоимость недвижимости']/..//input")
    private WebElement realtyPrice;

    @FindBy(xpath = "//div[text()='Первоначальный взнос']/..//input")
    private WebElement initialPayment;

    @FindBy(xpath = "//div[text()='Срок кредита']/..//input")
    private WebElement creditPeriod;

    @FindBy(xpath = "//div[text()='Есть зарплатная карта Сбербанка']/..//label")
    private WebElement salaryCard;

    @FindBy(xpath = "//div[text()='Есть зарплатная карта Сбербанка']/..//label/input")
    private WebElement salaryCardInput;

    @FindBy(xpath = "//div[text()='Есть возможность подтвердить доход справкой']/..//label")
    private WebElement confirmIncome;

    @FindBy(xpath = "//div[text()='Есть возможность подтвердить доход справкой']/..//label/input")
    private WebElement confirmIncomeInput;

    @FindBy(xpath = "//div[text()='Молодая семья']/..//label")
    private WebElement youngFamily;

    @FindBy(xpath = "//div[text()='Молодая семья']/..//label/input")
    private WebElement youngFamilyInput;


    public void fillField(String fieldName, String value) {
        switch (fieldName) {
            case "Стоимость недвижимости":
                fillField(realtyPrice, value);
                break;
            case "Первоначальный взнос":
                fillField(initialPayment, value);
                break;
            case "Срок кредита":
                fillField(creditPeriod, value);
                break;
            default:
                throw new AssertionError("Поле '" + fieldName + "' не объявлено на странице");
        }
    }

    public void setSelector(String selectorName, String value) {
        boolean selectorValue = true;

        switch (value) {
            case "on":
                selectorValue = true;
                break;
            case "off":
                selectorValue = false;
                break;
            default:
                try {
                    throw new Exception("Не верно введено значение (введите: on/off)");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        switch (selectorName) {
            case "Есть зарплатная карта Сбербанка":
                setSelector(salaryCard, selectorValue ^ salaryCardInput.isSelected());
                break;
            case "Есть возможность подтвердить доход справкой":
                setSelector(confirmIncome, selectorValue ^ confirmIncomeInput.isSelected());
                break;
            case "Молодая семья":
                setSelector(youngFamily, selectorValue ^ youngFamilyInput.isSelected());
                break;
            default:
                throw new AssertionError("Пункт '" + selectorName + "' не объявлен на странице");
        }
    }


}
