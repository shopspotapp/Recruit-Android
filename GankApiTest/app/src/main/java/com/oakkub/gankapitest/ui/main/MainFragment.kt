package com.oakkub.gankapitest.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oakkub.gankapitest.MainApplication
import com.oakkub.gankapitest.R
import com.oakkub.gankapitest.data.gank.model.Loading
import com.oakkub.gankapitest.data.gank.model.Photo
import com.oakkub.gankapitest.extensions.isPortrait
import com.oakkub.gankapitest.extensions.showToast
import com.oakkub.gankapitest.ui.views.recyclerviews.adapter.OnItemClickListener
import com.oakkub.gankapitest.ui.views.recyclerviews.adapter.PhotoItemAdapter
import com.oakkub.gankapitest.ui.views.recyclerviews.itemdecorations.GridOffsetItemDecoration
import com.oakkub.gankapitest.ui.views.recyclerviews.scrolllistener.InfiniteScrollListener
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by OaKKuB on 10/3/2016.
 */
class MainFragment : Fragment(), MainContract.View {

    @Inject
    lateinit var mainPresenter: MainPresenter

    var primaryPhotoItemAdapter by Delegates.notNull<PhotoItemAdapter>()
    var secondaryPhotoItemAdapter by Delegates.notNull<PhotoItemAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPrimaryRecyclerView()
        initSecondaryRecyclerView()
        mainPresenter.attachView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainPresenter.detachView()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun initPrimaryRecyclerView() {
        primaryPhotoRecyclerView.addItemDecoration(getOffsetItemDecoration())
        primaryPhotoRecyclerView.layoutManager = StaggeredGridLayoutManager(
                if (resources.configuration.isPortrait()) 2 else 3,
                StaggeredGridLayoutManager.VERTICAL)

        primaryPhotoRecyclerView.addOnScrollListener(object: InfiniteScrollListener(primaryPhotoRecyclerView.layoutManager) {
            override fun onLoadMore(totalItemCount: Int) {
                mainPresenter.fetchPrimaryPhotos(true)
            }
        })

        primaryPhotoItemAdapter = PhotoItemAdapter(R.layout.item_primary_photo,
                onItemClickListener = onPrimaryItemClickListener)
        primaryPhotoRecyclerView.adapter = primaryPhotoItemAdapter
    }

    private fun initSecondaryRecyclerView() {
        secondaryPhotoRecyclerView.addItemDecoration(getOffsetItemDecoration())
        secondaryPhotoRecyclerView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        secondaryPhotoRecyclerView.addOnScrollListener(object: InfiniteScrollListener(secondaryPhotoRecyclerView.layoutManager) {
            override fun onLoadMore(totalItemCount: Int) {
                mainPresenter.fetchSecondaryPhotos(true)
            }
        })

        secondaryPhotoItemAdapter = PhotoItemAdapter(R.layout.item_secondary_photo,
                shouldPaddingEvenPosition = true,
                onItemClickListener = onSecondaryItemClickListener)
        secondaryPhotoRecyclerView.adapter = secondaryPhotoItemAdapter
    }

    private fun getOffsetItemDecoration() = GridOffsetItemDecoration(resources.getDimension(R.dimen.photo_item_space).toInt(), true)

    override fun onAddSecondaryItemLoading() {
        secondaryPhotoItemAdapter.add(Loading())
    }

    override fun onRemoveSecondaryItemLoading() {
        secondaryPhotoItemAdapter.removeLast()
    }

    override fun onSecondaryPhotoListReceive(photoList: List<Photo>) {
        secondaryPhotoItemAdapter.addAll(photoList)
    }

    override fun onSecondaryPhotoListError(throwable: Throwable) {
        context.showToast(throwable.toString())
    }

    override fun onAddPrimaryItemLoading() {
        primaryPhotoItemAdapter.add(Loading())
    }

    override fun onRemovePrimaryItemLoading() {
        primaryPhotoItemAdapter.removeLast()
    }

    override fun onPrimaryPhotoListReceive(photoList: List<Photo>) {
        primaryPhotoItemAdapter.addAll(photoList)
    }

    override fun onPrimaryPhotoListError(throwable: Throwable) {
        context.showToast(throwable.toString())
    }

    val onPrimaryItemClickListener: OnItemClickListener = object : OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            (activity as Listener).onPhotoItemClick(view, primaryPhotoItemAdapter.getItem(position) as Photo)
        }

        override fun onItemLongClick(view: View, position: Int) { }
    }

    val onSecondaryItemClickListener: OnItemClickListener = object : OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            (activity as Listener).onPhotoItemClick(view, secondaryPhotoItemAdapter.getItem(position) as Photo)
        }

        override fun onItemLongClick(view: View, position: Int) { }
    }

    interface Listener {
        fun onPhotoItemClick(view: View, photo: Photo)
    }

}
