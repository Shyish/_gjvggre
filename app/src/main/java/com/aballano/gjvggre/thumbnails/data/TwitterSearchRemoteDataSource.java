package com.aballano.gjvggre.thumbnails.data;


import com.aballano.gjvggre.thumbnails.data.model.TwitResponse;

import rx.Single;

public interface TwitterSearchRemoteDataSource {
    Single<TwitResponse> performSearch(String query);
}
