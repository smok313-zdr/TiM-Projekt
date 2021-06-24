package com.example.shop.ui.addproduct

import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shop.api.ShopRepository
import com.example.shop.data.dtos.request.ProductRequest
import com.example.shop.data.dtos.response.ProductResponse
import com.example.shop.data.dtos.response.UserResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.stream.Collectors
import java.util.stream.Stream

class AddProductViewModel(private val shopRepository: ShopRepository) : ViewModel() {

    private val TAG = this.javaClass.canonicalName

    fun addProduct(
        name: String,
        description: String,
        price: String,
        amount: String,
        url: String
    ): MutableLiveData<ProductResponse> {
        val result: MutableLiveData<ProductResponse> = MutableLiveData()
        GlobalScope.launch {
            var body = shopRepository.addProducts(
                ProductRequest(
                    description, price.toInt(), amount.toInt(), price, name, url
                )
            ).body()
            result.postValue(body)
            Log.d(TAG, body.toString())
        }
        return result

    }

}