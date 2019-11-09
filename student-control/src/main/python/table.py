# -*- coding: utf-8 -*-
"""
Created on Tue Nov  5 13:49:12 2019

@author: User
"""


import json
import requests
import re
from bs4 import BeautifulSoup 

def get_html(url):
    s = requests.get(url)
    return s.text

def pars(text):
    soup = BeautifulSoup(text, 'html.parser')
    table = soup.find('table', class_='time-table')
    lines = table.find_all('tr')
    
    for line in lines[1:] :        
        subjects = line.find_all('td') 
        time = subjects[0].text.strip()
        for part_time in subjects[1:] : 
            cell = part_time.find_all('div', class_='cell')
            for one_pair in cell:
                subject = one_pair.find('div', class_='subject')
                room = one_pair.find('div', class_='room')
                teacher = one_pair.find('a', class_='tutor')
                week = one_pair.find('div', class_='week')
                
                print(subject.text)
                print(room.text)
                if teacher != None :
                    print(teacher.text)
                if week != None :
                    print(week.text)
                print("*********")
        print('-----------')        
        
def main():
    #change
    str_url = "https://table.nsu.ru/group/17206"
    text = get_html(str_url)
    pars(text)
    
    
if __name__ == '__main__':
    main()