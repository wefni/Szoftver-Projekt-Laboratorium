import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Map implements Serializable{
    private String logFile; // log file neve
    private int round;
    private int endRound;
    private int sabWater;
    private int mechWater;
    private Source source;
    private ArrayList<Cistern> cisterns;
    private ArrayList<Component> components;
    private ArrayList<Player> players;

    public Map(String file)
    {
        logFile = file;
        round = 0;
        mechWater = 0;
        sabWater = 0;
        endRound = 200;
        cisterns=new ArrayList<>();
        components=new ArrayList<>();
        players=new ArrayList<>();
    }
    public void Game()
    {
        while (round < endRound)
        {
            System.out.println("Round: "+round);
            round++;
            for (Player p : players)
            {
                p.YourTurn();
                source.FlowOut();
                for(Component c : components) { c.Tick(); }
                SetTeamStats();
            }
        }
    }

    public void GenerateMap()
    {

        //Ebbe a txt-be írjuk a teszt logokat, ami alapján eldöntjük majd, hogy a teszt sikeres volt-e
        File logF  = new File(logFile);
        //ki kene írni hogy létrejött miből mennyi

        //Initializing------------------------------------------
        //Source
        Source source0 = new Source("source - 0", logFile);
        components.add(source0);
        source = source0;

        //Cisterns
        for(int i = 1; i<4; i++){
            Cistern cistern = new Cistern("cistern - "+(i), logFile);
            components.add(cistern);
            cisterns.add(cistern);
        }

        //Pipes
        for(int i= 4; i < 30; i++){
            Pipe pipe = new Pipe("pipe - "+(i), logFile);
            components.add(pipe);
        }

        //Pumps
        for(int i = 30; i < 44; i++){
            Pump pump = new Pump("pump - "+(i), logFile);
            components.add(pump);
        }

        //Connections------------------------------------------
        //Source
        components.get(0).AddNeighbours(components.get(22));
        components.get(0).AddNeighbours(components.get(28));
        components.get(0).AddNeighbours(components.get(29));

        //Cisterns
        components.get(1).AddNeighbours(components.get(7));
        components.get(1).AddNeighbours(components.get(8));

        components.get(2).AddNeighbours(components.get(6));
        components.get(2).AddNeighbours(components.get(5));

        components.get(3).AddNeighbours(components.get(4));

        //Pipes
        components.get(4).AddNeighbours(components.get(3));
        components.get(4).AddNeighbours(components.get(37));

        components.get(5).AddNeighbours(components.get(2));
        components.get(5).AddNeighbours(components.get(37));

        components.get(6).AddNeighbours(components.get(2));
        components.get(6).AddNeighbours(components.get(38));

        components.get(7).AddNeighbours(components.get(1));
        components.get(7).AddNeighbours(components.get(39));

        components.get(8).AddNeighbours(components.get(1));
        components.get(8).AddNeighbours(components.get(40));

        components.get(9).AddNeighbours(components.get(40));
        components.get(9).AddNeighbours(components.get(41));

        components.get(10).AddNeighbours(components.get(41));
        components.get(10).AddNeighbours(components.get(39));

        components.get(11).AddNeighbours(components.get(39));
        components.get(11).AddNeighbours(components.get(43));

        components.get(12).AddNeighbours(components.get(43));
        components.get(12).AddNeighbours(components.get(38));

        components.get(13).AddNeighbours(components.get(38));
        components.get(13).AddNeighbours(components.get(35));

        components.get(14).AddNeighbours(components.get(38));
        components.get(14).AddNeighbours(components.get(31));

        components.get(15).AddNeighbours(components.get(37));
        components.get(15).AddNeighbours(components.get(35));

        components.get(16).AddNeighbours(components.get(37));
        components.get(16).AddNeighbours(components.get(36));

        components.get(17).AddNeighbours(components.get(36));
        components.get(17).AddNeighbours(components.get(33));

        components.get(18).AddNeighbours(components.get(36));
        components.get(18).AddNeighbours(components.get(34));

        components.get(19).AddNeighbours(components.get(34));
        components.get(19).AddNeighbours(components.get(33));

        components.get(20).AddNeighbours(components.get(34));
        components.get(20).AddNeighbours(components.get(35));

        components.get(21).AddNeighbours(components.get(33));
        components.get(21).AddNeighbours(components.get(31));

        components.get(22).AddNeighbours(components.get(0));
        components.get(22).AddNeighbours(components.get(33));

        components.get(23).AddNeighbours(components.get(31));
        components.get(23).AddNeighbours(components.get(32));

        components.get(24).AddNeighbours(components.get(32));
        components.get(24).AddNeighbours(components.get(43));

        components.get(25).AddNeighbours(components.get(32));
        components.get(25).AddNeighbours(components.get(42));

        components.get(26).AddNeighbours(components.get(42));
        components.get(26).AddNeighbours(components.get(43));

        components.get(27).AddNeighbours(components.get(42));
        components.get(27).AddNeighbours(components.get(30));

        components.get(28).AddNeighbours(components.get(0));
        components.get(28).AddNeighbours(components.get(32));

        components.get(29).AddNeighbours(components.get(0));
        components.get(29).AddNeighbours(components.get(30));

        //Pumps
        components.get(30).AddNeighbours(components.get(29));
        components.get(30).AddNeighbours(components.get(27));

        components.get(31).AddNeighbours(components.get(14));
        components.get(31).AddNeighbours(components.get(21));
        components.get(31).AddNeighbours(components.get(23));

        components.get(32).AddNeighbours(components.get(23));
        components.get(32).AddNeighbours(components.get(24));
        components.get(32).AddNeighbours(components.get(25));
        components.get(32).AddNeighbours(components.get(28));

        components.get(33).AddNeighbours(components.get(17));
        components.get(33).AddNeighbours(components.get(19));
        components.get(33).AddNeighbours(components.get(21));
        components.get(33).AddNeighbours(components.get(22));

        components.get(34).AddNeighbours(components.get(18));
        components.get(34).AddNeighbours(components.get(19));
        components.get(34).AddNeighbours(components.get(20));

        components.get(35).AddNeighbours(components.get(13));
        components.get(35).AddNeighbours(components.get(15));
        components.get(35).AddNeighbours(components.get(20));

        components.get(36).AddNeighbours(components.get(16));
        components.get(36).AddNeighbours(components.get(17));
        components.get(36).AddNeighbours(components.get(18));

        components.get(37).AddNeighbours(components.get(4));
        components.get(37).AddNeighbours(components.get(5));
        components.get(37).AddNeighbours(components.get(15));
        components.get(37).AddNeighbours(components.get(16));

        components.get(38).AddNeighbours(components.get(6));
        components.get(38).AddNeighbours(components.get(12));
        components.get(38).AddNeighbours(components.get(13));
        components.get(38).AddNeighbours(components.get(14));

        components.get(39).AddNeighbours(components.get(7));
        components.get(39).AddNeighbours(components.get(10));
        components.get(39).AddNeighbours(components.get(11));

        components.get(40).AddNeighbours(components.get(8));
        components.get(40).AddNeighbours(components.get(9));

        components.get(41).AddNeighbours(components.get(9));
        components.get(41).AddNeighbours(components.get(10));

        components.get(42).AddNeighbours(components.get(25));
        components.get(42).AddNeighbours(components.get(26));
        components.get(42).AddNeighbours(components.get(27));

        components.get(43).AddNeighbours(components.get(11));
        components.get(43).AddNeighbours(components.get(12));
        components.get(43).AddNeighbours(components.get(24));
        components.get(43).AddNeighbours(components.get(26));
    }
    public void AddPlayers(int numOfMechs, int numOfSaboteurs)
    {
        for(int i=0; i < numOfMechs; i++){
            Mechanic m = new Mechanic("Mechanic - " + i, logFile);
            m.ChangeWhere(components.get(2));
            players.add(m);
            components.get(2).AddPlayer(m);
        }

        for(int i=0; i < numOfSaboteurs; i++){
            Saboteur s = new Saboteur("Saboteur - " + i, logFile);
            s.ChangeWhere(components.get(0));
            players.add(s);
            components.get(0).AddPlayer(s);
        }
    }
    public void AddMechToComponent(int componentNumber)
    {
        if(componentNumber < 0 || componentNumber >= components.size())
            throw new IllegalArgumentException("Component number must be between 0 and components.size()");

        Mechanic m = new Mechanic("Mechanic - " + players.size(), logFile);
        m.ChangeWhere(components.get(componentNumber));
        players.add(m);
        components.get(componentNumber).AddPlayer(m);
    }

    public void AddSabToComponent(int componentNumber)
    {
        if(componentNumber < 0 || componentNumber >= components.size())
            throw new IllegalArgumentException("Component number must be between 0 and components.size()");

        Saboteur s = new Saboteur("Saboteur - " + players.size(), logFile);
        s.ChangeWhere(components.get(componentNumber));
        players.add(s);
        components.get(componentNumber).AddPlayer(s);
    }
    public void SetTeamStats()
    {
        mechWater =0;
        for(Cistern c : cisterns) { mechWater += c.GetWater();}

        sabWater = 0;
        int sumWaterInComponents = 0;
        for(Component c : components) { sumWaterInComponents += c.GetWater();}
        sabWater = 2 * source.GetWater() - sumWaterInComponents;
    }
    public void Serialize(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + fileName);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public static Map Deserialize(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Map map = (Map) in.readObject();
            in.close();
            fileIn.close();
            return map;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Map class not found");
            c.printStackTrace();
            return null;
        }
    }
}
