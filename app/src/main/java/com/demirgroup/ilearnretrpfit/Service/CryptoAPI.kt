package com.demirgroup.ilearnretrpfit.Service

import com.demirgroup.ilearnretrpfit.Model.CryptoModel
import com.demirgroup.ilearnretrpfit.Util.Const
import io.reactivex.Observable
import retrofit2.http.GET

interface CryptoAPI {
    @GET(Const.SECOND_URL)
    fun getData(): Observable<List<CryptoModel>> //Call<List<CryptoModel>>
}