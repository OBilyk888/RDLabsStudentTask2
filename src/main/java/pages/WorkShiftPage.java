package pages;

import com.google.inject.internal.asm.$ClassWriter;
import lombok.Getter;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

@Getter
public class WorkShiftPage extends BasePage {

    @FindBy(xpath = "//div[@data-tooltip='Add Work Shift']//a")
    private WebElementFacade addWorkShiftButton;

    @FindBy(css = ".primary-btn")
    private WebElementFacade saveButton;

    @FindBy(css = "sf-decorator:nth-of-type(1)  .help-block")
    private WebElementFacade requiredMsg;

    @FindBy(xpath = "//div[@class='list-container']//span[contains(text(),'General')]")
    private WebElementFacade clickOnGeneralButton;

    @FindBy(xpath = "//span[contains(text(),'Twilight')]")
    private WebElementFacade clickOnTwillightButton;

    @FindBy(xpath = "//th[contains(text(),'Work Shift')]")
    private WebElementFacade clickOnWorkShiftButton;

    @FindBy(xpath = "//div[@id='modal1']")
    private WebElementFacade addWorkShiftWindow;

    @FindBy(css = ".picker--opened .picker__box")
    WebElementFacade timePickerLocator;

}
