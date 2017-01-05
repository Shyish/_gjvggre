package com.aballano.gjvggre.thumbnails.presentation.ui;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.aballano.R;
import com.aballano.di.FakeInjector;
import com.aballano.gjvggre.thumbnails.domain.model.Twit;
import com.aballano.gjvggre.thumbnails.presentation.presenter.TwitThumbnailsPresenter;
import com.aballano.gjvggre.thumbnails.presentation.renderer.TwitThumbnailRenderer;
import com.pedrogomez.renderers.RendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscription;

public class TwitThumbnailsActivity extends AppCompatActivity implements TwitThumbnailsPresenter.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private RendererAdapter adapter;
    private Unbinder unbinder;

    private final TwitThumbnailsPresenter presenter = FakeInjector.provideTwitThumbnailsPresenter();
    private final View.OnClickListener onThumbnailClick = view -> presenter.onThumbnailClicked((Twit) view.getTag());

    private EditText searchEditText;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twit_thumbnails_screen);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        View.inflate(this, R.layout.search_edittext, toolbar);
        searchEditText = ButterKnife.findById(toolbar, R.id.search_toolbar_edittext);
        // Default text for the challenge
        searchEditText.setText("#lovewhereyouwork");

        RendererBuilder builder = new RendererBuilder<>()
                .bind(Twit.class, new TwitThumbnailRenderer(onThumbnailClick));

        adapter = new RendererAdapter(builder);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        subscription = presenter.init(this);
    }

    @Override
    public Observable<CharSequence> getSearchObservable() {
        return RxTextView.textChanges(searchEditText);
    }

    @Override
    public void clearItems() {
        adapter.clearAndNotify();
    }

    @Override
    public void showItems(List items) {
        adapter.addAllAndNotify(items);
    }

    @Override
    public void navigateToFullImageActivityWith(Twit twit) {
        FullScreenImageActivity.startActivity(this, twit);
    }

    @Override
    protected void onDestroy() {
        subscription.unsubscribe();
        unbinder.unbind();
        super.onDestroy();
    }
}
