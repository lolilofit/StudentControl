import {Component, Input, OnInit} from '@angular/core';
import {Attendance} from '../../services/attendance.service';

@Component({
  selector: 'app-attendance-container',
  templateUrl: './attendance-container.component.html',
  styleUrls: ['./attendance-container.component.css']
})
export class AttendanceContainerComponent implements OnInit {
  @Input() attendanceList: Array<Attendance>;

  get attendances() {
    return this.attendanceList;
  }

  ngOnInit(): void {
  }
}
