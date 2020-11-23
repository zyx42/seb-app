import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from './services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'seb-app-angular';

  constructor(private tokenStorageService: TokenStorageService) {
  }

  ngOnInit(): void {
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
