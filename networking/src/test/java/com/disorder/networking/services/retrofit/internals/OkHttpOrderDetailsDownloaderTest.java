package com.disorder.networking.services.retrofit.internals;

import com.squareup.okhttp.OkHttpClient;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class OkHttpOrderDetailsDownloaderTest {

    private OkHttpOrderDetailsDownloader subjectUnderTest;

    @Before
    public void setUp() throws Exception {
        OkHttpClient okHttpClient = new OkHttpClient();
        C10Extractor c10Extractor = new RegExC10Extractor();
        subjectUnderTest = new OkHttpOrderDetailsDownloader(okHttpClient, c10Extractor);
    }

    @Test
    @Ignore
    public void testOrderDoesNotExist() throws Exception {
        String[] itemsC10 = subjectUnderTest.getItemsC10("pippo");
        assertThat(itemsC10.length, is(0));
    }

    @Test
    @Ignore
    public void testGetOneItem() throws Exception {
        String[] itemsC10 = subjectUnderTest.getItemsC10("2810Y2C321502A");
        String result = itemsC10[0];
        assertThat(itemsC10.length, is(1));
        assertThat(result, is("36731894KJ"));
    }

    @Test
    @Ignore
    public void testGetTwoItems() throws Exception {
        String[] itemsC10 = subjectUnderTest.getItemsC10("2310Y5AC115029");
        String firstResult = itemsC10[0];
        String secondResult = itemsC10[1];
        assertThat(itemsC10.length, is(2));
        assertThat(firstResult, is("37647512DD"));
        assertThat(secondResult, is("37647512TK"));
    }
}