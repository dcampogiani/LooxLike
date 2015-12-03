package com.disorder.networking.services;


import com.disorder.networking.requests.CreatePostRequest;
import com.disorder.networking.responses.NewsPost;

import rx.Observable;

public class FakeLooxLikeAPI implements LooxLikeAPI {

    private final NewsPost[] fakeMale;
    private final NewsPost[] fakeFemale;
    private final NewsPost[] fakeNoGender;
    private final NewsPost[] fakeAll;
    private final String fakeItems[];

    public FakeLooxLikeAPI() {
        fakeMale = new NewsPost[2];
        fakeFemale = new NewsPost[2];
        fakeNoGender = new NewsPost[2];
        fakeAll = new NewsPost[6];

        fakeMale[0] = new NewsPost(1, "descrizione1", "http://images2.gazzettaobjects.it/methode_image/2014/07/12/Calcio/Foto%20Gallery%20-%20Trattate/Nargi%209-kVQC--458x458@Gazzetta-Web_mediagallery-fullscreen.jpg", "item1", "2015-10-08T20:44:55", "dcampogiani", 1, true);
        fakeMale[1] = new NewsPost(2, "descrizione2", "http://www.dailynews24.it/wp-content/uploads/2015/05/Nargi.jpg", "item2", "2015-10-08T20:44:55", "azanin", 2, false);

        fakeFemale[0] = new NewsPost(3, "descrizione3", "http://static.panorama.it/wp-content/uploads/2014/05/0apreMS719-MS718-1000x600.jpg?63b36c", "item3", "2015-10-08T20:44:55", "rlovino", 3, true);
        fakeFemale[1] = new NewsPost(4, "descrizione4", "http://www.dailynews24.it/wp-content/uploads/2015/05/Nargi.jpg", "item4", "2015-10-08T20:44:55", "ccorapi", 4, false);

        fakeNoGender[0] = new NewsPost(5, "descrizione5", "http://images2.gazzettaobjects.it/methode_image/2014/07/12/Calcio/Foto%20Gallery%20-%20Trattate/Nargi%209-kVQC--458x458@Gazzetta-Web_mediagallery-fullscreen.jpg", "item5", "2015-10-08T20:44:55", "smorettin", 9, true);
        fakeNoGender[1] = new NewsPost(5, "descrizione6", "http://static.panorama.it/wp-content/uploads/2014/05/0apreMS719-MS718-1000x600.jpg?63b36c", "item6", "2015-10-08T20:44:55", "idominici", 2, false);

        fakeAll[0] = fakeMale[0];
        fakeAll[1] = fakeFemale[0];
        fakeAll[2] = fakeMale[1];
        fakeAll[3] = fakeFemale[1];
        fakeAll[4] = fakeNoGender[0];
        fakeAll[5] = fakeNoGender[1];

        fakeItems = new String[3];
        fakeItems[0] = "35271183DJ";
        fakeItems[1] = "41603276FQ";
        fakeItems[2] = "41603299WN";

    }

    @Override
    public Observable<NewsPost[]> getNewsPosts(int page) {
        return Observable.just(fakeAll);
    }

    @Override
    public Observable<NewsPost[]> getNewsPosts(Gender gender, int page) {
        if (gender == Gender.MALE)
            return Observable.just(fakeMale);
        else if (gender == Gender.FEMALE)
            return Observable.just(fakeFemale);
        else
            return Observable.just(fakeNoGender);
    }

    @Override
    public Observable<NewsPost> createPost(CreatePostRequest request) {
        return Observable.just(fakeAll[0]);
    }

    @Override
    public Observable<Boolean> orderHasItems(String orderId) {
        return Observable.just(true);
    }

    @Override
    public Observable<String[]> getItemsOfOrder(String orderId) {
        return Observable.just(fakeItems);
    }

    @Override
    public Observable<NewsPost[]> getLikedPosts(int page) {
        return Observable.just(fakeAll);
    }
}
