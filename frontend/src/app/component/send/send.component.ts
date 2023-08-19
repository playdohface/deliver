import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Country, countryOptions} from "../../model/Country";
import {TransitLocation} from "../../model/TransitLocation";
import {TransitLocationService} from "../../service/transit-location.service";
import {DeliveryService} from "../../service/delivery.service";
import {Delivery} from "../../model/Delivery";

@Component({
  selector: 'app-send',
  templateUrl: './send.component.html',
  styleUrls: ['./send.component.css']
})
export class SendComponent {

  deliveryForm: FormGroup;
  dropOffOptions: TransitLocation[] = [];
  countryOptions: Country[] = countryOptions;
  error: string|null = null;
  success: Delivery|null = null;

  constructor(
    private fb: FormBuilder,
    private deliveryService: DeliveryService,
    private transitLocationService: TransitLocationService
    ){
    transitLocationService.getDropOffStations()
      .subscribe(tl => this.dropOffOptions = tl)

    this.deliveryForm = this.fb.group({
      sender: fb.group({
        id: [null],
        firstName: [""],
        lastName: [""],
        street: [""],
        addressExtras: [null],
        postalCode: [""],
        city: [""],
        country: ["GERMANY"]
      }),
      receiver: this.fb.group({
        id: [null],
        firstName: [""],
        lastName: [""],
        street: [""],
        addressExtras: [null],
        postalCode: [""],
        city: [""],
        country: ["GERMANY"]
      }),
      dropOffId: ["1"]
    })
  }
  submit() {
    this.deliveryService.send(this.deliveryForm.value).subscribe(d => {
      this.success = d;
      console.log(d);
    });
  }

}
