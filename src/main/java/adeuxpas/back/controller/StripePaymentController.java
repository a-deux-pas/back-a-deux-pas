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

/**
 * REST controller for handling Stripe payment operations.
 */
@RestController
@RequestMapping("/api/payment")
public class StripePaymentController {

    /**
     * The secret API key used to authenticate with Stripe.
     */
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    /**
     * The service used to interact with Stripe for payment operations.
     */
    private final StripePaymentService stripePaymentService;

    /**
     * Constructs a new StripePaymentController with the specified StripePaymentService.
     *
     * @param stripePaymentService the service used to handle Stripe payment operations
     */
    public StripePaymentController(StripePaymentService stripePaymentService){
        this.stripePaymentService = stripePaymentService;
    }

    /**
     * Initializes the Stripe API key after the bean's properties have been set.
     */
    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    /**
     * Creates a payment intent with the specified payment information.
     *
     * @param paymentInfo the payment information
     * @return a map containing the payment intent details
     * @throws Exception if an error occurs while creating the payment intent
     */
    @PostMapping("/create-payment-intent")
    public Map<String, String> createPaymentIntent(@RequestBody Map<String, Object> paymentInfo) throws Exception {
            return stripePaymentService.createPaymentIntent(paymentInfo);
    }
}
