package com.example.dtmovie.ui.movie_details.producer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dtmovie.R;
import com.example.dtmovie.data.model.Company;
import com.example.dtmovie.databinding.ItemMovieProduceBinding;

import java.util.List;

public class MovieProduceAdapter extends RecyclerView.Adapter<MovieProduceAdapter.ViewHolder> {
    private List<Company> mCompanies;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public MovieProduceAdapter(List<Company> companies, Context context) {
        mCompanies = companies;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setCompanies(List<Company> companies) {
        mCompanies = companies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieProduceBinding binding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_movie_produce, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mCompanies.get(position));
    }

    @Override
    public int getItemCount() {
        return mCompanies == null ? 0 : mCompanies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieProducerViewModel mViewModel;
        private ItemMovieProduceBinding mBinding;

        public ViewHolder(@NonNull ItemMovieProduceBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemMovieProducerViewModel();
            mBinding.setViewModel(mViewModel);
        }

        public void bindData(Company company) {
            mViewModel.mCompanys.set(company);
        }
    }
}
