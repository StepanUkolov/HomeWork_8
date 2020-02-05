package company.steps;

import company.pages.SendAppPage;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.HashMap;

public class SendAppSteps {

    @Step("Поле \"{0}\" заполняется значением: {1}")
    public void fillField(String field, String value){
        new SendAppPage().fillField(field, value.replaceAll("\\D", ""));
    }

    @Step("Пункт \"{0}\" устанавливается в положение: {1}")
    public void setSelector(String selector, String value){
        new SendAppPage().setSelector(selector, value);
    }


//    @Step("Заполняются поля")
//    public void fillFields(HashMap<String, String> fields){
//        fields.forEach((k, v)-> fillField(k,v));
//    }
//
//    @Step("Устанавливаются селекторы")
//    public void setSelectors(HashMap<String, String> selectors){
//        selectors.forEach((k, v)-> setSelector(k,v));
//    }
}
