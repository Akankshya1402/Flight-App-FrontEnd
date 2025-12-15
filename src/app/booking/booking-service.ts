import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class BookingService {

  private BASE_URL = 'http://localhost:8080/api/booking';

  constructor(private http: HttpClient) {}

  bookTicket(flightId: number, payload: any): Observable<any> {
    return this.http.post(
      `${this.BASE_URL}/${flightId}`,
      payload
    );
  }

  getUserBookings(email: string): Observable<any[]> {
    return this.http.get<any[]>(
      `${this.BASE_URL}/history/${email}`
    );
  }
}
