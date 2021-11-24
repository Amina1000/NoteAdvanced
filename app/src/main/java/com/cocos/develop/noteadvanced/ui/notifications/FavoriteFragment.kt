package com.cocos.develop.noteadvanced.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cocos.develop.noteadvanced.R
import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.databinding.FragmentFavoriteBinding
import com.cocos.develop.noteadvanced.ui.details.NOTE_DATA
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {


    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private val binding: FragmentFavoriteBinding by viewBinding (FragmentFavoriteBinding::bind)
    private val adapter by lazy { FavoriteAdapter(onListItemClickListener) }

    private val onListItemClickListener: FavoriteAdapter.OnListItemClickListener =
        object : FavoriteAdapter.OnListItemClickListener {
            override fun onItemClick(data: NoteData) {
                val bundle = Bundle()
                bundle.putParcelable(NOTE_DATA,data)
                openScreen(R.id.detailFragment, bundle)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        iniViewModel()
    }



    private fun iniViewModel() {
        favoriteViewModel.getData()
        favoriteViewModel.subscribe().observe(viewLifecycleOwner, { renderData(it) })
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

    fun openScreen(target: Int,bundle: Bundle?=null){
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main).also { nav->
            bundle?.let{
                nav.navigate(target,bundle)
            }?:nav.navigate(target)

        }
    }
}