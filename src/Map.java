import java.util.Objects;
import java.util.Scanner;

public class Map {

    public void Game(){
        System.out.println("$ Map.Game()");
        Scanner be=new Scanner(System.in);
        System.out.println("Melyik karakterrel szeretnél lenni?(Mechanic,Saboteur,RandomBreak,Water)");
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




    }
}
