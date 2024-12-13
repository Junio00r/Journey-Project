package com.devmobile.android.journey.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.devmobile.android.journey.databinding.LayoutTripMadeBinding

data class Trip(
    val origin: String,
    val destiny: String,
    val driverName: String,
    val date: String,
    val distance: String,
    val time: String,
    val cost: String,
)

class RecyclerTripsAdapter(private val trips: List<Trip>) :
    RecyclerView.Adapter<RecyclerTripsAdapter.TripViewHolder>() {

    class TripViewHolder(private val binding: LayoutTripMadeBinding) : ViewHolder(binding.root) {
        val origin: TextView = binding.textOrigin
        val destiny: TextView = binding.textDestiny
        val driverName: TextView = binding.textDriverName
        val date: TextView = binding.textDate
        val distance: TextView = binding.textDistance
        val time: TextView = binding.textTime
        val cost: TextView = binding.textCost
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val binding = LayoutTripMadeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TripViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        holder.origin.text = trips[position].origin
        holder.destiny.text = trips[position].destiny
        holder.driverName.text = trips[position].driverName
        holder.date.text = trips[position].date
        holder.cost.text = trips[position].cost
        holder.time.text = trips[position].time
        holder.distance.text = trips[position].distance
    }

    override fun getItemCount() = trips.size
}