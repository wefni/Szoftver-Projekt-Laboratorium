#!/bin/bash

# Replace "path/to/file" with the path to the file you want to search
FILE="log.log"

cat Input/WaterFlow_Elemi_Hibas_Pumpa.txt | java -jar *.jar > /dev/null 2>&1 & #txt végére mindig kell egy exit
sleep 1

#Jelenlegi teszt
echo "Teszt - WaterFlow Elemi_Hibas_Pumpa"

Pump=$(grep -c "pump-32@FlowOut | pump-32-ba/be nem tud víz folyni mert rossz" "$FILE")

if [ $Pump -gt 1 ] 
then	
	echo -e "\033[32mOK\033[0m" #green
else
	echo -e "\033[31mFAILED\033[0m" #red
fi