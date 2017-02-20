package com.shopspot.recruit.ui;

import com.shopspot.recruit.BasePresenter;
import com.shopspot.recruit.BaseView;

public interface ChatContract {
    interface View extends BaseView<Presenter> {
        void showLoading();

        void hideLoading();
    }

    interface Presenter extends BasePresenter {
    }
}
