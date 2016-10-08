package com.oakkub.gankapitest.ui.detail

import com.oakkub.gankapitest.data.gank.model.Photo
import com.oakkub.gankapitest.ui.base.MvpPresenter
import com.oakkub.gankapitest.ui.base.MvpView

/**
 * Created by OaKKuB on 10/5/2016.
 */
interface DetailContract {

    interface View : MvpView {
        fun getPhoto(): Photo
        fun onShowName(photo: Photo)
        fun onShowCreatedDate(photo: Photo)
        fun onShowImage(photo: Photo)
        fun onCloseDetailScreen()
    }

    interface Presenter : MvpPresenter {
        fun tapCloseButton()
    }

}