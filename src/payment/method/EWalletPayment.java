package payment.method;

import java.math.BigDecimal;
import payment.capability.*;

public class EWalletPayment implements PaymentStrategy, Refundable, Authenticatable {
    private final String phone;

    public EWalletPayment(String phone) {
        this.phone = phone;
    }

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Thanh toán bằng ví điện tử (" + phone + "): " + amount);
    }

    @Override
    public void refund(BigDecimal amount) {
        System.out.println("Hoàn tiền về ví " + phone + ": " + amount);
    }

    @Override
    public boolean authenticate(String otp) {
        System.out.println("Xác thực OTP gửi tới " + phone);
        return "123456".equals(otp); // chỉ để minh họa, thực tế sẽ gọi dịch vụ OTP
    }
}
