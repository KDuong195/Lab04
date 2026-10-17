package payment.service;

import java.math.BigDecimal;
import payment.capability.*;

public class RefundService {
    // ISP: chỉ phụ thuộc Refundable, không biết gì về PaymentStrategy
    public void refund(Refundable method, BigDecimal amount) {
        if (method == null) {
            throw new IllegalArgumentException("Phương thức hoàn tiền không hợp lệ");
        }
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Số tiền hoàn phải lớn hơn 0");
        }
        method.refund(amount);
    }
}
