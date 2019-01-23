package org.dougllas.securitycontrol.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.dougllas.securitycontrol.security.jwt.JwtTokenClaims.*;


/**
 * Criado por dougllas.sousa em 15/03/2018.
 */

@Component
public class JwtTokenService implements Serializable{

    @Value("${jwt.signingkey}")
    private String signingKey;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * Obtém o username contido no token JWT.
     *
     * @param token
     * @return String
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * Retorna a data de expiração de um token JWT.
     *
     * @param token
     * @return Date
     */
    public LocalDateTime getExpirationDateFromToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Cria um novo token (refresh).
     *
     * @param token
     * @return String
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = gerarToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * Verifica e retorna se um token JWT é válido.
     *
     * @param token
     * @return boolean
     */
    public boolean tokenValido(String token) {
        return !tokenExpirado(token);
    }

    /**
     * Retorna um novo token JWT com base nos dados do usuários.
     *
     * @param userDetails
     * @return String
     */
    public String obterToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        claims.put(JwtTokenClaims.CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_ROLE, "USER");
        claims.put(CLAIM_KEY_CREATED, new Date());

        return gerarToken(claims);
    }

    /**
     * Realiza o parse do token JWT para extrair as informações contidas no
     * corpo dele.
     *
     * @param token
     * @return Claims
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**eyJhbGciOiJIUzUx
     * Retorna a data de expiração com base na data atual.
     *
     * @return Date
     */
    private LocalDateTime gerarDataExpiracao() {
        return Instant.ofEpochMilli(System.currentTimeMillis() + expiration * 1000)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * Verifica se um token JTW está expirado.
     *
     * @param token
     * @return boolean
     */
    private boolean tokenExpirado(String token) {
        LocalDateTime dataExpiracao = this.getExpirationDateFromToken(token);
        if (dataExpiracao == null) {
            return false;
        }
        return dataExpiracao.isBefore(LocalDateTime.now());
    }

    /**
     * Gera um novo token JWT contendo os dados (claims) fornecidos.
     *
     * @param claims
     * @return String
     */
    private String gerarToken(Map<String, Object> claims) {
        Date dataExpiracao = Date.from(gerarDataExpiracao().atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS512, signingKey).compact();
    }
}