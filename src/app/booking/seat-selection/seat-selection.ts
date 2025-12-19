import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from '../booking-service';

@Component({
  selector: 'app-seat-selection',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './seat-selection.html',
  styleUrls: ['./seat-selection.css']
})
export class SeatSelectionComponent implements OnInit {

  flightId!: number;
  selectedSeat: string | null = null;
  seats: string[] = [];

  constructor(
    private route: ActivatedRoute,
    private bookingService: BookingService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.flightId = Number(this.route.snapshot.paramMap.get('flightId'));
    this.generateSeats();
  }

  generateSeats() {
    const rows = ['A', 'B', 'C', 'D'];
    for (let i = 1; i <= 20; i++) {
      rows.forEach(r => this.seats.push(`${r}${i}`));
    }
  }

  selectSeat(seat: string) {
    this.selectedSeat = seat;
  }

  confirmBooking() {
    const email = localStorage.getItem('email');
    if (!email || !this.selectedSeat) {
      alert('Login or select seat');
      return;
    }

    const payload = {
      customerName: 'Test User',
      email,
      numberOfSeats: 1,
      mealRequired: false,
      journeyDate: new Date().toISOString(),
      passengers: [
        {
          name: 'Test User',
          gender: 'FEMALE',
          age: 22,
          seatNumber: this.selectedSeat
        }
      ]
    };

    this.bookingService.bookTicket(this.flightId, payload)
      .subscribe({
        next: (res) => {
          alert(`Booking successful 🎉\nPNR: ${res.pnr}`);
          this.router.navigate(['/bookings']);
        },
        error: () => alert('Booking failed')
      });
  }
}
