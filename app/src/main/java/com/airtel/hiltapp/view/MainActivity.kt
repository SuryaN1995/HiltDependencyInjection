package com.airtel.hiltapp.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.airtel.hiltapp.R
import com.airtel.hiltapp.databinding.ActivityMainBinding
import com.airtel.hiltapp.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var adapter: MainAdapter? = null
    @VisibleForTesting val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
    }

    private fun initView() {
        adapter = MainAdapter(arrayListOf())
        viewModel.countryLiveData.observe(this, Observer { countryList ->
            countryList?.let {
                adapter?.countryList = it
                adapter?.notifyDataSetChanged()
            }
        })
        binding?.rvList?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
        viewModel.getCountryList()
    }
}