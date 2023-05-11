import org.apache.log4j.MDC;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        /* TESZTEKHEZ
        Igy deSerializálunk egy pályát a map.txt fájlból és rakunk ra karaktereket

         //Map map = Map.Deserialize("map.txt");
         map.AddSabToComponent(12);
         map.AddSabToComponent(13);
        */

        /* UJ PÁLYA KIMENTES
        Igy lehet kimenteni egy később serialiálható pályát. Ezt csak akkor kell hasznánod, ha új pályát raktál össze és ki akarod menteni, de amúgy soha!*/


/*


         Map map = new Map(fileName);
         map.GenerateMap();
         map.Serialize("map2.txt");



*/
        String fileName = "teszt";
        Map map = new Map(fileName);
        map.GenerateMap();
        map.AddSabToComponent(33);
        //map.AddPlayers(0, 1);
        map.Game();



    }
}