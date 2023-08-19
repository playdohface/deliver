import {Address} from "./Address";

export interface DeliveryRequest {
  sender: Address,
  receiver: Address,
  dropOffId: number
}
