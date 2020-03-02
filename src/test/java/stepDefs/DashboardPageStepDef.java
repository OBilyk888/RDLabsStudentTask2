package stepDefs;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import steps.CommonSteps;
import steps.DashboardPageSteps;
import steps.DefaultStepsData;

public class DashboardPageStepDef extends DefaultStepsData {

    @Steps
    private DashboardPageSteps dashboardPageSteps;
    @Steps
    private CommonSteps commonSteps;

    @Then("dashboard page opens with account name $userName")
    public void checkThatDashboardPageOpens(String userName) {
        softly.assertThat(dashboardPageSteps.getDashBoardPageTitle())
                .as("Wrong page title")
                .isEqualTo(dashboardPage.getTitle());
        softly.assertThat(dashboardPageSteps.getAccountNameFromDashboard())
                .as("Wrong account name is shown on page")
                .isEqualTo(userName);
    }

    //https://jbehave.org/reference/latest/aliases.html

    @When("I click on hide menu button")
    @Alias("I click on show menu button")
    public void whenClickOnTheHideMenuButton() {
        dashboardPageSteps.clickOnHideMenuButton();
    }

    @Then("main menu $condition")
    public void mainMenuCondition(String condition) {
        String warningMessage = "Menu not " + condition + " after clicking on the hide/show menu button";
        if (condition.equals("disappear")) {
            softly.assertThat(commonSteps.isMenuAvatarVisibleNow()).as(warningMessage).isFalse();
        }
    }

    @When("I click on the three dots button inside $sectionName section")
    public void clickiOnThreeDotsButton(String sectionName) {
        dashboardPageSteps.expandContainerClickingOnThreeDots(sectionName);
    }

    @Then("Legend component appears in $sectionName section")
    public void checkThatLegendAppears(String sectionName) {
        softly.assertThat(dashboardPageSteps.checkThatLegendAppearsIn(sectionName)).as("Legend component not appears").isTrue();
    }

    @Then("I check that $text section is present on Dashboard page with header $text")
    public void checkThatNewsSectionIsPresent(String sectionName, String value) {
        softly.assertThat(dashboardPageSteps.getTextFromHeader(sectionName)).
                as("News").isEqualTo(value);
    }

    @Then("I check that news counter (Showing: number / number) under $text section is same as real amount of News in list")
    public void checkThatCounterEqualsToRealAmount(String newsOrDocuments) {
        softly.assertThat(dashboardPageSteps.getCountItemsList(newsOrDocuments)).
                as("Documents").isEqualTo(dashboardPageSteps.getValueUnderSection(newsOrDocuments));
    }

//    @Then("I check that $Documents section is present on Dashboard page with header Documents")
//    public void checkThatDocumentsSectionIsPresent(String value) {
//        softly.assertThat((dashboardPageSteps.getTextFromHeader("Documents"))).isEqualTo(value);
//    }

//    @Then("I check that news counter (Showing: number / number) under Documents section is same as real amount of news in list")
//    public void checkThatDocumentsCounterEqualsToRealAmount() {
//        softly.assertThat(dashboardPageSteps.getCountItemsList("Documents")).isEqualTo(dashboardPageSteps.getValueUnderSection("Documents"));
//    }
}