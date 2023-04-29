import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Map {
    private int round;
    private int SabWater;
    private int MechWater;
    private Source source;
    private ArrayList<Cistern> cisterns=new ArrayList<>();
    private ArrayList<Component> components=new ArrayList<>();
    private ArrayList<Player> players=new ArrayList<>();


    public void Game(){
        System.out.println("$ Map.Game()");
        Scanner be=new Scanner(System.in);
        System.out.println("Melyik karakterrel szeretnél lenni?(Mechanic,Saboteur,RandomBreak,Water,spawnPipe)");
        String valasztas=be.nextLine().toLowerCase();
        if(Objects.equals(valasztas, "mechanic"))
        {
            Mechanic mechanic=new Mechanic();
            System.out.print("\t\tmechanic ");
            mechanic.YourTurn();
        }
        if(Objects.equals(valasztas, "randombreak"))
        {
            Pump pump=new Pump();
            System.out.print("\t\t pump ");
            pump.Break();

        }
        if(Objects.equals(valasztas, "water"))
        {
            Source source=new Source();
            System.out.print("\t\t source ");
            source.StartFlow();
        }
        if(Objects.equals(valasztas, "saboteur"))
        {
            Saboteur saboteur=new Saboteur();
            System.out.print("\t\tsaboteur ");
            saboteur.YourTurn();
        }
        if(Objects.equals(valasztas, "pipe")){
            Cistern c = new Cistern();
            c.SpawnPipe();
        }


    }
    public void StartGame(){}
    public void SetTeamStarts(){}

}
