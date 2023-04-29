import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Component implements Flow{
    protected String id;
    protected String logFile; //log file neve
    protected ArrayList<Player> onComponent=new ArrayList<>();
    protected ArrayList<Component> neighbours=new ArrayList<>();

    public Component(String ID, String logFILE)
    {
        id = ID;
        logFile = logFILE;
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
        try {
            FileWriter myWriter = new FileWriter(logFile, true);
            myWriter.write(this.id+"@RemovePlayer |"+me.name+" removed from "+this.id+"\n");
            myWriter.close();
        }
        catch (IOException e)
        {
            //Ha hiba van azt egyelőre csak lenyeljük
        }
    }
    public void AddPlayer(Player me)
    {
        //Funkció
        onComponent.add(me);

        //Logolás
        try {
            FileWriter myWriter = new FileWriter(logFile, true);
            myWriter.write(this.id+"@AddPlayer |"+me.name+" added to "+this.id+"\n");
            myWriter.close();
        }
        catch (IOException e)
        {
            //Ha hiba van azt egyelőre csak lenyeljük
        }
    }
    public ArrayList<Component> ShowNeighbours(){
        return neighbours;
    }
    public void AddNeighbours(Component c){
        //Funkció
        neighbours.add(c);

        //Logolás
        try {
            FileWriter myWriter = new FileWriter(logFile, true);
            myWriter.write(this.id+"@AddNeighbour |"+c.id+" added to "+this.id+"'s neighbours\n");
            myWriter.close();
        }
        catch (IOException e)
        {
            //Ha hiba van azt egyelőre csak lenyeljük
        }

    }
    public void RemoveNeighbours(Component c)
    {
        //Funkció
        neighbours.remove(c);

        //Logolás
        try {
            FileWriter myWriter = new FileWriter(logFile, true);
            myWriter.write(this.id+"@RemoveNeighbour |"+c.id+" removed from "+this.id+"'s neighbours\n");
            myWriter.close();
        }
        catch (IOException e)
        {
            //Ha hiba van azt egyelőre csak lenyeljük
        }
    }
    public void Step(){
    }
    public int FlowOut(){
        return 0;
    }
    public int GetWater(){return 0;}
    public void Tick(){}
}
