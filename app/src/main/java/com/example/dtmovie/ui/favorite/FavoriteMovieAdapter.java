package com.example.dtmovie.ui.favorite;

import android.content.Context;
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
import com.example.dtmovie.databinding.ItemFavoriteMovieBinding;
import com.example.dtmovie.ui.home.ItemMovieViewModel;
import com.example.dtmovie.ui.home.adapter.ItemMovieListener;

import java.util.List;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder> {
    private ObservableList<Movie> mMovies;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ItemMovieListener mListener;

    public FavoriteMovieAdapter(Context context) {
        mMovies = new ObservableArrayList<>();
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void setListener(ItemMovieListener listener) {
        mListener = listener;
    }

    public  void update(List<Movie> movies){
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFavoriteMovieBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_favorite_movie, parent, false);
        return new ViewHolder(binding, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemFavoriteMovieBinding mBinding;
        private ItemMovieViewModel mViewModel;

        public ViewHolder(@NonNull ItemFavoriteMovieBinding binding, ItemMovieListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemMovieViewModel(listener);
            mBinding.setViewModel(mViewModel);

        }

        public void bindData(Movie movie) {
            mViewModel.mMovies.set(movie);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int postion = getAdapterPosition();
                    mListener.onItemMovieClick(mMovies.get(postion));
                }
            });
        }
    }
}
