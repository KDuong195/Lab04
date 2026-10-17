package payment;

import java.math.BigDecimal;
import payment.method.*;
import payment.service.*;
import payment.capability.PaymentStrategy;

public class PaymentDemo {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        RefundService refundService = new RefundService();

        // Khai báo bằng kiểu cụ thể để giữ lại các khả năng riêng (Refundable, Authenticatable)
        CreditCardPayment   card   = new CreditCardPayment("4111111111111111", "NGUYEN VAN A");
        PayPalPayment       paypal = new PayPalPayment("a@example.com");
        EWalletPayment      wallet = new EWalletPayment("0901234567");
        BankTransferPayment bank   = new BankTransferPayment("0123456789");

        // 1. Thanh toán: đổi strategy mà không sửa PaymentService
        System.out.println("--- 1. Thanh toán ---");
        paymentService.pay(card,   new BigDecimal("1500000"));
        paymentService.pay(paypal, new BigDecimal("250.50"));
        paymentService.pay(wallet, new BigDecimal("99000"));
        paymentService.pay(bank,   new BigDecimal("5000000"));

        // 2. Thanh toán có xác thực: chỉ strategy Authenticatable mới truyền được
        System.out.println("--- 2. Thanh toán có xác thực ---");
        paymentService.payWithAuthentication(wallet, new BigDecimal("120000"), "123456");
        paymentService.payWithAuthentication(card,   new BigDecimal("800000"), "654321");
        // paymentService.payWithAuthentication(bank, ...);  // Lỗi biên dịch: không Authenticatable

        // 3. Hoàn tiền: chỉ truyền được đối tượng Refundable, không cần ép kiểu
        System.out.println("--- 3. Hoàn tiền ---");
        refundService.refund(card,   new BigDecimal("500000"));
        refundService.refund(paypal, new BigDecimal("50"));
        // refundService.refund(bank, ...);  // Lỗi biên dịch: BankTransferPayment không Refundable

        // 4. Chọn strategy lúc chạy, ví dụ theo lựa chọn của người dùng
        System.out.println("--- 4. Chọn strategy lúc chạy ---");
        PaymentStrategy chosen = userChoosesPayPal() ? paypal : bank;
        paymentService.pay(chosen, new BigDecimal("300000"));

        // 5. Mở rộng: thêm phương thức mới KHÔNG sửa code cũ (lambda vì interface chỉ có 1 phương thức)
        System.out.println("--- 5. Mở rộng bằng lambda ---");
        paymentService.pay(
            amount -> System.out.println("Thanh toán bằng Apple Pay: " + amount),
            new BigDecimal("700000"));

        // 6. Xử lý lỗi đầu vào và lỗi nghiệp vụ lúc chạy
        System.out.println("--- 6. Kiểm tra lỗi ---");
        try {
            paymentService.pay(null, new BigDecimal("1000"));
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        try {
            paymentService.pay(bank, new BigDecimal("-5"));
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        try {
            paymentService.payWithAuthentication(wallet, new BigDecimal("1000"), "000000");
        } catch (IllegalStateException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private static boolean userChoosesPayPal() {
        return true;
    }
}
