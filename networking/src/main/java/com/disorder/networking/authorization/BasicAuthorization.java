package com.disorder.networking.authorization;

import com.disorder.networking.utils.Base64Encoder;

public class BasicAuthorization implements Authorization {

    private final String username;
    private final String password;
    private final Base64Encoder encoder;
    private final String header;

    public BasicAuthorization(String username, String password, Base64Encoder encoder) {
        this.username = username;
        this.password = password;
        this.encoder = encoder;
        this.header = encodeCredentialsForBasicAuthorization();
    }

    @Override
    public String getHeader() {
        return header;
    }


    private String encodeCredentialsForBasicAuthorization() {
        String encoded = encoder.encode(username + ":" + password);
        return "Basic " + encoded;
    }
}
