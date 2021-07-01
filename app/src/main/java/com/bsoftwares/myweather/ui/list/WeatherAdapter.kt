package com.bsoftwares.myweather.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bsoftwares.myweather.R
import com.bsoftwares.myweather.databinding.ItemWeatherDayBinding
import com.bsoftwares.myweather.model.Day

class WeatherAdapter(val clickListener: WeatherListener) :
    ListAdapter<Day, WeatherAdapter.GincanaViewHolder>(SearchDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GincanaViewHolder {
        return GincanaViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: GincanaViewHolder, position: Int) {
        val item = getItem(position)

        try {
            if (item.dt_txt.length > 5)
                item.dt_txt = item.dt_txt.substring(5, 16)
        }catch (e : StringIndexOutOfBoundsException){

        }

        holder.bind(item, clickListener)
    }

    class GincanaViewHolder private constructor(val binding: ItemWeatherDayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Day,
            clickListener: WeatherListener,
        ) {
            binding.clickListener = clickListener
            binding.data = item
        }

        companion object {
            fun from(parent: ViewGroup): GincanaViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemWeatherDayBinding.inflate(layoutInflater, parent, false)
                return GincanaViewHolder(
                    binding
                )
            }
        }

    }

    class SearchDiffCallback : DiffUtil.ItemCallback<Day>() {
        override fun areItemsTheSame(
            oldItem: Day,
            newItem: Day
        ): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(
            oldItem: Day,
            newItem: Day
        ): Boolean {
            return oldItem == newItem
        }

    }
}

class WeatherListener(val clickListener: (data: Day) -> Unit) {
    fun onClick(data: Day) {
        return clickListener(data)
    }
}

