import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../services/product.service';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.scss']
})
export class ProductAddComponent implements OnInit {

  addProductForm: FormGroup;
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
  isSubmitted = false;
  isSubmitFailed = false;
  errorMessage = '';

  constructor(private productService: ProductService,
              private fb: FormBuilder) {
    this.addProductForm = this.fb.group({
      productName: ['', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(20)]],
      ageBrackets: this.fb.array([], [Validators.required]),
      incomeBrackets: this.fb.array([], [Validators.required]),
      student: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
  }

  get f(): any {
    return this.addProductForm.controls;
  }

  onCheckboxChange(e: any): void {
    const checkArray: FormArray = this.addProductForm.get(e.target.name) as FormArray;

    if (e.target.checked) {
      checkArray.push(new FormControl(e.target.value));
    } else {
      const index = checkArray.controls.findIndex(x => x.value === e.target.value);
      checkArray.removeAt(index);
    }
  }

  createProduct(): void {

    this.productService.create(this.addProductForm.value).subscribe(
      response => {
        console.log(response);
        this.isSubmitted = true;
      },
      error => {
        this.errorMessage = error.error.message;
        this.isSubmitFailed = true;
        console.log(error);
      }
    );
  }

  newProduct(): void {
    this.isSubmitted = false;
    this.isSubmitFailed = false;
    this.addProductForm.reset();
  }

}
