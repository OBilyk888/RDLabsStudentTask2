package stepDefs;

import net.thucydides.core.annotations.Steps;
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

    @When("I check that rows with values $General, $Twilight in WorkShift column are shown by default")
    public void checkThatRowsWithValuesAreShown(String general, String twillight){

        softly.assertThat(workShiftPage.getClickOnGeneralButton().getText().equals(general));
        softly.assertThat(workShiftPage.getClickOnTwillightButton().getText().equals(twillight));

    }

}
