package com.ms.kotlintest1.RvTest

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ms.kotlintest1.R
import com.ms.kotlintest1.databinding.ActivityRecyclerviewBinding

class RecyclerViewActivity: AppCompatActivity() {
    private lateinit var mRv: RecyclerView
    private lateinit var mBinding: ActivityRecyclerviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_recyclerview)
    }

}