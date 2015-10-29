package com.disorder.presentation.presenter.creation;

import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.presenter.BasePresenter;
import com.disorder.presentation.utils.RxScheduler;
import com.disorder.presentation.view.creation.CheckOrderView;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

public class OrderCheckPresenterImpl extends BasePresenter<CheckOrderView> implements OrderCheckPresenter {

    private final LooxLikeAPI mLooxLikeAPI;
    private final RxScheduler scheduler;

    @Inject
    public OrderCheckPresenterImpl(LooxLikeAPI mLooxLikeAPI, RxScheduler rxScheduler) {
        this.mLooxLikeAPI = mLooxLikeAPI;
        this.scheduler = rxScheduler;
    }

    @Override
    public void isOrderValid(String orderId) {

        getView().showLoading();
        Observable<Boolean> apiObservable = mLooxLikeAPI.orderHasItems(orderId);
        Observable<Boolean> scheduledObservable = scheduler.schedule(apiObservable);
        scheduledObservable.subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean apiResponse) {
                getView().hideLoading();
                if (apiResponse)
                    getView().orderIsValid();
                else
                    getView().orderIsNotValid();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                getView().hideLoading();
                getView().orderIsNotValid();
            }
        });
    }
}
