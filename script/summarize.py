#!/usr/bin/python
# -*- coding: utf-8 -*-

from textteaser import TextTeaser

path_read="/home/depiano/Scrivania/store_messages/output/formatted.dat"
path_output="/home/depiano/Scrivania/store_messages/output/summarize.dat"
result=""
title="Mailing Lists Apache Foundation"
documento= open(path_read,'r')
text=str(documento.readlines())

tt = TextTeaser()

sentences = tt.summarize(title, text)

for sentence in sentences:
	result=result+str(sentence)

documento = open(path_output, "w")
documento.write(result)
documento.close()
print('Title: '+title)
print('Text: '+result)
