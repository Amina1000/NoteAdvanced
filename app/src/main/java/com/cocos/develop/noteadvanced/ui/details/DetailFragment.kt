package com.cocos.develop.noteadvanced.ui.details

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cocos.develop.noteadvanced.R
import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.databinding.FragmentDetailBinding
import com.cocos.develop.noteadvanced.utils.setSrc
import java.util.*

const val NOTE_DATA = "NOTE_DATA"


class DetailFragment : Fragment() {

    private val noteData: NoteData? by lazy {
        arguments?.getParcelable(NOTE_DATA)
    }
    private val binding: FragmentDetailBinding by viewBinding (FragmentDetailBinding::bind)
    private lateinit var detailViewModel: DetailViewModel
    private val calendar = Calendar.getInstance()

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
            binding.dateTextView.setText(it.date)
            binding.dateTextView.inputType = 0
        }
        binding.inputLayout.setEndIconOnClickListener(this::onClickDate)
        binding.saveButton.setOnClickListener {
            setViewModelData()
        }
        binding.favoriteFab.setOnClickListener {
            noteData?.run {
                this.favorite = !this.favorite
                binding.favoriteFab.setSrc(this.favorite)
            }
        }


    }
    private fun onClickDate(v: View) {
        DatePickerDialog(
            requireContext(),
            { _: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                binding.dateTextView.setText(calendar.time.toString())
                binding.dateTextView.inputType = 0
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
            .show()
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