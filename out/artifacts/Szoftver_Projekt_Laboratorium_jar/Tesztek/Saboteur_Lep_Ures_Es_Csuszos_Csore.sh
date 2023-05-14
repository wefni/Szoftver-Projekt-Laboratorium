#!/bin/bash

# Replace "path/to/file" with the path to the file you want to search
FILE="log.log"

cat Input/Saboteur_Lep_Ures_Es_Csuszos_Csore.txt | java -jar *.jar > /dev/null 2>&1 & #txt végére mindig kell egy exit
sleep 0.4

#Jelenlegi teszt
echo "Teszt - Saboteur_Lep_Ures_Es_Csuszos_Csore"

URES=$(grep -c "pipe-22 ra/re ráléphetnek?  | onComponent.isEmpty(): true" "$FILE")
RALEPETT=$(grep -c "csúszósság lekérdezése | true" "$FILE")
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