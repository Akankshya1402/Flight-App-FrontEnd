import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookingService } from './booking-service';

@Component({
  selector: 'app-booking-history',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './booking-history.html'
})
export class BookingHistoryComponent implements OnInit {

  bookings: any[] = [];
  email = localStorage.getItem('email');

  constructor(private bookingService: BookingService) {}

  ngOnInit(): void {
    if (!this.email) return;

    this.bookingService.getBookingHistory(this.email)
      .subscribe(data => this.bookings = data);
  }

  cancelBooking(pnr: string) {
    this.bookingService.cancelBooking(pnr)
      .subscribe(() => {
        this.bookings = this.bookings.filter(b => b.pnr !== pnr);
      });
  }
}
