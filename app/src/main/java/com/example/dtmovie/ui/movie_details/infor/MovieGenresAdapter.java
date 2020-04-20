package com.example.dtmovie.ui.movie_details.infor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dtmovie.R;
import com.example.dtmovie.data.model.Genres;
import com.example.dtmovie.databinding.ItemGenresBinding;
import com.example.dtmovie.ui.home.ItemGenresViewModel;

import java.util.List;

public class MovieGenresAdapter extends RecyclerView.Adapter<MovieGenresAdapter.ViewHolder> {
    private List<Genres> mGenres;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public MovieGenresAdapter(Context context, List<Genres> genres) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setGenres(List<Genres> genres) {
        mGenres = genres;
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
