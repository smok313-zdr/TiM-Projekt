package com.example.shop.data

import com.example.shop.api.ShopRepository
import com.example.shop.data.dtos.request.LoginRequest
import com.example.shop.data.dtos.response.JwtResponse
import retrofit2.Response

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val shopRepository: ShopRepository) {

    // in-memory cache of the loggedInUser object
    var user: JwtResponse? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
    }

    suspend fun login(username: String, password: String): Response<JwtResponse> {
        // handle login
        val result = shopRepository.signIn(
            LoginRequest(username,password)
        )
        if (result.isSuccessful) {
            setLoggedInUser(result.body())
        }
        return result
    }

    private fun setLoggedInUser(loggedInUser: JwtResponse?) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}