package proofit.pages.leftMenu;

import com.codeborne.selenide.SelenideElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static proofit.objects.Wait.LONG;
import static proofit.objects.Wait.SHORT;

@Component
public class LeftMenu {
    @Autowired
    private LeaveMenu leaveMenu;

    private final SelenideElement wrapper = $("#left-menu");
    //User data
    private final SelenideElement accName = $("#account-name");
    private final SelenideElement accJob = $("#account-job");
    //Dropdowns
    private final SelenideElement userDropdown = $("#user-dropdown");
    private final SelenideElement userMenu = $("#user_menu.active");
    private final SelenideElement logout = $("#logoutLink");

    public void waitWhileReady() {
        wrapper.waitUntil(visible, LONG.length());
        accName.shouldBe(visible);
        accJob.shouldBe(visible);
    }

    public SelenideElement accountName() {
        return accName;
    }

    public SelenideElement accountJob() {
        return accJob;
    }

    public void goToApplyLeave() {
        leaveMenu.expandLeave()
                .expandApply();
    }

    public LeftMenu expandUserMenu() {
        userDropdown.click();
        userMenu.waitUntil(visible, SHORT.length());
        return this;
    }

    public LeftMenu logout() {
        logout.click();
        return this;
    }
}
