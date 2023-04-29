import java.util.ArrayList;

public abstract class Component implements Flow{
    protected String id;
    protected ArrayList<Player> onComponent=new ArrayList<>();
    protected ArrayList<Component> neighbours=new ArrayList<>();
    public void Act(){
        System.out.println("$ Component.Act()");
    }
    public boolean Accept(){
        System.out.print("$ Component.Accept()");
        return false;
    }
    public void RemovePlayer(Player me)
    {
        onComponent.remove(me);
    }
    public void AddPlayer(Player me)
    {
        onComponent.add(me);
    }
    public ArrayList<Component> ShowNeighbours(){
        return neighbours;
    }
    public void AddNeighbours(Component c){
        neighbours.add(c);

    }
    public void RemoveNeighbours(Component c)
    {
        neighbours.remove(c);
    }
    public void Step(){
        System.out.print("$ Component.Step()");
    }
    public int FlowOut(){
        System.out.print("$ Component.FlowOut()");
        return 0;
    }
    public int GetWater(){return 0;}
    public void Tick(){}
}
