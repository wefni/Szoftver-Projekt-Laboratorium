#!/bin/bash

# Replace "path/to/file" with the path to the file you want to search
FILE="log.log"

cat Input/Pump_in_torott.txt | java -jar *.jar > /dev/null 2>&1 & #txt végére mindig kell egy exit
sleep 0.5

#Jelenlegi teszt
echo "Teszt - Pump_in_torott"

ATRAKVA=$(grep -c "| Törött a pipe-17 ezért nem folyik benne tovább a víz" "$FILE")
LESZEDVE=$(grep -c "|pump-33 kimenete beállítva: pipe-17-ra/re" "$FILE")
LESZEDVE2=$(grep -c "|pipe-17 eltörve | broken: true" "$FILE")
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
if [ $LESZEDVE2 -eq 1 ]
then
	echo -e "\033[32mOK\033[0m" #green
else
	echo -e "\033[31mFAILED\033[0m" #red
fi
