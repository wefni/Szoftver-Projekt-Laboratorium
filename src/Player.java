import java.io.Serializable;
public abstract class Player implements Serializable {
protected Component where;
protected String name;

    public Player(String name) {
        this.name = name;
    }
    public void YourTurn(){

    }
    public void ChangeWhere(Component c){
        where=c;
    }

    public boolean GetPump() {
        return false;
    }
}
