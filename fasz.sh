#!/bin/bash

# Replace "path/to/file" with the path to the file you want to search
FILE="log.log"

cat input.txt | java -jar *.jar  #txt végére mindig kell egy exit


# Count the number of occurrences of the word in the file and save the result to a variable


# Check if the count is equal to 45
echo "Lefutott tesztek: 1/1"
echo "Pálya létrehozása:"


COUNT=$(grep -c "létrejött" "$FILE")
if [ $COUNT -eq 46 ]
then
  echo -e "\033[32mOK\033[0m" #green
else
  echo -e "\033[31mFAILED\033[0m" #red
fi


cat input2.txt | java -jar *.jar  #txt végére mindig kell egy exit
# Count the number of occurrences of the word in the file and save the result to a variable


# Check if the count is equal to 45
echo "Lefutott tesztek: 2/2"
echo "exit teszt:"

ASD=$(grep -c "exit" "$FILE")

echo $ASD

if [ $ASD -eq 1 ]
then
  echo -e "\033[32mOK\033[0m" #green
else
  echo -e "\033[31mFAILED\033[0m" #red
fi
