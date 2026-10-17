package payment.method;

import java.math.BigDecimal;
import payment.capability.*;

public class CreditCardPayment implements PaymentStrategy, Refundable, Authenticatable {
    private final String cardNumber;
    private final String holderName;

    public CreditCardPayment(String cardNumber, String holderName) {
        this.cardNumber = cardNumber;
        this.holderName = holderName;
    }

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Thanh toán bằng thẻ tín dụng (" + mask(cardNumber) + "): " + amount);
    }

    @Override
    public void refund(BigDecimal amount) {
        System.out.println("Hoàn tiền về thẻ " + mask(cardNumber) + ": " + amount);
    }

    @Override
    public boolean authenticate(String otp) {
        System.out.println("Xác thực 3D Secure cho " + holderName);
        return otp != null && otp.length() == 6;
    }

    private String mask(String n) {
        if (n == null || n.length() < 4) {
            throw new IllegalArgumentException("Số thẻ không hợp lệ");
        }
        return "**** " + n.substring(n.length() - 4);
    }
}
