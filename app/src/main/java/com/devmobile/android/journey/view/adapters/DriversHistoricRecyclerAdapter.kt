package com.devmobile.android.journey.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.devmobile.android.journey.databinding.LayoutDriverItemHistoricBinding

data class DriverHistoricItem(val name: String)

class DriverHistoricRecyclerAdapter(
    private val drivers: List<DriverHistoricItem>,
    private val onClick: (ViewBinding) -> Unit
) :
    RecyclerView.Adapter<DriverHistoricRecyclerAdapter.DriverHistoricViewHolder>() {

    class DriverHistoricViewHolder(private val binding: LayoutDriverItemHistoricBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val name: TextView = binding.textDriverName
        fun onClick(onClick: (ViewBinding) -> Unit) {
            binding.root.setOnClickListener {
                onClick(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverHistoricViewHolder {
        val binding = LayoutDriverItemHistoricBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return DriverHistoricViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DriverHistoricViewHolder, position: Int) {
        holder.name.text = drivers[position].name
        holder.onClick(onClick)
    }

    override fun getItemCount() = drivers.size
}