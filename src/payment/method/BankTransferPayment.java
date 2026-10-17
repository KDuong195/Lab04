package payment.method;

import java.math.BigDecimal;
import payment.capability.*;

public class BankTransferPayment implements PaymentStrategy {
    private final String accountNumber;

    public BankTransferPayment(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Thanh toán bằng chuyển khoản (TK " + accountNumber + "): " + amount);
    }
}
