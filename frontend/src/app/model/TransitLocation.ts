import {Address} from "./Address";

export interface TransitLocation {
  id?: number,
  address?: Address,
  description: string,
  dropOff: boolean
}
