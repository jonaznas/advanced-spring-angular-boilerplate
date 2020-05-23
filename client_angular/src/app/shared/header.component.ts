import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-header',
    template: `
      <header>

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <a class="navbar-brand" href="#">Navbar</a>

          <div class="collapse navbar-collapse">
            <ul class="navbar-nav mr-auto">
              <li class="nav-item" routerLink="/" routerLinkActive="active">
                <a class="nav-link mouse-pointer">Home</a>
              </li>
              <li class="nav-item" routerLink="/second/textvariable" routerLinkActive="active">
                <a class="nav-link mouse-pointer">Second</a>
              </li>
            </ul>
          </div>
        </nav>

      </header>
    `,
    styles: []
})
export class HeaderComponent implements OnInit {

    constructor() {
    }

    ngOnInit() {
    }

}
