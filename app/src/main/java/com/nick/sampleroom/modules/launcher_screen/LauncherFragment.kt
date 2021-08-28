package com.nick.sampleroom.modules.launcher_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.nick.sampleroom.R
import com.nick.sampleroom.databinding.FragmentLauncherBinding
import com.nick.sampleroom.utils.base_classes.BaseFragment

/**
 * A simple [Fragment] subclass.
 * Use the [LauncherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LauncherFragment : BaseFragment() {

    private lateinit var launcherViewModel: LauncherViewModel
    private var binding: FragmentLauncherBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_launcher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLauncherBinding.bind(view)
        launcherViewModel = ViewModelProvider(this).get(LauncherViewModel::class.java)

        launcherViewModel.startMainActivity()
        initObservable()
    }

    private fun initObservable() {
        launcherViewModel.startMainActivity.observe(viewLifecycleOwner, Observer {
            if (it) {
                view?.let { it1 -> Navigation.findNavController(it1).navigate(LauncherFragmentDirections.actionLauncherFragmentToBooksFragment()) }
            }
        })
    }
}