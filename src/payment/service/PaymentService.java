package payment.service;

import java.math.BigDecimal;
import payment.capability.*;

public class PaymentService {

    public void pay(PaymentStrategy strategy, BigDecimal amount) {
        if (strategy == null) {
            throw new IllegalArgumentException("Phương thức thanh toán không hợp lệ");
        }
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Số tiền phải lớn hơn 0");
        }
        strategy.pay(amount);
    }

    // ISP: ràng buộc kiểu giao (intersection type), chỉ nhận đối tượng vừa là
    // PaymentStrategy vừa là Authenticatable. Truyền BankTransferPayment sẽ bị
    // báo lỗi ngay lúc BIÊN DỊCH, không cần instanceof hay ép kiểu lúc chạy.
    public <T extends PaymentStrategy & Authenticatable> void payWithAuthentication(
            T method, BigDecimal amount, String otp) {
        if (!method.authenticate(otp)) {
            throw new IllegalStateException("Xác thực thất bại");
        }
        pay(method, amount);
    }
}
