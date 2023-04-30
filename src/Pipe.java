import java.util.ArrayList;
import java.util.Scanner;

public class Pipe extends Breakable {
    private double capacity;
    private int sticky;
    private int unBreakable;
    private int sloppy;
    private int hasWater;
    private boolean hasWaterPartOne;
    private boolean hasWaterPartTwo;

    public Pipe(String ID, String logFILE) {
        super(ID, logFILE);
    }

    public boolean Accept() {
        return onComponent.isEmpty();
    }

    public void ChangePipe() {
        ArrayList<Component> tmp = this.ShowNeighbours(); // a pipenak az elso szomszedai
        ArrayList<Component> tmp1 = new ArrayList<>();

        for (Component i : tmp) //
        {
            if (i != this)
                tmp.addAll(i.ShowNeighbours());  // az elso szomszed Pipe(2. szomszed) szomszédai ami nem az amin álltunk
        }
        for (Component i : tmp) {
            if (i.neighbours.get(0) != this.neighbours.get(0) && i.neighbours.get(0) != this.neighbours.get(1)) // ha a 2. szomszed szomszédai,de csak azok amik nem a 2. szomszéd és az eredeti Pipe közös Pump szomszédja
            {
                tmp1.add(i.neighbours.get(0));
            }
            if (i.neighbours.get(1) != this.neighbours.get(0) && i.neighbours.get(1) != this.neighbours.get(1))// ha a 2. szomszed szomszédai,de csak azok amik nem a 2. szomszéd és az eredeti Pipe közös Pump szomszédja
            {
                tmp1.add(i.neighbours.get(1));
            }

        }
        // a tmp1 -ben vannak azok a szomszédok ahova át lehet kötni
        System.out.println("Hány véget akarsz áthelyezni?"); // ezt át lehert rakni az actba
        Scanner be = new Scanner(System.in);
        int valasz = Integer.parseInt(be.nextLine());
        System.out.println("Melyik(ekre) szeretnéd rakni");
        for (Component i : tmp1) {
            System.out.println(i.id);
        }
        Scanner beolvasas = new Scanner(System.in);
        if (valasz == 1) {
            String bemenet = be.nextLine();
        }
        if(valasz==2)
        {
            String [] bemenet=be.nextLine();
        }


    }

    public void PlacePump() {
        System.out.print("$ Pipe.PlacePump()");
    }

    public int FlowOut() {
        return 0;
    }

    public void Act(Player me, int type) {
    }

    public void AddNeighbours() {
    }

    public void Step(Component c, Player me) {
        if (c.Accept()) {
            c.AddPlayer(me);
            this.RemovePlayer(me);
            me.ChangeWhere(c);
        }
    }

    public void RemovePlayer() {
    }

    public Component RandomEnd() {
        return null;
    }

    public void MakeSloppy() {
    }

    public void MakeSticky() {
    }

    public void Tick() {
    }

    public boolean IsSloppy() {
        return sloppy > 0;
    }

    public int GetWater() {
        if (hasWaterPartOne && hasWaterPartTwo)
            return 2;
        if (hasWaterPartOne || hasWaterPartTwo)
            return 1;

        return 0;
    }

}
