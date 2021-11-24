package com.cocos.develop.noteadvanced.utils
import com.cocos.develop.noteadvanced.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * homework com.cocos.develop.noteadvanced.utils
 *
 * @author Amina
 * 22.11.2021
 */
fun FloatingActionButton.setSrc(favorite: Boolean?){
    favorite?.let {
        if (it) {
            this.setImageResource(R.drawable.ic_baseline_favorite)
        } else {
            this.setImageResource(R.drawable.ic_baseline_favorite_border)
        }
    }

}