package steps;

import emuns.ItemsContainer;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;

import java.util.List;

@Slf4j
public class DashboardPageSteps extends DefaultStepsData {

    @Step
    public String getDashBoardPageTitle() {
        return dashboardPage.getTitle();
    }

    @Step
    public String getAccountNameFromDashboard() {
        return dashboardPage.getAccountNameLabel().getText();
    }

    @Step
    public void clickOnHideMenuButton() {
        dashboardPage.clickOnHideMenuButton();
    }

    @Step
    public void expandContainerClickingOnThreeDots(String sectionName) {
        ItemsContainer itemsContainer = ItemsContainer.getItemsContainerName(sectionName);
        switch (itemsContainer) {
            case EMPLOYEE_DISTRIBUTION:
                dashboardPage.getThreeDotsButton().waitUntilEnabled().click();
                break;
            case LEAVE_TAKEN:
                dashboardPage.getThreeDotsButton1().waitUntilEnabled().click();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + itemsContainer);
        }
    }

    public boolean checkThatLegendAppearsIn(String sectionName) {
        ItemsContainer itemsContainer = ItemsContainer.getItemsContainerName(sectionName);
        switch (itemsContainer) {
            case EMPLOYEE_DISTRIBUTION:
                return dashboardPage.getEmployeeLegend().isVisible();
            case LEAVE_TAKEN:
                return dashboardPage.getLeavesLegend().isVisible();
            default:
                throw new IllegalStateException("Unexpected value: " + itemsContainer);
        }
    }

    @Step
    public String getTextFromTheHeaderNews(String nameHeader) {

        ItemsContainer itemsContainer = ItemsContainer.getItemsContainerName(nameHeader);
        switch (itemsContainer) {
            case NEWS:
                return dashboardPage.getHeaderOfSectionNews().getText();
            case DOCUMENTS:
                return dashboardPage.getHeaderOfSectionDocuments().getText();
            default:
                throw new IllegalStateException("Unexpected value: " + itemsContainer);
        }
       // return dashboardPage.getNewsHeader().waitUntilVisible().getText();
    }

    @Step
    public int getCountOfNews(String listOf) {
        return dashboardPage.getRealCountOf(listOf);
    }

    @Step
    public String getTextFromHeader(String sectionName) {
        ItemsContainer itemsContainer = ItemsContainer.getItemsContainerName(sectionName);
        switch (itemsContainer) {
            case NEWS:
                return dashboardPage.getHeaderOfSectionNews().getText();
            case DOCUMENTS:
                return dashboardPage.getHeaderOfSectionDocuments().getText();
            default:
                throw new IllegalStateException("Unexpected value: " + itemsContainer);
        }
    }

    @Step
    public int getCountItemsList(String sectionName) {
       //return dashboardPage.getRealCountOf(listOf);

        ItemsContainer itemsContainer = ItemsContainer.getItemsContainerName(sectionName);
        switch (itemsContainer) {
            case NEWS:
                return Integer.parseInt(dashboardPage.getShowingNumberNews().waitUntilVisible().getText().split("/")[1].trim());
            case DOCUMENTS:
                return Integer.parseInt(dashboardPage.getShowingNumberOfDocuments().waitUntilVisible().getText().split("/")[1].trim());
            default:
                throw new IllegalStateException("Unexpected value: " + itemsContainer);
        }

    }

    @Step
    public int getValueUnderSection(String section) {
        String countText;
        switch (section) {
            case "News":
                countText = dashboardPage.getShowingNumberNews().getText().split("/")[1].trim();
                return Integer.parseInt(countText);
            case "Documents":
                countText = dashboardPage.getShowingNumberOfDocuments().getText().split("/")[1].trim();
                return Integer.parseInt(countText);
        }
        return -1;
    }

}
