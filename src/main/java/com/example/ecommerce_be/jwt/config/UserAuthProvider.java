package com.example.ecommerce_be.jwt.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.ecommerce_be.dto.AdminDTO;
import com.example.ecommerce_be.entity.Admin;
import com.example.ecommerce_be.jwt.exceptions.AppException;
import com.example.ecommerce_be.mapper.AdminMapper;
import com.example.ecommerce_be.repositories.AdminRepository;
import com.example.ecommerce_be.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {
  private  final AdminRepository userRepository;

  private  final AdminMapper userMapper;

  @Value("${security.jwt.token.secret-key:secret-key}")

  private String secretKey;

  private final AdminService userService;

  @PostConstruct
  protected void init() {
    // this is to avoid having the raw secret key available in the JVM
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String createToken(AdminDTO user) {
    Date now = new Date();
    Date validity = new Date(now.getTime() + 60000); // 1 hour= 3600000

    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    return JWT.create()
      .withSubject(user.getLogin())
      .withIssuedAt(now)
      .withExpiresAt(validity)
      .withClaim("userName", user.getUsername())
//      .withClaim("lastName", user.getLastname())
      .sign(Algorithm.HMAC256(secretKey));
  }

  public Authentication validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    JWTVerifier verifier = JWT.require(algorithm)
      .build();

    DecodedJWT decoded = verifier.verify(token);

    AdminDTO user = AdminDTO.builder()
      .login(decoded.getSubject())
      .username(decoded.getClaim("userName").asString())
//      .lastname(decoded.getClaim("lastName").asString())
      .build();

    return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
  }

  public Authentication validateTokenStrongly(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    JWTVerifier verifier = JWT.require(algorithm)
      .build();

    DecodedJWT decoded = verifier.verify(token);

    Admin user = userRepository.findByLogin(decoded.getIssuer())
      .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

    return new UsernamePasswordAuthenticationToken(userMapper.toUserDto(user), null, Collections.emptyList());

//        UserDto user = userService.findByLogin(decoded.getSubject());
//
//        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
  }
}
