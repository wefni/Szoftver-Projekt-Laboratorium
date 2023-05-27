import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main
{
    private static final Logger logger = Logger.getLogger(Main.class);
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



*/      Scanner DaFuckinScanner = new Scanner(System.in);
        String fileName = "teszt";
        Map map = new Map(fileName, DaFuckinScanner);
        map.GenerateMap();


        /*ViewFrame frame = new ViewFrame();
        frame.setVisible(true);

        for(int i = 0; i< 100; i++){
            frame.WriteQuestion("Helloka " + i);
            String[] options = {"hello" + i , "off" + (100-i)};
            if(i==4){
                options = new String[]{"hello" + i, "off" + (100 - i), "woooow"};
            }
            frame.WriteOptions(options);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        System.out.println("1. Játék kezdése\n2. Tesztek futtatása");
        String valasz;
        boolean jo = false;
        while (!jo)
        {
            valasz = DaFuckinScanner.nextLine(); //bekér
            valasz = valasz.toLowerCase(); //kisbetűsít
            switch (valasz)
            {
                case "1" -> //Jatek
                {
                    logger.info("Main@main | Játék elindítva | valasz: 1 \n");
                    jo = true;
                    //System.out.println("Random legyen ki- vagy bekapcsolva? (ki/be)");
                    boolean jol_valaszoltak = false;
                    while(!jol_valaszoltak)
                    {
                        jol_valaszoltak = true;
                        //valasz = DaFuckinScanner.nextLine(); //bekér
                        switch (valasz)
                        {
                            case "be" -> //Random bekapcsolás
                            {
                                logger.info("Main@main | Random bekapcsolva  | valasz: be\n");
                                jol_valaszoltak = true;
                                System.out.println("Random bekapcsolva.");
                            }
                            case "ki" -> //Random bekapcsolás
                            {
                                jol_valaszoltak = true;
                                logger.info("Main@main | Random kikapcsolva | valasz: ki\n");
                                for (Component i: map.getComponents()) //mindegyiket kikapcsol
                                {
                                    i.SetRandom(false);
                                }

                                System.out.println("Random kikapcsolva.");
                            }
                            default ->
                            {
                                //logger.info("Main@main | Érvénytelen input a játék menüjében | valasz: "+valasz+"\n");
                                //System.out.println("Érvénytelen bemenet. Add meg újra: ");
                            }
                        }
                    }

                    //Jatekosok bekerese
                    boolean felvette_a_jatekosokat = false;
                    String[] karakterek = new String[2];
                    karakterek[0] = "Saboteur";
                    karakterek[1] = "Mechanic";
                    ArrayList<String> karakter_nevek = new ArrayList<String>();
                    int i = 1;
                    while (!felvette_a_jatekosokat)
                    {
                        System.out.print(i + ". játékos: " + karakterek[i % 2] + "\nNév: ");
                         //bekér
                        boolean nem_ures = false;
                        while(!nem_ures)
                        {
                            valasz = DaFuckinScanner.nextLine();
                            if(valasz.equals(""))
                            {
                                System.out.print("A név nem lehet üres! Add meg újra:\nNév: ");
                                logger.info("Main@main | "+i + ". játékos: " + karakterek[i % 2] + "\n üres nevet adott meg ");
                            }
                            else
                            {
                                nem_ures = true;
                                logger.info("Main@main | "+i + ". játékos: " + karakterek[i % 2] + "\n neve beállítva | valasz: "+valasz+"\n");
                            }
                        }
                        karakter_nevek.add(valasz);
                        if(i > 3)
                        {
                            boolean jol_valaszolt = false;
                            while(!jol_valaszolt)
                            {
                                System.out.println("Szeretnél még játékost felvenni? (igen/nem)");
                                valasz = DaFuckinScanner.nextLine(); //bekér
                                valasz = valasz.toLowerCase(); //kisbetűsít
                                if(valasz.equals("nem"))
                                {
                                    felvette_a_jatekosokat = true;
                                    jol_valaszolt = true;
                                    logger.info("Main@main | Nem szeretne több játékost felvenni\n");
                                    logger.info("Main@main | 4 játékossal indul a játék\n");
                                }
                                else if(valasz.equals("igen"))
                                {
                                    logger.info("Main@main | Szeretne még több játékost felvenni\n");
                                    jol_valaszolt = true;
                                }
                                else
                                {
                                    System.out.println("Érvénytelen bemenet. Add meg újra: ");
                                    logger.info("Main@main | Érvénytelen bemenet | valasz: "+valasz+"\n");
                                }
                            }
                        }
                        i++;
                    }
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
                    map.AllPlayersAdded();
                    map.Game();
                }
                case "2" -> //Tesztek
                {
                    logger.info("Main@main | Tesztek elindítva | valasz: 2 \n");
                    jo = true;
                    System.out.println("Hány darab mechanicot és saboteurt szeretnél lerakni? Add meg vesszővel elválasztva: ");
                    valasz = DaFuckinScanner.nextLine();
                    logger.info("Main@main | Mechanic és saboteur darabszáma: | valasz: "+valasz+"\n");
                    String[] karakterek_szama = valasz.split(",", 2);
                    System.out.println("Add meg a mechanic-ok spawnolási helyét: ");
                    for (int i = 0; i < Integer.parseInt(karakterek_szama[0]); i++)
                    {
                        System.out.println(i + ". " + "Mechanic spawn helye: ");
                        valasz = DaFuckinScanner.nextLine();
                        map.AddMechToComponent(Integer.parseInt(valasz), "teszt" + i + "Mech");
                    }
                    for (int i = 0; i < Integer.parseInt(karakterek_szama[1]); i++)
                    {
                        System.out.println(i + ". " + "Saboteur spawn helye: ");
                        valasz = DaFuckinScanner.nextLine();
                        map.AddSabToComponent(Integer.parseInt(valasz), "teszt" + i + "Sab");
                    }
                    map.Game();
                }
                default ->
                {
                    System.out.println("Érvénytelen bemenet. Add meg újra: ");
                    logger.info("Main@main | Érvénytelen input a játék menüjében | valasz: "+valasz+"\n");
                }
            }
        }
        DaFuckinScanner.close();
    }
}