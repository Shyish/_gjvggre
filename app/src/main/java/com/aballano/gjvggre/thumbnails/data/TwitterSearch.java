package com.aballano.gjvggre.thumbnails.data;


import android.text.TextUtils;
import android.util.Base64;

import com.aballano.gjvggre.thumbnails.data.model.TokenResponse;
import com.aballano.gjvggre.thumbnails.data.model.TwitResponse;

import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Single;

public class TwitterSearch implements TwitterSearchRemoteDataSource {

    private static final String CONSUMER_KEY = "AaGvDUHTeohv1VEfsQ4ClpRzG";
    private static final String CONSUMER_SECRET = "hDtegDJ1q6BwBSr8bgIVKAjnn6z1ihkp23DEdlRs4Js0qfJEwD";

    private static final String BEARER_TOKEN_PARAM = "grant_type";
    private static final String BEARER_TOKEN_PARAM_VALUE = "client_credentials";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String QUERY_PARAM = "q";
    private static final String CONTENT_TYPE = "Content-Type: application/x-www-form-urlencoded;charset=UTF-8";
    private static final String IMG_FILTER = " filter:images";

    private String bearerToken = "";

    private final SearchEndpoint searchEndpoint;
    private final OauthEndpoint oauthEndpoint;

    public TwitterSearch(Retrofit retrofit) {
        searchEndpoint = retrofit.create(SearchEndpoint.class);
        oauthEndpoint = retrofit.create(OauthEndpoint.class);
    }

    // NOTE: Not my favorite approach, but for this challenge I prefer to do this here instead
    // of initializing the key in the Application or in the Activity/Presenter due to possible
    // race conditions. With more time my approach would be to put a splash screen with the app logo
    // so we ensure to have the bearerToken before any other call is made.
    private Single<String> obtainBearerToken() {
        if (TextUtils.isEmpty(bearerToken)) {
            String key = CONSUMER_KEY + ':' + CONSUMER_SECRET;
            String encodedKey = "Basic " + Base64.encodeToString(key.getBytes(), Base64.NO_WRAP);
            return oauthEndpoint.post(encodedKey, BEARER_TOKEN_PARAM_VALUE)
                    .map(TokenResponse::accessToken)
                    .doOnSuccess(accessToken -> bearerToken = accessToken);
        }

        return Single.just(bearerToken);
    }

    @Override
    public Single<TwitResponse> performSearch(String query) {
        return obtainBearerToken()
                .flatMap(bearerToken -> searchEndpoint.search("Bearer " + bearerToken, query + IMG_FILTER));
    }

    public interface SearchEndpoint {
        @GET("1.1/search/tweets.json?f=images")
        Single<TwitResponse> search(@Header(AUTHORIZATION_HEADER) String bearerKey, @Query(QUERY_PARAM) String query);
    }

    // For my example this could live in a BaseClass, same as the obtainBearerToke() method.
    public interface OauthEndpoint {
        @FormUrlEncoded
        @Headers(CONTENT_TYPE)
        @POST("oauth2/token")
        Single<TokenResponse> post(@Header(AUTHORIZATION_HEADER) String encodedKey, @Field(BEARER_TOKEN_PARAM) String param);
    }

}
