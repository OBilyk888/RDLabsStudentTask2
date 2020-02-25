package pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;

@Getter
@Slf4j
public class DashboardPage extends BasePage {

    public static final String PAGE_TITLE = "OrangeHRM";

    @FindBy(css = "#account-name")
    private WebElementFacade accountNameLabel;

    @FindBy(xpath = "//a[@id='side-menu-hamburger']")
    private WebElementFacade hideMenuButton;

    @FindBy(xpath = "//a[@id='side-menu-hamburger']")
    private WebElementFacade showMenuButton;

    @FindBy(css = "#dashboard__viewNewsOnDashboard")
    private WebElementFacade newsContainer;

    @FindBy(css = "#dashboard__viewDocumentsOnDashboard")
    private WebElementFacade documentsContainer;

//    @FindBy(css = ".card-content .material-icons")
//    private WebElementFacade threeDotsButton;

    @FindBy(xpath = "//i[(@class='material-icons moreIcon right')]")
    private WebElementFacade threeDotsButton;

    @FindBy(xpath = "//i[(@class='material-icons right')]")
    private WebElementFacade threeDotsButton1;

    @FindBy(css = "#task-list-group-panel-menu_holder-legend")
    private WebElementFacade employeeLegend;

    @FindBy(css = "#legend")
    private WebElementFacade leavesLegend;

    @FindBy(xpath = "//div[@id = 'panel_draggable_2_9']//div[@class = 'dashboardCard-title-for-card']")
    private WebElementFacade newsHeader;

    @FindBy(xpath = "//div[@id='panel_draggable_2_8']//div[@class='dashboardCard-title-for-card']")
    private  WebElementFacade documentsHeader;

    @FindBy(xpath = "//div[contains(@id,'dashboard__viewNewsOnDashboard')]//div[@class = 'inner']//ul//li")
    private List<WebElementFacade> listOfNews;

    @FindBy(xpath = "//div[contains(@id,'DocumentsOnDashboard')]//ul//li")
    private List<WebElementFacade> listOfDocuments;

    @FindBy(css = "#dashboard__viewNewsOnDashboard > div.document-count-text > div.right")
    private WebElementFacade realCountOfNews;

   public void clickOnHideMenuButton() {
        log.info("Clicking on the [Hide menu] button");
        hideMenuButton.waitUntilVisible().waitUntilClickable().click();
    }

    public void clickOnShowMenuButton() {
        log.info("Clicking on the [Show menu] button");
        showMenuButton.waitUntilVisible().waitUntilClickable().click();
    }

    public String getStringFromSectionNews(){
        return realCountOfNews.getText();
    }

}
