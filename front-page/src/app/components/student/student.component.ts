import {Component, Input, OnInit} from '@angular/core';
import {Student} from '../../services/students.service';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {
  @Input() student: Student;
  constructor() { }

  ngOnInit() {
  }

}
