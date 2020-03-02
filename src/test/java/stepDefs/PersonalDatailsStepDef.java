package stepDefs;

import com.google.common.collect.Ordering;
import emuns.ItemsSelect;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import steps.DefaultStepsData;
import steps.PersonalDetailsSteps;

import java.util.List;

import static utils.DateUtils.*;
import static utils.SessionVariables.DATE_OF_BIRTH;

public class PersonalDatailsStepDef extends DefaultStepsData {

    @Steps
    PersonalDetailsSteps personalDetailsSteps;

    @When("I click on $text radio button")
    public void checkGenderButton(String gender) {
        personalDetailsSteps.checkGenderButton(gender);
    }

    @Then("I save current Date of Birth to session")
    public void saveCurentDateOfBirthToSession() {
        DATE_OF_BIRTH.put(personalDetailsSteps.getValueFromDateOfBirthField());
    }

    @When("I change Date of Birth added 1 day to old date")
    public void changeDateOfBirth() {
        String currentDate = personalDetailsSteps.getValueFromDateOfBirthField();
        String updatedDate = getDateInFutureOrPastFromNow(DATEPATTERN, 1, currentDate);
        personalDetailsSteps.enterDateIntoDateBirthField(updatedDate);
    }

    @Then("Date of Birth field contains old date (date from session)")
    public void checkThatDateOfBirthNotChange() {
        String currentDate = personalDetailsSteps.getValueFromDateOfBirthField();
        softly.assertThat(currentDate).as("After refreshing Date of Birth change").isEqualTo(DATE_OF_BIRTH.get());
    }

    @When("I click on save button in Personal Details form")
    public void clickOnTheSaveButton() {
        personalDetailsPage.getButtonSave().submit();
    }

    @Then("I check that all countries in Nationality select box ordered by name asc")
    public void checkOrderingInNationalitySelectBox() {
        List<String> optionsFromNationalitySelect = personalDetailsSteps.getOptionsFromNationalitySelect();
        boolean isSorted = Ordering.natural().isOrdered(optionsFromNationalitySelect);
        softly.assertThat(isSorted).as("Wrong ordering inside select box").isTrue();
    }

    @When("I under Gender label I set $text radio button as checked")
    public void setMaleRadioButton(String gender) {
        personalDetailsPage.clickOnGenderRadioButton(gender);
    }

    @Then("$radioButtonName radio button is $condition")
    public void checkThatRadioButtonUnchecked(String radioButtonName, String condition) {
        if (condition.contains("not")) {
            softly.assertThat(personalDetailsSteps.checkSelectedGenderRadioButton(radioButtonName))
                    .as("After checking whether the radio button is not selected").isFalse();
        } else {
            softly.assertThat(personalDetailsSteps.checkSelectedGenderRadioButton(radioButtonName))
                    .as("After checking whether the radio button is selected").isTrue();
        }
    }

    @When("I set Data of Birth as tomorrow data")
    public void setDataOfBirthAsTommorowDate() {
        String updatedDate = getDateInFutureOrPastFromNow(DATEPATTERN, 1);
        personalDetailsSteps.enterDateIntoDateBirthField(updatedDate);
    }

    @When("I click on button Save in Personal Details form")
    @Then("I click on button Save in Personal Details form")
    public void clickOnTheButtonSave(){
        personalDetailsPage.getButtonSave().submit();
    }

    @Then("I check that error message with text $Should_be_on_or_before_today appears under Date of Birth field")
    public void checkThatErrorMessageContains(String message) {
        softly.assertThat(personalDetailsPage.getErrorMessage().getText()).isEqualTo(message);
    }

    @Then("I check that EEO Race and Ethnicity select has NO value by default")
    public void checkThatEEORaceAndEthnicityIsDefault() {
        softly.assertThat(personalDetailsSteps.getDefaultEEORaceAndEthnicityStatus()).isEqualTo(ItemsSelect.DEFAULT_VALUE.value);
    }

    @When("I click on Save button in Personal Details form")
    public void clickOnTheSaveButtonAC_6() {
        personalDetailsPage.getButtonSave().submit();
    }

    @Then("I check that error message with text $Required appears under EEO Race and Ethnicity field")
    public void checkThatRequiredAppearsUnderEEORace(String text) {
        softly.assertThat(personalDetailsSteps.getMessageFromEEORaceAndEthnicity()).isEqualTo((text));
    }

}