package com.oakkub.gankapitest.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oakkub.gankapitest.MainApplication
import com.oakkub.gankapitest.R
import com.oakkub.gankapitest.data.gank.model.Photo
import com.oakkub.gankapitest.extensions.runOnPreDraw
import kotlinx.android.synthetic.main.fragment_detail.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by OaKKuB on 10/5/2016.
 */
class DetailFragment : Fragment(), DetailContract.View {

    @Inject
    lateinit var detailPresenter: DetailPresenter

    companion object {
        val EXTRA_PHOTO = "extra:photo"

        fun createPhotoDetail(photo: Photo): DetailFragment {
            val args = Bundle()
            args.putParcelable(EXTRA_PHOTO, photo)

            val detailFragment = DetailFragment()
            detailFragment.arguments = args
            return detailFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApplication.appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTransitionName(detailImageDraweeView, getPhoto()._id)
        view?.runOnPreDraw { activity.supportStartPostponedEnterTransition() }

        detailCloseImageButton.setColorFilter(Color.WHITE)
        detailCloseImageButton.setOnClickListener { detailPresenter.tapCloseButton() }

        detailPresenter.attachView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        detailPresenter.detachView()
    }

    override fun getPhoto(): Photo = arguments.getParcelable(EXTRA_PHOTO)

    override fun onShowName(photo: Photo) {
        detailDescTextView.text = photo.who
    }

    override fun onShowCreatedDate(photo: Photo) {
        detailDateTextView.text = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(photo.createdAt)
    }

    override fun onShowImage(photo: Photo) {
        detailImageDraweeView.setImageURI(photo.url)
    }

    override fun onCloseDetailScreen() {
        activity.supportFinishAfterTransition()
    }

}