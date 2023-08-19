import { Component } from '@angular/core';
import {Delivery} from "../../model/Delivery";
import {DeliveryService} from "../../service/delivery.service";

@Component({
  selector: 'app-delivery-list',
  templateUrl: './delivery-list.component.html',
  styleUrls: ['./delivery-list.component.css']
})
export class DeliveryListComponent {

  deliveries?: Delivery[]

  constructor(private deliveryService: DeliveryService) {
    deliveryService.getAll().subscribe(d => this.deliveries = d);
  }

}
