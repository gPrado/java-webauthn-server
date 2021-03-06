package com.yubico.webauthn.data;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yubico.webauthn.impl.json.StringIdJsonSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Clients may communicate with authenticators using a variety of mechanisms.
 * For example, a client may use a platform-specific API to communicate with
 * an authenticator which is physically bound to a platform. On the other
 * hand, a client may use a variety of standardized cross-platform transport
 * protocols such as Bluetooth (see §4.7.4 Authenticator Transport
 * enumeration) to discover and communicate with cross-platform attached
 * authenticators. Therefore, we use AuthenticatorAttachment to describe an
 * authenticator's attachment modality. We define authenticators that are part
 * of the client’s platform as having a platform attachment, and refer to them
 * as platform authenticators. While those that are reachable via
 * cross-platform transport protocols are defined as having cross-platform
 * attachment, and refer to them as roaming authenticators.
 *
 * This distinction is important because there are use-cases where only
 * platform authenticators are acceptable to a Relying Party, and conversely
 * ones where only roaming authenticators are employed. As a concrete example
 * of the former, a credential on a platform authenticator may be used by
 * Relying Parties to quickly and conveniently reauthenticate the user with a
 * minimum of friction, e.g., the user will not have to dig around in their
 * pocket for their key fob or phone. As a concrete example of the latter,
 * when the user is accessing the Relying Party from a given client for the
 * first time, they may be required to use a roaming authenticator which was
 * originally registered with the Relying Party using a different client.
 */
@JsonSerialize(using = StringIdJsonSerializer.class)
@AllArgsConstructor
public enum AuthenticatorAttachment {
    /**
     * The respective authenticator is attached using cross-platform transports.
     *
     * Authenticators of this class are removable from, and can "roam" among,
     * client platforms.
     */
    CROSS_PLATFORM("cross-platform"),

    /**
     * The respective authenticator is attached using platform-specific
     * transports.
     *
     * Usually, authenticators of this class are non-removable from the platform.
     */
    PLATFORM("platform");

    @Getter
    private final String id;

}

