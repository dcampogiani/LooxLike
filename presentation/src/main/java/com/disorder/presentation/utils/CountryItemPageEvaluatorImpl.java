package com.disorder.presentation.utils;

public class CountryItemPageEvaluatorImpl implements ItemPageUrlEvaluator {

    private final String countryCode;

    private static final String url = "http://mobile.yoox.com/{countyCode}/{c10}/item#cod10={c10}";

    public CountryItemPageEvaluatorImpl(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String evaluate(String c10) {
        return url.replace("{countyCode}", countryCode).replace("{c10}", c10);
    }
}
