import java.util.Scanner;

public class Pipe extends Breakable{

    public void ChangePipe()
    {
        System.out.print("$ Pipe.ChangePipe()\n");

        //egy variacio

        Pump pump1 = new Pump();
        Pump pump2 = new Pump();
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Cistern cistern = new Cistern();

        System.out.print("\t\tmechanic ");
        pipe1.Act();
        System.out.print("->pipe1\n");

        System.out.print("\t\t\tpipe ");
        pipe2.ShowNeighbours();
        System.out.print("->pipe2\n");

        System.out.print("\t\t\tpump ");
        pump1.ShowNeighbours();
        System.out.print("->pump1\n");

        System.out.print("\t\t\tpump ");
        pump2.AddNeighbours();
        System.out.print("->pump2\n");

        System.out.print("\t\t\tpipe ");
        pipe1.AddNeighbours();
        System.out.print("->pipe1\n");

        System.out.print("\t\t\tpipe ");
        pipe1.RemoveNeighbours();
        System.out.print("->pipe1\n");

        System.out.print("\t\t\tcistern ");
        cistern.RemoveNeighbours();
        System.out.print("->cistern\n");

    }
    public void PlacePump(){
        System.out.print("$ Pipe.PlacePump()");
    }
    public int FlowOut(){
        System.out.println("$ Pipe.FlowOut()");
        Scanner be=new Scanner(System.in);
        System.out.println("El van törve a következő elem és az ciszterna?(nem-nem,igen-nem,...)");
        String valasztas=be.nextLine();
        if("nem-nem".equals(valasztas))
        {
            Pump pump=new Pump();
            System.out.print("\t\t\t\t\t\t pump ");
            pump.FlowOut();
        }
        if("nem-igen".equals(valasztas))
        {
            Cistern cistern=new Cistern();
            System.out.print("\t\t\t\t\t\t\t\t\t\t cistern ");
            cistern.FlowOut();

        }

        return 0;
    }
}
