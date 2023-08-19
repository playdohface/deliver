import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SendComponent} from "./component/send/send.component";
import {TrackComponent} from "./component/track/track.component";
import {AdminComponent} from "./component/admin/admin.component";
import {DeliveryAdminComponent} from "./component/delivery-admin/delivery-admin.component";

const routes: Routes = [
  {path: "", redirectTo:"send", pathMatch: "full"},
  {path:"send", component: SendComponent},
  {path:"track", component: TrackComponent},
  {path:"admin", component: AdminComponent},
  {path:"admin/:id", component: DeliveryAdminComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
