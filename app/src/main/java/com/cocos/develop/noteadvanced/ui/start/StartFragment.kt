package com.cocos.develop.noteadvanced.ui.start

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cocos.develop.noteadvanced.R
import com.cocos.develop.noteadvanced.data.Token
import com.cocos.develop.noteadvanced.data.User
import com.cocos.develop.noteadvanced.databinding.FragmentStartBinding
import com.cocos.develop.noteadvanced.utils.TOKEN
import com.cocos.develop.noteadvanced.utils.openScreen
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


const val REG_URI = "http://194.58.108.107/auth/register/"

class StartFragment : Fragment() {

    private val binding: FragmentStartBinding by viewBinding(FragmentStartBinding::bind)
    private var user: User? = null
    private val startViewModel: StartViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniViewModel()
        initView()

    }

    private fun iniViewModel() {
        startViewModel.subscribe().observe(viewLifecycleOwner, { renderData(it) })
    }

    private fun initView() {

        binding.singInButton.setOnClickListener {
            val email = binding.emailTextView.text.toString()
            val password = binding.passwordTextView.text.toString()
            if (email != "" && password != "") {
                user = User(email, password,null,null)
                user?.let {
                    startViewModel.getData(it)
                }
            } else {
                Snackbar.make(
                    binding.startFragment,
                    "Error authorization!",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
        }

        binding.singUpButton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(REG_URI)
            })
        }
    }

    private fun renderData(token: Token?) {
        token?.let {
            setTokenSettings(token)
            openScreen(requireActivity(), R.id.navigation_home)
        }
    }

    private fun setTokenSettings(token: Token) {

        val sharedPreferences: SharedPreferences? =
            context?.getSharedPreferences(TOKEN, MODE_PRIVATE)
        // Настройки сохраняются посредством специального класса editor
        val editor = sharedPreferences?.edit()
        editor?.let {
            it.putString("refresh", token.refresh)
            it.putString("access", "Bearer " + token.access)
            it.apply()
        }
    }
}