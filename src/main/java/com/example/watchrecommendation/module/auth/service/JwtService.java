package com.example.watchrecommendation.module.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.watchrecommendation.module.auth.dto.LoginReturnSuccesDto;
import com.example.watchrecommendation.module.user.dto.UserDto;
import com.example.watchrecommendation.module.utils.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    String secret = System.getenv("SECRET_KEY_JWT");
    String secretRefresh = System.getenv("SECRET_KEY_REFRESH");

    public String createToken(UserDto user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("auth0")
                .withClaim("id", user.getId())
                .withClaim("name", user.getName())
                .withClaim("expiresInSeconds", 3600)
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 3600 * 1000))
                .sign(algorithm);
    }

    public String createRefreshToken(UserDto user) {
        Algorithm algorithm = Algorithm.HMAC256(secretRefresh);
        return JWT.create()
                .withIssuer("auth0")
                .withClaim("id", user.getId())
                .withClaim("name", user.getName())
                .withClaim("expiresInSeconds", 1800)
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 1800 * 1000))
                .sign(algorithm);
    }

    public LoginReturnSuccesDto createTokens(UserDto user) {
        return new LoginReturnSuccesDto(createToken(user), createRefreshToken(user));
    }

    public long getId(String token) {
        try{
        return JWT.decode(token).getClaim("id").asLong();
        } catch (Exception e) {
            throw new UnauthorizedException("Unauthorized!");
        }
    }


}
