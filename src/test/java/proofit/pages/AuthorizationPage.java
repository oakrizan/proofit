package proofit.pages;

import com.codeborne.selenide.SelenideElement;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static proofit.objects.Wait.LONG;

@Component
public class AuthorizationPage {
    private final SelenideElement wrapper = $("#midPaneContentWrapper");
    //Fields
    private final SelenideElement username = $("#txtUsername");
    private final SelenideElement password = $("#txtPassword");
    //Buttons
    private final SelenideElement login = $("#btnLogin");

    public void waitWhileReady() {
        wrapper.waitUntil(visible, LONG.length());
        username.shouldBe(visible);
        password.shouldBe(visible);
        login.shouldBe(visible);
    }

    public AuthorizationPage withUsername(String user) {
        username.val(user);
        return this;
    }

    public AuthorizationPage withPassword(String pwd) {
        password.val(pwd);
        return this;
    }

    public AuthorizationPage login() {
        login.click();
        return this;
    }
}
