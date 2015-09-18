package com.disorder.looxlike.services;


import com.disorder.looxlike.responses.NewsPost;

import rx.Observable;

public class FakeLooxLikeAPI implements LooxLikeAPI {

    NewsPost[] fakeMale;
    NewsPost[] fakeFemale;

    public FakeLooxLikeAPI() {
        fakeMale = new NewsPost[2];
        fakeFemale = new NewsPost[2];

        fakeMale[0] = new NewsPost(1, "http://images2.gazzettaobjects.it/methode_image/2014/07/12/Calcio/Foto%20Gallery%20-%20Trattate/Nargi%209-kVQC--458x458@Gazzetta-Web_mediagallery-fullscreen.jpg", "descrizione1", "item1", 1, "dcampogiani", true);
        fakeMale[1] = new NewsPost(2, "http://www.dailynews24.it/wp-content/uploads/2015/05/Nargi.jpg", "descrizione2", "item2", 2, "azanin", true);

        fakeFemale[0] = new NewsPost(3, "http://static.panorama.it/wp-content/uploads/2014/05/0apreMS719-MS718-1000x600.jpg?63b36c", "descrizione3", "item3", 3, "rlovino", true);
        fakeFemale[1] = new NewsPost(4, "jpg", "descrizione4", "item3", 4, "azanin", true);
    }

    @Override
    public Observable<NewsPost[]> getNewsPosts(Gender gender, int page) {
        if (gender == Gender.MALE)
            return Observable.just(fakeMale);
        else
            return Observable.just(fakeFemale);
    }
}
