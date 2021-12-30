package com.wdl.ezz

import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.wdl.lib_common.data.TestData
import com.wdl.lib_common.database.TestDao
import com.wdl.ezz.databinding.ActivityMainBinding
import com.wdl.lib_common.ext.logE
import com.wdl.vblib.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(R.layout.activity_main) {
    private val vb by viewBinding(ActivityMainBinding::bind)

    @Inject
    lateinit var dao: TestDao

    override fun onResume() {
        super.onResume()
        vb.tv.text = "Txxxxxxxxx"



        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                dao.insert(TestData("wdl", System.currentTimeMillis()))

                dao.getAll().toString().logE()
            }
        }

    }
}
