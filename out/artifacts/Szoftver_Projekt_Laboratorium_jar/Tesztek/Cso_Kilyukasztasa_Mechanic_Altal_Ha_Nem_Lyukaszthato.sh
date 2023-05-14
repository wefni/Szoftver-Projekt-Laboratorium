#!/bin/bash

# Replace "path/to/file" with the path to the file you want to search
FILE="log.log"

cat Input/Cso_Kilyukasztasa_Mechanic_Altal_Ha_Nem_Lyukaszthato.txt | java -jar *.jar > /dev/null 2>&1 & #txt végére mindig kell egy exit
sleep 0.4

#Jelenlegi teszt
echo "Teszt - Cso_Kilyukasztasa_Mechanic_Altal_Ha_Nem_Lyukaszthato"

URES=$(grep -c "pipe-22 nem lehetett eltörni | broken: false" "$FILE")
if [ $URES -eq 1 ]
then
	echo -e "\033[32mOK\033[0m" #green
else
	echo -e "\033[31mFAILED\033[0m" #red
fi