#!/bin/bash

# Replace "path/to/file" with the path to the file you want to search
FILE="log.log"

cat Input/WaterFlow_Elemi_Mashogy_Konf_Pumpa.txt | java -jar *.jar > /dev/null 2>&1 & #txt végére mindig kell egy exit
sleep 1

#Jelenlegi teszt
echo "Teszt - WaterFlow - Elemi_Mashogy_Konf_Pumpa.txt"

Cso=$(grep -c "pipe-26@GetWater | A pipe-26-ben ennyi víz van: 2" "$FILE")
Cso2=$(grep -c "pipe-11@GetWater | A pipe-11-ben ennyi víz van: 2" "$FILE")
Cso3=$(grep -c "pipe-24@GetWater | A pipe-24-ben ennyi víz van: 2" "$FILE")

if [ $Cso -eq 1 ] &&  [ $Cso2 -eq 1 ] &&  [ $Cso3 -eq 3 ]
then	
	echo -e "\033[32mOK\033[0m" #green
else
	echo -e "\033[31mFAILED\033[0m" #red
fi