import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../services/product.service';
import {ActivatedRoute} from '@angular/router';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-product-update',
  templateUrl: './product-update.component.html',
  styleUrls: ['./product-update.component.scss']
})
export class ProductUpdateComponent implements OnInit {

  updateProductForm: FormGroup;
  ageBracketsData: Array<any> = [
    { name: 'Junior age (0-17)', value: 'JUNIOR' },
    { name: 'Adult age (18-64)', value: 'ADULT'},
    { name: 'Senior age(65+)', value: 'SENIOR' }
  ];
  incomeBracketsData: Array<any> = [
    { name: 'No income (0)', value: 'NO_INCOME' },
    { name: 'Low income (1-12000)', value: 'LOW_INCOME' },
    { name: 'Medium income(12001-40000)', value: 'MEDIUM_INCOME' },
    { name: 'High income (40001+)', value: 'HIGH_INCOME' }
  ];
  updated = false;

  constructor(private productService: ProductService,
              private route: ActivatedRoute,
              private fb: FormBuilder) {
    this.updateProductForm = this.fb.group({
      productName: ['', Validators.required],
      ageBrackets: this.fb.array([], [Validators.required]),
      incomeBrackets: this.fb.array([], [Validators.required]),
      student: ['false', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.updated = false;
    this.getProduct(this.route.snapshot.paramMap.get('productName') || '');
  }

  getProduct(productName: string): void {
    this.productService.read(productName).subscribe(
      response => {
        console.log(response);
        this.updateProductForm.patchValue({
          productName: response.productName,
          student: response.student + ''
        });
        this.updateProductForm.setControl('ageBrackets', this.fb.array(response.ageBrackets));
        this.updateProductForm.setControl('incomeBrackets', this.fb.array(response.incomeBrackets));
      },
      error => {
        console.log(error);
      }
    );
  }

  onCheckboxChange(e: any): void {
    const checkArray: FormArray = this.updateProductForm.get(e.target.name) as FormArray;
    console.log(this.updateProductForm.value);

    if (e.target.checked) {
      checkArray.push(new FormControl(e.target.value));
    } else {
      const index = checkArray.controls.findIndex(x => x.value === e.target.value);
      checkArray.removeAt(index);
    }
  }

  updateProduct(): void {
    this.productService.update(this.route.snapshot.paramMap.get('productName') || '', this.updateProductForm.value)
      .subscribe(
      response => {
        console.log(response);
        this.updated = true;
      },
      error => {
        console.log(error);
      }
    );
  }

}
