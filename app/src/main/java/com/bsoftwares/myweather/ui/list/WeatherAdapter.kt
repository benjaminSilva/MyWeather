package com.bsoftwares.myweather.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bsoftwares.myweather.databinding.ItemWeatherDayBinding
import com.bsoftwares.myweather.model.Day

class WeatherAdapter(val clickListener: (data: Day) -> Unit) :
    ListAdapter<Day, WeatherAdapter.WeatherViewHolder>(SearchDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder.from(
            parent
        ).also { holder ->
            holder.itemView.setOnClickListener {
                clickListener.invoke(getItem(holder.adapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = getItem(position)

        try {
            if (item.dt_txt.length > 5)
                item.dt_txt = item.dt_txt.substring(5, 16)
        } catch (e: StringIndexOutOfBoundsException) {

        }

        holder.bind(item)
    }

    class WeatherViewHolder private constructor(val binding: ItemWeatherDayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Day
        ) {
            binding.data = item
        }

        companion object {
            fun from(parent: ViewGroup): WeatherViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemWeatherDayBinding.inflate(layoutInflater, parent, false)
                return WeatherViewHolder(
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