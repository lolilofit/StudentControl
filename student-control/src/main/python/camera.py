# -*- coding: utf-8 -*-
"""
Created on Tue Nov 12 13:33:09 2019

@author: User
"""

import cv2 as cv
import face_recognition

def main():
    capture = cv.VideoCapture(0)
    cv.namedWindow("capture")
    face_locations = []
    face_encodings = []
    face_names = []
    number = 0

    image = face_recognition.load_image_file("examples/Усова_Дарья_Сергеевна.jpg")
    face_encoding = face_recognition.face_encodings(image)[0]
    
    familiar_faces = [
            face_encoding,
            ]
    familiar_names = [
            "Darya"
            ]
    i = 0
    while True:
        ret, frame = capture.read()
        rgb_frame = frame[:, :, ::-1]
        face_locations = face_recognition.face_locations(rgb_frame)
        face_encodings = face_recognition.face_encodings(rgb_frame, face_locations)
        for face_encoding in face_encodings:
            match = face_recognition.compare_faces(familiar_faces, face_encoding, tolerance=0.70)
            name = None
            
            for pos in range(len(familiar_names)) :
                if match[pos]:
                    name = familiar_names[pos]
            face_names.append(name)
        
            print(name)
            for (top, right, bottom, left), name in zip(face_locations, face_names):
                #if not name:
                #    continue
                cv.rectangle(frame, (left, top), (right, bottom), (0, 0, 255), 2)
                cv.rectangle(frame, (left, bottom - 25), (right, bottom), (0, 0, 255), cv.FILLED)
                font = cv.FONT_HERSHEY_DUPLEX
                cv.putText(frame, name, (left + 6, bottom - 6), font, 0.5, (255, 255, 255), 1)
            
        cv.imshow("capture", frame)
        cv.waitKey(10)
        #path = "dataset/capture%.4d.jpg" % i # Уникальное имя для каждого кадра
        #cv.imwrite(path, frame)
        
        
        i += 1
    capture.release()
    cv.destroyAllWindows()
    
if __name__ == '__main__':
    main()