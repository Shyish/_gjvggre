package com.aballano.gjvggre.thumbnails.data.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class TokenResponse {
    @SerializedName("access_token")
    public abstract String accessToken();

    public static TypeAdapter<TokenResponse> typeAdapter(Gson gson) {
        return new AutoValue_TokenResponse.GsonTypeAdapter(gson);
    }
}
