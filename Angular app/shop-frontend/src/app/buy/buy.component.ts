import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  styleUrls: ['./buy.component.css']
})
export class BuyComponent implements OnInit {
  id: number
  product: Product

  constructor(private productService: ProductService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.product = new Product();
    this.productService.getProductById(this.id).subscribe(data =>{
      this.product = data;
    })
  }

  deleteProduct(id: number){
    this.productService.deleteProduct(id).subscribe(data => {
      console.log(data);
    })
  }

  onSubmit() {
    this.productService.makePayment(this.product.price+15).subscribe((response: any) => {
      if(response["status"] == "success")
      {
        this.deleteProduct(this.id);
      }
      console.log(response);
    })
    
  }

  customerData = new FormGroup({
    firstName: new FormControl('',[
      Validators.required]),
    lastName: new FormControl('',[
      Validators.required]),
    adress: new FormControl('',[
      Validators.required]),
    zipCode: new FormControl('',[
      Validators.required]),
    city: new FormControl('',[
      Validators.required]),
    email: new FormControl('',[
      Validators.required,
      Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),
      phone: new FormControl('',[
        Validators.required,
        Validators.pattern("[0-9]{3}-[0-9]{3}-[0-9]{3}")])
    });  

    get email(){
      return this.customerData.get('email')
      }
    
      get phone(){
        return this.customerData.get('phone')
        }
    
        get city(){
          return this.customerData.get('city')
          }

          get zipCode(){
            return this.customerData.get('zipCode')
            }

            get adress(){
              return this.customerData.get('adress')
              }

              get lastName(){
                return this.customerData.get('lastName')
                }

                get firstName(){
                  return this.customerData.get('firstName')
                  }
}
