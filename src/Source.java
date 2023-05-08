import java.util.Objects;
import java.util.Scanner;

public class Source extends Component{
private int amountOfWater=0;

    public Source(String ID) {
        super(ID);
    }

    public void Act(Player me, int type){
        System.out.println("Mit szeretnél cselekedni?\nLépés");

        Scanner be=new Scanner(System.in);
        String valasz=be.nextLine();

        if(valasz.equals("Lépés")) Step(me);
    }

    public void Step(Player me){
        System.out.println("Melyik elemre szeretnél lépni?");
        Scanner be=new Scanner(System.in);

        for(Component i: this.neighbours)
        {
            System.out.println(i.id);
        }

        String bemenet=be.nextLine();
        for(Component j: this.neighbours)
        {
            if(Objects.equals(j.id, bemenet))
            {
                if (j.Accept()) {
                    j.IsSloppy();
                    j.AddPlayer(me);
                    this.RemovePlayer(me);
                    me.ChangeWhere(j);
                }
                else
                {
                    System.out.println("Nem lehet rálépni");
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
