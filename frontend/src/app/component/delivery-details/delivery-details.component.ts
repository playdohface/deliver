import {Component, Input} from '@angular/core';
import {Delivery} from "../../model/Delivery";

@Component({
  selector: 'app-delivery-details',
  templateUrl: './delivery-details.component.html',
  styleUrls: ['./delivery-details.component.css']
})


export class DeliveryDetailsComponent {

@Input()
delivery?: Delivery;
}
