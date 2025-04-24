import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {HttpClient, HttpResponse,HttpHeaders} from "@angular/common/http";
import { Observable } from 'rxjs';
import {map} from "rxjs/operators";





@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  constructor(private httpClient:HttpClient){}

  private baseURL:string='http://localhost:8080';

  private getUrl:string = this.baseURL + '/room/reservation/v1/';
  private postUrl:string = this.baseURL + '/room/reservation/v1';
  public submitted!:boolean;
  roomsearch! : FormGroup;
  rooms! : Room[];
  request!:ReserveRoomRequest;
  currentCheckInVal!:string;
  currentCheckOutVal!:string;
  welcomeMessages: string[] = [];
  presentationTimes: PresentationTimes | null = null;

    ngOnInit(){
      this.roomsearch= new FormGroup({
        checkin: new FormControl(' '),
        checkout: new FormControl(' ')
      });

 //     this.rooms=ROOMS;


    const roomsearchValueChanges$ = this.roomsearch.valueChanges;

    // subscribe to the stream
    roomsearchValueChanges$.subscribe(x => {
      this.currentCheckInVal = x.checkin;
      this.currentCheckOutVal = x.checkout;
    });

    // Fetch welcome messages
    this.getWelcomeMessages();
    this.getPresentationTimes();
  }

    onSubmit({value,valid}:{value:Roomsearch,valid:boolean}){
      this.getAll().subscribe({
        next: (response) => {
          console.log('Response:', response);
          // Check if response is an array or needs to be extracted
          if (Array.isArray(response)) {
            this.rooms = response;
          } else if (response && typeof response === 'object') {
            // If response is an object, try to extract the array
            const values = Object.values(response);
            if (values.length > 0 && Array.isArray(values[0])) {
              this.rooms = values[0];
            } else {
              console.error('Unexpected response format:', response);
            }
          }
        },
        error: (error) => {
          console.error('Error fetching rooms:', error);
        }
      });
    }
    reserveRoom(value:string){
      this.request = new ReserveRoomRequest(value, this.currentCheckInVal, this.currentCheckOutVal);

      this.createReservation(this.request);
    }
    createReservation(body:ReserveRoomRequest) {
      let bodyString = JSON.stringify(body); // Stringify payload
      let headers = new Headers({'Content-Type': 'application/json'}); // ... Set content type to JSON
     // let options = new RequestOptions({headers: headers}); // Create a request option

     const options = {
      headers: new HttpHeaders().append('key', 'value'),

    }

      this.httpClient.post(this.postUrl, body, options)
        .subscribe(res => console.log(res));
    }

  /*mapRoom(response:HttpResponse<any>): Room[]{
    return response.body;
  }*/

    getAll(): Observable<any> {
      return this.httpClient.get(this.baseURL + '/room/reservation/v1?checkin='+ this.currentCheckInVal + '&checkout='+this.currentCheckOutVal, {responseType: 'json'})
        .pipe(
          map((response: any) => {
            const rooms = Object.values(response)[0] as Room[];
            return rooms.map(room => ({
              ...room,
              priceUSD: `$${room.price}`,
              priceCAD: `C$${room.price}`,
              priceEUR: `€${room.price}`
            }));
          })
        );
    }

    getWelcomeMessages() {
      this.httpClient.get<string[]>(this.baseURL + '/api/welcome')
        .subscribe({
          next: (messages) => {
            console.log('Welcome messages received:', messages);
            this.welcomeMessages = messages;
          },
          error: (error) => {
            console.error('Error fetching welcome messages:', error);
          }
        });
    }

    getPresentationTimes() {
      this.httpClient.get<PresentationTimes>(this.baseURL + '/api/presentation-times')
        .subscribe({
          next: (times) => {
            console.log('Presentation times received:', times);
            this.presentationTimes = times;
          },
          error: (error) => {
            console.error('Error fetching presentation times:', error);
          }
        });
    }

  }



export interface Roomsearch{
    checkin:string;
    checkout:string;
  }




export interface Room{
  id:string;
  roomNumber:string;
  price:string;
  links:string;
  priceUSD: string;
  priceCAD: string;
  priceEUR: string;
}
export class ReserveRoomRequest {
  roomId:string;
  checkin:string;
  checkout:string;

  constructor(roomId:string,
              checkin:string,
              checkout:string) {

    this.roomId = roomId;
    this.checkin = checkin;
    this.checkout = checkout;
  }
}

export interface PresentationTimes {
  ET: string;
  MT: string;
  UTC: string;
}

/*
var ROOMS: Room[]=[
  {
  "id": "13932123",
  "roomNumber" : "409",
  "price" :"20",
  "links" : ""
},
{
  "id": "139324444",
  "roomNumber" : "509",
  "price" :"30",
  "links" : ""
},
{
  "id": "139324888",
  "roomNumber" : "609",
  "price" :"40",
  "links" : ""
}
] */

