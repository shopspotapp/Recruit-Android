package com.oakkub.gankapitest.ui.views.recyclerviews.itemanimators


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import java.util.*

class SlideInItemAnimator @JvmOverloads constructor(private val slideFromEdge: Int = Gravity.BOTTOM) : DefaultItemAnimator() {

    companion object {
        private val DEFAULT_ADD_DURATION = 300L
    }

    private val pendingAdds = ArrayList<RecyclerView.ViewHolder>()
    private val fastOutSlowInInterpolator: FastOutSlowInInterpolator = FastOutSlowInInterpolator()

    override fun getAddDuration(): Long {
        return DEFAULT_ADD_DURATION
    }

    override fun animateAdd(holder: RecyclerView.ViewHolder): Boolean {
        val itemView = holder.itemView
        itemView.alpha = 0f

        when (slideFromEdge) {
            Gravity.START -> itemView.translationX = -itemView.width.toFloat()
            Gravity.END -> itemView.translationX = itemView.width.toFloat()
            Gravity.TOP -> itemView.translationY = -itemView.height.toFloat()
            else ->
                // Default is Gravity.BOTTOM
                itemView.translationY = itemView.height.toFloat()
        }

        pendingAdds += holder
        return true
    }

    override fun runPendingAnimations() {
        super.runPendingAnimations()

        for (holder in pendingAdds) {
            holder.itemView.animate()
                    .alpha(1f)
                    .translationX(0f)
                    .translationY(0f)
                    .setDuration(addDuration)
                    .setInterpolator(fastOutSlowInInterpolator)
                    .setListener(addAnimatorListener(holder))
        }
        pendingAdds.clear()
    }

    internal fun dispatchFinishedWhenDone() {
        if (!isRunning) {
            dispatchAnimationsFinished()
        }
    }

    internal fun resetAddAnimationViewValue(view: View) {
        view.alpha = 1f
        view.translationX = 0f
        view.translationY = 0f
    }

    private fun addAnimatorListener(holder: RecyclerView.ViewHolder): AnimatorListenerAdapter {
        return object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animator: Animator) {
                dispatchAddStarting(holder)
            }

            override fun onAnimationEnd(animator: Animator) {
                animator.listeners.remove(this)
                dispatchAddFinished(holder)
                dispatchFinishedWhenDone()
            }

            override fun onAnimationCancel(animator: Animator) {
                animator.listeners.remove(this)
                resetAddAnimationViewValue(holder.itemView)
            }
        }
    }
}