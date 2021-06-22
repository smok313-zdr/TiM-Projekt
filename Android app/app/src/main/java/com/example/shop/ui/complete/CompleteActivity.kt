package com.example.shop.ui.complete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.shop.R
import com.example.shop.ui.login.LoginViewModel
import com.example.shop.ui.login.LoginViewModelFactory

class CompleteActivity : AppCompatActivity() {
    private lateinit var completeViewModel: CompleteViewModel
    private val TAG = this.javaClass.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete)

        var uri = intent.data
        if(uri!=null){
            var params = uri.queryParameterNames
            var paymentId = uri.getQueryParameter("paymentId")
            var tokenAPI = uri.getQueryParameter("tokenAPI")
            var payerID = uri.getQueryParameter("PayerID")
            completeViewModel = ViewModelProvider(this, CompleteViewModelFactory(tokenAPI))
                .get(CompleteViewModel::class.java)
            if(payerID!=null && paymentId!=null){
                completeViewModel.completePayment(paymentId,payerID)
                Log.d(TAG, "completePayment paymentId=$paymentId payerID=$payerID")
                Log.d(TAG, "completePayment uri=$uri")
                Toast.makeText(this,"Successful payment", Toast.LENGTH_LONG).show()
            }
        }
    }
}