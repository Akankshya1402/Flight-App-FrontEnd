import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FlightService } from '../flight.service';

@Component({
  selector: 'app-flight-search',
  standalone: true,
  templateUrl: './flight-search.html',
  styleUrls: ['./flight-search.css'],
  imports: [CommonModule, FormsModule]
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
  searched = false; // 🔥 IMPORTANT FLAG

  constructor(private flightService: FlightService) {}

  searchFlights() {

    if (!this.search.fromCity || !this.search.toCity || !this.search.date) {
      alert('Please fill all fields');
      return;
    }

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

    this.loading = true;
    this.searched = true;
    this.flights = [];

    this.flightService.searchFlights(payload).subscribe({
      next: (res) => {
        this.flights = res;
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.loading = false;
        alert('Failed to fetch flights');
      }
    });
  }
}
