package com.cocos.develop.noteadvanced.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cocos.develop.noteadvanced.R
import com.cocos.develop.noteadvanced.data.NoteData
import com.cocos.develop.noteadvanced.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter by lazy { HomeAdapter(onListItemClickListener) }

    private val onListItemClickListener: HomeAdapter.OnListItemClickListener =
        object : HomeAdapter.OnListItemClickListener {
            override fun onItemClick(data: NoteData) {
                openScreen(R.id.detailFragment)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchFab.setOnClickListener {openScreen(R.id.navigation_dashboard)}
        initRV()
    }

    private fun initRV() {
        adapter.setData()
        val recyclerView = binding.mainActivityRecyclerview
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun openScreen(target: Int){
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main).also {
            it.navigate(target)
        }
    }
}