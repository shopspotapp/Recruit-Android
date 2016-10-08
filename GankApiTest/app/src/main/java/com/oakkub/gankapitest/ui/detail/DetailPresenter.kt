package com.oakkub.gankapitest.ui.detail

import com.oakkub.gankapitest.ui.base.BasePresenter
import com.oakkub.gankapitest.ui.base.MvpView
import javax.inject.Inject

/**
 * Created by OaKKuB on 10/5/2016.
 */
class DetailPresenter @Inject constructor() : BasePresenter<DetailContract.View>(), DetailContract.Presenter {

    override fun onViewAttached(view: MvpView) {
        view()?.getPhoto()?.let {
            view()?.onShowName(it)
            view()?.onShowCreatedDate(it)
            view()?.onShowImage(it)
        }
    }

    override fun onViewDetached() { }

    override fun tapCloseButton() {
        view()?.onCloseDetailScreen()
    }
}