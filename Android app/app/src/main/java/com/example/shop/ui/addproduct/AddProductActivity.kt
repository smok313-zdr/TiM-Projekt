package com.example.shop.ui.addproduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.shop.R
import com.example.shop.databinding.ActivityAddProductBinding
import com.example.shop.databinding.ActivityListBinding
import com.example.shop.ui.list.ListRecyclerAdapter
import com.example.shop.ui.list.ListViewModel
import com.example.shop.ui.list.ListViewModelFactory

class AddProductActivity : AppCompatActivity() {
    private lateinit var addProductViewModel: AddProductViewModel
    private lateinit var binding: ActivityAddProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val addButton = binding.addButton
        val name = binding.nameText
        val description = binding.descriptionText
        val price = binding.priceText
        val amount = binding.amountText
        val url = binding.urlText
        val loading = binding.loading

        val token = getIntent().getExtras()?.getString("Token")
        addProductViewModel = ViewModelProvider(this, AddProductViewModelFactory(token))
            .get(AddProductViewModel::class.java)



        addButton.setOnClickListener {
            if (name.text.isNotBlank() &&
                description.text.isNotBlank() &&
                price.text.isNotBlank() &&
                amount.text.isNotBlank() &&
                url.text.isNotBlank()
            ){
                loading.visibility = View.VISIBLE
                val bodyLD = addProductViewModel.addProduct(
                    name.text.toString(),
                    description.text.toString(),
                    price.text.toString(),
                    amount.text.toString(),
                    url.text.toString()
                )
                bodyLD.observe(this@AddProductActivity, Observer {
                    loading.visibility = View.GONE
                    finish();
                })
            }

        }


    }
}