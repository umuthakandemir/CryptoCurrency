package com.demirgroup.ilearnretrpfit.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demirgroup.ilearnretrpfit.Model.CryptoModel
import com.demirgroup.ilearnretrpfit.databinding.CryptoitemsBinding
import com.squareup.picasso.Picasso

class CrytoAdapter(val cryptoList:ArrayList<CryptoModel>) : RecyclerView.Adapter<CrytoAdapter.CryptoHolder> (){
    class CryptoHolder(val binding: CryptoitemsBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val binding = CryptoitemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CryptoHolder(binding)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.binding.cryptoName.text = cryptoList.get(position).currencyName
        holder.binding.cryptoPrice.text = cryptoList.get(position).currencyPrice
        Picasso.get().load(cryptoList.get(position).currencyImage).into(holder.binding.cryptoImage)
    }
}