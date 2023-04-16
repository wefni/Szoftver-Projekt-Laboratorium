import java.util.Objects;
import java.util.Scanner;

public class Map {

    public void Game(){
        System.out.println("$ Map.Game()");
        Mechanic mechanic=new Mechanic();
        Scanner be=new Scanner(System.in);
        System.out.println("Melyik karakterrel szeretnél lenni?(Mechanic,Saboteur,RandomBreak,Water)");
        String valasztas=be.nextLine();
        if(Objects.equals(valasztas, "Mechanic"))
        {
            System.out.print("\t\tmechanic ");
            mechanic.YourTurn();
        }
        Pump pump=new Pump();
        if(Objects.equals(valasztas, "RandomBreak"))
        {
            System.out.print("\t\t pump ");
            pump.Break();

        }
        if(Objects.equals(valasztas, "Water"))
        {
            Source source=new Source();
            System.out.print("\t\t source ");
            source.StartFlow();
        }



    }
}
