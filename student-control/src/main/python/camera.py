# -*- coding: utf-8 -*-
"""
Created on Tue Nov 12 13:33:09 2019

@author: User
"""

import cv2 as cv
import face_recognition
import requests
import json
import datetime
import time

class Student(object):
    def __init__(self, j):
        self.__dict__ = json.loads(j)
        
    ''''
    def __init__(self, id, name, group):
        self.id = id
        self.name = name
        self.group = group
    '''
    
class Attendance:
    def __init__(self, studid, lessonid, status, datetime):
        self.studid = studid
        self.lessonid = lessonid
        self.status = status
        self.datetime = datetime

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
    table_id = 918
   
    #table_id = sys.argv[1]
    resp = requests.get('http://localhost:8080/api/student/activity/' + str(table_id));
    if resp.status_code != 200 : 
        return
    list_s = json.loads(resp.content)
    students = []
    for string_st in list_s :
        students.append(Student(json.dumps(string_st)))
    
    capture = cv.VideoCapture(0)
    cv.namedWindow("capture")
    face_locations = []
    face_encodings = []
    face_names = []

    familiar_faces = []
    familiar_names = []
    
    
    for student in students :
        loaded = student
        print(loaded.name)
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
    print(present_students)
    
    result_attendance = []
    for student in students:
        if(find_in_list(present_students, student.id) == True) :
            result_attendance.append({'studId' : student.id, 'lessonId' : table_id, 'status' : 1, 'datetime' : str(round(time.time() * 1000))})
        else :
            result_attendance.append({'studId' : student.id, 'lessonId' : table_id, 'status' : 0, 'datetime' : str(round(time.time() * 1000))})
         
    print('------')
    print(result_attendance)
    
    r = requests.session()
    r = requests.post('http://localhost:8080/api/attendance/all', json=result_attendance)
    print(r.text)
    
if __name__ == '__main__':
    main()