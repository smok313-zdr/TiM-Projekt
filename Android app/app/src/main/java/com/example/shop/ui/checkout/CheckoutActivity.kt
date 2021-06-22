package com.example.shop.ui.checkout

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.data.Basket
import com.example.shop.databinding.ActivityCheckoutBinding


class CheckoutActivity : AppCompatActivity() {
    private lateinit var checkoutViewModel: CheckoutViewModel
    private lateinit var binding: ActivityCheckoutBinding
    private var TAG = this.javaClass.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val buyButton = binding.buyButton
        val sumTextView = binding.sumTextView
        val basketRecyclerView = binding.basketRecyclerView

        val productList: List<BasketProduct>? = Basket.basket
        val token = getIntent().getExtras()?.getString("Token")
        checkoutViewModel = ViewModelProvider(this, CheckoutViewModelFactory(productList!!,token!!))
            .get(CheckoutViewModel::class.java)

        sumTextView.text = "Sum: "+checkoutViewModel.sum.toString()+" USD"

        basketRecyclerView.layoutManager = LinearLayoutManager(this)
        basketRecyclerView.adapter = CheckoutRecyclerAdapter(productList.toList())

        var url: MutableLiveData<String> = MutableLiveData()
        buyButton.setOnClickListener {
            checkoutViewModel.makePayment(url)
            val progressBar = ProgressDialog(this)
            progressBar.setCancelable(false) //you can cancel it by pressing back button
            progressBar.setMessage("Wait ...")
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressBar.show() //displays the progress bar

        }
        url.observe(this@CheckoutActivity, Observer {
            if(it.isNotEmpty()){
                Log.d(TAG,"url not empty "+it)
                checkoutViewModel.buyProducts()
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                startActivity(browserIntent)
//                finish()
            }
        })





//        buyButton.setOnClickListener {
//            val basket =  checkoutViewModel.getBasket(productList)
//            if (basket != null) {
//                if(basket.size>0){
//                    Toast.makeText(
//                        applicationContext,
//                        "Basket size"+basket.size,
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//                else{
//                    Toast.makeText(
//                        applicationContext,
//                        "Empty basket",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//
//            }
//            else{
//                Toast.makeText(
//                    applicationContext,
//                    "Basket is null",
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//
//        }

    }
}
