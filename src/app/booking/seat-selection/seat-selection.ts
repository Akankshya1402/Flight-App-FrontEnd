import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-seat-selection',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './seat-selection.html',
  styleUrls: ['./seat-selection.css']
})
export class SeatSelectionComponent {

  seatNumber = '';
  bookings: any[] = [];

  confirmSeat() {
    if (!this.seatNumber.trim()) {
      alert('Enter seat number');
      return;
    }

    const booking = {
      seat: this.seatNumber.toUpperCase(),
      bookedAt: new Date().toLocaleString()
    };

    this.bookings.push(booking);
    this.seatNumber = '';
  }
}
