import {Component, OnDestroy, OnInit} from '@angular/core';
import {Group, GroupsService} from '../../services/groups.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-group-container',
  templateUrl: './group-container.component.html',
  styleUrls: ['./group-container.component.css']
})
export class GroupContainerComponent implements OnInit, OnDestroy {
  get groups(): Array<Group> {
    return this.intGroups;
  }
  private sub: Subscription;
  private intGroups: Array<Group>;

  constructor(private service: GroupsService) { }

  ngOnInit() {
    this.sub = this.service.load()
      .subscribe(groups => this.intGroups = groups);
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }
}
