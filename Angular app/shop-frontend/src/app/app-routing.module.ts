import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { BuyComponent } from './buy/buy.component';
import { ContactComponent } from './contact/contact.component';
import { CreateProductComponent } from './create-product/create-product.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { ProductListComponent } from './product-list/product-list.component';
import { AuthGaurdService } from './service/auth-gaurd.service';
import { ShopClientComponent } from './shop-client/shop-client.component';
import { UpdateProductComponent } from './update-product/update-product.component';

const routes: Routes = [
  {path: 'products', component: ProductListComponent,canActivate:[AuthGaurdService]},
  {path: 'create-product', component: CreateProductComponent,canActivate:[AuthGaurdService]},
  {path: 'update-product/:id', component: UpdateProductComponent,canActivate:[AuthGaurdService]},
  {path: 'product-details/:id', component: ProductDetailsComponent},
  {path: 'about', component: AboutComponent},
  {path: 'buy/:id', component: BuyComponent},
  {path: 'shop', component: ShopClientComponent},
  {path: 'contact', component: ContactComponent},
  {path: 'login', component: LoginComponent},
  {path: 'logout', component: LogoutComponent,canActivate:[AuthGaurdService]},
  {path: '', redirectTo: 'shop', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
