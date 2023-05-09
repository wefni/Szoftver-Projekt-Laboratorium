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
        System.out.println("Kimenetet, bemenetet vagy mindkettőt szeretnéd állítani? (be/ki/mindketto)");
        Scanner be = new Scanner(System.in);
        String valasz;
        boolean jo = false;
        while (!jo)
        {
            valasz = be.nextLine(); //bekér
            valasz = valasz.toLowerCase(); //kisbetűsít
            switch (valasz)
            {
                case "be" ->
                {
                    jo = true;
                    //kiir aktualisat
                    System.out.println("Aktuális bemenet:");
                    System.out.println(this.in.id + "\n");

                    //kiir lehetosegeket
                    System.out.println("Lehetséges bemenetek:");
                    for (Component i : neighbours)
                    {
                        if(!Objects.equals(this.in.id, i.id)) //aktuálisat nem ír ki
                        {
                            System.out.print(i.id + ", ");
                        }
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
                    jo = true;
                    //kiir aktualisat
                    System.out.println("Aktuális kimenet:");
                    System.out.println(this.out.id + "\n");

                    //kiir lehetosegeket
                    System.out.println("Lehetséges kimenetek:");
                    for (Component i : neighbours)
                    {
                        if(!Objects.equals(this.out.id, i.id)) //aktuálisat nem ír ki
                        {
                            System.out.print(i.id + ", ");
                        }
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
                    jo = true;
                    //kiir aktualisat
                    System.out.println("Aktuális be- és kimenet:");
                    System.out.println(this.in.id + ", " + this.out.id + "\n");

                    //kiir lehetosegeket
                    System.out.println("Lehetséges be- és kimenetek:");
                    for (Component i : neighbours)
                    {
                            System.out.print(i.id + ", "); //mindegyiket kiir, mivel akar meg is cserelheti a kettot
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
    }

    /**
     * Ez egy publikus metódus. Azért felel, hogy elindítsa és kezelje egy játékos cselekvését
     * az adott pumpán. Külön kezeli a mechanic-ot, illetve a saboteurt.
     * @param me: a rálépő karakter
     * @param type: a rálépő karakter tulajdonsága
     */
    public void Act(Player me, int type)
    {
        System.out.println("Mit szeretnél cselekedni?");
        if(broken && type == 0)
        {
            System.out.println("RepairPump");
        }
        System.out.println("ConfigurePump");
        System.out.println("Step");

        Scanner be = new Scanner(System.in);
        String valasz;
        boolean jo = false;
        while (!jo)
        {
            valasz = be.nextLine(); //bekér a választ
            valasz = valasz.toLowerCase(); //kisbetűsít
            switch (valasz)
            {
                case "step" ->
                {
                    jo = true;
                    Step(me);
                }
                case "repairpump" ->
                {
                    jo = true;
                    Repair();
                }
                case "changepump" ->
                {
                    jo = true;
                    ConfigurePump();
                }
                default -> System.out.println("Nem jó bemenet. Add meg újra:");
            }
        }
    }

    /**
     * Ez a függvény vezényli a játékos lépését, kezeli.
     * @param me: a rálépő karakter
     */
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
     * @param sender: a függvényt meghívó komponens
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

    /**
     * Csökkenti a randomBreakCounter értékét 1-el és, ha az eléri a nullát, akkor meghívja
     * a Break() metódust magán.
     * Illetve, ha a didWaterFlow változó false és van víz a tartályában, akkor meghívja
     * az out csövén a WaterFlow() függvényt. Ezzel egy vízfolyást indítva. Ha sikerrel tér
     * vissza a (1) a WaterFlow, akkor a tartályból ki kell vonjon egy egység vizet.
     * Végül hamisra állítja a didWaterFlow értékét a következő körre előkészülve.
     */
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

    /**
     * Abban az esetben ha el volt törve a pumpa a broken változó értékét false-ra állítja és
     * a randomBreakCounter értékét egy 20 és 40 közötti int-re állítja.
     */
    public void Repair()
    {
        broken = false;
        Random rand = new Random();
        randomBreakCounter = rand.nextInt(20, 40);
    }

    /**
     * Visszaadja, hogy ráléphet-e a játékos.
     * @return mindig ráléphet
     */
    public boolean Accept()
    {
        return true;
    }

    /**
     * Visszaadja, hogy mennyi víz van a tartályban.
     * @return Víz mennyisége a tartályban.
     */
    public int GetWater()
    {
        return tank;
    }
}
