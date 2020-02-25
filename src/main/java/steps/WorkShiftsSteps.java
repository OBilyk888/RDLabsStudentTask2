package steps;

import grids.WorkShiftGrid;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.annotations.Step;
import pageComponents.AddWorkShiftModalWindow;
import pageComponents.TimePicker;

import java.util.List;

//@Getter
@Slf4j
public class WorkShiftsSteps extends DefaultStepsData {

    @Step
    public List<WorkShiftGrid> getWorkShiftGrid() {
        log.info("Getting Work Shift table from Work Shifts page");
        return new WorkShiftGrid().getAllItems(getDriver());
    }

    @Step
    public void clickOnAddWorkShiftButton() {
        log.info("Clicking on the [Add work shift] button");
        workShiftPage.getAddWorkShiftButton().waitUntilClickable().click();
    }

    @Step
    public void clickOnWorkShiftButton(){
        log.info("Clicking on the [Work shift] button");
        workShiftPage.getClickOnWorkShiftButton().waitUntilClickable().click();
    }

    @Step
    public void clickOnGeneralButton() {
        log.info("Clicking on the [General] button");
        workShiftPage.getClickOnGeneralButton().waitUntilClickable().click();
    }

    @Step
    public void clickOnTwillightButton(){
        workShiftPage.getClickOnTwillightButton().waitUntilClickable().click();
    }

    @Step
    private AddWorkShiftModalWindow getAddWorkShiftModalWindow() {
        return new AddWorkShiftModalWindow(workShiftPage.getAddWorkShiftWindow());
    }

    @Step
    private TimePicker getTimePickerElement() {
        return new TimePicker(workShiftPage.getTimePickerLocator());
    }
}
