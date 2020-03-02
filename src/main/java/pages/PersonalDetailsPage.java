package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

@Getter
@Slf4j
public class PersonalDetailsPage extends BasePage {

    public final static String MALE = "Male";
    public final static String FEMALE = "Female";

    @FindBy(css = "#personal_details_tab h4")
    private WebElementFacade personalDetailsHeader;

    @FindBy(css = "#emp_birthday")
    private WebElementFacade dateOfBirthInputField;

    @FindBy(xpath = "//div[@id='nation_code_inputfileddiv']//input")
    private WebElementFacade nationalitySelect;

    @FindBy(xpath = "//div[@id='eeo_race_ent_inputfileddiv']//input")
    private WebElementFacade eeoRaceAndEthnicitySelect;

    @FindBy(xpath = "//label[@for='emp_gender_1']")
    private WebElementFacade maleRadioButton;

    @FindBy(xpath = "//label[@for='emp_gender_2']")
    private WebElementFacade femaleRadioButton;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElementFacade buttonSave;

    @FindBy(xpath = "//span[@class='help-block-message']")
    private WebElementFacade errorMessage;

    @FindBy(xpath = "//*[@id='eeo_race_ent_inputfileddiv']/div/input")
    private WebElementFacade EEORaceAndEthnicity;

    @FindBy(xpath = "//*[@id='eeo_race_ent_inputfileddiv']/span")
    private WebElementFacade EEORaceAndEthnicityMessage;

    public void enterDateOfBirth(String date) {
        log.info(String.format("Putting %s date into [Date of birth] field", date));
        dateOfBirthInputField.clear();
        dateOfBirthInputField.waitUntilEnabled().sendKeys(date);
    }

    public void setDateOfBirthInputField(WebElementFacade dateOfBirthInputField) {
        this.dateOfBirthInputField = dateOfBirthInputField;
        dateOfBirthInputField.clear();
        dateOfBirthInputField.waitUntilEnabled().sendKeys();
    }

    public void clickOnGenderRadioButton(String gender) {
        log.info("set gender radio button checked");
        switch (gender) {
            case MALE:
                maleRadioButton.waitUntilClickable().click();
                break;
            case FEMALE:
                femaleRadioButton.waitUntilClickable().click();
                break;
            default:
                throw new IllegalStateException("Invalid gender");
        }
    }

    public boolean checkSelectedGenderRadioButton(String gender) {
        log.info("check selected gender radio button");
        switch (gender) {
            case MALE:
                return Boolean.parseBoolean(maleRadioButton.findElement(By.xpath("./../input")).getAttribute("checked"));
            case FEMALE:
                return Boolean.parseBoolean(femaleRadioButton.findElement(By.xpath("./../input")).getAttribute("checked"));
            default:
                throw new IllegalStateException("Invalid gender");
        }
    }

}
