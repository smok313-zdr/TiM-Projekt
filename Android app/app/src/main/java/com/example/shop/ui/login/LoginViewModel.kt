package com.example.shop.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shop.data.LoginRepository

import com.example.shop.R
import com.example.shop.api.ShopRepository
import com.example.shop.data.MyResult
import com.example.shop.data.dtos.response.JwtResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<Response<JwtResponse>>()
    val loginResult: LiveData<Response<JwtResponse>> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        GlobalScope.launch {
            val result = loginRepository.login(username, password)
            _loginResult.postValue(result)
        }

    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return username.isNotBlank()
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    fun completePayment(paymentId: String, payerID: String) {
        GlobalScope.launch {
            val result = loginRepository.shopRepository.completePayment(payerID,paymentId)
            Log.d("completePayment",result.body().toString())
        }
    }
}