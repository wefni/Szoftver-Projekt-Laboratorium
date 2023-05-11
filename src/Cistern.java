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
            randomCount=rand.nextInt(5,10);

        if(!random)
            randomCount=detrand.nextInt(5,10);

        logger.info(this.id + " @Cistern | "+this.id+" létrejött \n");
    }

    public void Act(Player me, int type){
        boolean t = false;
        while(!t){
            System.out.println("Mit szeretnél cselekedni?\nStep");
            if(type==0) System.out.println("PickUpPump");

            Scanner be=new Scanner(System.in);
            String valasz=be.nextLine();

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
//        System.out.println("asd");
//        Pipe p = new Pipe("pipe-"+Map.m.getComponents().size());  // Itt az ID-t lehet valtoztatni kell
//        logger.info(this.id + " @SpawnPipe | Létrejött egy új pumpa | ID: "+p.id+" | Eddig létrejött csövek száma: "+spawnedPipes+"\n");
//        this.AddNeighbours(p);
//        p.AddNeighbours(this);
//        System.out.println(p.id + "cis xdd");
//        Map.m.getComponents().add(p);
//        System.out.println("xd");
//        Map.m.SpawnPipeControl(this.id,this,spawnedPipes);
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
            System.out.println("Melyik elemre szeretnél lépni?");
            Scanner be=new Scanner(System.in);

            for(Component i: this.neighbours)
            {
                System.out.println(i.id);
            }

            String bemenet = be.nextLine();

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
            logger.info(this.id + " @Act | "+me+" rossz inputot ütött be \n");
        }
    }
    public void Tick()
    {
        randomCount--;
        if(randomCount==0)
        {
            if(random)
                randomCount=rand.nextInt(5,10);

            if(!random)
                randomCount=detrand.nextInt(0,2);

            this.SpawnPipe();
        }
    }
    public void SetRandom(boolean a)
    {
        random=a;
    }
}
