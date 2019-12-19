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

class Student:
    def __init__(self, id, name, group):
        self.name = name
        self.group = group
        self.id = id

def find_in_list(lst, find_him) :
    for el in lst :
        stud = Student(**el)
        if stud.name == find_him :
            return stud
    return None

def main():
    present_students = []
    table_id = sys.argv[1]
    resp = requests.get('http://localhost:8080/api/student/activity/' + table_id);
    if resp.status_code != 200 : 
        return
    students = json.loads(resp.content)
    print('stud to find')
    print(students)
    
    capture = cv.VideoCapture(0)
    cv.namedWindow("capture")
    face_locations = []
    face_encodings = []
    face_names = []
    number = 0

    familiar_faces = []
    familiar_names = []
    
    for student in students :
     #   loaded = Student(**student)
     #   print(loaded.name)
    #files_list = os.listdir("examples/")
    #for file in files_list:
        loaded = Student(**student) 
        try :
            image = face_recognition.load_image_file("examples/" + loaded.name)
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
           #face_names.append(name)
           
           if(name != None) :
               ret = find_in_list(present_students, name)
               if(ret != None) :
                   present_students.append(Student(**ret))
           
           for (top, right, bottom, left), name in zip(face_locations, face_names):
                #if not name:
                #    continue
                cv.rectangle(frame, (left, top), (right, bottom), (0, 0, 255), 2)
                cv.rectangle(frame, (left, bottom - 25), (right, bottom), (0, 0, 255), cv.FILLED)
                font = cv.FONT_HERSHEY_DUPLEX
                cv.putText(frame, name, (left + 6, bottom - 6), font, 0.5, (255, 255, 255), 1)
                
           cv.imshow("capture", frame)
           cv.waitKey(10)
          
    capture.release()
    cv.destroyAllWindows()
    print(present_students)
    
    
if __name__ == '__main__':
    main()