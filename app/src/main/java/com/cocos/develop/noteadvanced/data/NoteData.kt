package com.cocos.develop.noteadvanced.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * homework com.cocos.develop.noteadvanced.data
 *
 * @author Amina
 * 16.11.2021
 */
@Parcelize
data class NoteData(

    val id: String,
    val userId: String,
    val name:String,
    val description:String,
    val date: String,
    val favorite:Boolean = false

):Parcelable

fun noteListCreator():List<NoteData>{
    return arrayListOf(
        NoteData("21","34","Note 1","Lorem Ipsum is simply dummy text of " +
            "the printing and typesetting industry. Lorem Ipsum has been the industry\\" +
            "'s standard dummy text ever since the 1500s, when an unknown printer took" +
            " a galley of type and scrambled it to make a type specimen book.","04.12.1969"),
        NoteData("21","34","Note 2","Lorem Ipsum is simply dummy text of " +
                "the printing and typesetting industry. Lorem Ipsum has been the industry\\" +
                "'s standard dummy text ever since the 1500s, when an unknown printer took" +
                " a galley of type and scrambled it to make a type specimen book.","05.11.1984"),
        NoteData("21","34","Note 3","Lorem Ipsum is simply dummy text of " +
                "the printing and typesetting industry. Lorem Ipsum has been the industry\\" +
                "'s standard dummy text ever since the 1500s, when an unknown printer took" +
                " a galley of type and scrambled it to make a type specimen book.","07.01.2022"),
        NoteData("21","34","Note 4","Lorem Ipsum is simply dummy text of " +
                "the printing and typesetting industry. Lorem Ipsum has been the industry\\" +
                "'s standard dummy text ever since the 1500s, when an unknown printer took" +
                " a galley of type and scrambled it to make a type specimen book.","13.10.2021"),
        NoteData("21","34","Note 5","Lorem Ipsum is simply dummy text of " +
                "the printing and typesetting industry. Lorem Ipsum has been the industry\\" +
                "'s standard dummy text ever since the 1500s, when an unknown printer took" +
                " a galley of type and scrambled it to make a type specimen book.","13.10.2021"),
        NoteData("21","34","Note 6","Lorem Ipsum is simply dummy text of " +
                "the printing and typesetting industry. Lorem Ipsum has been the industry\\" +
                "'s standard dummy text ever since the 1500s, when an unknown printer took" +
                " a galley of type and scrambled it to make a type specimen book.","13.10.2021"),
        NoteData("21","34","Note 7","Lorem Ipsum is simply dummy text of " +
                "the printing and typesetting industry. Lorem Ipsum has been the industry\\" +
                "'s standard dummy text ever since the 1500s, when an unknown printer took" +
                " a galley of type and scrambled it to make a type specimen book.","13.10.2021")
    )
}