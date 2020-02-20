package steps;

import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.Alert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class LoginPageSteps extends DefaultStepsData {

    @Step
    public void loginToApplication(String userName, String password) {
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
    }

    @Step
    public String getErrorMessageAfterLogin() {
        log.info("Getting error message after unsuccessful login to application");
        return loginPage.getEmptyFieldErrorMessage().waitUntilVisible().getText();
    }

//    @Step
//    public String getinvalidCredentialsPopUp() {
//        log.info("Getting the pop up after unsuccessful login to application with the text");
//        return loginPage.getInvalidCredentialsPopUp().waitUntilVisible().getText();

//        try {
//            //WebDriverWait wait = new WebDriverWait(driver, 2);
//            wait.until(ExpectedConditions.alertIsPresent());
//            Alert alert = getDriver().switchTo().alert();
//            alert.accept();
//
//        } catch (Exception e) {
//            //exception handling
//        }

//        try {
//            getDriver().switchTo().alert();
//            return loginPage.getInvalidCredentialsPopUp().waitUntilVisible().getText();
//        } // try
//        finally {
//            softlyAssertAll();
//        }

//        Alert alert = getDriver().switchTo().alert();
//        alert.accept();

    @Step
    public void clickOnTheLoginAsDifferentRoleButton() {
        loginPage.clickOnTheLoginAsDifferentRoleButton();
        loginPage.withTimeoutOf(Duration.ofSeconds(5)).waitFor(loginPage.getUserRoles());
    }

    @Step
    public List<String> getAllUsersRolesFromDropDown() {
        return loginPage.getUserRoles().stream().map(WebElementFacade::getText).collect(Collectors.toList());
    }

    @Step
    public String getTexFromAdminField() {
        return loginPage.getLoginInputField().waitUntilEnabled().getValue();
    }
}
