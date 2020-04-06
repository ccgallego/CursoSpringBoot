import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'mi-primer-proyecto';
  public names: string[] = ['Cristian'];

  constructor(){
    this.addFirstElement('Juan');
    this.addLastElement('Adrian')
  }

  public addFirstElement(name: string): void {
    this.names.unshift(name);
  }

  public addLastElement(name: string): void {
    this.names.push(name);
  }


}
