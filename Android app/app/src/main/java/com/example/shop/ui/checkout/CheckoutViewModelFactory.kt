package com.example.shop.ui.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shop.api.ShopRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class CheckoutViewModelFactory(private val productList: List<BasketProduct>,private val token: String) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CheckoutViewModel::class.java)) {
            return CheckoutViewModel(
                productList,
                shopRepository = ShopRepository(token),
                token
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}