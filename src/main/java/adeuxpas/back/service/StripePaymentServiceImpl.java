package adeuxpas.back.service;

import adeuxpas.back.entity.Meeting;
import adeuxpas.back.repository.MeetingRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCaptureParams;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class StripePaymentServiceImpl implements StripePaymentService{
    private final MeetingRepository meetingRepository;

    public StripePaymentServiceImpl(MeetingRepository meetingRepository){
        this.meetingRepository = meetingRepository;
    }

    public Map<String, String> createPaymentIntent(Map<String, Object> paymentInfo) throws StripeException {
        String token = (String) paymentInfo.get("token"); // The tokenized card details
        Long amount = ((Number) paymentInfo.get("amount")).longValue();
        String currency = (String) paymentInfo.get("currency");
        Integer meetingId = (Integer) paymentInfo.get("meetingId");

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setCurrency(currency)
                .setAmount(amount)
                .addPaymentMethodType("card") // Add payment method type as card
                .putExtraParam("payment_method_data[type]", "card") // Set the type to card
                .putExtraParam("payment_method_data[card][token]", token) // Use token for card
                .setCaptureMethod(PaymentIntentCreateParams.CaptureMethod.MANUAL) // Manual capture for delayed payment until after confirmation from the 2 parties
                .build();

        PaymentIntent intent = PaymentIntent.create(params);
        Map<String, String> responseData = new HashMap<>();
        responseData.put("clientSecret", intent.getClientSecret());

        updateMeetingWithCardPaymentInfo(meetingId, intent.getId());

        return responseData;
    }

    private void updateMeetingWithCardPaymentInfo(Integer meetingId, String paymentIntentId){
        Optional<Meeting> savedMeeting = meetingRepository.findById(Long.valueOf(meetingId));
        if (savedMeeting.isPresent()) {
            savedMeeting.get().setStripePaymentIntentId(paymentIntentId);
            meetingRepository.save(savedMeeting.get());
        }
    }

    // To use when the meeting is confirmed/finalized and the payment should be actually debited
    public void capturePayment(String paymentIntentId) throws StripeException {
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
        PaymentIntentCaptureParams captureParams = PaymentIntentCaptureParams.builder().build();
        paymentIntent.capture(captureParams);
    }
}