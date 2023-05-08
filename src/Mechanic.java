import java.util.Objects;
import java.util.Scanner;

public class Mechanic extends Player{
private boolean havePump;

    public Mechanic(String name) {
        super(name);
        havePump=false;
    }
    public void YourTurn(){
        System.out.println("Your turn, "+name);
    }

    public void ChangeWhere(){

    }
    public void AddPump()
    {
        havePump=true;
    }
    public boolean GetPump(){return havePump;}
}
