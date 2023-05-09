import org.apache.log4j.MDC;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /* TESZTEKHEZ
        Igy deSerializálunk egy pályát a map.txt fájlból és rakunk ra karaktereket

         //Map map = Map.Deserialize("map.txt");
         map.AddSabToComponent(12);
         map.AddSabToComponent(13);
        */





        /* UJ PÁLYA KIMENTES
        Igy lehet kimenteni egy később serialiálható pályát. Ezt csak akkor kell hasznánod, ha új pályát raktál össze és ki akarod menteni, de amúgy soha!*/


/*

         String fileName = "teszt";
         Map map = new Map(fileName);
         map.GenerateMap();
         map.Serialize("map2.txt");
*/






        Map map = Map.Deserialize("map2.txt");
        map.AddPlayers(1, 0);
        map.Game();


    }
}