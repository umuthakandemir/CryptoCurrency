package com.demirgroup.ilearnretrpfit.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.demirgroup.ilearnretrpfit.Adapter.CrytoAdapter
import com.demirgroup.ilearnretrpfit.Model.CryptoModel
import com.demirgroup.ilearnretrpfit.R
import com.demirgroup.ilearnretrpfit.Service.CryptoAPI
import com.demirgroup.ilearnretrpfit.Util.Const
import com.demirgroup.ilearnretrpfit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var cryptoModels:ArrayList<CryptoModel>? = null
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: CrytoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
    }

    private fun setRecyclerview() {
        cryptoModels?.let { cryptoModels ->
            adapter = CrytoAdapter(cryptoModels)
            binding.cryptorecyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.cryptorecyclerview.adapter = adapter
        }

    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()
        call.enqueue( object: Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        cryptoModels = ArrayList(it)
                        setRecyclerview()
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}