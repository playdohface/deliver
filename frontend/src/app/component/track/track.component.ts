import {Component} from '@angular/core';
import {Delivery} from "../../model/Delivery";
import {Status} from "../../model/Status";

@Component({
  selector: 'app-track',
  templateUrl: './track.component.html',
  styleUrls: ['./track.component.css']
})


export class TrackComponent {

  delivery: Delivery;

  constructor() {
    this.delivery = {
      "sender": {
        "firstName": "Karl-Erik",
        "lastName": "Enkelmann",
        "street": "Dolziger Straße 11",
        "postalCode": "10247",
        "city": "Berlin",
        "country": "GERMANY",
        "id": 1
      },
      "receiver": {
        "firstName": "Somiantha",
        "lastName": "Moussa",
        "street": "Engelbertstr. 42",
        "postalCode": "50674",
        "city": "Köln",
        "country": "GERMANY",
        "id": 2
      },
      "createdAt": "2023-08-18T15:05:54.451368",
      "logs": [
        {
          "time": "2023-08-18T15:05:54.490063",
          "message": "Request received",
          "id": 1
        }
      ],
      "transitLocation": {
        "description": "Customer Center One",
        "id": 7,
        "dropOff": true
      },
      "status": Status.PENDING,
      "id": 1000001
    }
  }


}
