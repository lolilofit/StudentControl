import { Routes, RouterModule } from '@angular/router';
import { GroupContainerComponent } from './components/group-container/group-container.component';
import { ModuleWithProviders } from '@angular/core';
import {StudentSelectComponent} from './components/student-select/student-select.component';
import {TeacherContainerComponent} from './components/teacher-container/teacher-container.component';
import {TeacherSelectComponent} from './components/teacher-select/teacher-select.component';

const routes: Routes = [
  {
    path: 'groups',
    pathMatch: 'full',
    component: GroupContainerComponent
  },
  {
    path: 'teachers',
    pathMatch: 'full',
    component: TeacherContainerComponent
  },
  {
    path: 'attendance/student/:id',
    pathMatch: 'full',
    component: StudentSelectComponent
  },
  {
    path: 'attendance/teacher/:id',
    pathMatch: 'full',
    component: TeacherSelectComponent
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'groups'
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);
