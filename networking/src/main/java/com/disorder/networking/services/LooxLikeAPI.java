package com.disorder.networking.services;


import com.disorder.networking.requests.CreatePostRequest;
import com.disorder.networking.responses.NewsPost;

import rx.Observable;

public interface LooxLikeAPI {

    enum Gender {
        MALE('m'), FEMALE('f'), NOGENDER('n');

        private final char queryParameter;

        Gender(char queryParameter) {
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
        FULL
    }

    Observable<NewsPost[]> getNewsPosts(int page);

    Observable<NewsPost[]> getNewsPosts(Gender gender, int page);

    Observable<NewsPost> createPost(CreatePostRequest request);

    Observable<Boolean> orderHasItems(String orderId);

    Observable<String[]> getItemsOfOrder(String orderId);

    Observable<NewsPost[]> getLikedPosts(int page);

}
