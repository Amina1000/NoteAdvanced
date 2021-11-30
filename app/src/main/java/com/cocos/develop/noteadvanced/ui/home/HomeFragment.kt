package com.cocos.develop.noteadvanced.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cocos.develop.noteadvanced.R
import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.databinding.FragmentHomeBinding
import com.cocos.develop.noteadvanced.ui.details.NOTE_DATA
import com.cocos.develop.noteadvanced.utils.noteDefault
import com.cocos.develop.noteadvanced.utils.openScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private val binding: FragmentHomeBinding by viewBinding (FragmentHomeBinding::bind)
    private val adapter by lazy { HomeAdapter(onListItemClickListener) }

    private val onListItemClickListener: HomeAdapter.OnListItemClickListener =
        object : HomeAdapter.OnListItemClickListener {
            override fun onItemClick(data: NoteData) {
                val bundle = Bundle()
                bundle.putParcelable(NOTE_DATA,data)
                openScreen(requireActivity(), R.id.detailFragment, bundle)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        iniViewModel()
        initView()
    }

    private fun initView() {
        binding.searchFab.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(NOTE_DATA, noteDefault())
            openScreen(requireActivity(), R.id.detailFragment, bundle)
        }
    }

    private fun iniViewModel() {
        homeViewModel.getData()
        homeViewModel.subscribe().observe(viewLifecycleOwner, { renderData(it) })
    }

    private fun initRV() {
        val recyclerView = binding.mainActivityRecyclerview
        recyclerView.adapter = adapter
    }


    private fun renderData(noteDataList: List<NoteData>){
        if (noteDataList.isNotEmpty()){
            adapter.setData(noteDataList)
        }
    }
}