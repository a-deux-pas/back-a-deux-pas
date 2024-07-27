package adeuxpas.back.controller;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.JsonSyntaxException;

/**
 * REST controller for handling Stripe webhook events.
 */
@RestController
@RequestMapping("/api/webhooks")
public class StripeWebHookController {
    /**
     * The secret key used to verify the Stripe webhook signature.
     */
    @Value("${stripe.webhook.secret}")
    private String webhookSecret;

    /**
     * A logger, to log certain events
     */
    private static final Logger logger = LoggerFactory.getLogger(StripeWebHookController.class);


    /**
     * Endpoint to handle Stripe webhook events.
     *
     * @param payload The raw JSON payload from Stripe.
     * @param sigHeader The Stripe-Signature header.
     * @return ResponseEntity indicating the result of the event handling.
     */
    @PostMapping("/stripe")
    public ResponseEntity<String> handleStripeEvent(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        Event event;

        try {
            event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
        } catch (JsonSyntaxException e) {
            logger.error("Invalid payload", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid payload");
        }
        catch (SignatureVerificationException e) {
            logger.error("Invalid signature", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
        }

        // Handle the event
        switch (event.getType()) {
            case "payment_intent.created":
                logger.info("Payment intent created");
                break;
            case "payment_intent.succeeded":
                logger.info("Payment intent succeeded");
                break;
            case "charge.succeeded":
                logger.info("Holding the transaction amount as 'pending' in the account. The card is not yet charged.");
                break;
            case "payment_intent.amount_capturable_updated":
                logger.info("The Payment event has funds to be captured");
                break;
            case "charge.captured":
                logger.info("The transaction is finalized, funds are withdrawn from the buyer's account and ready to be transferred to the seller's account");
                break;
            default:
                logger.warn("Unhandled event type: " + event.getType());
        }

        return ResponseEntity.ok("Event received");
    }
}
