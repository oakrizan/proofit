package proofit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import proofit.helpers.SessionManager;
import proofit.objects.Agent;
import proofit.objects.LeaveData;
import proofit.pages.forms.LeavePage;
import proofit.pages.leftMenu.LeftMenu;

import static java.time.LocalDate.parse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = {TestConfig.class})
public class AuthorizationTests {
    @Autowired
    private SessionManager session;
    @Autowired
    private LeftMenu leftMenu;
    @Autowired
    private LeavePage leavePage;
    @Autowired
    private Agent agent;
    @Autowired
    private LeaveData leaveData;

    @Value("${url}")
    private String url;

    @BeforeEach
    public void setup() {
        session.openBrowser(url);
        session.login(agent);
        leftMenu.waitWhileReady();
    }

    @AfterEach
    public void tearDown() {
        session.logout();
        session.closeBrowser();
    }

    @Test
    public void validateAgentData() {
        assertThat(leftMenu.accountName().getText(), equalToIgnoringCase(agent.getFullName()));
        assertThat(leftMenu.accountJob().getText(), containsStringIgnoringCase(agent.getPosition()));
    }

    @Test
    public void applyLeave() {
        leftMenu.goToApplyLeave();
        leavePage.waitWhileReady()
                .selectFromDate(parse(leaveData.getFromDate()))
                .selectToDate(parse(leaveData.getToDate()))
                .selectLeaveType(leaveData.getType())
                .submitLeaveApplication()
                .confirmBalancePopup();
        assertThat(leavePage.isSaved(), is(true));
    }
}
