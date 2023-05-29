import org.apache.log4j.Logger;

import javax.swing.text.View;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Pipe extends Breakable {
    private static final Logger logger = Logger.getLogger(Pipe.class);
    private Random detvel = new Random(42);
    private Random vel = new Random();
    private boolean random = true;
    private int sticky;
    private int unBreakable;
    private int sloppy;
    private boolean hasWaterPartOne = false;
    private boolean hasWaterPartTwo = false;
    private boolean stepable=false;

    public Pipe(String ID) {
        super(ID);
        logger.info(this.id + "@Pipe | "+this.id+" létrejött \n");

    }

    public int getSticky() {
        return sticky;
    }

    public boolean isHasWaterPartOne() {
        return hasWaterPartOne;
    }

    public boolean isHasWaterPartTwo() {
        return hasWaterPartTwo;
    }

    public int getUnBreakable() {
        return unBreakable;
    }

    public boolean Accept() {

        //logolás
        logger.info(this.id+"@Accept | "+this.id+" ra/re ráléphetnek?  | onComponent.isEmpty(): "+ onComponent.isEmpty()+"\n");

        //funkció
        return onComponent.isEmpty();
    }

    public void ChangePipe() {

        ArrayList<Component> tmp0 = new ArrayList<>();
        ArrayList<Component> tmp1 = new ArrayList<>();
        ArrayList<Component> neighboursside0 = new ArrayList<>();
        ArrayList<Component> neighboursside1 = new ArrayList<>();

        if(this.neighbours.size()>0){
            tmp0.addAll(this.neighbours.get(0).ShowNeighbours());// az elso szomszed( a 0-as) Pipe(2. szomszed) szomszédai ami nem az amin álltunk
        }
        if(this.neighbours.size()>1){
            tmp1.addAll(this.neighbours.get(1).ShowNeighbours());// az elso szomszed( a 1-es) Pipe(2. szomszed) szomszédai ami nem az amin álltunk
        }



        for (Component i : tmp0){
            if(i.neighbours.size()>0){
                if (!i.neighbours.get(0).neighbours.contains(this) ) // ha a 2. szomszed szomszédai,de csak azok amik nem a 2. szomszéd és az eredeti Pipe közös Pump szomszédja
                {
                    neighboursside0.add(i.neighbours.get(0));
                }
            }
            if(i.neighbours.size()>1){
                if (!i.neighbours.get(1).neighbours.contains(this))// ha a 2. szomszed szomszédai,de csak azok amik nem a 2. szomszéd és az eredeti Pipe közös Pump szomszédja
                {
                    neighboursside0.add(i.neighbours.get(1));
                }
            }

        }
        for (Component i : tmp1){
            if(i.neighbours.size()>0){
                if (!i.neighbours.get(0).neighbours.contains(this) ) // ha a 2. szomszed szomszédai,de csak azok amik nem a 2. szomszéd és az eredeti Pipe közös Pump szomszédja
                {
                    neighboursside1.add(i.neighbours.get(0));
                }
            }
           if(i.neighbours.size()>1) {
               if (!i.neighbours.get(1).neighbours.contains(this))// ha a 2. szomszed szomszédai,de csak azok amik nem a 2. szomszéd és az eredeti Pipe közös Pump szomszédja
               {
                   neighboursside1.add(i.neighbours.get(1));
               }
           }
        }

        // a neighbourssode1 -ban vannak a 0 oldali szomszédok az 1 ben az 1 oldaliak
        boolean jo = true;
        int valasz=0;
        if(this.neighbours.size() == 1){
            valasz = 1;
            jo=false;
        }
        while(jo) {
            ViewField.instance.WriteQuestion("Hány véget akarsz áthelyezni?");
             valasz = Integer.parseInt(ViewField.instance.WriteOptions(new String[]{"1", "2"}));
            if((valasz==1 || valasz == 2))
                jo=false;
            else
                System.out.println("Rossz input, írj újat!");
        }
        jo=true;
        if(valasz==1) // ha csak egy oldalt szeretne áthelyezni
        {
             // az input helyessegere
            int oldal = 0; // melyik odallal szeretnel foglalkozni
            while (jo) {
                ViewField.instance.WriteQuestion("Melyik végét szeretnéd átrakni?");
                oldal = Integer.parseInt(ViewField.instance.WriteOptions(new String[]{"0", "1"}));
                if (oldal == 1 || oldal == 0)
                    jo = false;
            }
            jo = true;
            ViewField.instance.WriteQuestion("Melyik(ekre) szeretnéd rakni");
            while (jo) { // jo input biztositasa
                if (oldal == 0) {
                    for (Component i : neighboursside0) {
                        System.out.println(i.id);
                    }
                    String options[] = new String[neighboursside0.size()];
                    for (int i = 0; i < neighboursside0.size(); i++) {
                        options[i] = neighboursside0.get(i).id;
                    }
                    String bemenet = ViewField.instance.WriteOptions(options);
                    for (Component i : neighboursside0) {
                        if (Objects.equals(i.id, bemenet))// megkeressük a kiválasztottat
                        {
                            jo = false;
                            i.AddNeighbours(this); // átállítjuk a szomszédságot
                            this.AddNeighbours(i);
                            this.RemoveNeighbours(this.neighbours.get(0));
                            this.neighbours.get(0).RemoveNeighbours(this);

                            //logolás
                            logger.info(this.id + "@ChangePipe | " + this.id + " egyik vége átkötve " + i.id + "-ra/re | " + this.id + " neighbours.contains(" + i + "): " + this.neighbours.contains(i) + "\n");

                        }

                    }
                }
                if (oldal == 1) {
                    if(neighboursside1.size() == 0);
                        neighboursside1 = neighboursside0;
                    for (Component i : neighboursside1) {
                        System.out.println(i.id);
                    }
                    String options[] = new String[neighboursside1.size()];
                    for (int i = 0; i < neighboursside1.size(); i++) {
                        options[i] = neighboursside1.get(i).id;
                    }
                    String bemenet = ViewField.instance.WriteOptions(options);
                    for (Component i : neighboursside1) {
                        if (Objects.equals(i.id, bemenet))// megkeressük a kiválasztottat
                        {
                            jo=false;
                            i.AddNeighbours(this); // átállítjuk a szomszédságot
                            this.AddNeighbours(i);
                            this.RemoveNeighbours(this.neighbours.get(1));
                            this.neighbours.get(1).RemoveNeighbours(this);

                            //logolás
                            logger.info(this.id + "@ChangePipe | " + this.id + " egyik vége átkötve " + i.id + "-ra/re | " + this.id + " neighbours.contains(" + i + "): " + this.neighbours.contains(i) + "\n");

                        }

                    }
                }
            }
        }
        jo=true;
        if(valasz==2) // ha mindkét végét át akarjuk állítani
        {
            while(jo) {
                neighboursside0.addAll(neighboursside1); // egyberakjuk
                String[] bemenet = new String[2];
                String options[] = new String[neighboursside0.size()];
                for (int i = 0; i < neighboursside0.size(); i++) {
                    options[i] = neighboursside0.get(i).id;
                }
                ViewField.instance.WriteQuestion("Melyik az egyik pumpa, ahova szeretnéd a cső egyik végét?");
                bemenet[0] = ViewField.instance.WriteOptions(options); // egyik oldal kiválasztása
                ViewField.instance.WriteQuestion("Melyik a másik pumpa, ahova szeretnéd a cső másik végét? (ne ugyanazt válaszd pls)");
                bemenet[1] = ViewField.instance.WriteOptions(options); // másik oldal
                int j = 0;
                for (Component i : neighboursside0) {
                    if (Objects.equals(i.id, bemenet[0]))// megkeressük a kiválasztottat
                    {
                        jo=false;
                        i.AddNeighbours(this); // átállítjuk a szomszédságot
                        this.AddNeighbours(i);
                        this.RemoveNeighbours(this.neighbours.get(j));
                        this.neighbours.get(j).RemoveNeighbours(this);
                        //logolás
                        logger.info(this.id + "@ChangePipe | " + this.id + " egyik vége átkötve " + i.id + "-ra/re | " + this.id + " neighbours.contains(" + i + "): " + this.neighbours.contains(i) + "\n");
                    }
                    if (Objects.equals(i.id, bemenet[1]))// megkeressük a kiválasztottat
                    {
                        jo=false;
                        i.AddNeighbours(this); // átállítjuk a szomszédságot
                        this.AddNeighbours(i);
                        this.RemoveNeighbours(this.neighbours.get(j));
                        this.neighbours.get(j).RemoveNeighbours(this);
                        //logolás
                        logger.info(this.id + "@ChangePipe | " + this.id + " egyik vége átkötve " + i.id + "-ra/re | " + this.id + " neighbours.contains(" + i + "): " + this.neighbours.contains(i) + "\n");
                    }

                }
            }
        }
    }

    /**
     * Ez a függvény felelős egy pumpa lerakásáért.
     */
    public void PlacePump()
    {
        Map.map.PlacePumpOnPipe(this);
    }

    public int FlowOut(Component sender) {
        if(!hasWaterPartOne)
        {
            hasWaterPartOne=true;
            logger.info(this.id+"@FlowOut | "+sender.id+"-ból/ből víz érkezett a "+this.id+" első részébe\n");
            return 1;
        }
        if(broken)
        {
            logger.info(this.id+"@FlowOut | Törött a "+this.id+" ezért nem folyik benne tovább a víz\n");
            if(hasWaterPartTwo)
            {
                for(Component i: this.neighbours) // megkeressük azt a szomszédot ahonnan nem jött
                {
                    if(!Objects.equals(i.id, sender.id))
                    {
                        logger.info(this.id+"@FlowOut |"+this.id+"-ból/ből a víz tovább folyik a "+i.id+"-ba/be\n");
                        if(i.FlowOut(this) == 1){
                            hasWaterPartTwo = false;
                        }
                    }
                }
            }
            return 1;

        }


        if(hasWaterPartTwo)
        {
            for(Component i: this.neighbours) // megkeressük azt a szomszédot ahonnan nem jött
            {
                if(!Objects.equals(i.id, sender.id))
                {
                    logger.info(this.id+"@FlowOut |"+this.id+"-ból/ből a víz tovább folyik a "+i.id+"-ba/be\n");
                    return i.FlowOut(this);
                }
            }
        }
        logger.info(this.id+"@FlowOut | A víz tovább foly a "+this.id+" második részébe\n");
        hasWaterPartTwo=true;
        return 1;
    }

    public void Act(Player me, int type) {
        ViewField.instance.WriteQuestion("Mit szeretnél cselekedni?");
        String options[] = new String[8];
        if(sticky==0 || stepable)
        {
            options[0]="Step";
        }
        if(unBreakable==0 && !broken) {
            options[1] = "BreakPipe";
        }
        options[2]="ChangePipe";
        if(sticky==0)
        {
            options[3]="MakeSticky";
        }
        if(type==0) {
            if(broken)
            {
                options[4]="RepairPipe";
            }
            if(me.GetPump()) {
                options[5] = "PlacePump";
            }

        }
        else
        {
            options[6]="MakeSloppy";
        }

        String valasz;


        boolean jo;
        do {
            valasz=ViewField.instance.WriteOptions(options);
            jo=false;
            if(Objects.equals(valasz, "Step") && sticky>0 && stepable)
            {
                Step(me);
                stepable=false;
                break;
            }
            if(Objects.equals(valasz, "Step") && sticky>0)
            {
                break;
            }
            switch (valasz) {
                case "Step" -> Step(me);
                case "BreakPipe" -> Break();
                case "ChangePipe" -> ChangePipe();
                case "MakeSticky" -> MakeSticky();
                case "RepairPipe" -> Repair();
                case "PlacePump" -> PlacePump();
                case "MakeSloppy" -> MakeSloppy();
                case "exit" -> System.exit(10);
                default -> {
                    jo=true;
                    System.out.println("Nem jó bemenet");
                    logger.info(this.id + "@Act | " + me.name + " játékos nem jó bemenetet adott: " + valasz + "\n");
                }
            }
        } while(jo);
        logger.info(this.id+"@Act | "+me.name+" játékos a következő opciót választotta: "+valasz+"\n");//log
    }



    public void Step( Player me) {
        ViewField.instance.WriteQuestion("Melyik elemre szeretnél lépni?");

        String options[] = new String[this.neighbours.size()];
        for (int i = 0; i < this.neighbours.size(); i++) {
            options[i] = this.neighbours.get(i).id;
        }
        String bemenet;
        bemenet = ViewField.instance.WriteOptions(options);
        for (Component j : this.neighbours) {
            if (Objects.equals(j.id, bemenet)) {
                if (j.Accept()) {
                    j.AddPlayer(me);
                    this.RemovePlayer(me);
                    me.ChangeWhere(j);

                    //logolás
                    logger.info(this.id + "@Step | "+me.name+"  játékos "+ j.id +"-re szeretne lépni | rá tudott lépni \n");

                } else {
                    System.out.println("Nem lehet rálépni");

                    //logolás
                    logger.info(this.id + "@Step | "+me.name+"  játékos "+ j.id +"-re szeretne lépni | nem tudott rálépni \n");

                }
            }
        }

    }


    public Component RandomEnd() {
        if(random) {
            int vel1 = vel.nextInt(0, 1);
            logger.info(this.id + "@RandomEnd | A következő helyre fog csúszni a játékos: "+vel1+"\n");
            return this.neighbours.get(vel1);
        }

        int vel2 = detvel.nextInt(0, 1);
        logger.info(this.id + "@RandomEnd | A következő helyre fog csúszni a játékos: "+vel2+"\n");
        return this.neighbours.get(vel2);
    }

    public void MakeSloppy() {

        if(sloppy==0 && random)
        {
            sloppy=vel.nextInt(2,10);
            logger.info(this.id + "@MakeSloppy | "+sloppy+" körig csúszos lesz a "+this.id+"\n");

        }
        if(!random && sloppy==0)
        {
            sloppy=detvel.nextInt(2,10);
            logger.info(this.id + "@MakeSloppy | "+sloppy+" körig csúszos lesz a "+this.id+"\n");

        }
    }

    public void MakeSticky() {
        if(sticky==0 && random)
        {
            sticky=vel.nextInt(2,10);
            stepable=true;
            logger.info(this.id + "@MakeSticky | "+sticky+" körig ragadós lesz a "+this.id+"\n");

        }
        if(!random && sticky==0)
        {
            sticky=detvel.nextInt(2,10);
            stepable=true;
            logger.info(this.id + "@MakeSticky | "+sticky+" körig ragadós lesz a "+this.id+"\n");

        }
    }

    public void Tick() {
        if (sticky > 0) {
            logger.info(this.id + "@Tick | ragadósság időtartamának csökkentése | "+sticky+" --> ");
            sticky--;
            logger.info(sticky+"\n");
        }

        if (sloppy > 0) {
            logger.info(this.id + "@Tick | csúszósság időtartamának csökkentése | "+sloppy+" --> ");
            sloppy--;
            logger.info(sloppy+"\n");
        }

        if (unBreakable > 0) {
            logger.info(this.id + "@Tick | törhetetlenség időtartamának csökkentése | "+unBreakable+" --> ");
            unBreakable--;
            logger.info(unBreakable+"\n");
        }

    }

    public boolean IsSloppy() {
        logger.info(this.id + "@IsSloppy | csúszósság lekérdezése | "+(sloppy > 0)+"\n");
        return sloppy > 0;
    }

    public int GetWater() {
        if (hasWaterPartOne && hasWaterPartTwo)
        {
            logger.info(this.id + "@GetWater | A "+this.id+"-ben ennyi víz van: "+2+"\n");
            return 2;
        }
        if (hasWaterPartOne || hasWaterPartTwo)
        {
            logger.info(this.id + "@GetWater | A "+this.id+"-ben ennyi víz van: "+1+"\n");
            return 1;
        }
        logger.info(this.id + "@GetWater | A "+this.id+"-ben ennyi víz van: "+0+"\n");
        return 0;
    }
    public void SetRandom(boolean a)
    {
        random=a;
        logger.info(this.id+"@SetRandom |"+this.id+" random értéke beállítva: "+random+"-ra\n");
    }
    public void Repair()
    {
        this.broken = false;
        if(random)
            unBreakable=vel.nextInt(2,10);

        if(!random)
            unBreakable= detvel.nextInt(2,10);
        logger.info(this.id+"@Repair |"+this.id+" megjavítva | broken: "+this.broken+"\n");

    }
    public void Break()
    {
        if(unBreakable==0)
        {
            broken=true;
            logger.info(this.id+"@Break |"+this.id+" eltörve | broken: "+this.broken+"\n");
        }
        else
        {
            logger.info(this.id+"@Break |"+this.id+" nem lehetett eltörni | broken: "+this.broken+" unBreakable: "+unBreakable+"\n");
        }

    }
}
