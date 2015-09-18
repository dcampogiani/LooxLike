package com.disorder.looxlike.authorization;

import com.disorder.looxlike.utils.Base64Encoder;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class BasicAuthorizationTest {

    private BasicAuthorization subjectUnderTest;

    @Before
    public void setUp() throws Exception {
        String username = "username";
        String password = "password";
        Base64Encoder encoder = buildDummyEncoder();
        subjectUnderTest = new BasicAuthorization(username, password, encoder);
    }

    @Test
    public void testGetHeader() throws Exception {
        String expected = "Basic username:password";
        String result = subjectUnderTest.getHeader();
        assertThat(result, is(expected));

    }

    private Base64Encoder buildDummyEncoder() {
        return new Base64Encoder() {
            @Override
            public String encode(String origin) {
                return origin;
            }
        };
    }
}