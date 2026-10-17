package payment.capability;

import java.math.BigDecimal;

public interface Refundable {
    void refund(BigDecimal amount);
}
