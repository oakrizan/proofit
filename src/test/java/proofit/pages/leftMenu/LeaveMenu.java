package proofit.pages.leftMenu;

import com.codeborne.selenide.SelenideElement;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class LeaveMenu {
    private final SelenideElement leave = $("#menu_leave_viewLeaveModule");
    private final SelenideElement applyLeave = $("#menu_leave_applyLeave");

    public LeaveMenu expandLeave() {
        leave.click();
        return this;
    }

    public LeaveMenu expandApply() {
        applyLeave.click();
        return this;
    }
}
