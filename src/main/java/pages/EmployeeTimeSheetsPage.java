package pages;

import lombok.Getter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

@Getter
public class EmployeeTimeSheetsPage extends BasePage{

    @FindBy(css = "#x_report_employeeId_empName")
    private WebElementFacade searchInputField;

    @FindBy(css = ".ac_results li")
    private WebElementFacade employeeNameAutoCompleteElement;

//    @FindBy(xpath = "//div[@class='row col s6 input-field']")
//    private WebElementFacade searchInputField;
//
//    @FindBy(xpath = "//div[@class='row col s6 input-field']")
//    private WebElementFacade employeeNameAutoCompleteElement;



}
