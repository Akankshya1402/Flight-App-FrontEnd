import { Routes } from '@angular/router';

export const routes: Routes = [

  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },

  {
    path: 'home',
    loadComponent: () =>
      import('./core/home/home')
        .then(m => m.HomeComponent)
  },

  {
    path: 'login',
    loadComponent: () =>
      import('./auth/login/login')
        .then(m => m.LoginComponent)
  },

  {
    path: 'register',
    loadComponent: () =>
      import('./auth/register/register')
        .then(m => m.RegisterComponent)
  },

  {
    path: 'flights',
    loadComponent: () =>
      import('./flight/flight-search/flight-search')
        .then(m => m.FlightSearchComponent)
  },

  {
    path: 'seats',
    loadComponent: () =>
      import('./booking/seat-selection/seat-selection')
        .then(m => m.SeatSelectionComponent)
  },

  {
    path: '**',
    redirectTo: 'home'
  }
];
