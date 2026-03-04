package org.gs;

import io.smallrye.jwt.build.Jwt;

import jakarta.inject.Singleton;

@Singleton
public class InvestimentoJwtService {

    public String generateJwt() {
        return Jwt.issuer("investimento-jwt")
                .subject("investimento-app")
                .groups("Admin").expiresAt(System.currentTimeMillis() + 3600).sign();
    }
}
