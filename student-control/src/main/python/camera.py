# -*- coding: utf-8 -*-
"""
Created on Tue Nov 12 13:33:09 2019

@author: User
"""

import cv2 as cv
import face_recognition
import os
import sys
import requests
import json
import atexit

class Student:
    def __init__(self, id, name, group):
        self.name = name
        self.group = group
        self.id = id
        
class Attendance:
    def __init__(self, table_id, student_id, is_present):
        self.table_id = table_id
        self.student_id = student_id
        self.is_present = is_present

present_students = []
   
def find_in_list(lst, find_him) :
    for el in lst :
        if el == find_him :
            return True
    return False

def find_id(lst, find_him) :
    for el in lst :
        if el.name == find_him :
            return el.id
    return None


def main():
    table_id = 0
    students = [Student(0, 'Такого нет', '17206'), Student(1, 'Усова Дарья Сергеевна', '17206'), Student(2, 'Зулин Даниил Константинович', '17206')]
    '''
    table_id = sys.argv[1]
    resp = requests.get('http://localhost:8080/api/student/activity/' + table_id);
    if resp.status_code != 200 : 
        return
    students = json.loads(resp.content)
    print('stud to find')
    print(students)
    '''
    
    capture = cv.VideoCapture(0)
    cv.namedWindow("capture")
    face_locations = []
    face_encodings = []
    face_names = []

    familiar_faces = []
    familiar_names = []
    
    
    for student in students :
        loaded = student
        try :
            image = face_recognition.load_image_file("examples/" + loaded.name.replace(" ", "_") + ".jpg")
            face_encoding = face_recognition.face_encodings(image)[0]
            familiar_faces.append(face_encoding)
            familiar_names.append(loaded.name)
        except FileNotFoundError:
            print('file not found')
    
    while True:
            ret, frame = capture.read()
            rgb_frame = frame[:, :, ::-1]
            face_locations = face_recognition.face_locations(rgb_frame)
            face_encodings = face_recognition.face_encodings(rgb_frame, face_locations)
            for face_encoding in face_encodings:
                match = face_recognition.compare_faces(familiar_faces, face_encoding, tolerance=0.80)
                name = None
            
                for pos in range(len(familiar_names)) :
                    if match[pos]:
                        name = familiar_names[pos]
                face_names.append(name)
           
                if(name != None) :
                    found_id = find_id(students, name)
                    if found_id != None :
                        if(find_in_list(present_students, found_id) == False) :
                            present_students.append(found_id)
                        

                for (top, right, bottom, left), name in zip(face_locations, face_names):
                    cv.rectangle(frame, (left, top), (right, bottom), (0, 0, 255), 2)
                    cv.rectangle(frame, (left, bottom - 25), (right, bottom), (0, 0, 255), cv.FILLED)
                    font = cv.FONT_HERSHEY_DUPLEX
                    cv.putText(frame, name, (left + 6, bottom - 6), font, 0.5, (255, 255, 255), 1)
                
            cv.imshow("capture", frame)
            if cv.waitKey(10) != -1 :
                break
        
    capture.release()
    cv.destroyAllWindows()
    #print(present_students)
    result_attendance = []
    for student in students:
        if(find_in_list(present_students, student.id) == True) :
            att = Attendance(table_id, student.id, True)
            result_attendance.append(json.dumps(att.__dict__, ensure_ascii=False))
        else :
            att = Attendance(table_id, student.id, False)
            result_attendance.append(json.dumps(att.__dict__, ensure_ascii=False))
    
    students_info = json.dumps(result_attendance, ensure_ascii=False).replace('"{', '{').replace('}"', '}').replace('\\', '')
    print(students_info)
    #requests.post()
    
    
if __name__ == '__main__':
    main()