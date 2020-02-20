package stepDefs;

import com.google.common.collect.Ordering;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import steps.DefaultStepsData;
import steps.PersonalDetailsSteps;

import java.util.List;

import static utils.DateUtils.DATEPATTERN_MY;
import static utils.DateUtils.getDateInFutureOrPastFromNow;
import static utils.SessionVariables.DATE_OF_BIRTH;

public class PersonalDatailsStepDef extends DefaultStepsData {

    @Steps
    PersonalDetailsSteps personalDetailsSteps;

    @Then("I save current Date of Birth to session")
    public void saveCurentDateOfBirthToSession() {
        DATE_OF_BIRTH.put(personalDetailsSteps.getValueFromDateOfBirthField());
    }

    @When("I change Date of Birth added 1 day to old date")
    public void changeDateOfBirth() {
        String currentDate = personalDetailsSteps.getValueFromDateOfBirthField();
        String updatedDate = getDateInFutureOrPastFromNow(DATEPATTERN_MY, 1, currentDate);
        personalDetailsSteps.enterDateIntoDateBirthField(updatedDate);
    }

    @Then("Date of Birth field contains old date (date from session)")
    public void checkThatDateOfBirthNotChange() {
        String currentDate = personalDetailsSteps.getValueFromDateOfBirthField();
        softly.assertThat(currentDate).as("After refreshing Date of Birth change").isEqualTo(DATE_OF_BIRTH.get());
    }

    @Then("I check that all countries in Nationality select box ordered by name asc")
    public void checkOrderingInNationalitySelectBox() {
        List<String> optionsFromNationalitySelect = personalDetailsSteps.getOptionsFromNationalitySelect();
        boolean isSorted = Ordering.natural().isOrdered(optionsFromNationalitySelect);
        softly.assertThat(isSorted).as("Wrong ordering inside select box").isTrue();
    }

    @When("I under Gender label I set Male radio button as checked")
    public void setMaleRadioButton() {
        personalDetailsPage.getMaleRadioButton().waitUntilClickable().click();
    }

    @Then("I check that Female radio button is unchecked")
    public void checkThatFemaleIsUnchecked() {
        softly.assertThat(personalDetailsSteps.getFemaleButtonBooleanAttribute()).isEqualTo(false);
    }

    @When("I set Female radio button as checked")
    public void setFemaleRadioButton() {
        personalDetailsPage.getFemaleRadioButton().waitUntilClickable().click();
    }

    @Then("I check that Male radio button is unchecked")
    public void checkThatMaleIsUnchecked() {
        softly.assertThat(personalDetailsSteps.getMaleButtonBooleanAttribute()).isEqualTo(false);
    }
}