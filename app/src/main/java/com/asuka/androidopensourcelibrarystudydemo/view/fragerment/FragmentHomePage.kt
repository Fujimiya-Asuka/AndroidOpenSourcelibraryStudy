package com.asuka.androidopensourcelibrarystudydemo.view.fragerment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.asuka.androidopensourcelibrarystudydemo.R
import com.asuka.androidopensourcelibrarystudydemo.databinding.FragmentHomePageBinding


class FragmentHomePage : BaseFragment<FragmentHomePageBinding>() {

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomePageBinding? {
        return FragmentHomePageBinding.inflate(inflater, container, false)
    }

    override fun initListener() {
        binding.button.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.fragmentE)
        }
    }

    override fun initData() {

    }


}