import org.apache.log4j.MDC;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException
    {

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

        System.out.println("1. Játék kezdése\n2. Tesztek futtatása");
        Scanner be = new Scanner(System.in);
        String valasz;
        boolean jo = false;
        while (!jo)
        {
            valasz = be.nextLine(); //bekér
            valasz = valasz.toLowerCase(); //kisbetűsít
            switch (valasz)
            {
                case "1" -> //Jatek
                {
                    jo = true;
                    System.out.println("Random legyen ki- vagy bekapcsolva? (ki/be)");
                    boolean jol_valaszoltak = false;
                    while(!jol_valaszoltak)
                    {
                        valasz = be.nextLine(); //bekér
                        switch (valasz)
                        {
                            case "be" -> //Random bekapcsolás
                            {
                                jol_valaszoltak = true;

                            }
                            case "ki" -> //Random bekapcsolás
                            {
                                jol_valaszoltak = true;

                            }
                            default ->
                            {
                                System.out.println("Érvénytelen bemenet. Add meg újra: ");
                            }
                        }
                    }


                    //Jatekosok bekerese
                    boolean felvette_a_jatekosokat = false;
                    String[] karakterek = new String[2];
                    karakterek[0] = "Saboteur";
                    karakterek[1] = "Mechanic";
                    ArrayList<String> karakter_nevek = new ArrayList<String>();
                    int[] karakterek_szama = new int[2];
                    int i = 1;
                    while (!felvette_a_jatekosokat)
                    {
                        System.out.print(i + ". játékos: " + karakterek[i % 2] + "\nNév: ");
                        valasz = be.nextLine(); //bekér
                        karakter_nevek.add(valasz);
                        if(i > 3)
                        {
                            boolean jol_valaszolt = false;
                            while(!jol_valaszolt)
                            {
                                System.out.println("Szeretnél még játékost felvenni? (igen/nem)");
                                valasz = be.nextLine(); //bekér
                                valasz = valasz.toLowerCase(); //kisbetűsít
                                if(valasz.equals("nem"))
                                {
                                    felvette_a_jatekosokat = true;
                                    jol_valaszolt = true;
                                }
                                else if(valasz.equals("igen"))
                                {
                                    jol_valaszolt = true;
                                }
                                else
                                {
                                    System.out.println("Érvénytelen bemenet. Add meg újra: ");
                                }
                            }
                        }
                        i++;
                    }

                    String fileName = "teszt";
                    Map map = new Map(fileName);
                    map.GenerateMap();

                    //Jatekosok map-ra lerakása
                    for (int k = 0; k < i - 1; k++)
                    {
                        if(k % 2 == 0)
                        {
                            map.AddMechToComponent(2, karakter_nevek.get(k));
                        }
                        else
                        {
                            map.AddSabToComponent(0, karakter_nevek.get(k));
                        }
                    }

                    //map.AddPlayers(0, 1);
                    map.Game();
                }
                case "2" -> //Tesztek
                {
                    jo = true;

                }
                default ->
                {
                    System.out.println("Érvénytelen bemenet. Add meg újra: ");
                }
            }
        }





    }
}