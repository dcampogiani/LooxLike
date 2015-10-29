package com.disorder.presentation.presenter.creation;

import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.presenter.BasePresenter;
import com.disorder.presentation.utils.RxScheduler;
import com.disorder.presentation.view.creation.ItemSelectionView;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;

public class ItemSelectionPresenterImpl extends BasePresenter<ItemSelectionView> implements ItemSelectionPresenter {

    private final LooxLikeAPI mLooxLikeAPI;
    private final RxScheduler scheduler;

    @Inject
    public ItemSelectionPresenterImpl(LooxLikeAPI mLooxLikeAPI, RxScheduler rxScheduler) {
        this.mLooxLikeAPI = mLooxLikeAPI;
        this.scheduler = rxScheduler;
    }


    @Override
    public void loadItemsForOrder(String orderId) {
        getView().showLoading();
        Observable<String[]> apiObservable = mLooxLikeAPI.getItemsOfOrder(orderId);
        Observable<String[]> scheduledObservable = scheduler.schedule(apiObservable);
        scheduledObservable.subscribe(new Action1<String[]>() {
            @Override
            public void call(String[] items) {
                getView().hideLoading();
                getView().setModel(items);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                getView().hideLoading();
            }
        });

    }
}
