import {Address} from "./Address";
import {Log} from "./Log";
import {TransitLocation} from "./TransitLocation";
import {Status} from "./Status";

export interface Delivery {
  sender: Address,
  receiver: Address,
  createdAt: string|Date,
  logs?: Log[],
  transitLocation: TransitLocation,
  status: Status,
  id: number
}
