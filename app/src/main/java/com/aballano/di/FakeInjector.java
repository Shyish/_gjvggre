package com.aballano.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.aballano.common.GenericAdapterFactory;
import com.aballano.gjvggre.thumbnails.data.TwitterSearch;
import com.aballano.gjvggre.thumbnails.data.TwitterSearchRemoteDataSource;
import com.aballano.gjvggre.thumbnails.domain.mapper.TwitResponseMapper;
import com.aballano.gjvggre.thumbnails.domain.usecase.SearchInteractor;
import com.aballano.gjvggre.thumbnails.presentation.presenter.TwitThumbnailsPresenter;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

// Dummy class to emulate Dagger2 work
public final class FakeInjector {

    public static final String BASE_URL = "https://api.twitter.com/";

    private FakeInjector() {
    }

    public static TwitThumbnailsPresenter provideTwitThumbnailsPresenter() {
        return new TwitThumbnailsPresenter(provideSearchInteractor());
    }

    private static SearchInteractor provideSearchInteractor() {
        return new SearchInteractor(provideSearchRemoteDataSource(), Schedulers.io(), AndroidSchedulers.mainThread(),
                provideTwitResponseMapper());
    }

    private static TwitResponseMapper provideTwitResponseMapper() {
        return new TwitResponseMapper();
    }

    private static TwitterSearchRemoteDataSource provideSearchRemoteDataSource() {
        // We could also inject Retrofit here if we're using the same endpoints
        // or use annotations to provide different instances with different endpoints
        return new TwitterSearch(provideRetrofit());
    }

    private static Retrofit provideRetrofit() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(GenericAdapterFactory.create())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(provideLoggingInterceptor());

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
    }

    private static HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}
