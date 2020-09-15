export class User{
  id : number;
  firstName: string;
  lastName: string;
  phone: string;
  email: string;
  username: string;
  password: string;
  birthDay: string;
  active: boolean;
  role: string;
  constructor() {}
}
export class Role{
  id : number;
  name : string;

  constructor() {}
}
export class Movie{
  id : number;
  name : string;
  description : string;
  genre : string;
  duration : number;
  rating : number;
  projections : Projection[];
  rates : Rate[]

  constructor() {}
}

export class Rate{
  id : number;
  value : string;
  user : User;
  movie : Movie;

  constructor() {}
}

export class Cinema{
  id: number;
  name:string;
  address: string;
  phone: string;
  email:string;
  manager:string;

  constructor() {}
}

export class Hall{
  id:number;
  mark:string;
  capacity:number;
  constructor(){}
}

export class Projection {
  id: number;
  date : string;
  price : number;
  numOfReservedCards : number;
  hall : Hall;
  movie : Movie;
  constructor() {}
}

export class ProjectionDto {
  id: number;
  date : string;
  price : number;
  numOfReservedCards : number;
  hall : string;
  movie : string;
  constructor() {}
}
export class Reservation {
  id: number;
  numOfCards : number;
  created : string;
  user: User;
  projection : Projection;
  bought : boolean;
  constructor() {}
}
