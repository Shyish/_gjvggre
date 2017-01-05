package com.aballano.gjvggre.thumbnails.data.model;


import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class TwitResponse {
    @SerializedName("statuses")
    public abstract List<Status> statuses();

    public static TypeAdapter<TwitResponse> typeAdapter(Gson gson) {
        return new AutoValue_TwitResponse.GsonTypeAdapter(gson);
    }

    @AutoValue
    public static abstract class Status {
        @SerializedName("user")
        public abstract User user();

        @SerializedName("entities")
        public abstract Entities entities();

        public static TypeAdapter<Status> typeAdapter(Gson gson) {
            return new AutoValue_TwitResponse_Status.GsonTypeAdapter(gson);
        }
    }

    @AutoValue
    public abstract static class User {
        @SerializedName("name")
        public abstract String name();
        @SerializedName("profile_image_url")
        public abstract String profileImageUrl();

        public static TypeAdapter<User> typeAdapter(Gson gson) {
            return new AutoValue_TwitResponse_User.GsonTypeAdapter(gson);
        }
    }

    @AutoValue
    public abstract static class Entities {
        @SerializedName("media")
        public abstract List<Media> media();

        public static TypeAdapter<Entities> typeAdapter(Gson gson) {
            return new AutoValue_TwitResponse_Entities.GsonTypeAdapter(gson);
        }
    }

    @AutoValue
    public abstract static class Media {
        @SerializedName("media_url")
        public abstract String mediaUrl();

        public static TypeAdapter<Media> typeAdapter(Gson gson) {
            return new AutoValue_TwitResponse_Media.GsonTypeAdapter(gson);
        }
    }
}
