package payment.method;

import java.math.BigDecimal;
import payment.capability.*;

public class PayPalPayment implements PaymentStrategy, Refundable {
    private final String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Thanh toán bằng PayPal (" + email + "): " + amount);
    }

    @Override
    public void refund(BigDecimal amount) {
        System.out.println("Hoàn tiền về tài khoản PayPal " + email + ": " + amount);
    }
}
