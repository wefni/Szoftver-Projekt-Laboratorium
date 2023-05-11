#!/bin/bash

# Replace "path/to/file" with the path to the file you want to search
FILE="E:/BME/Projlab/log.log"

javac -cp log4j-1.2.17.jar: src/*.java

java -cp log4j-1.2.17.jar:src Main


# Count the number of occurrences of the word in the file and save the result to a variable
COUNT=$(grep -c "$létrejött" "$FILE")

# Check if the count is equal to 45
echo "Lefutott tesztek: 1/1"
echo "Pálya létrehozása:"
if [ $COUNT -eq 45 ]
then
  echo -e "\033[32mOK\033[0m" #green
else
  echo -e "\033[31mFAILED\033[0m" #red
fi
