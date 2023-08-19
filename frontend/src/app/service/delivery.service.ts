import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {DeliveryRequest} from "../model/DeliveryRequest";
import {Delivery} from "../model/Delivery";
import {Status} from "../model/Status";

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

  getAll() {
    return this.http.get<Delivery[]>(`${this.backendUrl}`);
  }

  track(id:number, lastName:string) {
    return this.http.get<Delivery>(`${this.backendUrl}/${id}?lastName=${lastName}`);
  }

  adminGetById(id:number) {
    return this.http.get<Delivery>(`${this.backendUrl}/admin/${id}`);
  }

  adminHandle(id:number, status:Status, locationId:number, message?:string) {
    return this.http.patch<Delivery>(`${this.backendUrl}/admin/${id}?status=${status}&locationId=${locationId}&message=${encodeURIComponent(message ?? '')}`,null);
  }
}
