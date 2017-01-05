package com.aballano.gjvggre.thumbnails.presentation.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.aballano.R;
import com.aballano.gjvggre.thumbnails.domain.model.Twit;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FullScreenImageActivity extends AppCompatActivity {

    private static final String EXTRA_TWIT = "extra_twit";
    @BindView(R.id.fullScreenImageView)
    ImageView fullScreenImageView;
    @BindView(R.id.userProfilePictureImageView)
    ImageView userProfilePictureImageView;
    @BindView(R.id.userNameTextView)
    TextView userNameTextView;

    private Unbinder unbinder;

    public static void startActivity(Context context, Twit twit) {
        Intent intent = new Intent(context, FullScreenImageActivity.class);
        intent.putExtra(EXTRA_TWIT, twit);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen_image_screen);
        unbinder = ButterKnife.bind(this);

        Twit twit = (Twit) getIntent().getSerializableExtra(EXTRA_TWIT);

        initUi(twit);
    }

    private void initUi(Twit twit) {
        Picasso picasso = Picasso.with(this);

        picasso.load(twit.thumbnailUrl()).into(fullScreenImageView);
        picasso.load(twit.userPicture()).into(userProfilePictureImageView);
        userNameTextView.setText(getString(R.string.format_user_name, twit.userName()));
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
