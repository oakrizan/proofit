package proofit.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proofit.objects.Agent;
import proofit.pages.AuthorizationPage;
import proofit.pages.leftMenu.LeftMenu;

import static com.codeborne.selenide.Selenide.*;

@Component
public class SessionManager {
    @Autowired
    private AuthorizationPage authorize;
    @Autowired
    private LeftMenu leftMenu;

    public void openBrowser(String url) {
        open(url);
    }

    public void closeBrowser() {
        closeWindow();
    }

    public void login(Agent agent) {
        authorize.waitWhileReady();
        authorize.withUsername(agent.getUsername())
                .withPassword(agent.getPassword())
                .login();
    }

    public void logout() {
        leftMenu.expandUserMenu()
                .logout();
        authorize.waitWhileReady();
        clearBrowserCookies();
    }
}
