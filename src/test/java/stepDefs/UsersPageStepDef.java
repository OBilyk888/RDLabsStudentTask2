package stepDefs;

import grids.UsersGrid;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import pageComponents.FilterUsersModalWindow;
import steps.DefaultStepsData;
import steps.UsersSteps;

import java.util.List;
import java.util.Map;

import static utils.SessionVariables.FILTER_USERS_WINDOW;

public class UsersPageStepDef extends DefaultStepsData {

    @Steps
    private UsersSteps usersSteps;

    @When("filter users by Employee Name $userName")
    public void filterUsersByEmployeeName(String employeeName) {
        usersSteps.filterUsersByEmployeeName(employeeName);
    }

    @When("Filter user by Admin Role with option $Global_Admin")
    public void filterUserByAdminRole() {
        usersSteps.switchFilter("Admin Role");
    }

    @Then("Select any value from Admin Role select")
    public void selectValueFromAdminRole() {
        usersSteps.switchFilter("Admin Role");
    }

    @When("filter users by $select : $value")
    public void selectDisabledStatus(String select, String value) {
         usersSteps.filterUsersBy(select,value);
    }

    @Then("record is shown with following parameters:$table")
    public void checkResultOfFiltering(ExamplesTable examplesTable) {
        Map<String, String> row = examplesTable.getRow(0);
        List<UsersGrid> allItems = usersSteps.getUsersGrid();
        softly.assertThat(allItems).as("Wrong search result size is shown").hasSize(1);
        softly.assertThat(allItems.get(0).getUserName()).as("Wrong [Username] is shown").isEqualTo(row.get("Username"));
        softly.assertThat(allItems.get(0).getUserRole()).as("Wrong [User Role(s)] is shown").isEqualTo(row.get("User Role(s)"));
        softly.assertThat(allItems.get(0).getEmployeeName()).as("Wrong [Employee Name] is shown").isEqualTo(row.get("Employee Name"));
        softly.assertThat(allItems.get(0).getStatus()).as("Wrong [Status] is shown").isEqualTo(row.get("Status"));
        softly.assertThat(allItems.get(0).getRegions()).as("Region field is not empty").isEqualTo(row.get("Region"));
    }

    @When("I open filter users window")
    @Alias("Click on the Filter users button again")
    public void openFilterUsersWindow() {
        usersSteps.openFilterWindow();
    }

    @When("I click on the Search button in Filter Users window")
    public void clickOnTheSearchButtonInFilterUsersWindow() {
        usersSteps.clickOnTheSearchButton();
    }

    @Then("I check that employee with name $name is $condition in the search result")
    public void checkThatEmployeeNotShown(String name, String condition) {
        if (condition.contains("NOT")) {
            softly.assertThat(usersSteps.employeeIsShown(name)).isEqualTo(false);
        } else {
            softly.assertThat(usersSteps.employeeIsShown(name)).isEqualTo(true);
        }
    }

    @Then("filter by Admin Role value is $adminRole")
    public void checkThatAdminRoleSaved(String adminRole) {
        softly.assertThat(usersSteps.getAdminRole()).as("Wrong Admin Role value").isEqualTo(adminRole);
    }

    @Then("filter by Status value is $status")
    public void checkThatStatusSaved(String status) {
        softly.assertThat(usersSteps.getStatus()).as("Wrong Status value").isEqualTo(status);
    }

}
