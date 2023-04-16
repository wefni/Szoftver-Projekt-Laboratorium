import java.util.Objects;
import java.util.Scanner;

public class Mechanic extends Player{

    public void YourTurn(){
        System.out.println("$ Mechanic.YourTurn()");

        Scanner be=new Scanner(System.in);
        System.out.println("Mit szeretnél csinálni?(pumpát felvenni, pumpát megjavítani, pumpát lerakni)");
        String valasztas=be.nextLine().toLowerCase();
        if(Objects.equals(valasztas, "pumpát felvenni"))
        {
            Cistern cistern=new Cistern();
            System.out.print("\t\t\t\tcistern ");
            cistern.PickUpPump();
        }
        if(Objects.equals(valasztas, "pumpát megjavítani"))
        {
            Pump pump = new Pump();
            System.out.print("\t\t\t\tpump ");
            pump.Act();
            System.out.print("\n\t\t\t\t\tpump ");
            pump.Repair();
        }
        if(Objects.equals(valasztas, "pumpát lerakni")) {
            Pump newpump = new Pump();
            Pump pump = new Pump();
            Pipe pipe = new Pipe();
            Pipe newpipe = new Pipe();

            System.out.print("\t\t\t\tpipe ");
            pipe.Act();

            System.out.print("\t\t\t\tpipe ");
            pipe.AddNeighbours();
            System.out.println("->newpipe");

            System.out.print("\t\t\t\tnewpipe ");
            newpipe.AddNeighbours();
            System.out.println("->pump");

            System.out.print("\t\t\t\t\tnewpipe ");
            newpipe.AddNeighbours();
            System.out.println("->newpump");

            System.out.print("\t\t\t\tpipe ");
            pipe.AddNeighbours();
            System.out.println("->newpump");

            System.out.print("\t\t\t\t\tnewpipe ");
            newpipe.AddNeighbours();
            System.out.println("->newpump");

            System.out.print("\t\t\t\tpipe ");
            pipe.AddNeighbours();
            System.out.println("->newpipe");

            System.out.print("\t\t\t\tpipe ");
            pipe.RemoveNeighbours();
            System.out.println("->pump");

            System.out.print("\t\t\t\tpump ");
            pipe.RemoveNeighbours();
            System.out.println("->pipe");

        }
    }
}
