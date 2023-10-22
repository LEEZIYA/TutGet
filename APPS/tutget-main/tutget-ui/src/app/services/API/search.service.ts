import { Injectable } from '@angular/core';
import { RestclientService } from '../restclient.service';


@Injectable({
  providedIn: 'root'
})
export class SearchService {
  BASE_URL: string = '/search';

  constructor(private restclient: RestclientService) {}

  getListing(id: String) {
    return this.restclient.getrawjson(this.BASE_URL + '/' + id, false);
  }

  getSearchResult(searchKey: String) {
    return this.restclient.getrawjson(this.BASE_URL + '/all/'+ searchKey, false);
  }

  getSearchResultAll(requestData: Object) {
    return this.restclient.getrawjson(this.BASE_URL + '/all/all/'+ requestData, false);
  }

}
