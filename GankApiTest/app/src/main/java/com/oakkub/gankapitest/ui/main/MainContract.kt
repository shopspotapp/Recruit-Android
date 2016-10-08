package com.oakkub.gankapitest.ui.main

import com.oakkub.gankapitest.data.gank.model.Photo
import com.oakkub.gankapitest.ui.base.MvpPresenter
import com.oakkub.gankapitest.ui.base.MvpView

/**
 * Created by OaKKuB on 10/3/2016.
 */
interface MainContract {

    interface View : MvpView {
        fun onAddSecondaryItemLoading()
        fun onRemoveSecondaryItemLoading()
        fun onSecondaryPhotoListReceive(photoList: List<Photo>)
        fun onSecondaryPhotoListError(throwable: Throwable)
        fun onAddPrimaryItemLoading()
        fun onRemovePrimaryItemLoading()
        fun onPrimaryPhotoListReceive(photoList: List<Photo>)
        fun onPrimaryPhotoListError(throwable: Throwable)
    }

    interface Presenter : MvpPresenter {}

}