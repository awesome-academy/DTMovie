package com.example.dtmovie.ui.favorite;

import com.example.dtmovie.R;
import com.example.dtmovie.base.BaseFragment;
import com.example.dtmovie.base.BaseViewModel;

public class FavoriteFragment extends BaseFragment {
    @Override
    protected int getVaribale() {
        return 0;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_favorive;
    }

    @Override
    protected BaseViewModel getViewModel() {
        return null;
    }

    public static FavoriteFragment getInstance() {
        return new FavoriteFragment();
    }
}
