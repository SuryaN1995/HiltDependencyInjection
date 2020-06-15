package com.airtel.hiltapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.airtel.hiltapp.R
import com.airtel.hiltapp.custom.loadImage
import com.airtel.hiltapp.custom.progressDrawable
import com.airtel.hiltapp.databinding.AdapterMainBinding
import com.airtel.hiltapp.model.Country

/**
 * Created by SURYA N on 4/6/20.
 */
class MainAdapter(var countryList: List<Country>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    class MainViewHolder(val binding: AdapterMainBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Country) {

            binding.ivImg.loadImage("https:${country.url}", progressDrawable(binding.root.context))
            binding.tvCountry.text = country.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainViewHolder(
        DataBindingUtil.inflate<AdapterMainBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_main,
            parent,
            false
        )
    )

    override fun getItemCount() = countryList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(countryList[position])
    }

}