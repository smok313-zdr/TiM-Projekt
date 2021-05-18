import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {Product} from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-shop-client',
  templateUrl: './shop-client.component.html',
  styleUrls: ['./shop-client.component.css']
})
export class ShopClientComponent implements OnInit {

  products: Product[];
  nameOfProduct : String;
  paymentID: String;
  payerID: String;   
  
  constructor(private productService: ProductService, private router: Router,private activatedRoute: ActivatedRoute) {
    this.activatedRoute.queryParams.subscribe(params => {
      this.paymentID = params['paymentId'];
      this.payerID = params['PayerID'];
      console.log(this.paymentID); // Print the parameter to the console. 
      console.log(this.payerID); // Print the parameter to the console. 
  });
   }

  ngOnInit(): void {
    this.getProducts();
    if(this.paymentID!=null && this.payerID!=null)
    {
      this.productService.completePayment(this.paymentID,this.payerID).subscribe(data => {
        console.log(data);
      })
    }
  }

  private getProducts(){
    this.productService.getProductsList().subscribe(data => {
      this.products = data;
    });
  } 

  productDetails(id: number){
    this.router.navigate(['product-details', id]); 
  }

  buyProduct(id: number){
    this.router.navigate(['buy', id]); 
  }

  Search(){
    if(this.nameOfProduct != "") {
      this.products = this.products.filter(res=>{
        return res.nameOfProduct.toLocaleLowerCase().match(this.nameOfProduct.toLocaleLowerCase());
      });
    } else if(this.nameOfProduct == "") {
      this.ngOnInit();
    }
  }

}
