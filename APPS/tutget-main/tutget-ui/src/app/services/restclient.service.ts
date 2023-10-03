import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RestclientService {

  BASE_URL: string = '/api'
  external: boolean = false;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private http: HttpClient) { }

  async getrawjson(url: string, external: boolean): Promise<any>{
    let finalURL;
    external ? finalURL = url : finalURL = this.BASE_URL + url;
    return await firstValueFrom(this.http.get(finalURL))
    .catch((err) => {
      console.log(err);
    });
  }


  async postjson(url: string, rawdata: any): Promise<any>{
    let data = JSON.stringify(rawdata);
    return await firstValueFrom(this.http.post(this.BASE_URL + url, data, this.httpOptions))
    .catch((err) => {
      console.log(err);
    });
  }

  async postjsonReturnString(url: string, rawdata: any): Promise<any>{
    let data = JSON.stringify(rawdata);
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return await firstValueFrom(this.http.post(this.BASE_URL + url, data, {headers, responseType: 'text'}))
    .catch((err) => {
      console.log(err);
    });
  }

  async putjson(url: string, rawdata: any): Promise<any>{
    let data = JSON.stringify(rawdata);
    return await firstValueFrom(this.http.put(this.BASE_URL + url, data, this.httpOptions))
    .catch((err) => {
      console.log(err);
    });
  }

  async delete(url: string): Promise<any>{
    return await firstValueFrom(this.http.delete(this.BASE_URL + url))
    .catch((err) => {
      console.log(err);
    });
  }


}
