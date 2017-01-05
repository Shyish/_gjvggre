package com.aballano.gjvggre.thumbnails.domain.mapper;

import com.aballano.gjvggre.thumbnails.data.model.TwitResponse;
import com.aballano.gjvggre.thumbnails.data.model.TwitResponse.Status;
import com.aballano.gjvggre.thumbnails.domain.model.Twit;

import java.util.ArrayList;
import java.util.List;

public class TwitResponseMapper {

    public List<Twit> map(TwitResponse twitResponses) {
        List<Status> statuses = twitResponses.statuses();
        int twitResponsesSize = statuses.size();
        List<Twit> twits = new ArrayList<>();

        for (int i = 0; i < twitResponsesSize; i++) {
            Twit twit = mapSingle(statuses.get(i));
            if (!Twit.EMPTY.equals(twit)) {
                twits.add(twit);
            }

        }
        return twits;
    }

    private Twit mapSingle(Status status) {
        if (status.entities() != null) {
            List<TwitResponse.Media> media = status.entities().media();
            if (media != null && !media.isEmpty()) {
                TwitResponse.User user = status.user();
                return Twit.builder()
                        .thumbnailUrl(media.get(0).mediaUrl())
                        .userName(user.name())
                        .userPicture(user.profileImageUrl())
                        .build();
            }
        }

        // This will filter out any empty image
        return Twit.EMPTY;
    }
}
