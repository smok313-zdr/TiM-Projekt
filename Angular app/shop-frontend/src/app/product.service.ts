import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'; 
import { Observable } from 'rxjs';
import { Product } from './product';
import { map, filter, switchMap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private baseURL = "http://localhost:8080/shop";

  constructor(private httpClient: HttpClient) { }

  getProductsList() : Observable<Product[]>{
    return this.httpClient.get<Product[]>(`${this.baseURL}`);
  }

  createProduct(product: Product): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, product);
  }

  getProductById(id: number): Observable<Product>{
    return this.httpClient.get<Product>(`${this.baseURL}/${id}`);  
  }

  updateProduct(id: number, product: Product): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, product);
  }

  deleteProduct(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }

  makePayment(sum: number) {
    return this.httpClient.post(this.baseURL+'/paypal/make/payment?sum='+sum, {});
  }

  completePayment(paymentId: any, payerId: any) {
    return this.httpClient.post(this.baseURL + '/paypal/complete/payment?paymentId=' + paymentId + '&payerId=' + payerId , {});
  }
}
