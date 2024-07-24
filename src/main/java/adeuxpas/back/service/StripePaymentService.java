package adeuxpas.back.service;

import com.stripe.exception.StripeException;

import java.util.Map;

public interface StripePaymentService {
    Map<String, String> createPaymentIntent(Map<String, Object> paymentInfo) throws StripeException;
    public void capturePayment(String paymentIntentId) throws StripeException;
}
