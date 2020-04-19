package com.example.dtmovie.ui.movie_details.trailer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dtmovie.R;
import com.example.dtmovie.data.model.Video;
import com.example.dtmovie.databinding.ItemMovieTrailerBinding;
import com.example.dtmovie.ui.movie_details.OnTrailerListener;

import java.util.List;

public class MoiveTrailerAdapter extends RecyclerView.Adapter<MoiveTrailerAdapter.ViewHolder> {
    private List<Video> mVideos;
    private Context mContext;
    private OnTrailerListener mListener;
    private LayoutInflater mLayoutInflater;

    public MoiveTrailerAdapter(List<Video> videos, Context context, OnTrailerListener listener) {
        mVideos = videos;
        mContext = context;
        mListener = listener;
        mLayoutInflater = LayoutInflater.from(context);

    }

    public MoiveTrailerAdapter(List<Video> videos, Context context) {
        mVideos = videos;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);

    }

    public MoiveTrailerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mVideos = new ObservableArrayList<>();
    }

    public void setVideos(List<Video> videos) {
        mVideos = videos;
        notifyDataSetChanged();
    }

    public void update(List<Video> videos) {
        mVideos.clear();
        mVideos.addAll(videos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieTrailerBinding binding = DataBindingUtil.
                inflate(mLayoutInflater, R.layout.item_movie_trailer, parent, false);
        return new ViewHolder(binding, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mVideos.get(position));
    }

    @Override
    public int getItemCount() {
        return mVideos == null ? 0 : mVideos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMoiveTrailerViewModel mViewModel;
        private ItemMovieTrailerBinding mBinding;
        private OnTrailerListener mListener;

        public ViewHolder(ItemMovieTrailerBinding binding, OnTrailerListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mListener = listener;
            mViewModel = new ItemMoiveTrailerViewModel();
            mBinding.setViewModel(mViewModel);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onPlayTrailer(mBinding.getViewModel().mVideo.get().getKey());
                }
            });
        }

        public void bindData(Video video) {
            mViewModel.mVideo.set(video);
        }
    }
}
