package com.disorder.networking.services;


import com.disorder.networking.responses.NewsPost;

import rx.Observable;

public interface LooxLikeAPI {

    enum Gender {
        MALE("male"), FEMALE("female");

        private final String text;

        Gender(String text) {
            this.text = text;
        }

        public String asText() {
            return text;
        }
    }

    Observable<NewsPost[]> getNewsPosts(Gender gender, int page);
}
