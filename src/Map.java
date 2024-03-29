import org.apache.log4j.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Map implements Serializable{

    /**
     * Logoláshoz használt objektum.
     */
    private static  final Logger logger = Logger.getLogger(Map.class);

    /**
     * A körök száma.
     */
    private int round;

    /**
     * A játék vége.
     */
    private int endRound;

    /**
     * A saboteur vízének mennyisége.
     */
    private int sabWater;

    /**
     * A mechanic vízének mennyisége.
     */
    private int mechWater;

    /**
     * A forrás.
     */
    private Source source;

    /**
     * A cisternák.
     */
    private ArrayList<Cistern> cisterns;

    /**
     * A pályán lévő komponensek.
     */
    private ArrayList<Component> components;

    /**
     * A játékosok.
     */
    private ArrayList<Player> players;

    /**
     * A pálya.
     */
    protected static Map map;

    /**
     * A pályán lévő komponensek listájának iterátora.
     */
    ListIterator<Component> componentIterator;

    /**
     * A játék indításához szükséges.
     */
    private boolean indul = false;

    /**
     * A játék grafikus felületének főablaka.
     */
    ViewFrame viewFrame;

    public Map(String file)
    {
        logger.info("Map@Map | Map létrejött \n");
        map = this;
        round = 0;
        mechWater = 0;
        sabWater = 0;
        endRound = 25;
        cisterns=new ArrayList<>();
        components=new ArrayList<>();
        players=new ArrayList<>();
    }

    /**
     * Az indítást figyelő függvény.
     * @return boolean indel-e a játék
     */
    public boolean GetIndul()
    {
        return indul;
    }

    /**
     * A játék indítását figyelő függvény.
     * @param _indul boolean indel-e a játék
     */
    public void SetIndul(boolean _indul)
    {
        indul = _indul;
    }

    public ArrayList<Component> getComponents()
    {
        return components;
    }

    /**
     * A játék köreit kezelő függvény.
     */
    public void Game()
    {
        logger.info("Map @Game | Játék elindítva\n");
        while (round < endRound)
        {
            System.out.println("Round: " + round);
            logger.info("Map @Game | Aktuális kör:"+round+"\n");
            round++;

            for (Player p : players)
            {
                viewFrame.UpdateUpPanel(mechWater,sabWater,p);
                System.out.println(p.name);
                System.out.println("Helye: " + p.where.id);
                p.YourTurn();
                source.FlowOut(null);

                componentIterator = components.listIterator();
                while(componentIterator.hasNext()) {
                    componentIterator.next().Tick();
                }
                SetTeamStats();

            }
        }

        viewFrame.setSize(350,200);
        if (sabWater < mechWater)
        {
            viewFrame.CreatWinnerCard("Szabotőrök nyertek");
        }
        else if(sabWater > mechWater)
        {
            viewFrame.CreatWinnerCard("Szerelők nyertek");
        }
        else
        {
            viewFrame.CreatWinnerCard("Döntetlen");
        }
    }

    public int GetRound()
    {
        return round;
    }

    /**
     * A játék térképének generálását végző függvény.
     */
    public void GenerateMap()
    {
        logger.info("Map @GenerateMap | Pálya generálása \n");
        //ki kene írni hogy létrejött miből mennyi

        //Initializing------------------------------------------
        //Source
        Source source0 = new Source("source-0");
        components.add(source0);
        source = source0;

        //Cisterns
        for(int i = 1; i<4; i++){
            Cistern cistern = new Cistern("cistern-"+(i));
            components.add(cistern);
            cisterns.add(cistern);
        }

        //Pipes
        for(int i= 4; i < 30; i++){
            Pipe pipe = new Pipe("pipe-"+(i));
            components.add(pipe);
        }

        //Pumps
        for(int i = 30; i < 44; i++){
            Pump pump = new Pump("pump-"+(i));
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

        //Pump in and out
        components.get(30).ConfigurePumpWithParameters(components.get(29), components.get(27));
        components.get(42).ConfigurePumpWithParameters(components.get(27), components.get(26));
        components.get(32).ConfigurePumpWithParameters(components.get(28), components.get(24));
        components.get(31).ConfigurePumpWithParameters(components.get(21), components.get(14));
        components.get(33).ConfigurePumpWithParameters(components.get(22), components.get(19));
        components.get(34).ConfigurePumpWithParameters(components.get(19), components.get(18));
        components.get(35).ConfigurePumpWithParameters(components.get(20), components.get(13));
        components.get(36).ConfigurePumpWithParameters(components.get(18), components.get(16));
        components.get(43).ConfigurePumpWithParameters(components.get(24), components.get(11));
        components.get(38).ConfigurePumpWithParameters(components.get(14), components.get(6));
        components.get(37).ConfigurePumpWithParameters(components.get(16), components.get(4));
        components.get(39).ConfigurePumpWithParameters(components.get(11), components.get(10));
        components.get(41).ConfigurePumpWithParameters(components.get(10), components.get(9));
        components.get(40).ConfigurePumpWithParameters(components.get(9), components.get(8));

        viewFrame = new ViewFrame(components);
    }

    /**
     * A mechanic karakterek hozzáadása a pályához.
     */
    public void Karakter_mech_hozzadasa()
    {
        String[] mech_nevek = viewFrame.Get_Mech_Names_From_Menu();
        for (int i = 0; i < mech_nevek.length; i++)
        {
            if(mech_nevek[i] != null)
            {
                map.AddMechToComponent(i % 3 + 1, mech_nevek[i]);
            }
        }
    }

    /**
     * A saboteur karakterek hozzáadása a pályához.
     */
    public void Karakter_sab_hozzadasa()
    {
        String[] sab_nevek = viewFrame.Get_Sab_Names_From_Menu();
        for (int i = 0; i < sab_nevek.length; i++)
        {
            if(sab_nevek[i] != null)
            {
                map.AddSabToComponent(0, sab_nevek[i]);
            }
        }
        indul = true;
    }

    /**
     * A játékosok hozzáadása egy componenthez.
     * @param componentNumber komponens száma
     * @param nev játékos neve
     */
    public void AddMechToComponent(int componentNumber, String nev)
    {
        if(componentNumber < 0 || componentNumber >= components.size())
            throw new IllegalArgumentException("Component number must be between 0 and components.size()");

        Mechanic m = new Mechanic(nev + ": Mechanic");
        m.ChangeWhere(components.get(componentNumber));
        players.add(m);
        components.get(componentNumber).AddPlayer(m);
        logger.info("Map @AddMechToComponent | mechanic elhelyezve a következő pályarészre: "+componentNumber+" | components.get(componentNumber).onComponent.contains(m): "+components.get(componentNumber).onComponent.contains(m)+"\n");
    }

    /**
     * A játékosok hozzáadása egy componenthez.
     * @param componentNumber komponens száma
     * @param nev játékos neve
     */
    public void AddSabToComponent(int componentNumber, String nev)
    {
        if(componentNumber < 0 || componentNumber >= components.size())
            throw new IllegalArgumentException("Component number must be between 0 and components.size()");

        Saboteur s = new Saboteur(nev + ": Saboteur");
        s.ChangeWhere(components.get(componentNumber));
        players.add(s);
        components.get(componentNumber).AddPlayer(s);
        logger.info("Map @AddSabToComponent | saboteur elhelyezve a következő pályarészre: "+componentNumber+" | components.get(componentNumber).onComponent.contains(s): "+components.get(componentNumber).onComponent.contains(s)+"\n");

    }
    public void SetTeamStats()
    {
        mechWater =0;
        for(Cistern c : cisterns) { mechWater += c.GetWater();}
        sabWater = 0;
        int sumWaterInComponents = 0;
        for(Component c : components) { sumWaterInComponents += c.GetWater();}
        sabWater = 2 * source.GetWater() - sumWaterInComponents;
        logger.info("Map @SetTeamStats | mechanic pontszáma: "+mechWater+"\n");
        logger.info("Map @SetTeamStats | saboteur pontszáma: "+sabWater+"\n");
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

    /**
     * Pipe lerakása komponensek közé.
     * @param c1 komponens 1
     * @param c2 komponens 2
     */
    public void SpawnPipeBetweenComponents(Component c1, Component c2)
    {
        Pipe pipe = new Pipe("pipe-" + components.size());

        if(c1 != null){
            c1.AddNeighbours(pipe);
            pipe.AddNeighbours(c1);
        }
        if(c2 != null){
            c2.AddNeighbours(pipe);
            pipe.AddNeighbours(c2);
        }
        if(componentIterator != null){
            componentIterator.add(pipe);
        }
    //logger.info("Map @SpawnPipeBetWeenComponents | "+pipe.id+" létrehozva "+c1.id+" és "+c2.id+" között\n");
        if(c1 != null && c2 != null)
        {
            logger.info("Map @SpawnPipeBetWeenComponents | "+pipe.id+" létrehozva "+c1.id+" és "+c2.id+" között\n");
        }

        viewFrame.PipeSpawned(pipe);
    }

    /**
     * A játékosok hozzáadása a pályához.
     */
    public void AllPlayersAdded()
    {
        do
        {
            System.out.print("");
        }
        while (!map.GetIndul());
        viewFrame.setSize(1900, 1000 );
        //viewField.AddPlayers(players);
        map.Karakter_mech_hozzadasa();
        map.Karakter_sab_hozzadasa();
        viewFrame.AddPlayers(players);
    }

    /**
     * Pumpa lerakása a csőre.
     * @param pipe1 a pumpa
     */
    public void PlacePumpOnPipe(Component pipe1) {
        Pipe pipe2 = new Pipe("pipe-" + components.size());
        Pump pump = new Pump("pump-" + components.size());

        pipe2.AddNeighbours(pump);
        if(pipe1.neighbours.size()>1)
            pipe2.AddNeighbours(pipe1.neighbours.get(1));

        pump.AddNeighbours(pipe2);
        pump.AddNeighbours(pipe1);
        pipe1.neighbours.get(1).AddNeighbours(pipe2);
        pipe1.neighbours.get(1).RemoveNeighbours(pipe1);
        pipe1.RemoveNeighbours(pipe1.neighbours.get(1));
        pipe1.AddNeighbours(pump);
        pump.ConfigurePumpWithParameters(pipe1, pipe2);

        components.add(pipe2);
        components.add(pump);
        viewFrame.Pumpeadded(pipe1);

        logger.info(pipe1.id+"@PlacePumpOnPipe | pumpa lehelyezve a "+pipe1.id+"-ra/re | új pumpa ID: "+pump.id+"\n");
        logger.info(pipe1.id+"@PlacePumpOnPipe |"+pump+" szomszédai: "+pump.neighbours.get(0).id+", "+pump.neighbours.get(1).id+"\n");
    }

    public void PipeSpawned(Component p)
    {

    }
}
