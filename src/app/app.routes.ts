import { Routes } from '@angular/router';
import { authGuard } from './core/auth.guard';

import { LoginComponent } from './auth/login/login';
import { RegisterComponent } from './auth/register/register';
import { FlightSearchComponent } from './flight/flight-search/flight-search';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  {
    path: 'flights',
    component: FlightSearchComponent,
    canActivate: [authGuard]
  },

  { path: '', redirectTo: 'login', pathMatch: 'full' }
];
