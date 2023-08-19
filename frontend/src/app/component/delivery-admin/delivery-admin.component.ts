import {Component} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {DeliveryService} from "../../service/delivery.service";
import {Delivery} from "../../model/Delivery";
import {Status} from "../../model/Status";
import {TransitLocationService} from "../../service/transit-location.service";
import {TransitLocation} from "../../model/TransitLocation";

@Component({
  selector: 'app-delivery-admin',
  templateUrl: './delivery-admin.component.html',
  styleUrls: ['./delivery-admin.component.css']
})
export class DeliveryAdminComponent {

  id:number;
  delivery:Delivery|null = null;
  transitOptions:TransitLocation[] = [];
  constructor(private route:ActivatedRoute,
              private deliveryService:DeliveryService,
              private transitLocationService:TransitLocationService) {

    this.id = parseInt(route.snapshot.paramMap.get("id") ?? "");

    deliveryService.adminGetById(this.id).subscribe(d => this.delivery = d);
    transitLocationService.findAll().subscribe(tl => this.transitOptions = tl.slice(2));

  }

  transit(locationId:string) {
    let transitId = parseInt(locationId);
    this.deliveryService.adminHandle(this.id, Status.TRANSIT, transitId)
      .subscribe(d => this.delivery = d);
  }

  deliver() {
    this.deliveryService.adminHandle(this.id, Status.DELIVERED, 1).subscribe(d => this.delivery = d);
  }

  cancel() {
    this.deliveryService.adminHandle(this.id, Status.CANCELED, 2).subscribe(d => this.delivery = d);
  }
  confirmDropOff() {
    if(!this.delivery?.transitLocation.id) {
      return;
    }
    this.deliveryService.adminHandle(this.id, Status.TRANSIT, this.delivery.transitLocation.id)
      .subscribe(d => this.delivery = d);
  }

}
