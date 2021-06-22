package com.example.shop.ui.complete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shop.api.ShopRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class CompleteViewModelFactory(private val token: String?) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CompleteViewModel::class.java)) {
            return CompleteViewModel(
                shopRepository = ShopRepository(token)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}