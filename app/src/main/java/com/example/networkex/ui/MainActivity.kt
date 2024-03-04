package com.example.networkex.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.networkex.R
import com.example.networkex.databinding.ActivityMainBinding
import com.example.networkex.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding?>(
            this,
            R.layout.activity_main
        )
            .apply {
                vm = viewModel
                lifecycleOwner = this@MainActivity
            }

        initUI()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.users.collect {
                    (binding.rv.adapter as MainAdapter).submitData(it)
                }
            }
        }
    }

    private fun initUI() {
        val adapter = MainAdapter()
        binding.rv.adapter = adapter
    }
}