package steps;

import grids.UsersGrid;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import pageComponents.FilterUsersModalWindow;

import java.util.List;

import static utils.SessionVariables.FILTER_USERS_WINDOW;

@Slf4j
public class UsersSteps extends DefaultStepsData {

    @Step
    public void openFilterWindow() {
        log.info("Opens Filter Users window");
        usersPage.clickOnFilterButton();
        FILTER_USERS_WINDOW.put(new FilterUsersModalWindow(usersPage.getFilterUsersModalWindow()));
    }

    @Step
    public FilterUsersModalWindow getFiltetUsersWindow() {
        return new FilterUsersModalWindow(usersPage.getFilterUsersModalWindow());
    }

    @Step
    public void filterUsersBy(String select, String value) {
        FilterUsersModalWindow filterUsersModalWindow = FILTER_USERS_WINDOW.get();
        switch (select) {
            case "Employee Name":
                log.info("Filtering by Employee Name: " + value);
                filterUsersModalWindow.changeEmployeeNameTo(value);
                break;
            case "Status":
                log.info("Filtering by Status: " + value);
                filterUsersModalWindow.changeStatusTo(value);
                break;
            case "Admin Role":
                log.info("Filtering by Admin Role: " + value);
                filterUsersModalWindow.changeAdminRoleTo(value);
                break;
            default:
                throw new IllegalStateException("Unexpected value");
        }
    }

    @Step
    public String getStatus() {
        FilterUsersModalWindow filterUsersModalWindow = FILTER_USERS_WINDOW.get();
        log.info("Getting Status");
        return filterUsersModalWindow.getStatus().getValue();
    }

    @Step
    public String getAdminRole() {
        FilterUsersModalWindow filterUsersModalWindow = FILTER_USERS_WINDOW.get();
        log.info("Getting Admin Role");
        return filterUsersModalWindow.getAdminRole().getValue();
    }

    @Step
    public void clickOnTheSearchButton() {
        log.info("Clicking on the Search button");
        FilterUsersModalWindow filterUsersModalWindow = FILTER_USERS_WINDOW.get();
        filterUsersModalWindow.clickOnSearchButton();
    }

    @Step
    public void filterUsersByEmployeeName(String employeeName) {
        FilterUsersModalWindow filterUsersModalWindow = FILTER_USERS_WINDOW.get();
        log.info("Filtering by Employee Name: " + employeeName);
        log.info("Typing employee name into [Employee Name] input field");
        filterUsersModalWindow.getEmployeeNameField().waitUntilEnabled().sendKeys(employeeName);

        WebElementFacade employeeDropDown = filterUsersModalWindow.getEmployeeNameField().find(By.xpath("./..//div[contains(@class,'angucomplete-row')]"));
        log.info("Clicking on the autocomplete search result");
        employeeDropDown.waitUntilVisible().waitUntilClickable().click();
        employeeDropDown.waitUntilNotVisible();
    }

    @Step
    public void switchFilter(String filter) {
        FilterUsersModalWindow filterUsersModalWindow = FILTER_USERS_WINDOW.get();
        switch (filter) {
            case "Status":
                filterUsersModalWindow.getStatus().waitUntilEnabled().click();
                filterUsersModalWindow.getStatus().findBy(By.xpath(".//..//ul//span[text()='Disabled']")).waitUntilEnabled().waitUntilClickable().click();
                break;

            case "Admin Role":
                filterUsersModalWindow.getAdminRole().waitUntilEnabled().click();
                filterUsersModalWindow.getAdminRole().findBy(By.xpath("./..//ul//span[text()='Global Admin']")).waitUntilEnabled().waitUntilClickable().click();
                break;
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step
    public List<UsersGrid> getUsersGrid() {
        log.info("Getting [Users] grid");
        return new UsersGrid().getAllItems(usersPage.getDriver());
    }

    @Step
    public boolean employeeIsShown(String employeeName) {
        List<UsersGrid> allItems = getUsersGrid();
        for (UsersGrid singeleObject : allItems) {
            if (singeleObject.getEmployeeName().equals(employeeName)) {
                log.info("MyUserObject = " + singeleObject);
                return true;
            }
        }
        return false;
    }

}
