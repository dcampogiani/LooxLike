package com.disorder.looxlike.authorization;

import com.disorder.looxlike.utils.Base64Encoder;

public class BasicAuthorization implements Authorization {

    private String username;
    private String password;
    private Base64Encoder encoder;
    private String header;

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
