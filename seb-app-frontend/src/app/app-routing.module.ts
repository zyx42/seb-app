import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ProductListComponent } from './components/product-list/product-list.component';
import { ProductAddComponent } from './components/product-add/product-add.component';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {ProductManageComponent} from './components/product-manage/product-manage.component';
import {RegisterComponent} from './components/register/register.component';
import {ProductUpdateComponent} from './components/product-update/product-update.component';
import {AuthGuard} from './helpers/auth.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'products',
    component: ProductListComponent,
    canActivate: [AuthGuard],
    data: {roles: ['ROLE_USER']}
  },
  {
    path: 'products-manage',
    component: ProductManageComponent,
    canActivate: [AuthGuard],
    data: {roles: ['ROLE_PRODUCT_MANAGER']}
  },
  {
    path: 'products-manage/product-add',
    component: ProductAddComponent,
    canActivate: [AuthGuard],
    data: {roles: ['ROLE_PRODUCT_MANAGER']}
  },
  {
    path: 'products-manage/:productName',
    component: ProductUpdateComponent,
    canActivate: [AuthGuard],
    data: {roles: ['ROLE_PRODUCT_MANAGER']}
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
