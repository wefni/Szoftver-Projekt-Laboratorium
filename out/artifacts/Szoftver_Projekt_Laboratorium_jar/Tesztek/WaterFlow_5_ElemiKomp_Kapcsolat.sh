#!/bin/bash

# Replace "path/to/file" with the path to the file you want to search
FILE="log.log"

cat ../Input/WaterFlow_5_ElemiKomp_Kapcsolat.txt | java -jar ../*.jar > /dev/null 2>&1 & #txt végére mindig kell egy exit
sleep 1

#Jelenlegi teszt
echo "Teszt - WaterFlow 5 ElemiKomp Kapcsolat"

Ciszterna=$(grep -c "Map @SetTeamStats | mechanic pontszáma: 1" "$FILE")

if [ $Ciszterna -eq 1 ] 
then	
	echo -e "\033[32mOK\033[0m" #green
else
	echo -e "\033[31mFAILED\033[0m" #red
fi