#!/bin/bash

# Replace "path/to/file" with the path to the file you want to search
FILE="log.log"

cat Input/Pipe_csatlakozik_egyik_felen.txt | java -jar *.jar > /dev/null 2>&1 & #txt végére mindig kell egy exit
sleep 0.5

#Jelenlegi teszt
echo "Teszt - Pipe_csatlakozik_egyik_felen"

ATRAKVA=$(grep -c "pipe-6 hozzáadva pump-37 szomszédjához | neighbours.contains(pipe-6): true" "$FILE")
LESZEDVE=$(grep -c "cistern-2 eltávolítva a pipe-6 szomszédai közül | neighbours.contains(cistern-2): false" "$FILE")
LESZEDVE2=$(grep -c "pipe-6 eltávolítva a pump-38 szomszédai közül | neighbours.contains(pipe-6): false" "$FILE")
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
