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
    public String getTextFromTheHeaderNews(){
        return dashboardPage.getNewsHeader().waitUntilVisible().getText();
    }
    @Step
    public int getCountOfNews(){
        int temp = 0;
        List<WebElementFacade> listOfNews = dashboardPage.getListOfNews();
        for (int i = 0; i < listOfNews.size(); i++) {
            WebElementFacade webElementFacade = listOfNews.get(i);   // element = 0;  dashboardPage.getListOfNews = 16;  element = 0   temo = 56;
            temp++;
        }
        return temp;
    }

    @Step
    public int getRealCount(){
        String value = dashboardPage.getStringFromSectionNews().split("/")[1].trim();
        return Integer.parseInt(value);
    }
}
