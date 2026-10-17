package payment.capability;

public interface Authenticatable {
    boolean authenticate(String otp);
}
