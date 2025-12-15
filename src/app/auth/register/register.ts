import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class RegisterComponent {

  user = {
    fullName: '',
    email: '',
    password: ''
  };

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

register() {
  const payload = {
    fullName: this.user.fullName,
    email: this.user.email,
    password: this.user.password,
    role: 'USER'
  };

  this.authService.register(payload).subscribe({
    next: () => {
      alert('Registration successful. Please login.');
      this.router.navigate(['/login']);   
    },
    error: (err) => {
      console.error(err);
      alert(err.error?.message || 'Registration failed');
    }
  });
}


}
