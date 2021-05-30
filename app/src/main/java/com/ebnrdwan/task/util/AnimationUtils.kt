package com.ebnrdwan.task.util


import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup


object AnimationUtils {

    private const val ANIMATION_TYPE_ROTATION = "rotation"


    fun startReversedRotatedAnimation(
        startRange: Int, endRange: Int,
        view: View,
        fromValue: Float,
        toValue: Float,
        animationDuration: Int
    ): AnimatorSet {
        val anim: ValueAnimator =
            ValueAnimator.ofInt(startRange, endRange)
        anim.addUpdateListener { valueAnimator ->
            val layoutParams: ViewGroup.LayoutParams = view.layoutParams
            layoutParams.height = valueAnimator.animatedValue as Int
            layoutParams.width = valueAnimator.animatedValue as Int
            view.layoutParams = layoutParams
        }

        val animationSet = AnimatorSet()
        animationSet.playTogether(
            ObjectAnimator.ofFloat(
                view,
                ANIMATION_TYPE_ROTATION,
                fromValue,
                toValue

            ).also {
                it.repeatCount = ValueAnimator.INFINITE
            }, anim.also {
                it.repeatMode = ValueAnimator.REVERSE
                it.repeatCount = ValueAnimator.INFINITE
            }
        )
        animationSet.duration = animationDuration.toLong()
        animationSet.start()
        return animationSet
    }

    fun clearAnimation(animationSet: AnimatorSet) {
        animationSet.apply {
            animationSet.removeAllListeners()
            animationSet.end()
            animationSet.cancel()
        }

    }

}