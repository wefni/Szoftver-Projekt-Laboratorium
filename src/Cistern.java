import java.util.Objects;
import java.util.Scanner;

public class Cistern extends Component{
    private int amountOfWater=0;

    public Cistern(String ID) {
        super(ID);
    }

    public void Act(Player me, int type){
        System.out.println("Mit szeretnél cselekedni?\nLépés");
        if(type==0) System.out.println("Pumpa felvétele");

        Scanner be=new Scanner(System.in);
        String valasz=be.nextLine();

        switch (valasz) {
            case "Lépés" -> Step(me);
            case "Pumpa felvétele" -> PickUpPump((Mechanic) me);
        }
    }

    public void PickUpPump(Mechanic me){
        me.AddPump();
    }
    public void SpawnPipe(){
        Pipe p = new Pipe("29");  // Itt az ID-t lehet valtoztatni kell
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
}
