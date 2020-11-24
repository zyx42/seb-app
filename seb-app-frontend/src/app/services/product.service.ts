import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const baseURL = 'http://localhost:8080/api/products';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  readAll(): Observable<any> {
    return this.httpClient.get(baseURL);
  }

  read(productName: string): Observable<any> {
    return this.httpClient.get(`${baseURL}/${productName}`);
  }

  create(data: any): Observable<any> {
    return this.httpClient.post(baseURL, data);
  }

  update(productName: string, data: any): Observable<any> {
    return this.httpClient.put(`${baseURL}/${productName}`, data);
  }

  delete(productName: string): Observable<any> {
    return this.httpClient.delete(`${baseURL}/${productName}`);
  }


  searchByCriteria(ageBracket: string, incomeBracket: string, student: boolean): Observable<any> {
    return this.httpClient.get(`${baseURL}/search?ageBracket=${ageBracket}&incomeBracket=${incomeBracket}&student=${student}`);
  }
}
