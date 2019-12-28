import {Component, Input, OnInit} from '@angular/core';
import {Teacher} from '../../services/teachers.service';

@Component({
  selector: 'app-teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.css']
})
export class TeacherComponent implements OnInit {
  @Input() teacher: Teacher;

  constructor() { }

  ngOnInit() {
  }

}
