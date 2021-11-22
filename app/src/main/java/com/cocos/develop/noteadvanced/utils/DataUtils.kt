package com.cocos.develop.noteadvanced.utils

import com.cocos.develop.noteadvanced.data.NoteData

/**
 * homework com.cocos.develop.noteadvanced.utils
 *
 * @author Amina
 * 22.11.2021
 */

fun noteDefault(): NoteData {
    return NoteData("", "", "", "", "")
}

fun noteListCreator(): List<NoteData> {
    return arrayListOf(
        NoteData(
            "21", "34", "Note 1", "Lorem Ipsum is simply dummy text of " +
                    "the printing and typesetting industry. Lorem Ipsum has been the industry\\" +
                    "'s standard dummy text ever since the 1500s, when an unknown printer took" +
                    " a galley of type and scrambled it to make a type specimen book.", "04.12.1969"
        ),
        NoteData(
            "21", "34", "Note 2", "Lorem Ipsum is simply dummy text of " +
                    "the printing and typesetting industry. Lorem Ipsum has been the industry\\" +
                    "'s standard dummy text ever since the 1500s, when an unknown printer took" +
                    " a galley of type and scrambled it to make a type specimen book.", "05.11.1984"
        ),
        NoteData(
            "21", "34", "Note 3", "Lorem Ipsum is simply dummy text of " +
                    "the printing and typesetting industry. Lorem Ipsum has been the industry\\" +
                    "'s standard dummy text ever since the 1500s, when an unknown printer took" +
                    " a galley of type and scrambled it to make a type specimen book.", "07.01.2022"
        ),
        NoteData(
            "21", "34", "Note 4", "Lorem Ipsum is simply dummy text of " +
                    "the printing and typesetting industry. Lorem Ipsum has been the industry\\" +
                    "'s standard dummy text ever since the 1500s, when an unknown printer took" +
                    " a galley of type and scrambled it to make a type specimen book.", "13.10.2021"
        ),
        NoteData(
            "21", "34", "Note 5", "Lorem Ipsum is simply dummy text of " +
                    "the printing and typesetting industry. Lorem Ipsum has been the industry\\" +
                    "'s standard dummy text ever since the 1500s, when an unknown printer took" +
                    " a galley of type and scrambled it to make a type specimen book.", "13.10.2021"
        ),
        NoteData(
            "21", "34", "Note 6", "Lorem Ipsum is simply dummy text of " +
                    "the printing and typesetting industry. Lorem Ipsum has been the industry\\" +
                    "'s standard dummy text ever since the 1500s, when an unknown printer took" +
                    " a galley of type and scrambled it to make a type specimen book.", "13.10.2021"
        ),
        NoteData(
            "21", "34", "Note 7", "Lorem Ipsum is simply dummy text of " +
                    "the printing and typesetting industry. Lorem Ipsum has been the industry\\" +
                    "'s standard dummy text ever since the 1500s, when an unknown printer took" +
                    " a galley of type and scrambled it to make a type specimen book.", "13.10.2021"
        )
    )
}