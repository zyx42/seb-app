import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../services/product.service';

@Component({
  selector: 'app-product-manage',
  templateUrl: './product-manage.component.html',
  styleUrls: ['./product-manage.component.scss']
})
export class ProductManageComponent implements OnInit {

  products: any;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.readProducts();
    console.log(this.products);
  }

  readProducts(): void {
    this.productService.readAll().subscribe(
      response => {
        this.products = response;
        console.log(response);
      },
      error => {
        console.log(error);
      }
    );
  }

  removeProduct(productName: string): void {
    this.productService.delete(productName).subscribe(
      response => {
        console.log(response);
      }
    );
    this.readProducts();
  }

}
