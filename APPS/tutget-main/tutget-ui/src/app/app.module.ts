import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './utilities/header/header.component';
import { MainComponent } from './components/main/main.component';
import { FooterComponent } from './utilities/footer/footer/footer.component';
import { QnaComponent } from './qna/components/qna.component';
import { QnaViewQuestionComponent } from './qna/components/qna-view-question/qna-view-question.component';
import { SharedModule } from './shared/shared.module';
import { CreateListingComponent } from './components/listing/create-listing/create-listing.component';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MainComponent,
    FooterComponent,
    QnaComponent,
    QnaViewQuestionComponent,
    CreateListingComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    SharedModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
