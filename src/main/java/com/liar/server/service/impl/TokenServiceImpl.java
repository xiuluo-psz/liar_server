package com.liar.server.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.liar.server.constant.Constants;
import com.liar.server.entity.UserEntity;
import com.liar.server.service.TokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenServiceImpl implements TokenService {

	@Override
	public String getToken(UserEntity user) {
		String token = "";
//		token = JWT.create().withAudience(Constants.AUDIENCE).withJWTId(Constants.JWTID).withSubject(Constants.SUBJECT)
//				.withIssuer(Constants.ISSUER).sign(Algorithm.HMAC256(Constants.SIGN_KEY));
		Date expiresDate = new Date(System.currentTimeMillis() + Constants.DEATH_TIME);
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put(Constants.UUID, user.getUserId());
		token = Jwts.builder().setIssuer(Constants.ISSUER).setAudience(Constants.AUDIENCE).setId(Constants.JWTID)
				.setSubject(Constants.SUBJECT).setExpiration(expiresDate).addClaims(claims)
				.signWith(SignatureAlgorithm.HS512, Constants.SIGN_KEY).compact();
		return token;
	}

	@Override
	public boolean verifyToken(String token) {
		try {
//			JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(Constants.SIGN_KEY)).withIssuer(Constants.ISSUER)
//					.withJWTId(Constants.JWTID).withAudience(Constants.AUDIENCE).withSubject(Constants.SUBJECT).build();
//			jwtVerifier.verify(token);

			Jwts.parser().requireIssuer(Constants.ISSUER).requireAudience(Constants.AUDIENCE).requireId(Constants.JWTID)
					.requireSubject(Constants.SUBJECT).setSigningKey(Constants.SIGN_KEY).parseClaimsJws(token);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public String refreshToken(UserEntity user) {
		return getToken(user);
	}
	
	@Override
	public String getUUID(String token) {
		Claims body = Jwts.parser().setSigningKey(Constants.SIGN_KEY).parseClaimsJws(token).getBody();
		String uuid = body.get(Constants.UUID).toString();
		return uuid;
	}
}
