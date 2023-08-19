import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavbarComponent} from './component/navbar/navbar.component';
import {SendComponent} from './component/send/send.component';
import {TrackComponent} from './component/track/track.component';
import {AdminComponent} from './component/admin/admin.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { AddressViewComponent } from './component/address-view/address-view.component';
import { DeliveryDetailsComponent } from './component/delivery-details/delivery-details.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SendComponent,
    TrackComponent,
    AdminComponent,
    AddressViewComponent,
    DeliveryDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
