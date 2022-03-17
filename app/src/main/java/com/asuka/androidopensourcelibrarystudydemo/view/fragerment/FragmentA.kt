package com.asuka.androidopensourcelibrarystudydemo.view.fragerment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.asuka.androidopensourcelibrarystudydemo.R
import com.asuka.androidopensourcelibrarystudydemo.databinding.FragmentABinding


class FragmentA : BaseFragment<FragmentABinding>() {

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentABinding? {
        return FragmentABinding.inflate(inflater, container, false)
    }

    override fun initData() {

    }

    override fun initListener() {
        binding.toBBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("key", "bundle")
            Navigation.findNavController(it).navigate(R.id.fragmentB, bundle)
        }
    }
}