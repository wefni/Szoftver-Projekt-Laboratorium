import java.util.Objects;
import org.apache.log4j.Logger;
import java.util.Scanner;

public class Source extends Component {

    private static final Logger logger = Logger.getLogger(Source.class);
    private int amountOfWater = 0;

    public Source(String ID) {
        super(ID);
        logger.info(this.id + "@Source | "+this.id+" létrejött \n");
    }
    public void Act(Player me, int type){
       boolean t = false;
       while(!t){
           System.out.println("Mit szeretnél cselekedni?\nStep");
           Scanner be=new Scanner(System.in);
           String valasz=be.nextLine();
           logger.info(this.id+"@Act | "+me.name+" játékos a következő opciót választotta: "+valasz+"\n");

           if(valasz.equals("Step")) {
               Step(me);
               t = true;
           }
           if(!t){
               System.out.println("Rossz input");
               logger.info(this.id+"@Act | "+me.name+" játékos nem jó bemenetet adott: "+valasz+"\n");
           }
       }

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
                        logger.info(this.id + "@Step | "+me.name+"  játékos "+ j.id +"-re szeretne lépni | rá tudott lépni \n");
                    }
                    else
                    {
                        System.out.println("Nem lehet rálépni");
                        logger.info(this.id + "@Step | "+me.name+"  játékos "+ j.id +"-re szeretne lépni | nem tudott rálépni \n");
                    }
                }
            }
            if(t) System.out.println("Rossz input!\n");
        }
    }

    public int FlowOut(Component sender){

        for(Component i : this.neighbours)
        {
            i.FlowOut(this);
            if(i.FlowOut(this)==1) {
                logger.info(this.id+"@FlowOut |"+this.id+"-ból/ből a víz tovább folyik a "+i.id+"-ba/be\n");
                amountOfWater+=1;
            }
        }

        return 1;
    }

    public int GetWater()
    {
        logger.info(this.id + "@GetWater | A "+this.id+"-ból ennyi víz folyt ki: "+amountOfWater+"\n");
        return amountOfWater;
    }
}
