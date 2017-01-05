package com.aballano.gjvggre.thumbnails.domain.model;


import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.Serializable;

@AutoValue
public abstract class Twit implements Serializable {
    public static final Twit EMPTY = builder().thumbnailUrl("").userName("").userPicture("").build();

    public abstract String userName();
    public abstract String userPicture();
    public abstract String thumbnailUrl();

    public static TypeAdapter<Twit> typeAdapter(Gson gson) {
        return new AutoValue_Twit.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_Twit.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder thumbnailUrl(String thumbnailUrl);

        public abstract Builder userName(String userName);

        public abstract Builder userPicture(String userPicture);

        public abstract Twit build();
    }
}
