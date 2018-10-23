package com.androidev.zuka.sunflowerclone


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.androidev.zuka.sunflowerclone.adapters.PlantAdapter
import com.androidev.zuka.sunflowerclone.data.model.Plant
import com.androidev.zuka.sunflowerclone.databinding.FragmentPlantListBinding
import com.androidev.zuka.sunflowerclone.ui.viewmodel.PlantListViewModel
import com.androidev.zuka.sunflowerclone.utilities.InjectorUtils



class PlantListFragment : Fragment() {

    private lateinit var viewModel: PlantListViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlantListBinding.inflate(inflater, container, false)
        val context = context ?: return binding.root

        val factory = InjectorUtils.providePlantListViewModelFactory(context)
        viewModel = ViewModelProviders.of(this, factory).get(PlantListViewModel::class.java)

        var lista = listOf<Plant>(Plant("1","test","desc",1),Plant("2","test2","desc",1))
        val adapter = PlantAdapter(lista)
        binding.test.adapter = adapter
        subscribeUi(adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(adapter: PlantAdapter) {
        viewModel.getPlants().observe(viewLifecycleOwner, Observer { plants ->
            if (plants != null)
            {
                Log.d("TAG",plants.size.toString())
                adapter.setList(plants)
            }
        })
    }

}