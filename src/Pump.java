import org.apache.log4j.Logger;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * A Pump osztaly valositja meg a pumpat.
 * A RandomBreakable-ből örököl, mivel random idöközönként el tud törni.
 */
public class Pump extends RandomBreakable
{
    private static final Logger logger = Logger.getLogger(Pump.class);
    private Pipe in;
    private Pipe out;
    private int tank;
    private int maxTank;
    private int randomBreakCounter;
    private boolean didWaterFlow;

    public Pump(String ID)
    {
        super(ID);
        logger.info(this.id + "@Pump | "+this.id+" létrejött \n");
    }

    /**
     * A pumpa kimnetet es bemenetet allitani kepes fuggveny.
     */

    public void ConfigurePump()
    {
        System.out.println("Kimenetet, bemenetet vagy mindkettőt szeretnéd állítani? (be/ki/mindeketto)");
        Scanner be = new Scanner(System.in);
        String valasz = be.nextLine();
        switch (valasz)
        {
            case "be" ->
            {
                //kiir aktualisat
                System.out.println("Aktuális bemenet:");
                System.out.println(this.in.id + "\n");

                //kiir lehetosegeket
                System.out.println("Lehetséges bemenetek:");
                for (Component i : neighbours)
                {
                    System.out.print(i.id + ", ");
                }
                System.out.println("Kérlek add meg a bemenetet!");
                String bemenet = be.nextLine();
                for (Component i : neighbours)
                {
                    if (Objects.equals(i.id, bemenet)) //megfelelot beallit
                    {
                        this.in = (Pipe) i;
                    }
                }
            }
            case "ki" ->
            {
                //kiir aktualisat
                System.out.println("Aktuális kimenet:");
                System.out.println(this.out.id + "\n");

                //kiir lehetosegeket
                System.out.println("Lehetséges kimenetek:");
                for (Component i : neighbours)
                {
                    System.out.print(i.id + ", ");
                }
                System.out.println("Kérlek add meg a kimenetet!");
                String kimenet = be.nextLine();
                for (Component i : neighbours)
                {
                    if (Objects.equals(i.id, kimenet)) //megfelelot beallit
                    {
                        this.out = (Pipe) i;
                    }
                }
            }
            case "mindketto" ->
            {
                //kiir aktualisat
                System.out.println("Aktuális be- és kimenet:");
                System.out.println(this.in.id + ", " + this.out.id + "\n");

                //kiir lehetosegeket
                System.out.println("Lehetséges be- és kimenetek:");
                for (Component i : neighbours)
                {
                    System.out.print(i.id + ", ");
                }
                System.out.println("Kérlek add meg a be- és kimenetet szóközzel elválasztva!");
                String mindketto = be.nextLine();
                String[] uj_bemenet = mindketto.split(" ", 2);
                for (Component i : neighbours)
                {
                    if (Objects.equals(i.id, uj_bemenet[0])) //megfelelot beallit
                    {
                        this.in = (Pipe) i;
                    }
                    if (Objects.equals(i.id, uj_bemenet[1])) //megfelelot beallit
                    {
                        this.out = (Pipe) i;
                    }
                }
            }
        }
    }

    public void Act(Player me, int type)
    {
        Step(me);
    }

    public void Step(Player me)
    {
        System.out.println("Melyik elemre szeretnél lépni?");
        for (Component i : this.neighbours)
        {
            System.out.println(i.id);
        }

        Scanner be = new Scanner(System.in);
        String bemenet = be.nextLine();
        for (Component j : this.neighbours)
        {
            if (Objects.equals(j.id, bemenet))
            {
                if (j.Accept())
                {
                    j.AddPlayer(me);
                    this.RemovePlayer(me);
                    me.ChangeWhere(j);

                    //logolás
                    logger.info(this.id + "@Step | "+me+"  játékos "+ j.id +"-re szeretne lépni | rá tudott lépni \n");
                }
                else
                {
                    System.out.println("Nem lehet rálépni");

                    //logolás
                    logger.info(this.id + "@Step | "+me+"  játékos "+ j.id +"-re szeretne lépni | nem tudott rálépni \n");
                }
            }
        }
    }

    /**
     * A víz folyását biztosító függvény.
     * @return int típusú, amely a tovabbfolyas sikeresseget jelzi
     */
    public int FlowOut(Component sender)
    {
       if(sender != in)
       {
           return 0;
       }
       else
       {
           if(broken)
           {
                return 0;
           }
           else
           {
                if(maxTank == tank)
                {
                    return 0;
                }
                else if(tank < maxTank)
                {
                    tank++;
                    if(didWaterFlow)
                    {
                        return 1;
                    }
                    else
                    {
                        didWaterFlow = true;
                    }

                    if(out == null)
                    {
                        return 1;
                    }
                    else
                    {
                        out.FlowOut();
                        return 1;
                    }
                }
           }
       }
       return 0; //semmikeppen sem fog teljesulni, de kell a hibajelzes miatt
    }

    public void Tick()
    {
        randomBreakCounter--;
        if(randomBreakCounter == 0)
        {
            Break();
        }

        if(didWaterFlow)
        {
            didWaterFlow = false;
        }
        else
        {
            if(tank == 0)
            {
                didWaterFlow = false;
            }
            else if(tank > 0)
            {
                if(out.FlowOut(this) == 0)
                {
                    didWaterFlow = false;
                }
                else if(out.FlowOut(this) == 1)
                {
                    tank--;
                    didWaterFlow = false;
                }
            }
        }
    }

    public void Repair()
    {
        broken = false;
        Random rand = new Random();
        randomBreakCounter = rand.nextInt(20, 40);
    }

    public boolean Accept()
    {
        return true;
    }

    public int GetWater()
    {
        return tank;
    }
}
