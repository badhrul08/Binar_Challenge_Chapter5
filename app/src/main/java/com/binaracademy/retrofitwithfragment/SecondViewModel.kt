package com.binaracademy.retrofitwithfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binaracademy.retrofitwithfragment.model.GetAllCarResponseItem
import com.binaracademy.retrofitwithfragment.model.MovieModel
import com.binaracademy.retrofitwithfragment.model.Result
import com.binaracademy.retrofitwithfragment.network.CarsApi
import com.binaracademy.retrofitwithfragment.network.MovieApi
import retrofit2.Call
import retrofit2.Response

class SecondViewModel : ViewModel() {

    private val movies : MutableLiveData<List<Result>> by lazy {
        MutableLiveData<List<Result>>().also {
            getAllMovies()
        }
    }


    fun getMovies(): LiveData<List<Result>>{
        return movies
    }
    private fun getAllMovies(){
        MovieApi.retrofitService.allMovies().enqueue(object : retrofit2.Callback<MovieModel>{
            override fun onResponse(
                call: Call<MovieModel>,
                response: Response<MovieModel>
            ) {
               movies.value = response.body()?.results
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                Log.d("Tag",t.message.toString())
            }

        })
    }
}