import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private BASE_URL = 'http://localhost:8082/api/auth';

  constructor(private http: HttpClient) {}

  login(credentials: { email: string; password: string }) {
    return this.http.post<{ token: string }>(
      `${this.BASE_URL}/login`,
      credentials
    );
  }

  register(user: any) {
    return this.http.post<{ token: string }>(
      `${this.BASE_URL}/register`,
      user
    );
  }
}

