import java.util.Objects;
import java.util.Scanner;

public class Mechanic extends Player{
private boolean havePump;

    public Mechanic(String name) {
        super(name);
        havePump=false;
    }
    public void YourTurn(){
        where.Act(this,0);
    }

    public void ChangeWhere(Component c){
        where = c;
    }
    public void AddPump()
    {
        havePump=true;
    }
    public boolean GetPump(){return havePump;}
}
