package adeuxpas.back.enums;

/**
 * Enumeration representing the status of an Ad.
 * <p>
 * This enum defines different states in which an Ad can be.
 * It encapsulates the possible statuses such as available, reserved, sold or
 * suspended.
 * </p>
 *
 */
public enum AdStatus {
    /**
     * The Ad is available for purchase.
     */
    AVAILABLE,
    /**
     * The Ad is purchased by another user, so it's reserved until they meet and
     * actually do the exchange.
     */
    RESERVED,
    /**
     * The meeting between seller and buyer is done.
     */
    SOLD,
    /**
     * The article or the user has disrespected one of the website rules, thus, the
     * Ad is suspended
     */
    SUSPENDED
}
