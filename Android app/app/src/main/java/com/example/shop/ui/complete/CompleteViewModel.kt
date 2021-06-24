package com.example.shop.ui.complete

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shop.api.ShopRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.stream.Collectors
import java.util.stream.Stream

class CompleteViewModel(private val shopRepository: ShopRepository) : ViewModel() {

    fun completePayment(paymentId: String, payerID: String) {
        GlobalScope.launch {
            val result = shopRepository.completePayment(payerID,paymentId)
            Log.d("completePayment",result.body().toString())
        }
    }
}