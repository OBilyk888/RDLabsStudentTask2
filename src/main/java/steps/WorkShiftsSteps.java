package steps;

import grids.WorkShiftGrid;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import pageComponents.AddWorkShiftModalWindow;
import pageComponents.FilterUsersModalWindow;
import pageComponents.TimePicker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static utils.SessionVariables.FILTER_USERS_WINDOW;
import static utils.SessionVariables.WORK_SHIFT_MODAL_WINDOW;

@Getter
@Slf4j
public class WorkShiftsSteps extends DefaultStepsData {

    @Step
    public List<WorkShiftGrid> getWorkShiftGrid() {
        log.info("Getting Work Shift table from Work Shifts page");
        return new WorkShiftGrid().getAllItems(getDriver());
    }

    @Step
    public List<String> checkWorkShiftColumn(){

        List<String> valuesOfWorkShiftColumn = new ArrayList<>();
        List<WorkShiftGrid> allItems = getWorkShiftGrid();

        for (WorkShiftGrid oneItem : allItems) {
            if (oneItem.getWorkShift().equals("General") || oneItem.getWorkShift().equals("Twilight")) {
                valuesOfWorkShiftColumn.add(oneItem.getWorkShift());
            }
        }

        return valuesOfWorkShiftColumn;
    }

    @Step
    public void clickOnAddWorkShiftButton() {
        log.info("Clicking on the [Add work shift] button");
        workShiftPage.getAddWorkShiftButton().waitUntilClickable().click();
        WORK_SHIFT_MODAL_WINDOW.put(new AddWorkShiftModalWindow(workShiftPage.getAddWorkShiftWindow()));

    }

    @Step
    public void clickOnSaveButton(){
        log.info("Clicking on the [Save] button");
        workShiftPage.getSaveButton().waitUntilClickable().click();
    }

    @Step
    public String msgUnderWorkShift(){
        AddWorkShiftModalWindow addWorkShiftModalWindow = WORK_SHIFT_MODAL_WINDOW.get();
        return addWorkShiftModalWindow.getAddWorkShiftModal().findBy(By.xpath("//span[contains(text(),'Required')]")).getText();
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
