package com.example.dtmovie.ui.option;

import com.example.dtmovie.R;
import com.example.dtmovie.base.BaseFragment;
import com.example.dtmovie.base.BaseViewModel;

public class OptionsFragment extends BaseFragment {
    @Override
    protected int getVaribale() {
        return 0;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_options;
    }

    @Override
    protected BaseViewModel getViewModel() {
        return null;
    }

    public static OptionsFragment getInstance() {
        return new OptionsFragment();
    }
}
