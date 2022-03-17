package com.asuka.androidopensourcelibrarystudydemo.view.fragerment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.asuka.androidopensourcelibrarystudydemo.databinding.FragmentMinePageBinding

class FragmentMinePage : BaseFragment<FragmentMinePageBinding>() {
    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMinePageBinding {
        return FragmentMinePageBinding.inflate(inflater, container, false)
    }

    override fun initData() {
    }

    override fun initListener() {
    }

}