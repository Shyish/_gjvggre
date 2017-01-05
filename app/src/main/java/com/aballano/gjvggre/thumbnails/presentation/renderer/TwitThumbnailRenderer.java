package com.aballano.gjvggre.thumbnails.presentation.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aballano.R;
import com.aballano.gjvggre.thumbnails.domain.model.Twit;
import com.pedrogomez.renderers.Renderer;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TwitThumbnailRenderer extends Renderer<Twit> {

    private final View.OnClickListener onThumbnailClick;

    @BindView(R.id.thumbnailImageView)
    ImageView thumbnailImageView;

    public TwitThumbnailRenderer(View.OnClickListener onThumbnailClick) {
        this.onThumbnailClick = onThumbnailClick;
    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.twit_thumbnail_list_item, parent, false);
        ButterKnife.bind(this, view);

        thumbnailImageView.setOnClickListener(onThumbnailClick);
        return view;
    }

    @Override
    public void render(List<Object> payloads) {
        Twit content = getContent();
        thumbnailImageView.setTag(content);

        Picasso.with(getContext()).load(content.thumbnailUrl()).into(thumbnailImageView);
    }
}
