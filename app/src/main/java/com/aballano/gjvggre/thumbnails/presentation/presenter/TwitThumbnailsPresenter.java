package com.aballano.gjvggre.thumbnails.presentation.presenter;


import com.aballano.gjvggre.thumbnails.domain.model.Twit;
import com.aballano.gjvggre.thumbnails.domain.usecase.SearchInteractor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;

public class TwitThumbnailsPresenter {

    private static final long SEARCH_DELAY = 300;
    private final SearchInteractor searchInteractor;
    private View view;

    public TwitThumbnailsPresenter(SearchInteractor searchInteractor) {
        this.searchInteractor = searchInteractor;
    }

    public Subscription init(View view) {
        this.view = view;

        // Simplified version, some retry policy should be added
        return view.getSearchObservable()
                .debounce(SEARCH_DELAY, TimeUnit.MILLISECONDS)
                .switchMap(charSequence -> searchInteractor.performSearch(charSequence.toString()).toObservable())
                .subscribe(this::processTwits, Throwable::printStackTrace);
    }

    private void processTwits(List<Twit> twits) {
        view.clearItems();
        view.showItems(twits);
    }

    public void onThumbnailClicked(Twit twit) {
        view.navigateToFullImageActivityWith(twit);
    }

    public interface View {
        Observable<CharSequence> getSearchObservable();

        void clearItems();

        void showItems(List twits);

        void navigateToFullImageActivityWith(Twit twit);
    }
}
