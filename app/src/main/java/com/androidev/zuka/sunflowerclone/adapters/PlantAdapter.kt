package com.androidev.zuka.sunflowerclone.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.androidev.zuka.sunflowerclone.PlantListFragmentDirections
import com.androidev.zuka.sunflowerclone.data.model.Plant
import com.androidev.zuka.sunflowerclone.databinding.ListItemPlantBinding

class PlantAdapter(var lista: List<Plant>) : RecyclerView.Adapter<PlantAdapter.ViewHolder>()
//ListAdapter<Plant, PlantAdapter.ViewHolder>(PlantDiffCallBack())
{
    override fun getItemCount(): Int {
        return lista?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = lista.get(position)
        Log.d("TAG", "doso u on bind")
        holder.apply {
            bind(createOnClickListener(plant.plantId), plant)
            itemView.tag = plant
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemPlantBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    private fun createOnClickListener(plantId: String): View.OnClickListener {
        return View.OnClickListener {
            val direction = PlantListFragmentDirections.ActionPlantListFragmentToPlantDetailFragment(plantId)
            it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
            private val binding: ListItemPlantBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: Plant) {
            binding.apply {
                clickListener = listener
                plant = item
                Log.d("TAG", "doso u bind metodu ime planta je " + plant?.name)
                executePendingBindings()
            }
        }
    }

}