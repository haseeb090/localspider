# localspider
A local java crawler that indexes the directories specified.

Introduction

A spider (also known as crawler) application capable of indexing the local file system was to be created. The spider crawls through the file system, creating an easily searchable index of file paths, file names & contents (of text files only). The user then searches this index against a keyword, getting back all the file paths where the keyword was encountered. 

Approach

The simple file visitor interface was used to create a tree of all directories and go through them recursively. The directories are stored in a Map and so are the files in an individual file. The files are then tokenized and indexed i.e. against every word the file path it was encountered in is written in an index.csv file. The user is then prompted for a search word which is then searched through the index.csv file and wherever the search term matches the first column of the csv its respectable file path is printed.

Usage

The user can specify any directory in the code by inserting the exact location in the String named root. The program will do the rest itself and will prompt the user for a search term and print the appropriate locations for the user to observe.
