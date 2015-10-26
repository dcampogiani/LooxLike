package com.disorder.networking.services;


import com.disorder.networking.responses.NewsPost;

import rx.Observable;

public interface LooxLikeAPI {

    enum Gender {
        MALE("male", 'm'), FEMALE("female", 'f'), NOGENDER("nogender", 'n');

        private final String text;
        private final char queryParameter;

        Gender(String text, char queryParameter) {
            this.text = text;
            this.queryParameter = queryParameter;
        }

        public char queryParameter() {
            return this.queryParameter;
        }
    }

    Observable<NewsPost[]> getNewsPosts(int page);

    Observable<NewsPost[]> getNewsPosts(Gender gender, int page);

}
