import { Routes, RouterModule } from '@angular/router';
import { GroupContainerComponent } from './components/group-container/group-container.component';
import { AttendanceContainerComponent } from './components/attendance-container/attendance-container.component';
import { ModuleWithProviders } from '@angular/core';

const routes: Routes = [
  {
    path: 'groups',
    pathMatch: 'full',
    component: GroupContainerComponent
  },
  {
    path: 'attendance/:id',
    pathMatch: 'full',
    component: AttendanceContainerComponent
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'groups'
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);
