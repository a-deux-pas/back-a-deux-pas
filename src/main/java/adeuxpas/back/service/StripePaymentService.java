package adeuxpas.back.service;

import com.stripe.exception.StripeException;
import com.stripe.model.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Interface for Stripe payment services.
 * Establishes the contract (abstract methods) that the implementing classes must adhere to (implement)
 */
public interface StripePaymentService {
    Map<String, String> createPaymentIntent(Map<String, Object> paymentInfo) throws StripeException;
    void capturePayment(String paymentIntentId) throws StripeException;
    void createPayout(String accountId, Long amount, String currency, String bankAccountTokenId) throws StripeException;
    Account createConnectAccountWithToken(String accountToken, String businessProfileUrl) throws StripeException;
    public Token createAccountToken(String email, String city, String line1,
                                    String postalCode, String dobDay, String dobMonth, String dobYear, String firstName,
                                    String lastName)throws StripeException;
    ExternalAccount addExternalBankAccountToStripeConnect(String accountId, String bankAccountToken, BigDecimal amount) throws StripeException;
}
