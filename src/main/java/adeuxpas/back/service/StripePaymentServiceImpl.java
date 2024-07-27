package adeuxpas.back.service;

import adeuxpas.back.entity.Meeting;
import adeuxpas.back.repository.MeetingRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.net.RequestOptions;
import com.stripe.param.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Service implementation for handling Stripe payment operations.
 */
@Service
public class StripePaymentServiceImpl implements StripePaymentService{
    private final MeetingRepository meetingRepository;

    /**
     * Constructs a new StripePaymentServiceImpl with the specified MeetingRepository.
     *
     * @param meetingRepository the repository used to manage meetings
     */
    public StripePaymentServiceImpl(MeetingRepository meetingRepository){
        this.meetingRepository = meetingRepository;
    }

    /**
     * Creates a payment intent with the specified payment information.
     *
     * @param paymentInfo the payment information
     * @return a map containing the payment intent details
     * @throws StripeException if an error occurs while creating the payment intent
     */
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

        // The response contains 2 strings : the clientSecret and the id of the payment intent
        Map<String, String> responseData = new HashMap<>();
        // The clientSecret must be sent to our front end,
        // who will use it in a separate call to the Stripe API,
        // to prove to Stripe that the payment intent came from there
        responseData.put("clientSecret", intent.getClientSecret());

        // The id of the payment intent, which represents the buyer's tokenized card details, will be saved to our database
        updateMeetingWithCardPaymentInfo(meetingId, intent.getId());

        return responseData;
    }

    /**
     * Updates the meeting with the specified ID with the given payment intent ID (the tokenized card details).
     *
     * @param meetingId the ID of the meeting to update
     * @param paymentIntentId the ID of the payment intent to associate with the meeting
     */
    private void updateMeetingWithCardPaymentInfo(Integer meetingId, String paymentIntentId){
        Optional<Meeting> savedMeeting = meetingRepository.findById(Long.valueOf(meetingId));
        if (savedMeeting.isPresent()) {
            savedMeeting.get().setStripePaymentIntentId(paymentIntentId);
            meetingRepository.save(savedMeeting.get());
        }
    }

    // The following methods are called when the meeting is confirmed/finalized and the buyer's card should actually be charged

    /**
     * Creates an account token with the specified individual details.
     *
     * @param email the email address
     * @param city the city of the individual
     * @param line1 the first line of the address
     * @param postalCode the postal code
     * @param dobDay the day of birth
     * @param dobMonth the month of birth
     * @param dobYear the year of birth
     * @param firstName the first name
     * @param lastName the last name
     * @return the created account token
     * @throws StripeException if an error occurs while creating the account token
     */
    @Override
    public Token createAccountToken(String email, String city, String line1,
                                    String postalCode, String dobDay, String dobMonth, String dobYear, String firstName,
                                    String lastName) throws StripeException {
        TokenCreateParams.Account accountParams = TokenCreateParams.Account.builder()

                .setBusinessType(TokenCreateParams.Account.BusinessType.INDIVIDUAL)
                .setIndividual(
                        TokenCreateParams.Account.Individual.builder()
                                .setEmail(email)
                                .setAddress(
                                        TokenCreateParams.Account.Individual.Address.builder()
                                                .setCity(city)
                                                .setLine1(line1)
                                                .setPostalCode(postalCode)
                                                .build()
                                )
                                .setDob(
                                        TokenCreateParams.Account.Individual.Dob.builder()
                                                .setDay(Long.parseLong(dobDay))
                                                .setMonth(Long.parseLong(dobMonth))
                                                .setYear(Long.parseLong(dobYear))
                                                .build()
                                )
                                .setFirstName(firstName)
                                .setLastName(lastName)
                                .build()
                )
                .setTosShownAndAccepted(true)
                .build();

        TokenCreateParams params = TokenCreateParams.builder()
                .setAccount(accountParams)
                .build();

        return Token.create(params);
    }

    /**
     * Creates a Stripe Connect account using the provided account token ID and business profile URL.
     *
     * @param accountTokenId the account token ID
     * @param businessProfileUrl the business profile URL
     * @return the created Stripe Connect account
     * @throws StripeException if an error occurs while creating the Stripe Connect account
     */
    @Override
    public Account createConnectAccountWithToken(String accountTokenId, String businessProfileUrl) throws StripeException {
        AccountCreateParams params = AccountCreateParams.builder()
                .setType(AccountCreateParams.Type.CUSTOM)
                .setCountry("FR")
                .setAccountToken(accountTokenId)
                .setBusinessProfile(
                        AccountCreateParams.BusinessProfile.builder()
                                .setUrl(businessProfileUrl)
                                .build()
                )
                .setCapabilities(AccountCreateParams.Capabilities.builder()
                        .setTransfers(AccountCreateParams.Capabilities.Transfers.builder()
                                .setRequested(true)
                                .build())
                        .build())
                .build();

        return Account.create(params);
    }

    /**
     * Adds an external bank account to the specified Stripe Connect account.
     *
     * @param accountId the ID of the Stripe Connect account
     * @param bankAccountToken the ID of the external account
     * @param amount the amount to be added to the external account
     * @return the added external account
     * @throws StripeException if an error occurs while adding the external account
     */
    @Override
    public ExternalAccount addExternalBankAccountToStripeConnect(String accountId, String bankAccountToken, BigDecimal amount) throws StripeException {
        Account account = Account.retrieve(accountId);

        ExternalAccountCollectionCreateParams params =
                ExternalAccountCollectionCreateParams.builder()
                        .setExternalAccount(bankAccountToken)
                        .build();
        ExternalAccount externalAccount = account.getExternalAccounts().create(params);

        account.getExternalAccounts().getData().add(externalAccount);

        return externalAccount;
    }

    /**
     * Captures a payment for the specified payment intent ID.
     *
     * @param paymentIntentId the ID of the payment intent to capture
     * @throws StripeException if an error occurs while capturing the payment
     */
    @Override
    public void capturePayment(String paymentIntentId) throws StripeException {
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
        paymentIntent.capture();
    }

    /**
     * Creates a payout for the specified Stripe Connect account.
     *
     * @param accountId the ID of the Stripe Connect account
     * @param amount the amount to payout
     * @param currency the currency of the payout
     * @param externalAccountId the ID of the external account to payout to
     * @return the created payout
     * @throws StripeException if an error occurs while creating the payout
     */
    @Override
    public Payout createPayout(String accountId, Long amount, String currency, String externalAccountId) throws StripeException {
        PayoutCreateParams params = PayoutCreateParams.builder()
                .setAmount(amount)
                .setCurrency(currency)
                .setDestination(externalAccountId)
                .build();

        RequestOptions requestOptions = RequestOptions.builder()
                .setStripeAccount(accountId)
                .build();

        return Payout.create(params, requestOptions);
    }
}

