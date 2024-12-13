package com.devmobile.android.journey.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devmobile.android.journey.databinding.LayoutDriverItemTripBinding
import com.devmobile.android.journey.usecase.Driver

class DriversRecyclerAdapter(
    private var driverTripItems: List<Driver>,
    private val onSelectDriver: (View) -> Unit
) :
    RecyclerView.Adapter<DriversRecyclerAdapter.DriverViewHolder>() {

    class DriverViewHolder(private val binding: LayoutDriverItemTripBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val name: TextView = binding.textDriverName
        val descritpion: TextView = binding.textDriverDescription
        val vehicle: TextView = binding.textDriverVehicle
        val avaliation: TextView = binding.textDriverAvaliation
        val travelCost: TextView = binding.textDriverTravelCost

        fun onClick(onClick: (View) -> Unit) {
            binding.buttonSelect.setOnClickListener {
                onClick(binding.buttonSelect)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        val binding = LayoutDriverItemTripBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DriverViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        holder.name.text = driverTripItems[position].name
        holder.descritpion.text = driverTripItems[position].name
        holder.vehicle.text = driverTripItems[position].vehicle
        holder.avaliation.text = driverTripItems[position].review.rating.toString()
        holder.travelCost.text = driverTripItems[position].value.toString()
        holder.onClick(onSelectDriver)
    }

    override fun getItemCount() = driverTripItems.size
}