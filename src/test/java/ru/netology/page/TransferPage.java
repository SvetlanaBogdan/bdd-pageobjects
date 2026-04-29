package ru.netology.page;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    public TransferPage() {
        $("[data-test-id=amount] input").shouldBe(visible);
    }

    public DashboardPage transferMoney(int amount, String fromCardNumber) {
        $("[data-test-id=amount] input").setValue(String.valueOf(amount));
        $("[data-test-id=from] input").setValue(fromCardNumber);
        $("[data-test-id=action-transfer]").click();

        return new DashboardPage();
    }
}