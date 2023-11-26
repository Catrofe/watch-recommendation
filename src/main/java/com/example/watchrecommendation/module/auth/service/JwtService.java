package com.example.watchrecommendation.module.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.watchrecommendation.module.auth.dto.LoginReturnSuccesDto;
import com.example.watchrecommendation.module.auth.dto.TypeToken;
import com.example.watchrecommendation.module.user.dto.UserDto;
import com.example.watchrecommendation.module.utils.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    String secret = System.getenv("SECRET_KEY_JWT");
    String secretRefresh = System.getenv("SECRET_KEY_REFRESH");

    DecodedJWT decodedJWT;

    public String createToken(UserDto user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("auth0")
                .withClaim("id", user.getId())
                .withClaim("name", user.getName())
                .withClaim("expiresInSeconds", 3600)
                .withClaim("typeToken", "AccessToken")
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
                .withClaim("typeToken", "RefreshToken")
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 1800 * 1000))
                .sign(algorithm);
    }

    public LoginReturnSuccesDto createTokens(UserDto user) {
        return new LoginReturnSuccesDto(createToken(user), createRefreshToken(user));
    }

    public long getId(String token) {
        try{
            if (isTokenValid(token)){
                return decodedJWT.getClaim("id").asLong();
            }
            throw new UnauthorizedException("Unauthorized!");
        } catch (Exception e) {
            throw new UnauthorizedException("Unauthorized!");
        }
    }

    public boolean isTokenValid(String token) {
        try{
            TypeToken typeToken = TypeToken.getTypeToken(JWT.decode(token).getClaim("typeToken").asString());
            Algorithm algorithm = Algorithm.HMAC256(typeToken == TypeToken.ACCESSTOKEN ? secret : secretRefresh);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();

            decodedJWT = verifier.verify(token);
            return true;
        } catch (Exception e) {
            throw new UnauthorizedException("Unauthorized!");
        }
    }


}