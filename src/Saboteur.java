import java.util.Objects;
import java.util.Scanner;

public class Saboteur extends Player{

    public void YourTurn() {
        System.out.print("$ Saboteur.YourTurn()");

        Scanner be=new Scanner(System.in);
        System.out.println("Mit szeretnél csinálni?(csövet eltörni,...)");
        String valasztas=be.nextLine().toLowerCase();

        if(Objects.equals(valasztas, "csövet eltörni")) {
            Pipe pipe = new Pipe();
            System.out.print("\t\t\t\tpipe ");
            pipe.Act();
            System.out.print("\t\t\t\t\tpipe ");
            pipe.Break();
        }
    }
}
