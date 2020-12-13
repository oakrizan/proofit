package proofit.pages.calendar;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static proofit.objects.Wait.LONG;

@Component
public class PopupCalendar {
    private final SelenideElement wrapper = $(".picker--opened .picker__box");
    //Calendar
    private final SelenideElement monthCal = $(".picker--focused .picker__month");
    private final SelenideElement yearCal = $(".picker--focused .picker__year");
    private final String dayCollection = ".picker[aria-hidden=false] .picker__day--infocus";
    //Buttons
    private final SelenideElement previousMonth = $(".picker--opened .picker__nav--prev");
    private final SelenideElement nextMonth = $(".picker--opened .picker__nav--next");

    public void selectDate(LocalDate date) {
        wrapper.waitUntil(visible, LONG.length());
        selectYearAndMonth(date);
        selectDay(date);
    }

    private void selectYearAndMonth(LocalDate date) {
        String month = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String year = Integer.toString(date.getYear());

        Map<Integer, Runnable> strategy = Map.of(
                1, nextMonth::click,
                -1, previousMonth::click,
                0, () -> {}
        );

        int compare = YearMonth.from(date).compareTo(YearMonth.now());
        System.out.println("Scrolling to " + date + " " + compare);
        while (!yearCal.getText().contentEquals(year) || !monthCal.getText().contentEquals(month)) {
            strategy.get(compare).run();
        }
    }

    private void selectDay(LocalDate date) {
        String dayStr = Integer.toString(date.getDayOfMonth());
        ElementsCollection days = $$(dayCollection);
        days.stream()
            .filter(dayElement -> dayElement.getText().contentEquals(dayStr))
            .findFirst()
            .ifPresent(SelenideElement::click);
    }
}
