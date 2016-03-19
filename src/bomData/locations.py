#!/usr/bin/python

import urllib2
from bs4 import BeautifulSoup

file = open ('locations', 'w')

def getLocations(state):	
    url = 'http://www.bom.gov.au/' + state + '/observations/' + state + 'all.shtml'
    page = urllib2.urlopen(url)

    soup = BeautifulSoup(page)

    for tag in soup.find_all('a'):

        path = tag.get('href')
        if path.find('products') > 0:
            path = "http://www.bom.gov.au" + path.replace("shtml", "json").replace("products", "fwo")
            location = tag.contents[0]
            print location + ': ' + path
            file.write(path + '\n')


states = ['vic', 'nsw', 'tas', 'wa', 'sa', 'nt', 'qld', 'ant']

for state in states:
    getLocations(state)
