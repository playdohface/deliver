import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TransitLocation} from "../model/TransitLocation";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TransitLocationService {

  private backendUrl = "http://localhost:8080/api/transitlocation"
  constructor(private http:HttpClient) { }

  getDropOffStations():Observable<TransitLocation[]> {
    return this.http.get<TransitLocation[]>(`${this.backendUrl}/dropoff`);
  }
}
