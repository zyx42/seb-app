import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

  products: any;
  ageBracket = '';
  incomeBracket = '';
  student = false;

  constructor(private productService: ProductService) { }

  ngOnInit(): void { }

  searchProductsByCriteria(): void {
    this.productService.searchByCriteria(this.ageBracket, this.incomeBracket, this.student)
      .subscribe(
          (products: any) => {
          this.products = products;
          console.log(products);
        },
        error => {
          console.log(error);
    });
  }
}
