package com.disorder.looxlike.utils;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ApacheBase64EncoderTest {

    private ApacheBase64Encoder subjectUnderTest;

    @org.junit.Before
    public void setUp() throws Exception {
        subjectUnderTest = new ApacheBase64Encoder();
    }

    @org.junit.Test
    public void testEncode() throws Exception {

        String origin = "pippopluto";
        String expected = "cGlwcG9wbHV0bw==";
        String encoded = subjectUnderTest.encode(origin);
        assertThat(encoded, is(expected));
    }
}