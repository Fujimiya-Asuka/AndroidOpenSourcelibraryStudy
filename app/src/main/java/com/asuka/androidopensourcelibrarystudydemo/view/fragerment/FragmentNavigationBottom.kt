package com.asuka.androidopensourcelibrarystudydemo.view.fragerment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.asuka.androidopensourcelibrarystudydemo.databinding.FragmentNavitationBottomBinding

class FragmentNavigationBottom : BaseFragment<FragmentNavitationBottomBinding>() {

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNavitationBottomBinding {
        return FragmentNavitationBottomBinding.inflate(inflater, container, false)
    }


    override fun initData() {

    }

    override fun initListener() {

    }

}