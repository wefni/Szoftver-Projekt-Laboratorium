#!/bin/bash

# Replace "path/to/file" with the path to the file you want to search
FILE="log.log"

cat Input/Pumpa_javitasa.txt | java -jar *.jar > /dev/null 2>&1 & #txt végére mindig kell egy exit
sleep 0.5

#Jelenlegi teszt
echo "Teszt - Pumpa_javitasa"

ATRAKVA=$(grep -c " | pump-38megjavítva, randomBreakCounter új értéket kapott | brokem: false, randomBreakCounter: 8" "$FILE")
LESZEDVE=$(grep -c "| teszt0Mech: Mechanic játékos a következő opciót választotta: repairpump" "$FILE")
LESZEDVE2=$(grep -c "| pump-38 véletlenszerűen elromlott | broken= true" "$FILE")
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
