package com.example.dtmovie.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dtmovie.R;
import com.example.dtmovie.data.model.MovieCategory;
import com.example.dtmovie.databinding.ItemCategoryMovieBinding;
import com.example.dtmovie.ui.home.ItemCategoryViewModel;

import java.util.List;

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCategoryViewModel mViewModel;
        private ItemCategoryMovieBinding mBindingCategory;
        private MovieCategory mMovieCategory;
        private MovieAdapter mMovieAdapter;


        public ViewHolder(@NonNull ItemCategoryMovieBinding binding) {
            super(binding.getRoot());
            mBindingCategory = binding;
            mMovieAdapter = new MovieAdapter(mContext);
            mBindingCategory.recyclerMovie.setAdapter(mMovieAdapter);
            mBindingCategory.recyclerMovie.hasFixedSize();
        }

        public void bindData(MovieCategory movieCategory) {
            mMovieCategory = movieCategory;
            mViewModel = new ItemCategoryViewModel(mContext, mMovieCategory);
            mBindingCategory.setViewModel(mViewModel);
            mViewModel.mCategoryMovieObservableField.set(movieCategory);
            mViewModel.setItemListCategoryBinding(movieCategory);
            mBindingCategory.executePendingBindings();
        }
    }
}
