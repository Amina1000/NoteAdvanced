package com.cocos.develop.noteadvanced.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.cocos.develop.noteadvanced.R
import com.cocos.develop.noteadvanced.data.User
import com.cocos.develop.noteadvanced.databinding.FragmentDashboardBinding
import com.cocos.develop.noteadvanced.utils.openScreen
import com.cocos.develop.noteadvanced.utils.readPrefAccess
import com.cocos.develop.noteadvanced.utils.showSnackBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModel()
    private val binding: FragmentDashboardBinding by viewBinding(FragmentDashboardBinding::bind)
    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
    }

    private fun initView() {
        binding.saveChanges.setOnClickListener {
            setViewModelData()
        }
    }

    private fun setViewModelData() {

        val email = binding.emailTextView.text.toString()
        val password = binding.passwordTextView.text.toString()
        val name = binding.nameTextView.text.toString()
        val lastName = binding.lastnameTextView.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            if (user == null) {
                user = User(1, email, password, name, lastName)
            } else {
                user?.password = password
                user?.name = name
                user?.lastName = lastName
            }
            user?.run{
                dashboardViewModel.setData(readPrefAccess(context),this)
                binding.navigationDashboard.showSnackBar(getString(R.string.save_profile))
            }
        }else{
            binding.navigationDashboard.showSnackBar(getString(R.string.error_row))
        }
    }

    private fun initViewModel() {
        dashboardViewModel.getData(readPrefAccess(context))
        dashboardViewModel.subscribe().observe(viewLifecycleOwner, { renderData(it) })
    }

    private fun renderData(users: List<User>?) {
        if (!users.isNullOrEmpty()) {
            user = users.last()
            setUser()
        }
    }

    private fun setUser() {
        user?.let {
            binding.emailTextView.setText(it.email)
            binding.passwordTextView.setText(it.password)
            binding.nameTextView.setText(it.name)
            binding.lastnameTextView.setText(it.lastName)
        }
    }

}