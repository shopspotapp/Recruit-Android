package com.oakkub.gankapitest.ui.base

/**
 * Created by OaKKuB on 10/3/2016.
 */
abstract class BasePresenter<V : MvpView> {

    private var view: V? = null

    fun attachView(view: V) {
        this.view = view
        onViewAttached(view)
    }

    fun detachView() {
        view = null
        onViewDetached()
    }

    fun view() : V? = view

    abstract fun onViewAttached(view: MvpView)
    abstract fun onViewDetached()

}