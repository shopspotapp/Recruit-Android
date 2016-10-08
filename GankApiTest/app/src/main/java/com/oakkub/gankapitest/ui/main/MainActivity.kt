package com.oakkub.gankapitest.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.oakkub.gankapitest.R
import com.oakkub.gankapitest.data.gank.model.Photo
import com.oakkub.gankapitest.extensions.doTransaction
import com.oakkub.gankapitest.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainFragment.Listener {

    companion object {
        val CONTENT_TAG = "tag:MAIN_CONTENT_CONTAINER"
        val DETAIL_TAG = "tag:DETAIL_CONTAINER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.doTransaction {
                add(mainContentContainer.id, MainFragment(), CONTENT_TAG)
            }
        }
    }

    override fun onPhotoItemClick(view: View, photo: Photo) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_PHOTO, photo)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, photo._id)
        ActivityCompat.startActivity(this, intent, options.toBundle())
    }

}
