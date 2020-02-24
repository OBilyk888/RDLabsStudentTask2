package steps;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class PersonalDetailsSteps extends DefaultStepsData {

    @Step
    public String getValueFromDateOfBirthField() {
        return personalDetailsPage.getDateOfBirthInputField().getAttribute("value");
    }

    @Step
    public void enterDateIntoDateBirthField(String date) {
        personalDetailsPage.getDateOfBirthInputField().clear();
        personalDetailsPage.enterDateOfBirth(date);
    }


    @Step
    public String getDefaultEEORaceAndEthnicityStatus() {
        return personalDetailsPage.getEEORaceAndEthnicity().getValue();
    }

    @Step
    public String getMessageFromEEORaceAndEthnicity() {
        return personalDetailsPage.getEEORaceAndEthnicityMessage().getText();
    }


    @Step
    public List<String> getOptionsFromNationalitySelect() {
        List<String> nationalityOptions = personalDetailsPage.getNationalitySelect().thenFindAll(By.xpath("./..//li//span"))
                .stream().map(we -> we.getAttribute("innerText")).collect(Collectors.toList());
        return nationalityOptions;
    }

    @Step
    public boolean getFemaleButtonBooleanAttribute() {
        System.out.println("Attribute from Female: " + personalDetailsPage.getFemaleRadioButton().waitUntilEnabled().getAttribute("checked"));
        return Boolean.parseBoolean(personalDetailsPage.getFemaleRadioButton().find(By.xpath("./../input")).waitUntilEnabled().getAttribute("checked"));
    }

    @Step
    public boolean getMaleButtonBooleanAttribute() {
        return Boolean.parseBoolean(personalDetailsPage.getMaleRadioButton().find(By.xpath("./../input")).waitUntilEnabled().getAttribute("checked"));
    }
}
