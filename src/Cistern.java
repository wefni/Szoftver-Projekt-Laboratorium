import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class Cistern extends Component{

    private Random rand=new Random();
    private Random detrand=new Random(42);
    private boolean random=false;
    private int randomCount;
    private static final Logger logger = Logger.getLogger(Cistern.class);
    private int amountOfWater=0;
    private int spawnedPipes = 0;


    public Cistern(String ID)  {
        super(ID);


        if(random)
            randomCount=rand.nextInt(5,100);

        if(!random)
            randomCount=detrand.nextInt(5,10);

        logger.info(this.id + " @Cistern | "+this.id+" létrejött \n");
    }

    public void Act(Player me, int type){
        boolean t = false;
        while(!t){
            String options[];
            String valasz;
            ViewField.instance.WriteQuestion("Mit szeretnél cselekedni?");
            if(type==1) options = new String[]{"Step"};
            else options = new String[]{"Step", "PickUpPump"};
            valasz = ViewField.instance.WriteOptions(options);

            switch (valasz) {
                case "Step" -> {
                    logger.info(this.id + " @Act | "+me.name+" Step menüpontot választotta \n");
                    Step(me);
                    t = true;
                }
                case "PickUpPump" -> {
                    logger.info(this.id + " @Act | "+me.name+" PickUpPump menüpontot választotta \n");
                    PickUpPump((Mechanic) me);
                    t = true;
                }
                case "exit" -> {
                    System.exit(10);
                }
            }

            if(!t){
                logger.info(this.id + " @Act | "+me.name+" rossz inputot ütött be \n");
                System.out.println("Rossz input!");
            }
        }
    }

    public void PickUpPump(Mechanic me){
        me.AddPump();
        logger.info(this.id + " @PickUpPump | "+me+" felvett egy pumpát \n");
    }
    public void SpawnPipe(){
        logger.info(this.id + " @SpawnPipe | random lett egy új pipe \n");
        Map.map.SpawnPipeBetweenComponents(this, null);
    }

    public boolean Accept(){
        logger.info(this.id+"@Accept | "+this.id+" ra/re ráléphetnek?  | igen\n");
        return true;
    }
    public int FlowOut(Component sender){
        amountOfWater+=1;
        logger.info(this.id + " @FlowOut | "+sender+"-tól/től víz érkezett a ciszternába | víz mennyisége: "+amountOfWater+"\n");
        return 1;
    }

    public int GetWater()
    {
        logger.info(this.id + " @GetWater | Lekérdezték a víz mennyiségét | mennyisége: "+amountOfWater+"\n");
        return amountOfWater;
    }

    public void Step(Player me){
        boolean t = true;
        while(t){



            String options[] = new String[neighbours.size()];
            String bemenet;
            ViewField.instance.WriteQuestion("Melyik elemre szeretnél lépni?");
            for(int i = 0; i < neighbours.size(); i++)
            {
                options[i] = neighbours.get(i).id;
            }
            bemenet = ViewField.instance.WriteOptions(options);

            for(Component j: this.neighbours)
            {
                if(Objects.equals(j.id, bemenet))
                {
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

                        t = false;
                        logger.info(this.id + "@Step | "+me+"  játékos "+ j.id +"-re szeretne lépni | rá tudott lépni \n");
                    }
                    else
                    {
                        System.out.println("Nem lehet rálépni");
                        logger.info(this.id + "@Step | "+me+"  játékos "+ j.id +"-re szeretne lépni | nem tudott tudott lépni \n");
                    }
                }
            }
            if(t) System.out.println("Rossz input!\n");
            logger.info(this.id + " @Act | "+me+" rossz inputot ütött be | bemenet: "+bemenet+"\n");
        }
    }
    public void Tick()
    {
        randomCount--;
        logger.info(this.id+"@Tick | "+this.id+" randomCount értéke csökkentve 1-el  | randomCount: "+ randomCount+"\n");
        if(randomCount==0)
        {
            if(random)
                randomCount=rand.nextInt(5,100);

            if(!random)
                randomCount= detrand.nextInt(0,100);
            logger.info(this.id+"@Tick | "+this.id+" randomCount új értéket kapott  | randomCount "+ randomCount+"\n");
            this.SpawnPipe();
        }
    }
    public void SetRandom(boolean a)
    {
        random=a;
        logger.info(this.id+"@SetRandom | "+this.id+" random új értéket kapott  | random: "+ random+"\n");
    }
}
