package com.example.dtmovie.ui.movie_details.cast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dtmovie.R;
import com.example.dtmovie.data.model.Actor;
import com.example.dtmovie.databinding.ItemMovieCastBinding;

import java.util.List;

public class MovieCastApdapter extends RecyclerView.Adapter<MovieCastApdapter.ViewHolder> {
    private List<Actor> mActors;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public MovieCastApdapter(Context context,List<Actor> actors) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setActors(List<Actor> actors) {
        mActors = actors;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieCastBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_movie_cast, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mActors.get(position));
    }

    @Override
    public int getItemCount() {
        return mActors == null ? 0 : mActors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieCastViewModel mViewModel;
        private ItemMovieCastBinding mBinding;

        public ViewHolder(@NonNull ItemMovieCastBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemMovieCastViewModel();
            mBinding.setViewModel(mViewModel);
        }

        public void bindData(Actor actor) {
            mViewModel.mActors.set(actor);
        }
    }
}
