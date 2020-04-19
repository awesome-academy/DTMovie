package com.example.dtmovie.utils;

import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dtmovie.R;
import com.example.dtmovie.data.model.Genres;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.data.model.Video;
import com.example.dtmovie.ui.home.adapter.GenresAdapter;
import com.example.dtmovie.ui.home.adapter.MovieAdapter;
import com.example.dtmovie.ui.movie_details.trailer.MoiveTrailerAdapter;

import java.util.List;

public class BindingUtils {
    @BindingAdapter("setSplashAnimation")
    public static void setSplashAnimation(ImageView imageView, boolean isSet) {
        if (isSet) {
            imageView.setAnimation(
                    AnimationUtils.loadAnimation(imageView.getContext(), R.anim.animation_splash));
        }
    }

    @BindingAdapter("setImage")
    public static void setImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(StringUtils.getImageUrl(url)).centerCrop().fallback(R.drawable.giphy)
                .error(R.drawable.no_image)
                .into(imageView);
    }

    @BindingAdapter("setImage300")
    public static void setImage300(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(StringUtils.getImage300Url(url)).centerCrop()
                .fallback(R.drawable.giphy).error(R.drawable.no_image).into(imageView);
    }

    @BindingAdapter("setCircleImage")
    public static void setCircleImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(StringUtils.getImageUrl(url)).centerCrop().fallback(R.drawable.giphy)
                .apply(RequestOptions.circleCropTransform())
                .error(R.drawable.no_image)
                .into(imageView);
    }

    @BindingAdapter("bindGenres")
    public static void binGenres(RecyclerView recyclerView, List<Genres> genres) {
        GenresAdapter genresAdapter = (GenresAdapter) recyclerView.getAdapter();
        if (genresAdapter != null) {
            genresAdapter.update(genres);
        }
    }

    @BindingAdapter("bindSimilar")
    public static void bindSimilar(RecyclerView recyclerView, List<Movie> movies) {
        MovieAdapter movieAdapter = (MovieAdapter) recyclerView.getAdapter();
        if (movies != null) {
            if (movieAdapter != null) {
                movieAdapter.update(movies);
            }
        }
    }

    @BindingAdapter("bindTrailer")
    public static void bindTrailer(RecyclerView recyclerView, List<Video> videos) {
        MoiveTrailerAdapter moiveTrailerAdapter = (MoiveTrailerAdapter) recyclerView.getAdapter();
        if (videos != null) {
            if (moiveTrailerAdapter != null) {
                moiveTrailerAdapter.setVideos(videos);
            }
        }
    }

    @BindingAdapter("bindThumYoutube")
    public static void bindThumYoutube(ImageView imageView, String key) {
        Glide.with(imageView.getContext()).load(StringUtils.getThumYoutube(key)).
                fallback(R.drawable.giphy).error(R.drawable.no_image).into(imageView);
    }

}
