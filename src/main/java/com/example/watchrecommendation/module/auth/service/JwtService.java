package com.example.watchrecommendation.module.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.watchrecommendation.module.auth.dto.LoginReturnSuccesDto;
import com.example.watchrecommendation.module.user.dto.UserDto;
import com.example.watchrecommendation.module.user.entity.User;
import com.example.watchrecommendation.module.utils.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String createToken(UserDto user) {
        Algorithm algorithm = Algorithm.HMAC256(user.getTokenAssignature());
        return JWT.create()
                .withIssuer("auth0")
                .withClaim("id", user.getId())
                .withClaim("name", user.getName())
                .withClaim("expiresInSeconds", 3600)
                .withClaim("typeToken", "AccessToken")
                .withClaim("email", user.getEmail())
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 3600 * 1000))
                .sign(algorithm);
    }

    public String createRefreshToken(UserDto user) {
        Algorithm algorithm = Algorithm.HMAC256(user.getRefreshTokenAssignature());
        return JWT.create()
                .withIssuer("auth0")
                .withClaim("id", user.getId())
                .withClaim("name", user.getName())
                .withClaim("expiresInSeconds", 1800)
                .withClaim("typeToken", "RefreshToken")
                .withClaim("email", user.getEmail())
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 1800 * 1000))
                .sign(algorithm);
    }

    public LoginReturnSuccesDto createTokens(UserDto user) {
        return new LoginReturnSuccesDto(createToken(user), createRefreshToken(user));
    }

    public long getId(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("id").asLong();
    }

    public String getEmail(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("email").asString();
    }

    public void isTokenValid(String token, User user, Boolean isRefresh) throws UnauthorizedException {
        try{
            DecodedJWT decodedJWT = JWT.decode(token);
            Algorithm algorithm = Algorithm.HMAC256(isRefresh ? user.getRefreshTokenAssignature() : user.getTokenAssignature());

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();

            verifier.verify(token);
        } catch (Exception e) {
            throw new UnauthorizedException("Unauthorized!");
        }
    }


}
