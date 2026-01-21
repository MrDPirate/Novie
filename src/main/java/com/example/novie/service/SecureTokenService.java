package com.example.novie.service;

import com.example.novie.model.SecureToken;
import org.springframework.stereotype.Service;

public interface SecureTokenService {

    SecureToken createToken();
    void saveSecureToken(SecureToken secureToken);
    SecureToken findByToken(String token);
    void removeToken(SecureToken token);

}