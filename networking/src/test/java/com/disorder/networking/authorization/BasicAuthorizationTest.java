package com.disorder.networking.authorization;

import com.disorder.networking.utils.Base64Encoder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;


public class BasicAuthorizationTest {

    private com.disorder.networking.authorization.BasicAuthorization subjectUnderTest;

    @Mock(answer = Answers.RETURNS_SMART_NULLS)
    private Base64Encoder base64Encoder;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        String username = "username";
        String password = "password";
        subjectUnderTest = new BasicAuthorization(username, password, base64Encoder);
    }

    @Test
    public void testGetHeader() throws Exception {
        String expected = "Basic ";
        String result = subjectUnderTest.getHeader();
        assertThat(result).startsWith(expected);

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