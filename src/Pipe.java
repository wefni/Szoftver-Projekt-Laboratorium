import java.util.Scanner;

/**
 * A Pipe osztaly valositja meg a csoveket, amelyekben folyik a viz.
 * A Breakable osztalybol szarmazik, mivel eltorheto palyaelem.
 */
public class Pipe extends Breakable{


    /**
     * A ChangePipe függveny a cső egyik pumparol a masikra valo atköteset vegzi.
     */
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

    /**
     * Az adott csövet "kettevagja" ez a fuggveny es a kozepere tesz egy pumpat.
     */
    public void PlacePump(){
        System.out.print("$ Pipe.PlacePump()");
    }

    /**
     * A víz folyását biztosító függvény.
     * @return int típusú, amely a tovabbfolyas sikeresseget jelzi
     */
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
