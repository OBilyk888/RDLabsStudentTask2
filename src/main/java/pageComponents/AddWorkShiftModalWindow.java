package pageComponents;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

@Getter
@Slf4j
public class AddWorkShiftModalWindow {

    private WebElementFacade addWorkShiftModal;
    private WebElementFacade saveButton;
    private WebElementFacade cancelButton;
    private WebElementFacade workShiftNameInputField;
    private WebElementFacade fromInputField;
    private WebElementFacade fromClockIcon;
    private WebElementFacade toInputField;
    private WebElementFacade toClockIcon;
    private WebElementFacade hoursPerDayInputField;
    private WebElementFacade buttonTimePicker;
    private WebElementFacade bottomButtonTimePicker;
    private WebElementFacade hourPerDay;


    public AddWorkShiftModalWindow(WebElementFacade addWorkShiftModal) {
        this.addWorkShiftModal = addWorkShiftModal;
        this.saveButton = addWorkShiftModal.find(By.xpath(".//a[text()='Save']"));
        this.cancelButton = addWorkShiftModal.find(By.xpath(".//a[text()='Cancel']"));
        this.workShiftNameInputField = addWorkShiftModal.find(By.xpath(".//input[@id='name']"));
        this.fromInputField = addWorkShiftModal.find(By.xpath(".//input[@id='start_time']"));
        this.fromClockIcon = fromInputField.find(By.xpath("./..//i[contains(@class,'time-picker-open-icon')]"));
        this.buttonTimePicker = addWorkShiftModal.find(By.xpath("//sf-decorator[2]//div[1]//span[1]//span[1]//i[1]"));
        this.bottomButtonTimePicker = addWorkShiftModal.find(By.xpath("//*[@id=\"modal1\"]/form/div[1]/div/materializecss-decorator[1]/div/sf-decorator[3]/div/span[1]/span[1]/i"));
        this.hourPerDay = addWorkShiftModal.find(By.xpath("//*[@id=\"hoursPerDay\"]"));
    }

    public void clickOnTheSafeButton() {
        log.info("Click on the save button");
        this.saveButton.waitUntilEnabled().waitUntilClickable().click();
    }

    public void clickOnTheTimePicker() {
        log.info("Open Time Picker");
        this.buttonTimePicker.waitUntilEnabled().waitUntilClickable().click();
    }

    public void clickOnTheBottomButtonTimePicker() {
        log.info("Open Time Picker");
        this.bottomButtonTimePicker.waitUntilEnabled().waitUntilClickable().click();
    }

}
