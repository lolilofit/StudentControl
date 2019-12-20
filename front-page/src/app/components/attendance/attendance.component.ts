import { Component, Input, OnInit } from '@angular/core';
import { Attendance } from '../../services/attendance.service';

@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrls: ['./attendance.component.css']
})
export class AttendanceComponent implements OnInit {
  @Input() attendance: Attendance;
  private statusString: string;

  constructor() { }

  ngOnInit() {
    if (this.attendance.status) {
      this.statusString = 'ПОСЕЩЕНО';
    } else {
      this.statusString = 'ПРОПУЩЕНО';
    }
  }
}
