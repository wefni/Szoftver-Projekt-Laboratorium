import org.apache.log4j.Logger;

import java.util.Objects;
import java.util.Scanner;

public class Mechanic extends Player {
    private boolean havePump;
    private static final Logger logger = Logger.getLogger(Component.class);

    public Mechanic(String name) {
        super(name);
        havePump = false;
        logger.info(this.name + "@Mechanic | " + this.name + " létrejött \n");
    }

    public void YourTurn() {
        System.out.println("Your turn, " + name);
    }

    public void ChangeWhere() {

    }

    public void AddPump() {
        havePump = true;
    }

    public boolean GetPump() {
        return havePump;
    }
}
