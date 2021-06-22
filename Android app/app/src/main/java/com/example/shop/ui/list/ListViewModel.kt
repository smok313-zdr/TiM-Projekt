package com.example.shop.ui.list

import android.R.attr
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shop.api.ShopRepository
import com.example.shop.data.dtos.response.UserResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Collectors


class ListViewModel(private val shopRepository: ShopRepository) : ViewModel() {
    private val _productList = MutableLiveData<MutableList<AvailableProduct>>()
    val productListState: LiveData<MutableList<AvailableProduct>> = _productList
    private val _user = MutableLiveData<MutableList<UserResponse>>()
    val user: LiveData<MutableList<UserResponse>> = _user
    val TAG = this.javaClass.canonicalName

    fun refreshProductList() {
        Log.d(TAG,"refreshProductList")
        GlobalScope.launch {
            var body = shopRepository.getProducts().body()
            Log.d(TAG,body.toString())
            _productList.postValue(body?.stream()?.filter{ it.amount>0 }?.map {
                AvailableProduct(
                    it.id,
                    it.nameOfProduct,
                    it.description,
                    it.price,
                    it.amount,
                    it.picture,
                    0
                )
            }?.collect(Collectors.toList()))
            Log.d(TAG,"_productList    "+_productList.value.toString())
            Log.d(TAG,"productListState"+productListState.value.toString())
        }
    }


    fun getProductsForBasket(): MutableList<AvailableProduct>? {
        Log.d(TAG,"getProductsForBasket")
        return _productList.value?.stream()?.filter{ it.amountToBuy>0}?.collect(Collectors.toList())
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
            catch (e:IOException){
                Log.d(TAG,"setPicture Error"+e)
            }

        }
        return pictureLD
    }
}