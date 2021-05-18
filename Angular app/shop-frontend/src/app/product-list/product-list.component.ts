import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {Product} from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[];
  nameOfProduct : String;

  constructor(private productService: ProductService, private router: Router) { }

  ngOnInit(): void {
    this.getProducts();
  }

  private getProducts(){
    this.productService.getProductsList().subscribe(data => {
      this.products = data;
    });
  } 

  productDetails(id: number){
    this.router.navigate(['product-details', id]); 
  }

  updateProduct(id: number){
    this.router.navigate(['update-product', id]);  
  }

  deleteProduct(id: number){
    this.productService.deleteProduct(id).subscribe(data => {
      console.log(data);
      this.getProducts();  
    })
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
