import org.apache.log4j.Logger;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * A Pump osztaly valositja meg a pumpat.
 * A RandomBreakable-ből örököl, mivel random idöközönként el tud törni.
 */
public class Pump extends Breakable
{
    private  boolean random=true;
    private Random rand = new Random();
    private Random detrand= new Random(42);
    private static final Logger logger = Logger.getLogger(Pump.class);

    /**
     * Pumpa bemeneti csöve
     */
    private Pipe in;
    
    /**
     * Pumpa kimeneti csöve
     */
    private Pipe out;
    
    /**
     * A pumpa tartálya
     */
    private int tank = 0;
    
    /**
     * A pumpa tartályának maximális mérete
     */
    private int maxTank = 3;
    
    /**
     * A pumpa random elromlását segíti elő
     */
    private int randomBreakCounter;
    
    /**
     * A pumpa tartályának kiürülését teszi lehetővé
     */
    private boolean didWaterFlow;

    public Pump(String ID)
    {
        super(ID);
        if(random)
        randomBreakCounter=rand.nextInt(13,100);

        if(!random)
            randomBreakCounter= detrand.nextInt(11,13);

        logger.info(this.id + "@Pump | "+this.id+" létrejött \n");

    }

    public Pipe getOut() {
        return out;
    }

    public void ConfigurePumpWithParameters(Component _in, Component _out)
    {
        in = (Pipe) _in;
        out = (Pipe) _out;
        logger.info(this.id+"@ConfigurePumpWithParameters |"+this.id+" bemenete beállítva: "+in.id+"-ra/re\n");
        logger.info(this.id+"@ConfigurePumpWithParameters |"+this.id+" kimenete beállítva: "+out.id+"-ra/re\n");
    }

    /**
     * A pumpa kimnetet es bemenetet allitani kepes fuggveny.
     */
    public void ConfigurePump()
    {
        ViewField.instance.WriteQuestion("Mit szeretnél állítani?");
        String options[] = new String[]{"Bemenet", "Kimenet", "Mindkettő"};
        String valasz = ViewField.instance.WriteOptions(options);
        boolean jo = false;
        while (!jo)
        {
            valasz = valasz.toLowerCase(); //kisbetűsít
            switch (valasz)
            {
                case "bemenet" ->
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
                            System.out.println(i.id);
                        }
                    }
                    System.out.println("\nKérlek add meg a bemenetet!");
                    ViewField.instance.WriteQuestion("Kérlek add meg a bemenetet!");
                    String options2[] = new String[neighbours.size()];
                    for(int i=0;i<neighbours.size();i++)
                    {
                        options2[i]=neighbours.get(i).id;
                    }
                    String bemenet = ViewField.instance.WriteOptions(options2);
                    for (Component i : neighbours)
                    {
                        if (Objects.equals(i.id, bemenet)) //megfelelot beallit
                        {
                            this.in = (Pipe) i;
                            logger.info(this.id+"@ConfigurePump |"+this.id+" bemenete beállítva: "+i.id+"-ra/re\n");
                        }
                    }
                }
                case "kimenet" ->
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
                            System.out.println(i.id);

                        }
                    }
                    ViewField.instance.WriteQuestion("Kérlek add meg a kimenetet!");
                    String options3[] = new String[neighbours.size()];
                    for(int i=0;i<neighbours.size();i++)
                    {
                        options3[i]=neighbours.get(i).id;
                    }
                    String kimenet = ViewField.instance.WriteOptions(options3);
                    for (Component i : neighbours)
                    {
                        if (Objects.equals(i.id, kimenet)) //megfelelot beallit
                        {
                            this.out = (Pipe) i;
                            logger.info(this.id+"@ConfigurePump |"+this.id+" kimenete beállítva: "+i.id+"-ra/re\n");
                        }
                    }
                }
                case "mindkettő" ->
                {
                    jo = true;
                    //kiir aktualisat
                    System.out.println("Aktuális be- és kimenet:");
                    System.out.println(this.in.id + ", " + this.out.id + "\n");

                    //kiir lehetosegeket
                    System.out.println("Lehetséges be- és kimenetek:");
                    for (Component i : neighbours)
                    {
                            System.out.println(i.id); //mindegyiket kiir, mivel akar meg is cserelheti a kettot
                    }
                    System.out.println("\nKérlek add meg a be- és kimenetet vesszővel elválasztva!");
                    ViewField.instance.WriteQuestion("Kérlek add meg a bemenetet");
                    String options4[] = new String[neighbours.size()];
                    for(int i=0;i<neighbours.size();i++)
                    {
                        options4[i]=neighbours.get(i).id;
                    }
                    String[] uj_bemenet = new String[2];
                    uj_bemenet[0] = ViewField.instance.WriteOptions(options4);
                    ViewField.instance.WriteQuestion("Kérlek add meg a kimenetet. (Ne ugyanazt pls");
                    uj_bemenet[1] = ViewField.instance.WriteOptions(options4);

                    for (Component i : neighbours)
                    {
                        if (Objects.equals(i.id, uj_bemenet[0])) //megfelelot beallit
                        {
                            this.in = (Pipe) i;
                            logger.info(this.id+"@ConfigurePump |"+this.id+" bemenete beállítva: "+i.id+"-ra/re\n");
                        }
                        if (Objects.equals(i.id, uj_bemenet[1])) //megfelelot beallit
                        {
                            this.out = (Pipe) i;
                            logger.info(this.id+"@ConfigurePump |"+this.id+" kimenete beállítva: "+i.id+"-ra/re\n");
                        }
                    }
                }
                default ->
                {
                    System.out.println("Érvénytelen bemenet. Add meg újra: ");
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
        ViewField.instance.WriteQuestion("Mit szeretnél cselekedni?");
        String options[] = new String[3];
        if(broken && type == 0)
        {
            options[0] = "repairpump";
        }
        System.out.println("ConfigurePump");
        options[1] = "configurepump";
        System.out.println("Step");
        options[2] = "step";

        String valasz;
        boolean jo = false;
        while (!jo)
        {
            valasz = ViewField.instance.WriteOptions(options);
            valasz = valasz.toLowerCase(); //kisbetűsít
            logger.info(this.id+"@Act | "+me.name+" játékos a következő opciót választotta: "+valasz+"\n");
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
                case "configurepump" ->
                {
                    jo = true;
                    ConfigurePump();
                }
                case "" ->
                {
                    jo = true; //idő léptetés cheat code
                }
                case "exit" ->
                {
                    System.exit(10);
                }
                default -> {
                    System.out.println("Érvénytelen bemenet. Add meg újra: ");
                    logger.info(this.id+"@Act | "+me.name+" nem jó bemenetet adott: "+valasz+"\n");
                }
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
        ViewField.instance.WriteQuestion("Melyik elemre szeretnél lépni?");
        String options[] = new String[neighbours.size()];
        for(int i=0;i<neighbours.size();i++)
        {
            options[i]=neighbours.get(i).id;
        }

        String valasz;
        boolean jo = false;
        boolean ervenyes_bemenet = false;
        while(!jo)
        {
            valasz = ViewField.instance.WriteOptions(options);
            for (Component j : this.neighbours)
            {
                if (Objects.equals(j.id, valasz))
                {
                    ervenyes_bemenet = true;
                    if (j.Accept()) {
                        if(j.IsSloppy()){
                            Component temp = ((Pipe) j).RandomEnd();
                            if(!temp.equals(this)){
                                temp.AddPlayer(me);
                                this.RemovePlayer(me);
                                me.ChangeWhere(temp);
                            }
                        }else {
                            j.AddPlayer(me);
                            this.RemovePlayer(me);
                            me.ChangeWhere(j);
                        }

                        //logolás
                        logger.info(this.id + "@Step | "+me.name+"  játékos "+ j.id +"-re szeretne lépni | rá tudott lépni \n");
                    }
                    else
                    {
                        System.out.println("Nem lehet rálépni!");

                        //logolás
                        logger.info(this.id + "@Step | "+me.name+"  játékos "+ j.id +"-re szeretne lépni | nem tudott rálépni \n");
                    }
                }
            }
            if(ervenyes_bemenet)
            {
                jo = true;
            }
            else
            {
                System.out.println("Érvénytelen bemenet. Add meg újra: ");
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
           logger.info(this.id+"@FlowOut | "+sender.id+" nem a "+this.id+" bemenetére van kötve | pumpa bemenete: "+this.in+"\n");
           return 0;
       }

       if(broken) {
           logger.info(this.id + "@FlowOut | " + this.id + "-ba/be nem tud víz folyni mert rossz\n");
           return 0;
       }
       if(!broken)
       {
           if(maxTank == tank)
           {
               logger.info(this.id+"@FlowOut | "+this.id+"-ba/be nem tud víz folyni mert tele van a tartálya\n");
               return 0;
           }
           else if(tank < maxTank)
           {
               tank++;
               logger.info(this.id+"@FlowOut | "+this.id+" tank tartalma növelve | tank: "+this.tank+"\n");
               if(didWaterFlow)
               {
                   logger.info(this.id+"@FlowOut | didWaterFlow=true volt, azaz nem indít tovabb FlowOut()-ot.\n");
               }
               else {
                   logger.info(this.id + "@FlowOut | didWaterFlow=false volt, tehát indít tovább FlowOut()-ot.\n");
                   didWaterFlow = true;
                   if (out == null) {
                       logger.info(this.id + "@FlowOut | " + this.id + "-ból/ből nem tud víz kifolyni mert nincs kimenet beállítva\n");
                   }
                   else {
                       logger.info(this.id + "@FlowOut | " + this.id + "-ból/ből tovább folyt a víz\n");
                       tank -= out.FlowOut(this);
                   }
               }
               return 1;
           }
           //should never run
           return 0;
       }
       //should never run
       return 0;
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
            logger.info(this.id+"@Tick | "+this.id+" véletlenszerűen elromlott | broken= "+broken+"\n");
        }

        if(didWaterFlow)
        {
            logger.info(this.id+"@Tick | didWaterFlow=true volt ezért legközelebb didWaterFlow=false\n");
            didWaterFlow = false;
        }
        else
        {
            if(tank == 0)
            {
                logger.info(this.id+"@Tick | didWaterFlow=false és a tank üres volt ezért marad didWaterFlow=false\n");
                didWaterFlow = false;
            }
            else if(tank > 0)
            {
                if(out.FlowOut(this) == 0)
                {
                    logger.info(this.id+"@Tick | out.FlowOut==0(true) tank nem üres, de nem folyt tovább a víz\n");
                    didWaterFlow = false;
                }
                else if(out.FlowOut(this) == 1)
                {
                    tank--;
                    logger.info(this.id+"@Tick | out.FlowOut==1(true) tank nem üres és tovább folyhat a víz | tank: "+tank+"\n");
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
        if(random) {
            randomBreakCounter = rand.nextInt(5, 10);
        }
        if(!random)
        {
            randomBreakCounter = detrand.nextInt(5, 10);
        }
        logger.info(this.id+"@Repiar | "+this.id+"megjavítva, randomBreakCounter új értéket kapott | brokem: "+broken+", randomBreakCounter: "+randomBreakCounter+"\n");
    }

    /**
     * Visszaadja, hogy ráléphet-e a játékos.
     * @return mindig ráléphet
     */
    public boolean Accept()
    {
        logger.info(this.id+"@Accept | "+this.id+" ra/re ráléphetnek?  | Igen mert PUMPA \n");
        return true;
    }

    /**
     * Visszaadja, hogy mennyi víz van a tartályban.
     * @return Víz mennyisége a tartályban.
     */
    public int GetWater()
    {
        logger.info(this.id + "@GetWater | A "+this.id+"-ben ennyi víz van: "+tank+"\n");
        return tank;
    }
    public void SetRandom(boolean a)
    {
        random=a;
        logger.info(this.id+"@SetRandom |"+this.id+" random értéke beállítva: "+random+"-ra\n");
    }
}
