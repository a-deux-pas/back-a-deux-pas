package adeuxpas.back.enums;

/**
 * Enumeration representing the status of a user account.
 * <p>
 * This enum defines different states in which a user account can be.
 * It encapsulates the possible statuses such as active, suspended, or reported.
 * </p>
 *
 * @author Mircea Bardan
 */
public enum AccountStatus {
    /**
     * The account is active and operational.
     */
    ACTIVE,

    /**
     * The account is suspended or temporarily disabled.
     */
    SUSPENDED,

    /**
     * The account has been reported for violating terms of service or community guidelines.
     */
    REPORTED
}
