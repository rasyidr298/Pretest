package id.rrdev.pretest.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputLayout

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("OK") {
            snackbar.dismiss()
        }
    }.show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun tag(context: Context) : String {
    return context.javaClass.simpleName
}

fun enableClick(view: View) {
    with(view) {
        isFocusable = true
        isClickable = true
        isEnabled = true
    }
}

fun disableClick(view: View) {
    with(view) {
        isFocusable = false
        isClickable = false
        isEnabled = false
    }
}

fun List<View>.clearErrorInputLayout(){
    forEach { vi ->
        if(vi is TextInputLayout){
            vi.isErrorEnabled = false
        }
    }
}
