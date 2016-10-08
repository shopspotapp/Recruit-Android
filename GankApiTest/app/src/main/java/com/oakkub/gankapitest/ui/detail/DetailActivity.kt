package com.oakkub.gankapitest.ui.detail

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.view.DraweeTransition
import com.oakkub.gankapitest.R
import com.oakkub.gankapitest.data.gank.model.Photo
import com.oakkub.gankapitest.extensions.doTransaction
import com.oakkub.gankapitest.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        val EXTRA_PHOTO = "extra:DETAIL_PHOTO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= 21) {
            val enterTransition = DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.FOCUS_CROP, ScalingUtils.ScaleType.FIT_CENTER)
            val returnTransition = DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.FIT_CENTER, ScalingUtils.ScaleType.FOCUS_CROP)

            window.sharedElementEnterTransition = enterTransition
            window.sharedElementReturnTransition = returnTransition
        }
        supportPostponeEnterTransition()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (savedInstanceState == null) {
            val photo: Photo = intent.getParcelableExtra<Photo>(EXTRA_PHOTO)
            supportFragmentManager.doTransaction {
                add(detailContainer.id, DetailFragment.createPhotoDetail(photo), MainActivity.DETAIL_TAG)
            }
        }
    }
}
