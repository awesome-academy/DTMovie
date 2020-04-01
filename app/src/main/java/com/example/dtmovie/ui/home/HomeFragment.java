package com.example.dtmovie.ui.home;

import com.example.dtmovie.R;
import com.example.dtmovie.base.BaseFragment;
import com.example.dtmovie.databinding.FragmentHomeBinding;

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

        return mViewModel;
    }
}
