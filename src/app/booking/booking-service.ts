import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class BookingService {

  private BASE_URL = 'http://localhost:8083/api/booking';

  constructor(private http: HttpClient) {}

  bookTicket(flightId: number, payload: any): Observable<any> {
    return this.http.post(`${this.BASE_URL}/${flightId}`, payload);
  }

  getBookingHistory(email: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.BASE_URL}/history/${email}`);
  }

  cancelBooking(pnr: string): Observable<any> {
    return this.http.delete(`${this.BASE_URL}/cancel/${pnr}`);
  }

  getTicketByPnr(pnr: string): Observable<any> {
    return this.http.get(`${this.BASE_URL}/ticket/${pnr}`);
  }
}
