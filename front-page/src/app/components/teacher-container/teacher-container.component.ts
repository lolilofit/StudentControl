import {Component, OnDestroy, OnInit} from '@angular/core';
import {Teacher, TeachersService} from '../../services/teachers.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-teacher-container',
  templateUrl: './teacher-container.component.html',
  styleUrls: ['./teacher-container.component.css']
})
export class TeacherContainerComponent implements OnInit, OnDestroy {
  constructor(private teacherService: TeachersService) { }
  private sub: Subscription;
  private initTeachers: Array<Teacher>;

  get teachers() {
    return this.initTeachers;
  }

  ngOnInit() {
    this.sub = this.teacherService.load()
      .subscribe(teachers => this.initTeachers = teachers);
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

}
