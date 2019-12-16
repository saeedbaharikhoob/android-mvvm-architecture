package com.saeedbaharikhoob.testproject.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.saeedbaharikhoob.testproject.R;
import com.saeedbaharikhoob.testproject.databinding.ActivityNewsBinding;
import com.saeedbaharikhoob.testproject.model.retromodel.CommentRetro;
import com.saeedbaharikhoob.testproject.model.retromodel.LogicRespanseRetro;
import com.saeedbaharikhoob.testproject.model.NewsDesc;
import com.saeedbaharikhoob.testproject.model.retromodel.NewsRetro;
import com.saeedbaharikhoob.testproject.network.Urls;
import com.saeedbaharikhoob.testproject.utils.Account;
import com.saeedbaharikhoob.testproject.utils.G;
import com.saeedbaharikhoob.testproject.utils.HashTagView;
import com.saeedbaharikhoob.testproject.utils.IntentKeys;
import com.saeedbaharikhoob.testproject.utils.ItemImage;
import com.saeedbaharikhoob.testproject.utils.ItemText;
import com.saeedbaharikhoob.testproject.utils.interfaces.OnHashTagClickListener;
import com.saeedbaharikhoob.testproject.utils.SliderPack.Slider;
import com.saeedbaharikhoob.testproject.utils.SliderPack.SliderView;
import com.saeedbaharikhoob.testproject.utils.SliderViews;
import com.saeedbaharikhoob.testproject.view.adapter.CommentsAdapter;
import com.saeedbaharikhoob.testproject.view.di.componet.DaggerNewsComponent;
import com.saeedbaharikhoob.testproject.view.di.module.NewsModule;
import com.saeedbaharikhoob.testproject.view.interfaces.NewsNavigator;
import com.saeedbaharikhoob.testproject.viewmodel.NewsViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class NewsActivity extends AppCompatActivity implements NewsNavigator,OnHashTagClickListener {

    @Inject
    NewsViewModel newsViewModel;
    @Inject
    ActivityNewsBinding activityNewsBinding;
    @Inject
    TrackSelection.Factory videoTrackSelectionFactory;
    @Inject
    TrackSelector trackSelector;
    @Inject
    LoadControl loadControl;
    @Inject
    CommentsAdapter commentsAdapter;
    @Inject
    GridLayoutManager layoutManager;

    private SliderViews slider;
    private RelativeLayout layoutSlider;
    private LinearLayout indicator;
    private LinearLayout layoutItems;
    private PlayerView simpleExoPlayerView;
    private SimpleExoPlayer player;
    private int newsId;
    private RecyclerView listComment;
    private Button btnSend;
    private EditText editComment;
    private HashTagView txtHashtag;
    private TextView txtTitle;
    private TextView txtWriterName;
    private ImageView imgLike;
    private boolean likeStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerNewsComponent.builder().newsModule(new NewsModule(this)).build().inject(this);

        newsId = getIntent().getIntExtra(IntentKeys.NEWS_ID, 0);

        imgLike = activityNewsBinding.imgLike;
        txtHashtag = activityNewsBinding.txtHashtag;
        layoutSlider = activityNewsBinding.layoutSlider;
        editComment = activityNewsBinding.editComment;
        btnSend = activityNewsBinding.btnSend;
        txtTitle = activityNewsBinding.txtTitle;
        txtWriterName = activityNewsBinding.txtWriterName;
        slider = activityNewsBinding.slider;
        layoutItems = activityNewsBinding.layoutItems;
        indicator = activityNewsBinding.indicator;
        simpleExoPlayerView = activityNewsBinding.playerView;
        listComment = activityNewsBinding.listComment;
        listComment.setLayoutManager(layoutManager);
        listComment = activityNewsBinding.listComment;
        listComment.setAdapter(commentsAdapter);
        txtHashtag.setOnHashTagClickListener(this);
        newsViewModel.getNews(newsId);
        newsViewModel.getComments(newsId);
        newsViewModel.setNavigator(this);
        activityNewsBinding.setItem(newsViewModel);





        slider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < indicator.getChildCount(); i++) {
                    indicator.getChildAt(i).setBackgroundResource(R.drawable.ic_circle);
                    indicator.getChildAt(i).setAlpha(0.5f);
                }
                indicator.getChildAt(slider.getPos(position)).setBackgroundResource(R.drawable.ic_circle);
                indicator.getChildAt(slider.getPos(position)).setAlpha(1f);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (simpleExoPlayerView.getVisibility() == View.VISIBLE) {
            player.stop();
        }
    }

    @Override
    public void onClick(HashTagView v, char c, String tag) {
        Intent intent = G.tools.getIntent();
        intent.putExtra(IntentKeys.TEXT, tag);
        intent.setClass(this, TagsResultActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void handleError(Throwable throwable) {
        Toast.makeText(G.context, throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void newsResult(NewsRetro newsRetro) {
        setData(newsRetro);
    }

    @Override
    public void likePost() {
        if (Account.getInstant(G.context).getStatusLogin())
            newsViewModel.getLikeStatus(newsId);
        else
            Toast.makeText(G.context, getString(R.string.need_login), Toast.LENGTH_LONG).show();
    }

    @Override
    public void addComment() {
        if (Account.getInstant(G.context).getStatusLogin()) {

            if (!editComment.getText().toString().equals("") && editComment.getText().toString().length() > 5)
                newsViewModel.newComment(newsId, editComment.getText().toString());
            else
                Toast.makeText(this, getString(R.string.error_min_five_character), Toast.LENGTH_LONG).show();

        } else
            Toast.makeText(G.context, getString(R.string.need_login), Toast.LENGTH_LONG).show();
    }

    @Override
    public void commentResult(CommentRetro commentRetro) {
        commentsAdapter.setItems(commentRetro.getComments());
    }

    @Override
    public void addCommentResult(LogicRespanseRetro logicRespanseRetro) {
        if (logicRespanseRetro.isStatus()) {
            newsViewModel.getComments(newsId);
            editComment.setText("");
        }
    }

    @Override
    public void addLikeResult(LogicRespanseRetro logicRespanseRetro) {
        if (likeStatus) {
            setLikeStatus(false);
            likeStatus = false;
        } else {
            setLikeStatus(true);
            likeStatus = true;
        }
    }


    private void setData(NewsRetro newsRetro) {

        likeStatus = newsRetro.isLikeStatus();
        setLikeStatus(newsRetro.isLikeStatus());

        txtTitle.setText(newsRetro.getTitle());
        txtWriterName.setText(newsRetro.getWriterName());
        txtHashtag.setText(newsRetro.getTags());

        if (newsId > 0) {

            //player
            if (!newsRetro.getMedia().equals("")) {
                layoutSlider.setVisibility(View.GONE);
                simpleExoPlayerView.setVisibility(View.VISIBLE);


                player = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);
                simpleExoPlayerView.setUseController(true);
                simpleExoPlayerView.requestFocus();
                simpleExoPlayerView.setPlayer(player);

                MediaSource mediaSource = new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("exoplayer-codelab"))
                        .createMediaSource(Uri.parse(Urls.VIDEO_URL + newsRetro.getMedia()));

                player.prepare(mediaSource);
            } else {
                layoutSlider.setVisibility(View.VISIBLE);
                simpleExoPlayerView.setVisibility(View.GONE);

            }
            //slider
            if (newsRetro.getImages().size() > 0) {
                slider.removeAllImage();
                indicator.removeAllViews();
                ArrayList<SliderView.SliderFragmentItem> sliders = new ArrayList<>();
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(20, 20);
                lp.setMargins(5, 0, 0, 0);

                for (int i = 0; i < newsRetro.getImages().size(); i++) {

                    Slider s = new Slider("", newsRetro.getImages().get(i).getFilename(), "", "", "");
                    sliders.add(s);
                    ImageView imageView = new ImageView(this);
                    imageView.setId(i);
                    imageView.setBackgroundResource(R.drawable.ic_circle);
                    imageView.setLayoutParams(lp);
                    imageView.setAlpha(0.5f);
                    indicator.addView(imageView);
                }


                slider.setCustomAdapter(new SliderView.SliderFragmentAdapter(getSupportFragmentManager(), sliders, true, newsRetro));
                slider.pageSwitcher();
                if (sliders.size() > 0)
                    indicator.getChildAt(slider.getPos(0)).setAlpha(1f);

                //Description
                for (NewsDesc newsDesc : newsRetro.getDesc()) {
                    if (newsDesc.getType().equals("text")) {
                        ItemText itemText = G.tools.getItemText();
                        itemText.setTxt(newsDesc.getData());
                        layoutItems.addView(itemText.getView(this, null));
                    } else if (newsDesc.getType().equals("image")) {
                        ItemImage itemImage = G.tools.getItemImage();
                        itemImage.setImage(newsDesc.getData());
                        layoutItems.addView(itemImage.getView(this, null));
                    }


                }
            }
        }
    }

    private void setLikeStatus(boolean status) {
        if (status) {
            imgLike.setImageDrawable(getResources().getDrawable(R.drawable.ic_hurt_red));
        } else {
            imgLike.setImageDrawable(getResources().getDrawable(R.drawable.ic_hurt_dark));
        }
    }
}
