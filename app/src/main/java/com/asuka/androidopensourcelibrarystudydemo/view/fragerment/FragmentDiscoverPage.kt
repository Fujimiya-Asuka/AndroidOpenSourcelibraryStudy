package com.asuka.androidopensourcelibrarystudydemo.view.fragerment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.asuka.androidopensourcelibrarystudydemo.databinding.FragmentDiscoverPagerBinding


class FragmentDiscoverPage : BaseFragment<FragmentDiscoverPagerBinding>() {
    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDiscoverPagerBinding {
        return FragmentDiscoverPagerBinding.inflate(layoutInflater)
    }

    override fun initData() {
    }

    override fun initListener() {
    }
}