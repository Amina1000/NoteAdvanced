package com.cocos.develop.noteadvanced.ui.start

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cocos.develop.noteadvanced.R
import com.cocos.develop.noteadvanced.data.Token
import com.cocos.develop.noteadvanced.data.User
import com.cocos.develop.noteadvanced.data.domain.AppState
import com.cocos.develop.noteadvanced.databinding.FragmentStartBinding
import com.cocos.develop.noteadvanced.ui.dashboard.DashboardViewModel
import com.cocos.develop.noteadvanced.utils.*
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


class StartFragment : Fragment() {

    private val binding: FragmentStartBinding by viewBinding(FragmentStartBinding::bind)
    private var user: User? = null
    private val startViewModel: StartViewModel by viewModel()
    private val dashboardViewModel: DashboardViewModel by viewModel()

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
        dashboardViewModel.getData(readPrefAccess(context))
        dashboardViewModel.subscribe().observe(viewLifecycleOwner, { renderMail(it) })
    }

    private fun renderMail(users: List<User>?) {
        if (!users.isNullOrEmpty()) {
            user = users.last()
            setUser()
        }
    }

    private fun initView() {

        binding.singInButton.setOnClickListener {
            val email = binding.emailTextView.text.toString()
            val password = binding.passwordTextView.text.toString()
            if (email != "" && password != "") {
                user = User(1, email, password, null, null)
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
            openScreen(requireActivity(), R.id.navigation_dashboard)
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success<*> -> {
                val token = appState.data as Token?
                token?.let {
                    setTokenSettings(token)
                    openScreen(requireActivity(), R.id.navigation_home)
                }
            }

            is AppState.Loading ->
                binding.loadingFrameLayout.loadingFrame.showViewLoading()

            is AppState.Error -> {
                binding.loadingFrameLayout.loadingFrame.showViewWorking()
                makeToast(context, appState.error.message)
            }

        }
    }

    private fun setUser() {
        user?.let {
            binding.emailTextView.setText(it.email)
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