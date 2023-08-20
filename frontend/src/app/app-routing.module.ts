import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SendComponent} from "./component/send/send.component";
import {TrackComponent} from "./component/track/track.component";
import {AdminComponent} from "./component/admin/admin.component";
import {DeliveryAdminComponent} from "./component/delivery-admin/delivery-admin.component";
import {ErrorComponent} from "./component/error/error.component";
import {WelcomeComponent} from "./component/welcome/welcome.component";
import {ApiDocsComponent} from "./component/api-docs/api-docs.component";

const routes: Routes = [
  {path: "", component:WelcomeComponent, pathMatch: "full"},
  {path: "api/docs", component:ApiDocsComponent},
  {path:"send", component: SendComponent},
  {path:"track", component: TrackComponent},
  {path:"admin", component: AdminComponent},
  {path:"admin/:id", component: DeliveryAdminComponent},
  {path:"**", component: ErrorComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
