package com.disorder.networking.utils;


import org.apache.commons.codec.binary.Base64;

public class ApacheBase64Encoder implements Base64Encoder {

    @Override
    public String encode(String origin) {
        byte[] bytes = origin.getBytes();
        byte[] encoded = Base64.encodeBase64(bytes);
        return new String(encoded);
    }
}
