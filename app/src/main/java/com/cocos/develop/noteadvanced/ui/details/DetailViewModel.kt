package com.cocos.develop.noteadvanced.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cocos.develop.noteadvanced.data.NoteData

/**
 * homework com.cocos.develop.noteadvanced.ui.details
 *
 * @author Amina
 * 22.11.2021
 */
class DetailViewModel: ViewModel() {

    private val _detailsLiveData = MutableLiveData<NoteData>()
    private val detailsLiveData: LiveData<NoteData> = _detailsLiveData

    fun subscribe(): LiveData<NoteData> {
        return detailsLiveData
    }

    fun setData(data: NoteData) {
        // save to DB
    }
}