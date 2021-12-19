package com.cocos.develop.noteadvanced.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cocos.develop.noteadvanced.R
import com.cocos.develop.noteadvanced.data.di.BASE_URL
import com.cocos.develop.noteadvanced.databinding.FragmentAboutBinding
import com.cocos.develop.noteadvanced.databinding.FragmentDashboardBinding

class AboutFragment : Fragment() {

    private val binding: FragmentAboutBinding by viewBinding(FragmentAboutBinding::bind)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.website.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(BASE_URL)
            })
        }
    }
}