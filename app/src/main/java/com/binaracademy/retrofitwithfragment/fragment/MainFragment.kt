package com.binaracademy.retrofitwithfragment.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaracademy.retrofitwithfragment.MainViewModel
import com.binaracademy.retrofitwithfragment.R
import com.binaracademy.retrofitwithfragment.SecondViewModel
import com.binaracademy.retrofitwithfragment.adapter.MainAdapter
import com.binaracademy.retrofitwithfragment.adapter.SecondAdapter
import com.binaracademy.retrofitwithfragment.databinding.FragmentDetailBinding
import com.binaracademy.retrofitwithfragment.databinding.FragmentMainBinding
import com.binaracademy.retrofitwithfragment.model.GetAllCarResponseItem
import com.binaracademy.retrofitwithfragment.network.CarsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {


    private val viewModel: MainViewModel by viewModels()
    private val secondViewModel: SecondViewModel by viewModels()
    lateinit var binding : FragmentMainBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSecondObserver()

        }

    private fun setupObserver() {
        viewModel.getCars().observe(requireActivity()){
            print("Main activity : datanya -> $it")
            val adapter = MainAdapter(it)
            val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            binding.rvMain.layoutManager = layoutManager
            binding.rvMain.adapter = adapter
        }
    }
    private fun setupSecondObserver() {
        Log.d("Tag","Fragment activity : datanya ->")
        secondViewModel.getMovies().observe(requireActivity()){
            Log.d("Tag","Fragment activity : datanya -> $it")
            val adapter = SecondAdapter(it)
            val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            binding.rvMain.layoutManager = layoutManager
            binding.rvMain.adapter = adapter
        }
    }
    fun showList(data:List<GetAllCarResponseItem>?){
        val adapter = MainAdapter(data!!)
        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.rvMain.layoutManager = layoutManager
        binding.rvMain.adapter = adapter
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }


}