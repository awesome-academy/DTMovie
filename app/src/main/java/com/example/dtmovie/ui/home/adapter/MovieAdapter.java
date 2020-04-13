package com.example.dtmovie.ui.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
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

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewModel> {
    private ObservableList<Movie> mMovies;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

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

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding binding = DataBindingUtil.inflate(mLayoutInflater,
                R.layout.item_movie, parent, false);
        return new ViewModel(binding);
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

        public ViewModel(@NonNull ItemMovieBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemMovieViewModel();
            mBinding.setViewModel(mViewModel);
        }

        public void bindData(Movie movie) {
            mViewModel.setItemListNewsBiding(movie);
            mBinding.executePendingBindings();
        }
    }
}
