package com.example.shop.ui.list

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shop.data.Basket
import com.example.shop.databinding.ActivityListBinding
import com.example.shop.ui.checkout.BasketProduct
import com.example.shop.ui.checkout.CheckoutActivity

class ListActivity : AppCompatActivity() {
    private lateinit var listViewModel: ListViewModel
    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val buyButton = binding.buyButton
        val refreshButton = binding.refreshButton
        val productsRecyclerView = binding.productsRecyclerView


        val token = getIntent().getExtras()?.getString("Token")
        listViewModel = ViewModelProvider(this, ListViewModelFactory(token))
            .get(ListViewModel::class.java)


        listViewModel.productListState.observe(this@ListActivity, Observer {
            productsRecyclerView.adapter = ListRecyclerAdapter(listViewModel.productListState)
        })
        productsRecyclerView.layoutManager = LinearLayoutManager(this)
        productsRecyclerView.adapter = ListRecyclerAdapter(listViewModel.productListState)
        listViewModel.refreshProductList()
        refreshButton.setOnClickListener{
            listViewModel.refreshProductList()

        }
        buyButton.setOnClickListener {
            val basket =  listViewModel.getProductsForBasket()
            if (basket != null) {
                if(basket.size>0){
                    Toast.makeText(
                        applicationContext,
                        "Basket size"+basket.size,
                        Toast.LENGTH_LONG
                    ).show()
                    setResult(Activity.RESULT_OK)
                    val intent = Intent(this, CheckoutActivity::class.java).apply {
                        putExtra("Token", token)
                        Basket.basket = basket.map { BasketProduct(
                            it.id,
                            it.nameOfProduct,
                            it.price,
                            it.amountToBuy
                        ) }
                    }
                    startActivity(intent)
                }
                else{
                    Toast.makeText(
                        applicationContext,
                        "Empty basket",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
            else{
                Toast.makeText(
                    applicationContext,
                    "Basket is null",
                    Toast.LENGTH_LONG
                ).show()
            }

        }


    }
}