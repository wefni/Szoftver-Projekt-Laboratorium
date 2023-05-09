import org.apache.log4j.Logger;

import java.util.Objects;
import java.util.Scanner;

public class Saboteur extends Player{
    private static final Logger logger = Logger.getLogger(Component.class);

    public Saboteur(String name) {
        super(name);
        logger.info(this.name + "@Saboteur | " + this.name + " létrejött \n");

    }

    public void YourTurn() {
        where.Act(this,1);
    }
    public void ChangeWhere(Component c){
        where = c;
    }
}
