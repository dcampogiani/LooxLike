package com.disorder.presentation.utils;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class LooxLikeAPIUserAvatarGeneratorTest {

    private LooxLikeAPIUserAvatarGenerator subjectUnderTest;
    private static final String baseAPIUrl = "http://54.93.89.176/LooxLikeAPI";

    @Before
    public void setUp() throws Exception {
        subjectUnderTest = new LooxLikeAPIUserAvatarGenerator(baseAPIUrl);
    }

    @Test
    public void testGetUrl() throws Exception {
        String expected = "http://54.93.89.176/LooxLikeAPI/user/avatar/daniele";
        String result = subjectUnderTest.getUrl("daniele");
        assertThat(result).isEqualTo(expected);
    }
}