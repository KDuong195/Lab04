# Hệ thống thanh toán (Strategy + ISP)

```
src/payment/
  PaymentDemo.java          client code minh họa (main)
  capability/               interface nhỏ theo ISP: PaymentStrategy, Refundable, Authenticatable
  method/                   Concrete Strategy: CreditCard, PayPal, EWallet, BankTransfer
  service/                  PaymentService (Context), RefundService
```

Biên dịch và chạy (Java 16+):

```
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out payment.PaymentDemo
```
