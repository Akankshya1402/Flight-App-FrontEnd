import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class FlightService {

  private readonly BASE_URL = 'http://localhost:8081/api/flights';

  constructor(private http: HttpClient) {}

  searchFlights(payload: any): Observable<any[]> {
    return this.http.post<any[]>(
      `${this.BASE_URL}/search`,
      payload,
      {
        params: {
          page: '0',
          size: '10'
        }
      }
    );
  }
}
