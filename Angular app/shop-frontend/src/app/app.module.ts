import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductListComponent } from './product-list/product-list.component';
import { CreateProductComponent } from './create-product/create-product.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { UpdateProductComponent } from './update-product/update-product.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AboutComponent } from './about/about.component';
import { AgmCoreModule } from '@agm/core';
import { ContactComponent } from './contact/contact.component';
import { ShopClientComponent } from './shop-client/shop-client.component';
import { BuyComponent } from './buy/buy.component'

@NgModule({
  declarations: [
    AppComponent,
    ProductListComponent,
    CreateProductComponent,
    UpdateProductComponent,
    ProductDetailsComponent,
    LoginComponent,
    LogoutComponent,
    AboutComponent,
    ContactComponent,
    ShopClientComponent,
    BuyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCKn261V5y_XLR1yolwG202wAjkP62g5_o'
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
