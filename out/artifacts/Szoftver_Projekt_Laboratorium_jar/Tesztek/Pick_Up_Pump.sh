#!/bin/bash

# Replace "path/to/file" with the path to the file you want to search
FILE="log.log"

cat Input/Pick_Up_Pump.txt | java -jar *.jar > /dev/null 2>&1 & #txt végére mindig kell egy exit
sleep 0.4
#Jelenlegi teszt
echo "Teszt - Pick_Up_Pump"

URES=$(grep -ci "Mechanic felvett egy pumpát | havepump:  true" "$FILE")
if [ $URES -eq 1 ]
then
	echo -e "\033[32mOK\033[0m" #green
else
	echo -e "\033[31mFAILED\033[0m" #red
fi