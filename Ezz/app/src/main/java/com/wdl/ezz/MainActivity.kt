package com.wdl.ezz

import androidx.activity.ComponentActivity
import com.wdl.ezz.databinding.ActivityMainBinding
import com.wdl.vblib.viewBinding

class MainActivity : ComponentActivity(R.layout.activity_main) {
    private val vb by viewBinding(ActivityMainBinding::bind)

    override fun onResume() {
        super.onResume()
        vb.tv.text = "Txxxxxxxxx"

//        toast("----")
    }
}
