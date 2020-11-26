import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {of} from 'rxjs';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

  products: any;
  queryForm: FormGroup;

  constructor(private productService: ProductService,
              private fb: FormBuilder) {
    this.queryForm = this.fb.group({
      ageBracket: ['', [Validators.required]],
      incomeBracket: ['', [Validators.required]],
      student: ['', [Validators.required]]
    });
  }

  ngOnInit(): void { }

  get f(): any {
    return this.queryForm.controls;
  }

  searchProductsByCriteria(): void {
    this.productService.searchByCriteria(
      this.queryForm.value.ageBracket,
      this.queryForm.value.incomeBracket,
      this.queryForm.value.student)
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
