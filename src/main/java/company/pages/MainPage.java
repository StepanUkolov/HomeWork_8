package company.pages;

import company.steps.BaseSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePageObject {

    public MainPage() {
        PageFactory.initElements(BaseSteps.getDriver(), this);
    }

    @FindBy(xpath = "//li[@class='lg-menu__item']")
    private WebElement menuItems;

    @FindBy(xpath = "//li[@class='lg-menu__sub-item']")
    private WebElement subMenuItem;

    public void selectMenuItem(String itemName) {
        menuItems.findElement(By.xpath("//span[text()='" + itemName + "']")).click();  // .//span[text()= не работает
    }

    public void selectSubMenuItem(String itemName) {
        subMenuItem.findElement(By.xpath("//a[text()='" + itemName + "']")).click();
    }
}
