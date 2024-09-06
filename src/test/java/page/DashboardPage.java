package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private static ElementsCollection cards = $$(".list__item div");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";
    private SelenideElement header = $("[data-test-id='dashboard']");

    public DashboardPage() {
        header.shouldBe(visible);
    }

    public static int getCardBalance(DataHelper.Card card) {

        var text = cards.findBy(text(card.getNumber().substring(15))).getText();
        return extractBalance(text);
    }

    public MoneyTransferPage cardToTransfer(DataHelper.Card card) {
        cards.findBy(attribute("data-test-id", card.getTestId())).$("button").click();
        return new MoneyTransferPage();
    }

    private static int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

}
