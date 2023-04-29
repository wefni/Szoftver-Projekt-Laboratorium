import java.util.Objects;
import java.util.Scanner;

public class Pipe extends Breakable{
private double capacity;
private int sticky;
private int unBreakable;
private int sloppy;
private int hasWater;
private boolean hasWaterPartOne;
private boolean hasWaterPartTwo;

public boolean Accept()
{
    return onComponent.isEmpty();
}

    public void ChangePipe()
    {
        System.out.print("$ Pipe.ChangePipe()\n");

        Mechanic mechanic = new Mechanic();
        Pipe pipe2 = new Pipe();
        Pipe pipe1 = new Pipe();
        Source source = new Source();
        Pump pump = new Pump();
        System.out.print("\t\tmechanic ");
        mechanic.YourTurn();

        Cistern cistern = new Cistern();
        Scanner be = new Scanner(System.in);

        System.out.print("\t\t\tcistern ");
        cistern.Act();

        System.out.print("\n\t\t\t\tcistern ");
        cistern.Step();

        System.out.println("\nRalephet a jatekos?(igen / nem)");
        String valasztas=be.nextLine();
        if ("igen".equals(valasztas))
        {
            System.out.print("\t\t\t\tpipe ");
            pipe2.Accept(); //true

            System.out.print("\n\t\t\t\tpipe ");
            pipe2.AddPlayer();

            System.out.print("\n\t\t\t\tcistern ");
            cistern.RemovePlayer();

            System.out.print("\n\t\t\tmechanic ");
            mechanic.ChangeWhere();
        }
        else
        {
            System.out.print("\t\t\tpipe ");
            pipe2.Accept(); //false
        }

        System.out.print("\n\t\t\tpipe ");
        pipe2.Act();

        System.out.print("\n\t\t\t\tpipe ");
        pipe1.ShowNeighbours();

        System.out.print("\n\t\t\t\tsource ");
        source.AddNeighbours();

        System.out.print("\n\t\t\t\tpipe ");
        pipe2.AddNeighbours();

        System.out.print("\n\t\t\t\tpump ");
        pump.RemoveNeighbours();

        System.out.print("\n\t\t\t\tpipe ");
        pipe2.RemoveNeighbours();
        System.out.print("\n");

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

    public void Act(){
        this.Step();

    }

    public void AddNeighbours(){
        System.out.println("$ Pipe.AddNeighbours()");
        System.out.println("->cistern");
        Cistern c = new Cistern();
        c.AddNeighbours();
    }

    public void Step(){
        Pump p = new Pump();
        Mechanic m = new Mechanic();
        p.Accept();
        p.AddPlayer();
        this.RemovePlayer();
        m.ChangeWhere();
    }

    public void RemovePlayer(){
        System.out.println("$ Pipe.RemovePlayer()");
    }
    public Component RandomEnd(){return null;}
    public void MakeSloppy(){}
    public void MakeSticky(){}
    public void Tick(){}
    public boolean IsSloppy()
    {
        return sloppy>0;
    }
    public int GetWater()
    {
        if(hasWaterPartOne && hasWaterPartTwo)
            return 2;
        if(hasWaterPartOne || hasWaterPartTwo)
            return 1;

        return 0;
    }

}
