package stepDefs;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import steps.DefaultStepsData;
import steps.LoginPageSteps;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginPageStepDef extends DefaultStepsData {

    @Steps
    private LoginPageSteps loginPageSteps;

    @Given("I am on the login page of application")
    public void goToLoginPage() {
        loginPage.openLoginPage();
    }

    @When("I login to application with username '$userName' and password '$password'")
    @Given("I login to application with username '$userName' and password '$password'")
    public void loginToApplicationWithFollowingCredentials(String login, String password) {
        loginPageSteps.loginToApplication(login, password);
    }

    @Then("error message appears with text: $errorText")
    public void checkErrorMessageAfterUnsuccesfulLogin(String errorTextMessage) {
        softly.assertThat(loginPageSteps.getErrorMessageAfterLogin()).as("Wrong message is shown")
                .contains(errorTextMessage);
    }

    @Then("I check that pop up with text $Invalid_Credentials appears")
    public void checkThatPopUPInvalidCredentialsAppears(String textPopUp) {
        softly.assertThat(loginPageSteps.getTextFromPopUPInvalidCredentials()).isEqualTo(textPopUp);
    }

    @When("I click on Login as a Different Role button")
    public void clickOnShowRoleButton() {
        loginPageSteps.clickOnTheLoginAsDifferentRoleButton();
    }

    @Then("following roles are shown in the drop down: $table")
    public void checkThatAllUserRolesArePresent(ExamplesTable examplesTable) {
        List<Map<String, String>> rows = examplesTable.getRows();
        List<String> expectedRoles = new ArrayList<>();
        List<String> actualRoles = loginPageSteps.getAllUsersRolesFromDropDown();
        for (Map<String, String> row : rows) {
            expectedRoles.add(row.get("role"));
        }
        softly.assertThat(actualRoles).as("List of roles is not correct").containsExactlyElementsOf(expectedRoles);
    }

    @Then("I check that following social-media buttons are visible: $table")
    public void checkSocialMediaButtons(ExamplesTable examplesTable) {
        List<Map<String, String>> rows = examplesTable.getRows();
        WebElementFacade socialMediaContainer = loginPage.getSocialMediaContainer();
        for (Map<String, String> row : rows) {
            String socialMediaButtonName = row.get("social_media_button");
            boolean isButtonVisible = socialMediaContainer.then(By.xpath("//a[contains(@class,'" + socialMediaButtonName + "')]")).isVisible();
            softly.assertThat(isButtonVisible).as(String.format("button %s visible", socialMediaButtonName)).isTrue();
        }
    }

    @Then("Check that text '$admin' admin is shown in Username field")
    public void checkThatTextAdminIsDefault(String admin) {
        softly.assertThat(loginPageSteps.getTexFromAdminField()).isEqualTo(admin);
    }
}