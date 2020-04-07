package com.example.dtmovie.ui.home;

import androidx.lifecycle.ViewModelProvider;

import com.example.dtmovie.R;
import com.example.dtmovie.base.BaseFragment;
import com.example.dtmovie.databinding.FragmentHomeBinding;

import java.util.Objects;

public class HomeFragment extends BaseFragment {
    private HomeViewModel mViewModel;
    private FragmentHomeBinding mBinding;

    @Override
    protected int getVaribale() {
        return 0;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeViewModel getViewModel() {
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(
                Objects.requireNonNull(getActivity()).getApplication()).
                create(HomeViewModel.class);
        mViewModel.initViewModel(getContext());
        return mViewModel;
    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }
}
