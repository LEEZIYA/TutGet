import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SearchService } from 'src/app/services/API/search.service';
import { RestclientService } from 'src/app/services/restclient.service';
import { ACADEMICSUBJECTIDLIST,ACADEMICLEVELIDLIST } from 'src/app/utilities/code-table/AcademicLevelSubjectList';
import { HOURLYRATERANGELIST } from 'src/app/utilities/code-table/HourlyRateRangeList';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  selectedSearch: string = '';
  searchQuery: string = '';
  qaSearchQuery: string = '';
  searchResults: any [] = [];
  searchResultList: Object[] | undefined;
  academicLvlList: Map<String, String> = ACADEMICLEVELIDLIST;
  acadlvl: string[] | undefined;
  academicSubjectList: Map<String, String> = ACADEMICSUBJECTIDLIST;
  acadSubject: string[] | undefined;
  hourlyRateRangeList: Map<String, String>  = HOURLYRATERANGELIST;
  hourlyRateRange: string[] | undefined;

  constructor(private restClient: RestclientService, private searchService: SearchService, private router: Router) {
    //console.log(this.router.getCurrentNavigation()?.extras.state);
  }
  ngOnInit(){

    this.loadComponent();


  }
  loadComponent(){

    // this.academicLvlSubjectList = ACADEMICLEVELIDLIST.get(acadLvl);

  }

  // constructor(private restClient: RestclientService, private searchService: SearchService, private utilitiesService: UtilitiesService, private reloadService: ReloadService, private router: Router, public activatedRoute: ActivatedRoute) {
  //   //console.log(this.router.getCurrentNavigation()?.extras.state);
  // }

  onSubmit() {
    const trimmedSearchQuery = this.searchQuery.trim();

    if (trimmedSearchQuery === 'invalidsearch'){
      alert("Please enter a valid search.");
    }else{
      this.searchService.getSearchResult(trimmedSearchQuery ? trimmedSearchQuery : null)
      .then(res => {
        this.searchResults = res ? res.allListing : [];
        console.log(this.searchResults);
      }, reject => {});
    }
    if (trimmedSearchQuery === 'invalidsearch'){
      alert("Please enter a valid search.");
    }else{
      const requestData = {
      trimmedSearchQuery: trimmedSearchQuery,
      acadlvl: this.acadlvl,
      acadSubject: this.acadSubject,
      };
      console.log("HELP: " + requestData.acadlvl + requestData.acadSubject + requestData.trimmedSearchQuery);
      this.searchService.getSearchResultAll(requestData)
      .then(res => {
        this.searchResults = res ? res.allListing : [];
        console.log(this.searchResults);
      }, reject => {});


    }
  }

  navigate(listingId: string){
    this.router.navigate(['listing/' + listingId]);
  }

}

