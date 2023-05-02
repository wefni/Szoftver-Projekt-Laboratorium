import java.util.ArrayList;
import java.util.Objects;
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
        ArrayList<Component> tmp0 = new ArrayList<>();
        ArrayList<Component> tmp1 = new ArrayList<>();
        ArrayList<Component> neighboursside0 = new ArrayList<>();
        ArrayList<Component> neighboursside1 = new ArrayList<>();

                tmp0.addAll(this.neighbours.get(0).ShowNeighbours());// az elso szomszed( a 0-as) Pipe(2. szomszed) szomszédai ami nem az amin álltunk

                tmp1.addAll(this.neighbours.get(1).ShowNeighbours());// az elso szomszed( a 1-es) Pipe(2. szomszed) szomszédai ami nem az amin álltunk

        for (Component i : tmp0) {
            if (!i.neighbours.get(0).neighbours.contains(this) ) // ha a 2. szomszed szomszédai,de csak azok amik nem a 2. szomszéd és az eredeti Pipe közös Pump szomszédja
            {
                neighboursside0.add(i.neighbours.get(0));
            }
            if (!i.neighbours.get(1).neighbours.contains(this))// ha a 2. szomszed szomszédai,de csak azok amik nem a 2. szomszéd és az eredeti Pipe közös Pump szomszédja
            {
                neighboursside0.add(i.neighbours.get(1));
            }
        }
        for (Component i : tmp1) {
            if (!i.neighbours.get(0).neighbours.contains(this) ) // ha a 2. szomszed szomszédai,de csak azok amik nem a 2. szomszéd és az eredeti Pipe közös Pump szomszédja
            {
                neighboursside1.add(i.neighbours.get(0));
            }
            if (!i.neighbours.get(1).neighbours.contains(this))// ha a 2. szomszed szomszédai,de csak azok amik nem a 2. szomszéd és az eredeti Pipe közös Pump szomszédja
            {
                neighboursside1.add(i.neighbours.get(1));
            }
        }

        // a neighbourssode1 -ban vannak a 0 oldali szomszédok az 1 ben az 1 oldaliak
        System.out.println("Hány véget akarsz áthelyezni?");
        Scanner be = new Scanner(System.in);
        int valasz = Integer.parseInt(be.nextLine());
        if(valasz==1) // ha csak egy oldalt szeretne áthelyezni
        {
            System.out.println("Melyik végét szeretnéd átrakni?");
            int oldal = Integer.parseInt(be.nextLine());
            System.out.println("Melyik(ekre) szeretnéd rakni");
            if(oldal==0) {
                for (Component i : neighboursside0) {
                    System.out.println(i.id);
                }
                String bemenet=be.nextLine();
                for(Component i: neighboursside0)
                {
                    if(Objects.equals(i.id, bemenet))// megkeressük a kiválasztottat
                    {
                        i.AddNeighbours(this); // átállítjuk a szomszédságot
                        this.AddNeighbours(i);
                        this.RemoveNeighbours(this.neighbours.get(0));
                        this.neighbours.get(0).RemoveNeighbours(this);
                    }

                }
            }
            if(oldal==1)
            {
                for (Component i : neighboursside1) {
                    System.out.println(i.id);
                }
                String bemenet=be.nextLine();
                for(Component i: neighboursside1)
                {
                    if(Objects.equals(i.id, bemenet))// megkeressük a kiválasztottat
                    {
                        i.AddNeighbours(this); // átállítjuk a szomszédságot
                        this.AddNeighbours(i);
                        this.RemoveNeighbours(this.neighbours.get(1));
                        this.neighbours.get(1).RemoveNeighbours(this);
                    }

                }
            }
        }
        if(valasz==2) // ha mindkét végét át akarjuk állítani
        {
            System.out.println("Melyik(ekre) szeretnéd rakni");
            neighboursside0.addAll(neighboursside1); // egyberakjuk
            for (Component i : neighboursside0) {
                System.out.println(i.id);
            }
            String [] bemenet= new String[2];
                    bemenet[0]=be.nextLine(); // egyik oldal kiválasztása
                    bemenet[1]= be.nextLine(); // másik oldal
                    int j=0;
                        for (Component i : neighboursside0) {
                            if (Objects.equals(i.id, bemenet[j]))// megkeressük a kiválasztottat
                            {
                                i.AddNeighbours(this); // átállítjuk a szomszédságot
                                this.AddNeighbours(i);
                                this.RemoveNeighbours(this.neighbours.get(j));
                                this.neighbours.get(j).RemoveNeighbours(this);
                                j++;

                            }

                        }


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



    public void Step( Player me) {
        System.out.println("Melyik elemre szeretnél lépni?");
        Scanner be=new Scanner(System.in);
        for(Component i: this.neighbours)
        {
            System.out.println(i.id);
        }
        String bemenet=be.nextLine();
        for(Component i: this.neighbours)
        {
            if(Objects.equals(i.id, bemenet))
            {
                if (i.Accept()) {
                    i.AddPlayer(me);
                    this.RemovePlayer(me);
                    me.ChangeWhere(i);
                }
            else
                {
                System.out.println("Nem lehet rálépni");
                }
            }
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
