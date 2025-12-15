import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookingService } from '../booking-service';

@Component({
  selector: 'app-my-bookings',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './my-bookings.html',
  styleUrls: ['./my-bookings.css']
})
export class MyBookingsComponent implements OnInit {

  bookings: any[] = [];

  constructor(private bookingService: BookingService) {}

  ngOnInit(): void {
    const userStr = localStorage.getItem('user');
    if (!userStr) {
      alert('Please login again');
      return;
    }

    const user = JSON.parse(userStr);

    this.bookingService
      .getUserBookings(user.email)
      .subscribe({
        next: (data) => this.bookings = data,
        error: (err) => console.error(err)
      });
  }
}
