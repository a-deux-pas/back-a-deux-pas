package adeuxpas.back.controller;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.JsonSyntaxException;

@RestController
@RequestMapping("/api/webhooks")
public class StripeWebHookController {
    @Value("${stripe.webhook.secret}")
    private String webhookSecret;

    @PostMapping("/stripe")
    public ResponseEntity<String> handleStripeEvent(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        Event event;

        try {
            event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
        } catch (JsonSyntaxException e) {
            // Invalid payload
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid payload");
        }
        catch (SignatureVerificationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
        }

        // Handle the event
        switch (event.getType()){
            case "charge.refund.updated": {
                System.out.println("Charge refund updated");
                break;
            }
            case "payment_intent.succeeded": {
                System.out.println("Payment intent succeeded");
                break;
            }
            default:
                System.out.println("Unhandled event type: " + event.getType());
        }

        return ResponseEntity.ok("Event received");
    }
}
