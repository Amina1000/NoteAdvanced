package com.cocos.develop.noteadvanced.ui.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cocos.develop.noteadvanced.R
import com.cocos.develop.noteadvanced.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private val binding: FragmentStartBinding by viewBinding (FragmentStartBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.singInButton.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main).also { nav->
               nav.navigate(R.id.navigation_home)
            }
        }
    }
}