package com.cocos.develop.noteadvanced.utils

import android.view.View
import com.cocos.develop.noteadvanced.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

/**
 * homework com.cocos.develop.noteadvanced.utils
 *
 * @author Amina
 * 22.11.2021
 */
fun FloatingActionButton.setSrc(favorite: Boolean?) {
    favorite?.let {
        if (it) {
            this.setImageResource(R.drawable.ic_baseline_favorite)
        } else {
            this.setImageResource(R.drawable.ic_baseline_favorite_border)
        }
    }

}

fun View.showSnackBar(
    message: String,
    length: Int = Snackbar.LENGTH_SHORT
) {
    Snackbar
        .make(this, message, length)
        .show()
}