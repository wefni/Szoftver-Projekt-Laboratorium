#!/bin/bash

# Replace "path/to/file" with the path to the file you want to search
FILE="log.log"

cat ../Input/Pump_nem_torott_csore.txt | java -jar ../*.jar > /dev/null 2>&1 & #txt végére mindig kell egy exit
sleep 0.5

#Jelenlegi teszt
echo "Teszt - Pump_nem_torott_csore"

ATRAKVA=$(grep -c "| pipe-44 létrejött" "$FILE")
ATRAKVA2=$(grep -c " | pump-44 létrejött" "$FILE")
LESZEDVE=$(grep -c "| pump-44 hozzáadva pipe-44 szomszédjához | neighbours.contains(pump-44): true" "$FILE")
LESZEDVE2=$(grep -c "| pump-38 hozzáadva pipe-44 szomszédjához | neighbours.contains(pump-38): true" "$FILE")
ATRAKVA3=$(grep -c "| pipe-44 hozzáadva pump-44 szomszédjához | neighbours.contains(pipe-44): true" "$FILE")
ATRAKVA4=$(grep -c " | pipe-6 hozzáadva pump-44 szomszédjához | neighbours.contains(pipe-6): true" "$FILE")
LESZEDVE3=$(grep -c "| pipe-44 hozzáadva pump-38 szomszédjához | neighbours.contains(pipe-44): true" "$FILE")
LESZEDVE4=$(grep -c "| pipe-6 eltávolítva a pump-38 szomszédai közül | neighbours.contains(pipe-6): false" "$FILE")

if [ $ATRAKVA -eq 1 ] 
then
	echo -e "\033[32mOK\033[0m" #green
else
	echo -e "\033[31mFAILED\033[0m" #red
fi
if [ $ATRAKVA2 -eq 1 ] 
then
	echo -e "\033[32mOK\033[0m" #green
else
	echo -e "\033[31mFAILED\033[0m" #red
fi
if [ $ATRAKVA3 -eq 1 ] 
then
	echo -e "\033[32mOK\033[0m" #green
else
	echo -e "\033[31mFAILED\033[0m" #red
fi
if [ $ATRAKVA4 -eq 1 ] 
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
if [ $LESZEDVE3 -eq 1 ] 
then
	echo -e "\033[32mOK\033[0m" #green
else
	echo -e "\033[31mFAILED\033[0m" #red
fi
if [ $LESZEDVE4 -eq 1 ] 
then
	echo -e "\033[32mOK\033[0m" #green
else
	echo -e "\033[31mFAILED\033[0m" #red
fi