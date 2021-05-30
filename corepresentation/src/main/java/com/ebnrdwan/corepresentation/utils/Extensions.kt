import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.widget.ImageViewCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*


//---------------------- Any extensions --------------------
fun Any?.getString(context: Context, locale: Locale, vararg args: Any?): String? {
    return when (this) {
        is Int -> String.format(locale, context.getString(this), *args)
        is String -> String.format(locale, this, *args)
        else -> null
    }
}

fun TextView.clear() {
    this.text = ""
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}

fun View.hide(makeViewInVisible: Boolean = false) {
    if (makeViewInVisible)
        this.visibility = View.INVISIBLE
    else this.visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}


fun Context.getColorEx(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

fun View.setVisibilityState(visible: Boolean, makeViewInVisible: Boolean = false) {
    if (visible) show()
    else hide(makeViewInVisible)
}

fun EditText.onTextChangeDo(action: (String) -> Unit) {

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            action(p0.toString())
        }
    })
}

fun EditText.onTextChangesObservable(): Observable<String> {
    val subject = BehaviorSubject.create<String>()

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            subject.onNext(p0.toString())
        }
    })

    return subject
}

fun View.tintViewBackground(@ColorRes colorRes: Int) {
    this.backgroundTintList = ColorStateList.valueOf(context.getColorEx(colorRes));
}

fun ImageView.tintImage(context: Context, @ColorRes colorId: Int) {
    val color = ContextCompat.getColor(context, colorId)
    val colorStateList = ColorStateList.valueOf(color)
    ImageViewCompat.setImageTintList(this, colorStateList)
}

fun String.hexColorToInt(): Int {
    return Color.parseColor(if (this.contains("#")) this else "#".plus(this))
}

fun View.tintViewBackground(colorRes: String) {
    this.backgroundTintList = ColorStateList.valueOf(colorRes.hexColorToInt());
}

fun ImageView.tintVictor(context: Context, @ColorRes colorRes: Int, @DrawableRes icon: Int) {
    var d: Drawable? = VectorDrawableCompat.create(context.resources, icon, null)
    if (d != null)
        d = DrawableCompat.wrap(d)
    d?.let { DrawableCompat.setTint(it, context.getColorEx(colorRes)) }
    this.setImageDrawable(d)
}

fun ImageView.tintVictor(context: Context, colorHex: String, @DrawableRes icon: Int) {
    var d: Drawable? = VectorDrawableCompat.create(context.resources, icon, null)
    if (d != null)
        d = DrawableCompat.wrap(d)
    d?.let {
        DrawableCompat.setTintMode(it, PorterDuff.Mode.SRC_IN);
        DrawableCompat.setTintList(it, ColorStateList.valueOf(colorHex.hexColorToInt()))
    }
    this.setImageDrawable(d)
}

fun TextView.setTextTemplate(string: String?, placeHolder: String = "-") {
    text = if (string.isNullOrEmpty()) placeHolder
    else string
}


fun View.isRTL(): Boolean {
    val config = resources.configuration
    return config.layoutDirection == View.LAYOUT_DIRECTION_RTL
}

fun TextView.setNumberTemplateWithSign(
    bigDouble: Double?,
    decimals: Int,
    sign: String? = "",
    placeHolder: String = "-"
) {
    text = try {
        val cleanedNumber = bigDouble?.let { BigDecimal(it) }
        val number = NumberFormat.getNumberInstance(Locale.ENGLISH)
            .format(cleanedNumber?.setScale(decimals, RoundingMode.HALF_EVEN))
        var textValue = "$number $sign"
        if (cleanedNumber == null) placeHolder
        else textValue
    } catch (e: Exception) {
        placeHolder
    }

}


fun View.tintIndicators(color: String) {
    this.tintViewBackground(color)
}

fun View.setMargin(
    leftMargin: Int? = null, topMargin: Int? = null,
    rightMargin: Int? = null, bottomMargin: Int? = null
) {
    val params = layoutParams as ViewGroup.MarginLayoutParams
    params.setMargins(
        leftMargin ?: params.leftMargin,
        topMargin ?: params.topMargin,
        rightMargin ?: params.rightMargin,
        bottomMargin ?: params.bottomMargin
    )
    layoutParams = params
}

fun ViewGroup.setMargin(
    leftMargin: Int? = null,
    rightMargin: Int? = null
) {
    val params = MarginLayoutParams(layoutParams)
    val margins = (layoutParams as RelativeLayout.LayoutParams).apply {
        marginStart = leftMargin ?: params.leftMargin
        marginEnd = rightMargin ?: params.rightMargin
    }
    layoutParams = margins
}


fun List<String>.containsIgnoreCase(string: String?): Boolean {
    for (item in this) {
        if (item.toLowerCase(Locale.ENGLISH) == string?.toLowerCase(Locale.ENGLISH)) return true
    }
    return false
}

