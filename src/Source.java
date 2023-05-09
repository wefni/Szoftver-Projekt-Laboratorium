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

           if(valasz.equals("1. Step")) {
               Step(me);
               t = true;
           }
           if(!t) System.out.println("Rossz input");
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

    public int FlowOut(Component sender){

        for(Component i : this.neighbours){
            i.FlowOut();
            if(i.FlowOut()==1) amountOfWater+=1;
        }

        return 1;
    }

    public int GetWater()
    {
        return amountOfWater;
    }
}
