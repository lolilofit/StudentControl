import { Component, OnInit } from '@angular/core';
import {Group, GroupsService} from '../../services/groups.service';

@Component({
  selector: 'app-group-container',
  templateUrl: './group-container.component.html',
  styleUrls: ['./group-container.component.css']
})
export class GroupContainerComponent implements OnInit {
  get groups(): Array<Group> {
    return this.intGroups;
  }
  private intGroups: Array<Group>;

  constructor(private service: GroupsService) { }

  ngOnInit() {
    this.service.load()
      .subscribe(groups => this.intGroups = groups);
  }

}
