import java.util.Objects;
import java.util.Scanner;

public class Mechanic extends Player{

    public void YourTurn(){
        System.out.println("$ Mechanic.YourTurn()");

        Scanner be=new Scanner(System.in);
        System.out.println("Mit szeretnél csinálni?(pumpát felvenni,...)");
        String valasztas=be.nextLine();
        if(Objects.equals(valasztas, "pumpát felvenni"))
        {
            Cistern cistern=new Cistern();
            System.out.print("\t\t\t\tcistern ");
            cistern.PickUpPump();
        }
    }
}
