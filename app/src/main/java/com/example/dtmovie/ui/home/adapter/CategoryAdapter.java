package com.example.dtmovie.ui.home.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dtmovie.R;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.data.model.MovieCategory;
import com.example.dtmovie.databinding.ItemCategoryMovieBinding;
import com.example.dtmovie.ui.home.ItemCategoryViewModel;
import com.example.dtmovie.ui.movie_details.MovieDetailActivity;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ObservableList<MovieCategory> mMovieCategories;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public CategoryAdapter(Context context) {
        mContext = context;
        mMovieCategories = new ObservableArrayList<>();
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void update(List<MovieCategory> movieCategories) {
        mMovieCategories.clear();
        mMovieCategories.addAll(movieCategories);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryMovieBinding binding = DataBindingUtil.inflate(mLayoutInflater,
                R.layout.item_category_movie, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mMovieCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovieCategories == null ? 0 : mMovieCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements ItemMovieListener {
        private ItemCategoryViewModel mViewModel;
        private ItemCategoryMovieBinding mBindingCategory;
        private MovieCategory mMovieCategory;
        private MovieAdapter mMovieAdapter;


        public ViewHolder(@NonNull ItemCategoryMovieBinding binding) {
            super(binding.getRoot());
            mBindingCategory = binding;
            mMovieAdapter = new MovieAdapter(mContext);
            mMovieAdapter.setListener(this);
            mBindingCategory.recyclerMovie.setAdapter(mMovieAdapter);
            mBindingCategory.recyclerMovie.hasFixedSize();
        }

        public void bindData(MovieCategory movieCategory) {
            mMovieCategory = movieCategory;
            mViewModel = new ItemCategoryViewModel(mContext, mMovieCategory);
            mBindingCategory.setViewModel(mViewModel);
            mViewModel.getMovie(movieCategory).observe((LifecycleOwner) mContext, new Observer<List<Movie>>() {
                @Override
                public void onChanged(List<Movie> movies) {
                    mMovieAdapter.update(movies);
                }
            });
            mViewModel.mCategoryMovieObservableField.set(movieCategory);
            mViewModel.setItemListCategoryBinding(movieCategory);
            mBindingCategory.executePendingBindings();
        }

        @Override
        public void onItemMovieClick(Movie movie) {
            mContext.startActivity(MovieDetailActivity.getIntent(mContext, movie));
        }
    }
}
