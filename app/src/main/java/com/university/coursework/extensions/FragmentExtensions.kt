package com.university.coursework.extensions

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.university.coursework.R


/**
 * Opens the bottom sheet in the container.
 *
 * @param fragment The fragment to fill in.
 * @param containerId Place to fill in.
 * @param isAnimated  Action with animation

 */
fun Fragment.showChildFragment(fragment: Fragment, containerId: Int, isAnimated: Boolean = false) {
    if (isAnimated) {
        childFragmentManager
            .beginTransaction()
/*
            .setCustomAnimations(R.anim.slide_from_bot, android.R.anim.fade_out)
*/
            .replace(containerId, fragment)
            .addToBackStack(null)
            .commit()
    } else {
        childFragmentManager
            .beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(null)
            .commit()
    }
}

/**
 * Hide the bottom sheet in the container.
 *
 * @param containerId Place of filled fragment
 * @param isAnimated  Action with animation
 */

fun Fragment.hideChildFragment(containerId: Int, isAnimated: Boolean = false) {
    if (isAnimated) {
        childFragmentManager
            .beginTransaction()
/*
            .setCustomAnimations(R.anim.slide_from_bot, android.R.anim.fade_out)
*/
            .replace(containerId, Fragment())
            .addToBackStack(null)

            .commit()
    } else {
        childFragmentManager
            .beginTransaction()
            .replace(containerId, Fragment())
            .addToBackStack(null)

            .commit()
    }
}
/**
 * Shows alert dialog.
 *
 * @param title Title of alert dialog
 * @param message Text on alert dialog
 * @param textBtn Text on complete button
 * @param action Action after on clicked button
 */

fun Fragment.alertDialog(title: String, message: String, textBtn: String, action: () -> Unit) {

    val mDialog = AlertDialog.Builder(context, R.style.AlertDialogCustom)
    val dialog: AlertDialog = with(mDialog) {
        setTitle(title)
        setMessage(message)
        setCancelable(false)
        setPositiveButton(textBtn) { _: DialogInterface, _ ->
            action()
        }
        show()
    }
    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
        ContextCompat.getColor(
            requireContext(), R.color.camera_frg__dialog_ok
        )
    )
}