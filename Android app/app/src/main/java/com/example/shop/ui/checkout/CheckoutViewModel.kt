package com.example.shop.ui.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shop.api.ShopRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.stream.Collectors

class CheckoutViewModel(productList: List<BasketProduct>, private val shopRepository: ShopRepository, private val token:String):ViewModel(){
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

    val basket = productList.stream().map {
        BasketProduct(
            it.id,
            it.nameOfProduct,
            it.price,
            it.amount
        )
    }?.collect(Collectors.toList())?.toList()
    val sum = basket?.stream()?.map { it.price*it.amount}?.reduce(0, Integer::sum)
}