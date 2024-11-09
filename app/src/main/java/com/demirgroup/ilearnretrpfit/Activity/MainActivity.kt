package com.demirgroup.ilearnretrpfit.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.demirgroup.ilearnretrpfit.Adapter.CrytoAdapter
import com.demirgroup.ilearnretrpfit.Model.CryptoModel
import com.demirgroup.ilearnretrpfit.Service.CryptoAPI
import com.demirgroup.ilearnretrpfit.Util.Const
import com.demirgroup.ilearnretrpfit.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var cryptoModels:ArrayList<CryptoModel>? = null
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: CrytoAdapter
    private var compositeDisposable: CompositeDisposable?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        compositeDisposable = CompositeDisposable()
        loadData()
    }

    private fun setRecyclerview(cryptoList: List<CryptoModel>) {
        cryptoModels = ArrayList(cryptoList)
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
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)

        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this@MainActivity::setRecyclerview)
        )

       /* val service = retrofit.create(CryptoAPI::class.java)
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
        })*/
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}