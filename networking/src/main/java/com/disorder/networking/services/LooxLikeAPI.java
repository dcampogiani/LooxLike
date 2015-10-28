package com.disorder.networking.services;


import com.disorder.networking.requests.CreatePostRequest;
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

    class Unauthorized extends RuntimeException {
        public Unauthorized(Throwable cause) {
            super(cause);
        }
    }

    class ServerError extends RuntimeException {
        public ServerError(Throwable cause) {
            super(cause);
        }
    }

    class NotFound extends RuntimeException {
        public NotFound(Throwable cause) {
            super(cause);
        }
    }

    enum LogLevel {
        NONE,
        BASIC,
        HEADERS,
        HEADERS_AND_ARGS,
        FULL;
    }

    Observable<NewsPost[]> getNewsPosts(int page);

    Observable<NewsPost[]> getNewsPosts(Gender gender, int page);

    Observable<NewsPost> createPost(CreatePostRequest request);

}
