package com.cocos.develop.noteadvanced.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cocos.develop.noteadvanced.R
import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.data.domain.AppState
import com.cocos.develop.noteadvanced.databinding.FragmentFavoriteBinding
import com.cocos.develop.noteadvanced.ui.details.NOTE_DATA
import com.cocos.develop.noteadvanced.utils.*
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
                openScreen( requireActivity(), R.id.detailFragment, bundle)
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
        favoriteViewModel.getData(readPrefAccess(context))
        favoriteViewModel.subscribe().observe(viewLifecycleOwner, { renderData(it) })
    }

    private fun initRV() {
        val recyclerView = binding.mainActivityRecyclerview
        recyclerView.adapter = adapter
    }

    private fun renderData(appState: AppState){

        when (appState) {
            is AppState.Success<*> -> {
                val noteDataList = appState.data as List<NoteData>?
                if (!noteDataList.isNullOrEmpty()){
                    adapter.setData(noteDataList)
                }
            }

            is AppState.Loading ->
                binding.loadingFrameLayout.loadingFrame.showViewLoading()

            is AppState.Error -> {
                binding.loadingFrameLayout.loadingFrame.showViewWorking()
                makeErrorToast(context, appState.error.message)
            }

        }
    }

}