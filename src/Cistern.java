import java.util.Objects;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class Cistern extends Component{

    private static final Logger logger = Logger.getLogger(Cistern.class);
    private int amountOfWater=0;
    private int spawnedPipes = 0;

    public Cistern(String ID) {
        super(ID);
        logger.info(this.id + "@Cistern | "+this.id+" létrejött \n");
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
                    Step(me);
                    t = true;
                }
                case "PickUpPump" -> {
                    PickUpPump((Mechanic) me);
                    t = true;
                }
            }

            if(!t) System.out.println("Rossz input!");
        }
    }

    public void PickUpPump(Mechanic me){
        me.AddPump();
    }
    public void SpawnPipe(){
        Pipe p = new Pipe(""+spawnedPipes++);  // Itt az ID-t lehet valtoztatni kell
        this.AddNeighbours(p);
        p.AddNeighbours(this);
    }

    public int FlowOut(Component sender){
        amountOfWater+=1;
        return 1;
    }

    public int GetWater()
    {
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
                        j.IsSloppy();
                        j.AddPlayer(me);
                        this.RemovePlayer(me);
                        me.ChangeWhere(j);
                        t = false;
                    }
                    else
                    {
                        System.out.println("Nem lehet rálépni");
                    }
                }
                else{
                    System.out.println("Rossz input!\n");
                }
            }
        }
    }
}
