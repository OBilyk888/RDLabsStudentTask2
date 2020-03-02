package stepDefs;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import steps.DefaultStepsData;
import steps.WorkShiftsSteps;

public class WorkShiftsStepDefs extends DefaultStepsData {

    @Steps
    private WorkShiftsSteps workShiftsSteps;

    @When("I click on Add Work Shift button")
    public void clickOnAddWorkShiftButton() {
        workShiftsSteps.clickOnAddWorkShiftButton();
    }

    @When("I Click on Save button in Add Work Shift window")
    public void clickOnSaveButton() {
        workShiftsSteps.clickOnSaveButton();
    }

    @When("I check that rows with values $General, $Twilight in WorkShift column are shown by default")
    public void checkThatRowsWithValuesAreShown(String general, String twilight) {

//        softly.assertThat(workShiftPage.getClickOnGeneralButton().getText().equals(general));
//        softly.assertThat(workShiftPage.getClickOnTwillightButton().getText().equals(twillight));

        softly.assertThat(workShiftsSteps.checkWorkShiftColumn()).contains(general, twilight);
    }

    @Then("Check that $Required error message is shown under Work Shift field")
    public void checkRequiredErrMsgIsShown(String requiredMsg) {
        softly.assertThat(workShiftsSteps.msgUnderWorkShift()).isEqualTo(requiredMsg);
    }

    @Then("I using time picker set $hours : $minutes value into $field filed")
    public void setHoursAndMinutes(String hours, String minutes, String field) {
        workShiftsSteps.setTime(hours, minutes, field);
    }

    @Then("I check that $time value calculated in Hours Per Day field")
    public void checkCalculatedTime(String t) {
        softly.assertThat(workShiftsSteps.getCalculateHourPerDay().equals(t));
    }
}
