import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../user';
@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  users: User[]; //Array<any>;//User[];//this also works
  title: 'asdf';
  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getUsers();
  }
  getUsers(): void {
    this.userService.getUsers()
    .subscribe(users => this.users = users);
  }
  add(username: string): void {
    username = username.trim();
    if (!username) { return; }
    this.userService.addUser({ username } as User)
      .subscribe(user => {
        this.users.push(user);
      });
  }
//snomula starts
  addUser(username: string): void {
    username = username.trim();
    if (!username) { return; }
    this.userService.addUser({ username } as User)
      .subscribe(user => {
        this.users.push(user);
      });
  }

  
  //snomuls ends

  delete(user: User): void {
    this.users = this.users.filter(h => h !== user);
    this.userService.deleteUser(user).subscribe();
  }


}
