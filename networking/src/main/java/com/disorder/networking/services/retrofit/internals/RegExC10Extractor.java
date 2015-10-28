package com.disorder.networking.services.retrofit.internals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExC10Extractor implements C10Extractor {

    private final Pattern pattern;

    public RegExC10Extractor() {
        pattern = Pattern.compile("[0-9]{8}[a-zA-Z]{2}");
    }

    @Override
    public String[] extract(String rawData) {
        //TODO implement
        Matcher matcher = pattern.matcher(rawData);
        List<String> matches = new ArrayList<>();
        while (matcher.find())
            matches.add(matcher.group());

        //From the webPage we get two matches for each item, so we remove one occurrence for item
        int i = 0;
        for (Iterator<String> it = matches.iterator(); it.hasNext(); ) {
            it.next();
            if (i % 2 == 0)
                it.remove();
            i++;
        }
        return matches.toArray(new String[matches.size()]);
    }
}
