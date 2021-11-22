package com.cocos.develop.noteadvanced.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cocos.develop.noteadvanced.R
import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.databinding.FragmentDetailBinding

const val NOTE_DATA = "NOTE_DATA"


class DetailFragment : Fragment() {

    private val noteData: NoteData? by lazy {
        arguments?.getParcelable(NOTE_DATA)
    }
    private val binding: FragmentDetailBinding by viewBinding (FragmentDetailBinding::bind)
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()

    }

    private fun initView() {
        noteData?.let {
            binding.headerEditText.setText(it.name)
            binding.descriptionsTextview.setText(it.description)
            binding.dateTextView.text = it.date
        }

    }

    private fun setViewModelData() {
        noteData?.let {
            detailViewModel.setData(it)
        }
    }
    private fun initViewModel() {
        detailViewModel =
            ViewModelProvider(this)[DetailViewModel::class.java]
        detailViewModel.subscribe()
    }

}