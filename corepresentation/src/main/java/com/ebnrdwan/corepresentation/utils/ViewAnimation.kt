package com.ebnrdwan.corepresentation.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Build
import android.view.View
import android.view.animation.BaseInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import com.airbnb.lottie.LottieAnimationView


fun View.translateFromStart(
    interpolator: BaseInterpolator = OvershootInterpolator(),
    reverse: Boolean = Constants.Animations.reverse,
    repeatCount: Int = Constants.Animations.repeatCount,
    duration: Long = Constants.Animations.Duration,
    delay: Long = Constants.Animations.Delay,
    xStart: Float = Constants.Animations.XLeftStart,
    xEnd: Float = Constants.Animations.EndPosition

) {
    val animator = ObjectAnimator.ofFloat(this, View.TRANSLATION_X, xStart, xEnd)
    val view = this
    view.visibility = View.INVISIBLE
    animator?.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            view.visibility = View.VISIBLE
            super.onAnimationStart(animation)
        }
    })
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
        animator.interpolator = interpolator
    } else animator.interpolator = OvershootInterpolator()
    if (reverse) {

        animator.repeatMode = ObjectAnimator.REVERSE
    }
    animator.repeatCount = repeatCount
    animator.duration = duration
    animator?.startDelay = delay
    animator.start()
}


fun View.translaterFromEnd(
    interpolator: BaseInterpolator = OvershootInterpolator(),
    reverse: Boolean = Constants.Animations.reverse,
    repeatCount: Int = Constants.Animations.repeatCount,
    duration: Long = Constants.Animations.Duration,
    delay: Long = Constants.Animations.Delay,
    xStart: Float = Constants.Animations.XRightStart,
    xEnd: Float = Constants.Animations.EndPosition
) {


    val animator = ObjectAnimator.ofFloat(this, View.TRANSLATION_X, xStart, xEnd)
    animator.duration = 1000
    val view = this
    view.visibility = View.INVISIBLE
    animator?.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            view.visibility = View.VISIBLE
            super.onAnimationStart(animation)
        }
    })
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
        animator.interpolator = interpolator
    } else animator.interpolator = OvershootInterpolator()
    animator.repeatCount = repeatCount
    if (reverse) {
        animator.repeatMode = ObjectAnimator.REVERSE
    }

    animator.duration = duration
    animator?.startDelay = delay
    animator.start()
}


fun View.translaterFromUp(
    interpolator: BaseInterpolator = OvershootInterpolator(),
    reverse: Boolean = Constants.Animations.reverse,
    repeatCount: Int = Constants.Animations.repeatCount,
    duration: Long = Constants.Animations.Duration,
    delay: Long = Constants.Animations.Delay,
    start: Float = Constants.Animations.YTopStart,
    end: Float = Constants.Animations.EndPosition
) {
    val animator = ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, start, end)
    val view = this
    view.visibility = View.INVISIBLE
    animator?.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            view.visibility = View.VISIBLE
            super.onAnimationStart(animation)
        }
    })
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
        animator.interpolator = interpolator
    } else animator.interpolator = OvershootInterpolator()
    if (reverse) {

        animator.repeatMode = ObjectAnimator.REVERSE
    }
    animator.repeatCount = repeatCount
    animator.duration = duration
    animator?.startDelay = delay
    animator.start()
}


fun View.translaterFromBottom(
    interpolator: BaseInterpolator = OvershootInterpolator(),
    reverse: Boolean = Constants.Animations.reverse,
    repeatCount: Int = Constants.Animations.repeatCount,
    duration: Long = Constants.Animations.Duration,
    delay: Long = Constants.Animations.Delay,
    start: Float = Constants.Animations.YBottomStart,
    end: Float = Constants.Animations.EndPosition
) {
    val animator = ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, start, end)
    val view = this
    view.visibility = View.INVISIBLE
    animator.duration = duration
    animator?.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            view.visibility = View.VISIBLE
            super.onAnimationStart(animation)
        }
    })
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
        animator.interpolator = interpolator
    } else animator.interpolator = OvershootInterpolator()
    if (reverse) {

        animator.repeatMode = ObjectAnimator.REVERSE
    }
    animator.repeatCount = repeatCount

    animator?.startDelay = delay
    animator.start()
}


fun View.fade(
    fadeout: Boolean = false,
    interpolator: BaseInterpolator = DecelerateInterpolator(),
    reverse: Boolean = false,
    repeatCount: Int = Constants.Animations.repeatCount,
    duration: Long = Constants.Animations.Duration,
    delay: Long = Constants.Animations.Delay,
    fadedOut: Float = Constants.Animations.fadedOut,
    fadedIn: Float = Constants.Animations.fadedIn
) {

    val view = this
    view.visibility = View.INVISIBLE
    val fade: ObjectAnimator? = if (fadeout) {
        ObjectAnimator.ofFloat(this, View.ALPHA, fadedIn, fadedOut)
    } else ObjectAnimator.ofFloat(this, View.ALPHA, fadedOut, fadedIn)

    fade?.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            view.visibility = View.VISIBLE
            super.onAnimationStart(animation)
        }

        override fun onAnimationEnd(animation: Animator?) {
            super.onAnimationEnd(animation)
            if (fadeout) view.visibility = View.GONE
        }
    })
    fade?.apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            fade.interpolator = interpolator
        } else fade.interpolator = OvershootInterpolator()
        if (reverse) {
            fade.repeatMode = ObjectAnimator.REVERSE
        }
        fade.repeatCount = repeatCount
        fade.duration = duration
    }
    fade?.startDelay = delay
    fade?.start()
}

fun View.toTransitionPair() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    this to transitionName
} else {
    this to "not supported"
}


fun LottieAnimationView.playLottieAnimationReverse(progress: Float = Constants.Animations.lottieStart) {

    val valueAnimator = ValueAnimator.ofFloat(-progress, 0f)
        .setDuration((this.duration * progress).toLong())
    valueAnimator.addUpdateListener {
        this.progress = Math.abs(it.animatedValue as Float)
    }
    valueAnimator.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
        }
    })
    valueAnimator.start()
}


fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}
