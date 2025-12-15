import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FlightService } from '../flight.service';
import { BookingService } from '../../booking/booking-service';

@Component({
  selector: 'app-flight-search',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './flight-search.html',
  styleUrls: ['./flight-search.css']
})
export class FlightSearchComponent {

  search = {
    fromCity: '',
    toCity: '',
    date: '',
    roundTrip: false
  };

  flights: any[] = [];
  loading = false;
  searched = false;

  constructor(
    private flightService: FlightService,
    private bookingService: BookingService
  ) {}

  searchFlights() {

    if (!this.search.fromCity || !this.search.toCity || !this.search.date) {
      alert('Please fill all fields');
      return;
    }

    // 🔥 BACKEND EXPECTS DATE RANGE
    const departureFrom = new Date(this.search.date);
    departureFrom.setHours(0, 0, 0, 0);

    const departureTo = new Date(this.search.date);
    departureTo.setHours(23, 59, 59, 999);

    const payload = {
      fromCity: this.search.fromCity.trim(),
      toCity: this.search.toCity.trim(),
      departureFrom: departureFrom.toISOString(),
      departureTo: departureTo.toISOString(),
      roundTrip: this.search.roundTrip
    };

    console.log('FINAL PAYLOAD:', payload);

    this.loading = true;
    this.searched = true;
    this.flights = [];

    this.flightService.searchFlights(payload).subscribe({
      next: (data: any[]) => {
        console.log('RAW API RESPONSE:', data);
        this.flights = data;
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.loading = false;
        alert('Failed to fetch flights');
      }
    });
  }

bookFlight(flight: any) {

  const email = localStorage.getItem('email');
  if (!email) {
    alert('Please login again');
    return;
  }

  // TEMPORARY STATIC PASSENGER (for now)
  const bookingPayload = {
    customerName: 'Test User',
    email: email,
    numberOfSeats: 1,
    mealRequired: false,
    journeyDate: flight.departureTime, // ISO string OK
    passengers: [
      {
        name: 'Test User',
        gender: 'FEMALE',
        age: 22,
        seatNumber: 'A1'
      }
    ]
  };

  console.log('BOOKING PAYLOAD:', bookingPayload);

  this.bookingService
    .bookTicket(flight.id, bookingPayload)
    .subscribe({
      next: (res) => {
        console.log('BOOKING SUCCESS:', res);
        alert('Booking successful. PNR: ' + res.pnr);
      },
      error: (err) => {
        console.error('BOOKING ERROR:', err);
        alert('Booking failed');
      }
    });
}



}
