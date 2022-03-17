package com.asuka.androidopensourcelibrarystudydemo.view.fragerment


import android.view.LayoutInflater
import android.view.ViewGroup
import com.asuka.androidopensourcelibrarystudydemo.databinding.FragmentCBinding


class FragmentC : BaseFragment<FragmentCBinding>() {

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCBinding? {
        return FragmentCBinding.inflate(inflater, container, false)
    }

    override fun initData() {

    }

    override fun initListener() {
        binding.button.setOnClickListener {
        }
    }


}