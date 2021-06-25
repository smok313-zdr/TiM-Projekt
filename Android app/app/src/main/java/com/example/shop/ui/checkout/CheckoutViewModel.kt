package com.example.shop.ui.checkout

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shop.api.ShopRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Collectors

class CheckoutViewModel(productList: List<BasketProduct>, private val shopRepository: ShopRepository, private val token:String):ViewModel(){

    val TAG = this.javaClass.canonicalName

    fun makePayment(url:MutableLiveData<String>) {
        GlobalScope.launch {
            if(sum!=null){
                val result = shopRepository.makePayment(sum.toDouble(),token)
                if(result.isSuccessful){
                    url.postValue(result.body()?.redirectUrl)
                }
            }
        }
    }

    fun buyProducts() {
        GlobalScope.launch {
            if(basket!=null){
                for(product in basket){
                    shopRepository.buyProduct(product.id,product.amount)
                }
            }
        }
    }

    fun getPicture(picture: String): MutableLiveData<Bitmap> {
        Log.d(TAG,"setPicture")
        val pictureLD = MutableLiveData<Bitmap>()
        GlobalScope.launch {
            try {
                val url = URL(picture)
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.setDoInput(true)
                connection.connect()
                val input: InputStream = connection.getInputStream()
                pictureLD.postValue(BitmapFactory.decodeStream(input))
            }
            catch (e: IOException){
                Log.d(TAG,"setPicture Error"+e)
            }

        }
        return pictureLD
    }

    val basket = productList.stream().map {
        BasketProduct(
            it.id,
            it.nameOfProduct,
            it.price,
            it.amount,
            it.picture
        )
    }?.collect(Collectors.toList())?.toList()
    val sum = basket?.stream()?.map { it.price*it.amount}?.reduce(0, Integer::sum)
}