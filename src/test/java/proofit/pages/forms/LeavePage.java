package proofit.pages.forms;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proofit.pages.calendar.PopupCalendar;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.util.Objects.requireNonNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.openqa.selenium.By.cssSelector;
import static proofit.objects.FormHeader.APPLY_LEAVE;
import static proofit.objects.Wait.LONG;
import static proofit.objects.Wait.SHORT;

@Component
public class LeavePage {
    @Autowired
    private PopupCalendar calendar;
    private final SelenideElement wrapper = $("#applyLeaveDiv");
    //Dropdowns
    private final SelenideElement leaveType = $("#leaveType_inputfileddiv");
    private final ElementsCollection typeList = $$(".dropdown-content.active > li");
    //Buttons
    private final SelenideElement submit = $("[type=submit]");
    private final SelenideElement balanceCheck = $(".link");
    //Fields
    private final SelenideElement fromDate = $("#from");
    private final SelenideElement toDate = $("#to");
    //Popup
    private final SelenideElement balancePopup = $("#application_balance_modal");
    private final SelenideElement confirmPopup = $("[ng-click=\"modal.confirm()\"]");
    private final SelenideElement successMsg = $(".toast-success");
    //Motion elements
    private final SelenideElement loadingSign = $("#preloader");
    //String selectors
    private final String formName = "h4";
    private final String calendarIcon = ".date-picker-open-icon";

    public LeavePage waitWhileReady() {
        wrapper.waitUntil(visible, LONG.length());
        String headerText =  wrapper.find(cssSelector(formName)).getText();
        assertThat(headerText, containsStringIgnoringCase(APPLY_LEAVE.header()));
        return this;
    }

    public LeavePage selectLeaveType(String type) {
        leaveType.click();
        requireNonNull(typeList.stream().filter(value -> value.getText()
                .contentEquals(type))
                .findFirst().orElse(null))
                .click();
        balanceCheck.waitUntil(visible, LONG.length());
        return this;
    }

    public LeavePage selectFromDate(LocalDate date) {
        fromDate.parent().find(cssSelector(calendarIcon)).click();
        calendar.selectDate(date);
        return this;
    }

    public LeavePage selectToDate(LocalDate date) {
        toDate.parent().find(cssSelector(calendarIcon)).click();
        calendar.selectDate(date);
        return this;
    }

    public LeavePage submitLeaveApplication() {
        submit.waitUntil(visible, SHORT.length());
        submit.click();
        return this;
    }

    public LeavePage confirmBalancePopup() {
        balancePopup.waitUntil(visible, SHORT.length());
        confirmPopup.click();
        balancePopup.waitUntil(not(visible), SHORT.length());
        return this;
    }

    public Boolean isSaved() {
        loadingSign.waitUntil(visible, SHORT.length());
        loadingSign.waitUntil(not(visible), SHORT.length());
        return successMsg.isDisplayed();
    }
}
