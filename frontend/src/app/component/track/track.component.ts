import {Component} from '@angular/core';
import {Delivery} from "../../model/Delivery";
import {Status} from "../../model/Status";
import {FormBuilder, FormGroup} from "@angular/forms";
import {DeliveryService} from "../../service/delivery.service";

@Component({
  selector: 'app-track',
  templateUrl: './track.component.html',
  styleUrls: ['./track.component.css']
})


export class TrackComponent {

  form:FormGroup;
  delivery: Delivery|null = null;
  error: string|null = null;

  constructor(private fb:FormBuilder,
              private deliveryService: DeliveryService) {
    this.form = fb.group({
      id: [],
      lastName: []
    })
  }

  submit() {
    let id = parseInt(this.form.value["id"]);
    let lastName:string = this.form.value["lastName"]
    this.deliveryService.track(id, lastName).subscribe({
      next : res => {
        this.delivery = res;
    },
      error : err => {
        console.log("status: ",err.status);
        switch (err.status) {
          case 400:
            this.error = "It appears the recipients last name didn't match the delivery";
            break;
          case 404:
            this.error = "We could not find a delivery by that tracking number";
            break;
          default:
            this.error = "We are sorry, there has been an error. Please try again later."
        }
      }
    });
  }

}
