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
import com.example.dtmovie.data.model.Genres;
import com.example.dtmovie.databinding.ItemGenresBinding;
import com.example.dtmovie.ui.home.ItemGenresViewModel;

import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {
    private ObservableList<Genres> mGenres;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public GenresAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mGenres = new ObservableArrayList<>();
    }

    public void update(List<Genres> genres) {
        mGenres.clear();
        mGenres.addAll(genres);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGenresBinding binding = DataBindingUtil.inflate(mLayoutInflater,
                R.layout.item_genres, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres == null ? 0 : mGenres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemGenresViewModel mViewModel;
        private ItemGenresBinding mBinding;

        public ViewHolder(ItemGenresBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemGenresViewModel();
            mBinding.setViewModel(mViewModel);
        }

        public void bindData(Genres genres) {
            mViewModel.mGenres.set(genres);
        }
    }
}
