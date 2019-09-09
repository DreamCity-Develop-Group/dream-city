package com.dream.city.service;

import com.auth0.jwt.interfaces.Claim;

import java.util.Map;

/**
 * @author Wvv
 */
public interface AuthService {

    String generiteToken(String user);

    Map<String, Claim> verifyToken(String token);

    String getUsername(String token);
}
