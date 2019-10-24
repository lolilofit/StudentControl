# -*- coding: utf-8 -*-
"""
Created on Tue Oct 22 14:14:07 2019

@author: User
"""
class StudentData:
    def __init__(self, name, group, student_id):
        self.name = name
        self.group = group
        self.student_id = student_id
    

import requests
import re
from bs4 import BeautifulSoup 

def get_html(url):
    s = requests.get(url)
    return s.text

def pars(text):
    _list = []
    count = 0
    group_number = 0
    
    soup = BeautifulSoup(text, 'lxml')
    item = soup.find('div', class_="item-page")
    
    all_groups = item.find_all('ol')
    all_groups_names = item.find_all('strong')
    for group in all_groups:
        group_name = re.sub(r"[^0-9]+", "", all_groups_names[group_number].text.strip())
        members = group.find_all('li')
        for student in members:
            st = StudentData(student.text.strip(), group_name, count)
            _list.append(st)
            count = count + 1
        group_number = group_number + 1
    
    for e in _list:
        print(e.name, e.group, e.student_id)    
     

def main():
    #change
    str_url = "http://fit.nsu.ru/uch/22-uch/1501-spiski-grupp-3-kurs"
    text = get_html(str_url)
    pars(text)
  #  print(text)
    
    
if __name__ == '__main__':
    main()