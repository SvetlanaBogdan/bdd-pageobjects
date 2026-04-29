package ru.netology;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    @Test
    void shouldTransferMoney() {
        open("http://localhost:9999");

        var authInfo = DataHelper.getAuthInfo();
        var code = DataHelper.getVerificationCode();

        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage =
                loginPage.login(authInfo.getLogin(), authInfo.getPassword());
        DashboardPage dashboardPage =
                verificationPage.verify(code.getCode());

        // балансы ДО
        int balance1Before = dashboardPage.getCardBalance(0);
        int balance2Before = dashboardPage.getCardBalance(1);

        int amount = 1000;

        var firstCard = DataHelper.getFirstCard();

        // перевод
        TransferPage transferPage = dashboardPage.selectCardToTransfer(1);
        DashboardPage updatedPage =
                transferPage.transferMoney(amount, firstCard.getNumber());

        // балансы ПОСЛЕ
        int balance1After = updatedPage.getCardBalance(0);
        int balance2After = updatedPage.getCardBalance(1);


        assertEquals(balance1Before - amount, balance1After);
        assertEquals(balance2Before + amount, balance2After);
    }
}