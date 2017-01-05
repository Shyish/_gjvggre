package com.aballano.gjvggre.thumbnails.domain.usecase;


import com.aballano.gjvggre.thumbnails.data.TwitterSearchRemoteDataSource;
import com.aballano.gjvggre.thumbnails.domain.mapper.TwitResponseMapper;
import com.aballano.gjvggre.thumbnails.domain.model.Twit;

import java.util.List;

import rx.Scheduler;
import rx.Single;

public class SearchInteractor {

    private final TwitterSearchRemoteDataSource remoteDataSource;
    private final Scheduler subscribeScheduler;
    private final Scheduler mainScheduler;
    private final TwitResponseMapper twitResponseMapper;

    public SearchInteractor(TwitterSearchRemoteDataSource remoteDataSource,
                            Scheduler subscribeScheduler, Scheduler mainScheduler,
                            TwitResponseMapper twitResponseMapper) {

        this.remoteDataSource = remoteDataSource;
        this.subscribeScheduler = subscribeScheduler;
        this.mainScheduler = mainScheduler;
        this.twitResponseMapper = twitResponseMapper;
    }

    public Single<List<Twit>> performSearch(String query) {
        return remoteDataSource.performSearch(query)
                .map(twitResponseMapper::map)
                .subscribeOn(subscribeScheduler)
                .observeOn(mainScheduler);
    }
}
