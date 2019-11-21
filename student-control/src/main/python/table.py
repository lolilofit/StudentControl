# -*- coding: utf-8 -*-
"""
Created on Tue Nov  5 13:49:12 2019

@author: User
"""

import json
import requests
import re
from bs4 import BeautifulSoup 
from enum import Enum

class DayOfWeek(Enum):
    Понедельник = 1,
    Вторник = 2,
    Среда = 3, 
    Четверг = 4,
    Пятница = 5,
    Суббота = 6


class Pair():
    def __init__(self, subject, room, teacher):
        self.subject = subject
        self.room = room
        self.teacher = teacher

facultet_timetable = {}

def get_html(url):
    s = requests.get(url)
    return s.text

def pars_group(group_name, text):
    '''
    group_timetable = {
            DayOfWeek.Понедельник : dict(),
            DayOfWeek.Вторник : dict(),
            DayOfWeek.Среда : dict(), 
            DayOfWeek.Четверг : dict(),
            DayOfWeek.Пятница : dict(),
            DayOfWeek.Суббота : dict()
    }
    '''
    
    group_timetable = {
            1 : dict(),
            2 : dict(),
            3 : dict(), 
            4 : dict(),
            5 : dict(),
            6 : dict()
    }
    
    soup = BeautifulSoup(text, 'html.parser')
    table = soup.find('table', class_='time-table')
    lines = table.find_all('tr')
    current_week = soup.find('div', class_='container-header').find('div', class_='parity').text
    
    pair_num = 1;
    for line in lines[1:] :        
        subjects = line.find_all('td') 
        time = subjects[0].text.strip()
        
        week_day = 1
        for part_time in subjects[1:] : 
            cell = part_time.find_all('div', class_='cell')
            for one_pair in cell:
                subject = one_pair.find('div', class_='subject')
                room = one_pair.find('div', class_='room')
                teacher = one_pair.find('a', class_='tutor')
                week = one_pair.find('div', class_='week')
                
                #put depending on week
                if teacher == None :
                    teacher = "НЕТ"
                else :
                    teacher = teacher.text.strip()
                if week == None :
                    week = ""
                else : 
                    week = week.text
                pair = Pair(subject.text.strip(), room.text.strip(), teacher)
                #print(subject.text.strip())
                if week == '':
                    (group_timetable[week_day])[pair_num] = json.dumps(pair.__dict__, ensure_ascii=False)
                else :
                    if('Четная неделя' in current_week) :
                        if 'Нечетная' in week :
                            continue
                        if 'Четная' in week :
                            (group_timetable[week_day])[pair_num] = json.dumps(pair.__dict__, ensure_ascii=False)
                    else :
                        if 'Нечетная' in week:
                            (group_timetable[week_day])[pair_num] = json.dumps(pair.__dict__, ensure_ascii=False)
            week_day = week_day + 1        
                
        pair_num = pair_num + 1;        
    '''
    for i in range(1, 7):
        for el in group_timetable[i] :
            print(((group_timetable[i])[el]).subject)
        print("------new day----------")
    '''
    facultet_timetable[group_name] =  group_timetable
    

def get_group_urls_pars(base, facultet, text):
    soup = BeautifulSoup(text, 'html.parser')
    groups = soup.find('table', class_='degree_groups').find_all('tr')
   
    for line in groups:
        l = line.find_all('td')
        for group in l[1:]:
            link = group.find('a', class_='group')
            url = base + link.get('href')
            name = link.text
            pars_group(name, get_html(url))
    ser_list = json.dumps(facultet_timetable, ensure_ascii=False).replace('"{', '{').replace('}"', '}').replace('\\', '')
    print(ser_list)
            
        

def get_facultets_urls(base, text):
    soup = BeautifulSoup(text, 'html.parser')
    facultet_container = soup.find_all('div', class_='item-faculty')
    for item in facultet_container:
        ref_container = item.find('a', class_='faculty')
        name = ref_container.text
        url = base + ref_container.get('href')
        get_group_urls_pars(base, name, get_html(url))
 
def main():
   # str_url = "https://table.nsu.ru/faculties"
    get_group_urls_pars("https://table.nsu.ru", "fit", get_html("https://table.nsu.ru/faculty/fit"))
    
    
if __name__ == '__main__':
    main()