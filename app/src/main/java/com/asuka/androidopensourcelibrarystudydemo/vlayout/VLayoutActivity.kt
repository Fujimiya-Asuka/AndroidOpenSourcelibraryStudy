package com.asuka.androidopensourcelibrarystudydemo.vlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityVlayoutBinding
import com.asuka.androidopensourcelibrarystudydemo.view.activity.BaseActivity

class VLayoutActivity : BaseActivity<ActivityVlayoutBinding>() {
    override fun initViewBinding(): ActivityVlayoutBinding {
        return ActivityVlayoutBinding.inflate(layoutInflater)
    }

    override fun initView() {

    }

    override fun initListener() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

}