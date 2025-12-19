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
export class FlightSearch {

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

  const payload = {
    fromCity: this.search.fromCity.trim(),
    toCity: this.search.toCity.trim(),
    journeyDate: this.search.date, // yyyy-MM-dd
    roundTrip: this.search.roundTrip
  };

  console.log('FINAL PAYLOAD (ANGULAR):', payload);

  this.loading = true;
  this.flights = [];
  this.searched = false;

  this.flightService.searchFlights(payload).subscribe({
    next: (data: any[]) => {
      console.log('FLIGHTS FROM API:', data);
      this.flights = data;
      this.loading = false;
      this.searched = true;
    },
    error: (err) => {
      console.error(err);
      this.loading = false;
      this.searched = true;
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

const bookingPayload = {
  customerName: 'Test User',
  email: email,
  numberOfSeats: 1,
  mealRequired: false,
  journeyDate: flight.departureTime, // ✅ ISO datetime
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

  this.bookingService.bookTicket(flight.id, bookingPayload)
    .subscribe({
      next: (res) => {
        alert(`Booking successful 🎉\nPNR: ${res.pnr}`);
      },
      error: (err) => {
        console.error('BOOKING ERROR:', err);
        alert('Booking failed');
      }
    });
}


}
