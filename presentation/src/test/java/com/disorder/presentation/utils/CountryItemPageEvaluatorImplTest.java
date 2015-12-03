package com.disorder.presentation.utils;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CountryItemPageEvaluatorImplTest {

    private CountryItemPageEvaluatorImpl subjectUnderTest;

    @Before
    public void setUp() throws Exception {
        subjectUnderTest = new CountryItemPageEvaluatorImpl("it");
    }

    @Test
    public void testEvaluate() throws Exception {
        String expected = "http://mobile.yoox.com/it/37647512DD/item#cod10=37647512DD";
        String result = subjectUnderTest.evaluate("37647512DD");
        assertThat(result).isEqualTo(expected);
    }
}