#!/bin/bash

# Replace "path/to/file" with the path to the file you want to search
FILE="log.log"

cat Input/Pump_in_out.txt | java -jar *.jar > /dev/null 2>&1 & #txt végére mindig kell egy exit
sleep 0.5

#Jelenlegi teszt
echo "Teszt - Pump_in_out"

ATRAKVA=$(grep -c "|pump-38 bemenete beállítva: pipe-6-ra/re" "$FILE")
LESZEDVE=$(grep -c "|pump-38 kimenete beállítva: pipe-12-ra/re" "$FILE")
if [ $ATRAKVA -eq 1 ]
then
	echo -e "\033[32mOK\033[0m" #green
else
	echo -e "\033[31mFAILED\033[0m" #red
fi

if [ $LESZEDVE -eq 1 ]
then
	echo -e "\033[32mOK\033[0m" #green
else
	echo -e "\033[31mFAILED\033[0m" #red
fi

