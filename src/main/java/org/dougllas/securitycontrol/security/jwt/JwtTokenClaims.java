package org.dougllas.securitycontrol.security.jwt;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Criado por dougllas.sousa em 15/03/2018.
 */
public interface JwtTokenClaims {

    String CLAIM_KEY_USERNAME = "sub";
    String CLAIM_KEY_ROLE = "role";
    String CLAIM_KEY_AUDIENCE = "audience";
    String CLAIM_KEY_CREATED = "created";

    static Map<String, Object> export(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_ROLE, "USER");
        return claims;
    }
}
