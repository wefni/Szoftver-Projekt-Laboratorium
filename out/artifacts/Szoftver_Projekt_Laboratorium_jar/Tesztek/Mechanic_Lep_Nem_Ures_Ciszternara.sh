#!/bin/bash

# Replace "path/to/file" with the path to the file you want to search
FILE="log.log"

cat Input/Mechanic_Lep_Nem_Ures_Ciszternara.txt | java -jar *.jar > /dev/null 2>&1 & #txt végére mindig kell egy exit
sleep 0.4

#Jelenlegi teszt
echo "Teszt - Mechanic_Lep_Nem_Ures_Ciszternara"

URES=$(grep -ci "cistern-2 ra/re ráléphetnek?  | igen" "$FILE")
RALEPETT=$(grep -ci "Mechanic rálépett a cistern-2-ra/re | onComponent.contains(teszt0Mech: Mechanic): true" "$FILE")
if [ $URES -eq 1 ]
then
	if [ $RALEPETT -eq 1 ]
	then
		echo -e "\033[32mOK\033[0m" #green
	else
		echo -e "\033[31mFAILED\033[0m" #red
	fi
else
	echo -e "\033[31mFAILED\033[0m" #red
fi