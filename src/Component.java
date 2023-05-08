import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Component implements Flow, Serializable {

    private static final Logger logger = Logger.getLogger(Component.class);
    protected String id;
    protected ArrayList<Player> onComponent=new ArrayList<>();
    protected ArrayList<Component> neighbours=new ArrayList<>();

    public Component(String ID) {
        id = ID;
    }

    public void Act(){

    }
    public boolean Accept(){
        return false;
    }
    public void RemovePlayer(Player me)
    {
        //Funkció
        onComponent.remove(me);

        //Logolás
        logger.info(this.id+"@RemovePlayer | "+me.name+" lelépett a "+this.id+"-ról/ről | onComponent.contains("+me.name+"): "+ onComponent.contains(me)+"\n");
    }
    public void AddPlayer(Player me)
    {
        //Funkció
        onComponent.add(me);

        //Logolás
        logger.info(this.id+"@AddPlayer | "+me.name+" rálépett a "+this.id+"-ra/re | onComponent.contains("+me.name+"): "+ onComponent.contains(me)+"\n");
    }
    public ArrayList<Component> ShowNeighbours(){
        return neighbours;
    }
    public void AddNeighbours(Component c){
        //Funkció
        neighbours.add(c);

        //Logolás
        logger.info(this.id+"@AddNeighbour | "+c.id+" hozzáadva "+this.id+" szomszédjához | neighbours.contains("+c.id+"): "+ neighbours.contains(c)+"\n");
    }
    public void RemoveNeighbours(Component c)
    {
        //Funkció
        neighbours.remove(c);

        //Logolás
        logger.info(this.id+"@RemoveNeighbour | "+c.id+" eltávolítva a "+this.id+" szomszédai közül | neighbours.contains("+c.id+"): "+ neighbours.contains(c)+"\n");
    }
    public void Step(Player me){
    }
    public int FlowOut(){
        return 0;
    }
    public int GetWater(){return 0;}
    public void Tick(){}

    public void ChangePipe() {}
}
