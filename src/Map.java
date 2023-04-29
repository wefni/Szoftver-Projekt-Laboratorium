import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Map {


    private String logFile; // log file neve
    private int round;
    private int SabWater;
    private int MechWater;
    private Source source;
    private ArrayList<Cistern> cisterns=new ArrayList<>();
    private ArrayList<Component> components=new ArrayList<>();
    private ArrayList<Player> players=new ArrayList<>();

    public Map(String file)
    {
        logFile = file;
    }

    public void Game(){

    }
    public void StartGame(){

        //Ebbe a txt-be írjuk a teszt logokat, ami alapján eldöntjük majd, hogy a teszt sikeres volt-e
        File logF  = new File(logFile);
    }
    public void SetTeamStarts(){}

}
