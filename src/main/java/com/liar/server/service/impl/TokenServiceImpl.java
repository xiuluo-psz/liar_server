package com.liar.server.service.impl;

import org.springframework.stereotype.Service;

import com.liar.server.constant.Constants;
import com.liar.server.entity.UserEntity;
import com.liar.server.service.TokenService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenServiceImpl implements TokenService {
	
	@Override
	public String getToken(UserEntity user) {
		String token = "";
//		token = JWT.create().withAudience(Constants.AUDIENCE).withJWTId(Constants.JWTID).withSubject(Constants.SUBJECT)
//				.withIssuer(Constants.ISSUER).sign(Algorithm.HMAC256(Constants.SIGN_KEY));
		token = Jwts.builder().setIssuer(Constants.ISSUER).setAudience(Constants.AUDIENCE).setId(Constants.JWTID)
				.setSubject(Constants.SUBJECT).signWith(SignatureAlgorithm.HS512, Constants.SIGN_KEY).compact();
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
}
