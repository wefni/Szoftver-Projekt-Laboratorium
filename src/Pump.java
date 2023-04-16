import java.util.Scanner;

/**
 * A Pump osztaly valositja meg a pumpat.
 * A RandomBreakable-ből örököl, mivel random idöközönként el tud törni.
 */
public class Pump extends RandomBreakable
{
    /**
     * A pumpa kimnetet es bemenetet allitani kepes fuggveny.
     */
    public void ConfigurePump()
    {
        System.out.print("$ Pump.ConfigurePump()\n");

        System.out.println("Milyen karakterrel szeretne a pumpat allitani?(saboteur / mechanic)");
        Scanner be = new Scanner(System.in);
        String valasztas=be.nextLine();
        Mechanic mechanic = new Mechanic();
        Saboteur saboteur = new Saboteur();
        if("mechanic".equals(valasztas))
        {
            System.out.print("\t\tmechanic ");
            mechanic.YourTurn();
        }
        else
        {
            System.out.print("\t\tsaboteur ");
            saboteur.YourTurn();
        }

        Pipe pipe2 = new Pipe();

        Cistern cistern = new Cistern();

        System.out.print("\t\t\tcistern ");
        cistern.Act();

        System.out.print("\n\t\t\t\tcistern ");
        cistern.Step();

        System.out.println("\nRalephet a jatekos?(igen / nem)");
        valasztas=be.nextLine();
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

        System.out.print("\n\t\t\tpipe ");
        pipe2.Step();

        Pump pump = new Pump();

        System.out.println("\nRalephet a jatekos?(igen / nem)");
        valasztas=be.nextLine();
        if ("igen".equals(valasztas))
        {
            System.out.print("\t\t\t\tpump ");
            pipe2.Accept(); //true

            System.out.print("\n\t\t\t\tpump ");
            pipe2.AddPlayer();

            System.out.print("\n\t\t\t\tpipe ");
            cistern.RemoveNeighbours();

            System.out.print("\n\t\t\tmechanic ");
            mechanic.ChangeWhere();

            System.out.print("\n\t\t\tpump ");
            pump.Act();
        }
        else
        {
            System.out.print("\t\t\tpump ");
            pipe2.Accept(); //false
        }
        System.out.print("\n");
    }

    /**
     * A víz folyását biztosító függvény.
     * @return int típusú, amely a tovabbfolyas sikeresseget jelzi
     */
    public int FlowOut()
    {
        System.out.println("$ Pump.FlowOut()");
        Scanner be=new Scanner(System.in);
        System.out.println("El van törve a következő cső?");
        String valasztas=be.nextLine();
        if("nem".equals(valasztas))
        {
            Pipe pipe2=new Pipe();
            System.out.print("\t\t\t\t\t\t\t\t pipe2 ");
            pipe2.FlowOut();
        }
        return 0;
    }

    public boolean Accept(){
        System.out.println("$ Pump.Accept()");
        return true;
    }

    public void AddPlayer(){
        System.out.println("$ Pump.AddPlayer()");
        System.out.println("->Player");
    }
}
