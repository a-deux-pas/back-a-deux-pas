package adeuxpas.back.controller;

import adeuxpas.back.service.StripePaymentService;
import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class StripePaymentController {

    @Value("${stripe.api.key}")
    private String stripeApiKey;
    private final StripePaymentService stripePaymentService;

    public StripePaymentController(StripePaymentService stripePaymentService){
        this.stripePaymentService = stripePaymentService;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    @PostMapping("/create-payment-intent")
    public Map<String, String> createPaymentIntent(@RequestBody Map<String, Object> paymentInfo) throws Exception {
            return stripePaymentService.createPaymentIntent(paymentInfo);
    }
}
