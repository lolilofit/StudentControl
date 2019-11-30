# -*- coding: utf-8 -*-
"""
Created on Tue Oct 22 14:14:07 2019

@author: User
"""
class StudentData:
    def __init__(self, id, name, group):
        self.name = name
        self.group = group
        self.id = id
    
import json
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
    
    #soup = BeautifulSoup(text, 'lxml')
    soup = BeautifulSoup(text, 'html.parser')
    item = soup.find('div', class_="item-page")
    
    all_groups = item.find_all('ol')
    all_groups_names = item.find_all('strong')
    found = []
    count = 0
    
    for el in all_groups_names:
        if(el.text.strip() == ''):
            found.append(count)
        count = count + 1
    if(found.count != 0):
        found.reverse();
        for ind in found:
            all_groups_names.pop(ind)
        
    for group in all_groups:
        group_name = re.sub(r"[^0-9]+", "", all_groups_names[group_number].text.strip())
        #if(group_name != ''):   
        group_num = int(group_name)
        members = group.find_all('li')
        for student in members:
            st = StudentData(count, student.text.strip(), group_num)
            #print(st.group, st.name)     
            _list.append(json.dumps(st.__dict__, ensure_ascii=False))
            count = count + 1
        group_number = group_number + 1
     
    ser_list = json.dumps(_list, ensure_ascii=False).replace('"{', '{').replace('}"', '}').replace('\\', '')
    #decoded = ser_list.decode() 
    r = requests.post('http://localhost:8080/api/data/students', data = ser_list)
    #print(ser_list)

def main():
    #change
    str_url = "http://fit.nsu.ru/uch/22-uch/1501-spiski-grupp-3-kurs"
    text = get_html(str_url)
    pars(text)
    
    
if __name__ == '__main__':
    main()