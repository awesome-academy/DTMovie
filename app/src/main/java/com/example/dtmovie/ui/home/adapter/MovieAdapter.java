package com.example.dtmovie.ui.home.adapter;

import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dtmovie.R;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.databinding.ItemMovieBinding;
import com.example.dtmovie.ui.home.ItemMovieViewModel;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewModel> {
    private ObservableList<Movie> mMovies;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ItemMovieListener mListener;

    public MovieAdapter(Context context) {
        mMovies = new ObservableArrayList<>();
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void update(List<Movie> movie) {
        mMovies.clear();
        mMovies.addAll(movie);
        notifyDataSetChanged();
    }

    public void setListener(ItemMovieListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding binding = DataBindingUtil.inflate(mLayoutInflater,
                R.layout.item_movie, parent, false);
        return new ViewModel(binding, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel holder, int position) {
        holder.bindData(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public class ViewModel extends RecyclerView.ViewHolder {
        private ItemMovieViewModel mViewModel;
        private ItemMovieBinding mBinding;
        private long mLastClickTime = 0;
        public static final int MAX_CLICK_TIME = 3000;

        public ViewModel(@NonNull ItemMovieBinding binding, ItemMovieListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemMovieViewModel(listener);
            mBinding.setViewModel(mViewModel);
        }

        public void bindData(Movie movie) {
            mViewModel.setItemListNewsBiding(movie);
            mBinding.executePendingBindings();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SystemClock.elapsedRealtime() - mLastClickTime < MAX_CLICK_TIME) {
                        return;
                    }
                    mLastClickTime = SystemClock.elapsedRealtime();
                    int position = getAdapterPosition();
                    mListener.onItemMovieClick(mMovies.get(position));
                }
            });
        }
    }
}
