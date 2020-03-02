package pageComponents;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

import java.time.Duration;

@Getter
@Slf4j
public class FilterUsersModalWindow {

    private WebElementFacade modalWindow;
    private WebElementFacade userNameInputField;
    private WebElementFacade employeeNameField;
    private WebElementFacade essRole;
    private WebElementFacade adminRole;
    private WebElementFacade superVisorRole;
    private WebElementFacade status;
    private WebElementFacade location;
    private WebElementFacade resetButton;
    private WebElementFacade cancelButton;
    private WebElementFacade searchButton;

    public FilterUsersModalWindow(WebElementFacade modalWindow) {
        this.modalWindow = modalWindow;
        this.userNameInputField = modalWindow.find(By.cssSelector("#systemuser_uname_filter"));
        this.employeeNameField = modalWindow.find(By.cssSelector("#employee_name_filter_value"));
        this.essRole = modalWindow.find(By.xpath("//div[@id='essroles_inputfileddiv']//input"));
        this.adminRole = modalWindow.find(By.xpath("//div[@id='adminroles_inputfileddiv']//input"));
        this.superVisorRole = modalWindow.find(By.xpath("//div[@id='supervisorroles_inputfileddiv']//input"));
        this.status = modalWindow.find(By.xpath("//div[@id='status_inputfileddiv']//input"));
        this.location = modalWindow.find(By.xpath("//div[@id='location_inputfileddiv']//input"));
        this.resetButton = modalWindow.find(By.xpath("//a[@ng-click='modal.reset()']"));
        this.cancelButton = modalWindow.find(By.xpath("//a[@ng-click='modal.cancel()']"));
        this.searchButton = modalWindow.find(By.xpath("//a[@ng-click='modal.search()']"));
    }

    public void changeEmployeeNameTo(String employeeName) {
        log.info("Change Employee Name to " + employeeName);
        this.employeeNameField.waitUntilEnabled().sendKeys(employeeName);
        this.employeeNameField.find(By.xpath("./..//div[contains(@class,'angucomplete-searching')]")).withTimeoutOf(Duration.ofSeconds(15)).waitUntilNotVisible();
        WebElementFacade employeeDropDown = this.employeeNameField.find(By.xpath("./..//div[contains(@class,'angucomplete-row')]"));
        employeeDropDown.waitUntilVisible().waitUntilClickable().click();
        employeeDropDown.waitUntilNotVisible();
    }

    public void changeStatusTo(String status) {
        log.info("Change Status to " + status);
        this.status.waitUntilClickable().click();
        this.status.find(By.xpath("./..//ul//span[text()='" + status + "']")).waitUntilEnabled().waitUntilClickable().click();
    }

    public void changeAdminRoleTo(String adminRole) {
        log.info("Change Admin Role to " + adminRole);
        this.adminRole.waitUntilClickable().click();
        this.adminRole.find(By.xpath("./..//ul//span[text()='" + adminRole + "']")).waitUntilEnabled().waitUntilClickable().click();
    }

    public void clickOnStatusList() {
        log.info("Clicking on the [Status list]");
        this.status.waitUntilEnabled().waitUntilClickable().click();
    }

    public void clickOnSearchButton() {
        log.info("Clicking on the [Search button]");
        this.searchButton.waitUntilEnabled().waitUntilClickable().click();
    }

    public void clickOnCancelButton() {
        log.info("Clicking on the [Cancel button]");
        this.cancelButton.waitUntilEnabled().waitUntilClickable().click();
    }

    public void clickOnResetButton() {
        log.info("Clicking on the [Reset button]");
        this.resetButton.waitUntilEnabled().waitUntilClickable().click();
    }

}
