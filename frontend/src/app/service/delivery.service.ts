import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DeliveryRequest} from "../model/DeliveryRequest";
import {Delivery} from "../model/Delivery";

@Injectable({
  providedIn: 'root'
})
export class DeliveryService {

  private backendUrl = "http://localhost:8080/api/deliver"
  constructor(private http:HttpClient) { }

  send(deliveryRequest: DeliveryRequest) {
    console.log(deliveryRequest)
    return this.http.post<Delivery>(`${this.backendUrl}`, deliveryRequest);
  }
}
