import java.util.Objects;
import java.util.Scanner;

public class Saboteur extends Player{

    public Saboteur(String name) {
        super(name);
    }

    public void YourTurn() {
        where.Act(this,1);
    }
    public void ChangeWhere(Component c){
        where = c;
    }
}
