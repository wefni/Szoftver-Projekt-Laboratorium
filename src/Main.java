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
        String fileName = "teszt";
        Map map = new Map(fileName);
        map.GenerateMap();


        while (true)
        {
            System.out.print("");
            if(map.GetIndul())
            {
                break;
            }
        }
        map.AllPlayersAdded();
        map.Game();


    }
}
