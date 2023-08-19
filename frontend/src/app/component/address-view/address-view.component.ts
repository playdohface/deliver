import {Component, Input} from '@angular/core';
import {Address} from "../../model/Address";

@Component({
  selector: 'app-address-view',
  templateUrl: './address-view.component.html',
  styleUrls: ['./address-view.component.css']
})
export class AddressViewComponent {

  @Input()
  address?: Address;
  constructor() {

  }

}
