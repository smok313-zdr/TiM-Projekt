package com.example.shop.ui.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shop.api.ShopRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.stream.Collectors
import java.util.stream.Stream

class ListViewModel(private val shopRepository: ShopRepository) : ViewModel() {
    private val _productList = MutableLiveData<MutableList<AvailableProduct>>()
    val productListState: LiveData<MutableList<AvailableProduct>> = _productList
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
}