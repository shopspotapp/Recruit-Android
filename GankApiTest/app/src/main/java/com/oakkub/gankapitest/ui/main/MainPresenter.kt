package com.oakkub.gankapitest.ui.main

import com.oakkub.gankapitest.data.gank.GankRepository
import com.oakkub.gankapitest.data.gank.model.Photo
import com.oakkub.gankapitest.extensions.runIfEmpty
import com.oakkub.gankapitest.extensions.runIfNotEmpty
import com.oakkub.gankapitest.ui.base.BasePresenter
import com.oakkub.gankapitest.ui.base.MvpView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by OaKKuB on 10/3/2016.
 */
@Singleton
class MainPresenter @Inject constructor(val gankApiService: GankRepository): BasePresenter<MainContract.View>(), MainContract.Presenter {

    val primaryPhotoList: ArrayList<Photo> = ArrayList()
    val secondaryPhotoList: ArrayList<Photo> = ArrayList()

    /**
     * Use to load odd number page
     */
    var primaryPhotoPage: Int = 1

    /**
     * Use to load even number page
     */
    var secondaryPhotoPage: Int = 2

    var isPrimaryPhotoLoading: Boolean = false
    var isSecondaryPhotoLoading: Boolean = false

    var isPrimaryPhotoLoadComplete: Boolean = false
    var isSecondaryPhotoLoadComplete: Boolean = false

    override fun onViewAttached(view: MvpView) {
        fetchPrimaryPhotos()
        fetchSecondaryPhotos()
    }

    override fun onViewDetached() { }

    fun incrementPrimaryPage() {
        primaryPhotoPage += 2
    }

    fun incrementSecondaryPage() {
        secondaryPhotoPage += 2
    }

    fun fetchPrimaryPhotos(isLoadingMore: Boolean = false) {
        primaryPhotoList.runIfNotEmpty {
            if (!isLoadingMore) {
                view()?.onAddPrimaryItemLoading()
                onPrimaryPhotoReceive(this)
                return
            }
        }

        Observable.fromCallable { isPrimaryPhotoLoading }
                .filter { !isPrimaryPhotoLoadComplete }
                .filter { !isPrimaryPhotoLoading }
                .doOnNext { isPrimaryPhotoLoading = true }
                .doOnNext { if (isLoadingMore) incrementPrimaryPage() }
                .doOnNext { view()?.onAddPrimaryItemLoading() }
                .observeOn(Schedulers.io())
                .flatMap { gankApiService.getPhotos(primaryPhotoPage) }
                .doOnNext { it.filter { primaryPhotoList.indexOf(it) == -1 }.map { primaryPhotoList.add(it) } }
                .doOnNext { it.runIfEmpty { isPrimaryPhotoLoadComplete = true } }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: Observer<List<Photo>> {
                    override fun onNext(value: List<Photo>) {
                        onPrimaryPhotoReceive(value)
                    }

                    override fun onError(e: Throwable) {
                        isPrimaryPhotoLoading = false
                        view()?.onPrimaryPhotoListError(e)
                    }

                    override fun onSubscribe(d: Disposable?) { }

                    override fun onComplete() {
                        isPrimaryPhotoLoading = false
                    }
                })
    }

    fun onPrimaryPhotoReceive(value: List<Photo>) {
        view()?.onRemovePrimaryItemLoading()
        view()?.onPrimaryPhotoListReceive(value)
    }

    fun fetchSecondaryPhotos(isLoadingMore: Boolean = false) {
        secondaryPhotoList.runIfNotEmpty {
            if (!isLoadingMore) {
                view()?.onAddSecondaryItemLoading()
                onSecondaryPhotoReceive(this)
                return
            }
        }

        Observable.fromCallable { isSecondaryPhotoLoading }
                .filter { !isSecondaryPhotoLoadComplete }
                .filter { !isSecondaryPhotoLoading }
                .doOnNext { isSecondaryPhotoLoading = true }
                .doOnNext { if (isLoadingMore) incrementSecondaryPage() }
                .doOnNext { view()?.onAddSecondaryItemLoading() }
                .observeOn(Schedulers.io())
                .flatMap { gankApiService.getPhotos(secondaryPhotoPage) }
                .doOnNext { it.filter { secondaryPhotoList.indexOf(it) == -1 }.map { secondaryPhotoList.add(it) } }
                .doOnNext { it.runIfEmpty { isSecondaryPhotoLoadComplete = true } }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: Observer<List<Photo>> {
                    override fun onNext(value: List<Photo>) {
                        onSecondaryPhotoReceive(value)
                    }

                    override fun onError(e: Throwable) {
                        isSecondaryPhotoLoading = false
                        view()?.onSecondaryPhotoListError(e)
                    }

                    override fun onSubscribe(d: Disposable?) { }

                    override fun onComplete() {
                        isSecondaryPhotoLoading = false
                    }
                })
    }

    fun onSecondaryPhotoReceive(value: List<Photo>) {
        view()?.onRemoveSecondaryItemLoading()
        view()?.onSecondaryPhotoListReceive(value)
    }

}