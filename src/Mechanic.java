import org.apache.log4j.Logger;

import java.util.Objects;
import java.util.Scanner;

public class Mechanic extends Player {
    private boolean havePump;
    private static final Logger logger = Logger.getLogger(Mechanic.class);

    public Mechanic(String name) {
        super(name);
        havePump = false;
        logger.info(this.name + "@Mechanic | " + this.name + " létrejött \n");
    }
    public void YourTurn(){
        logger.info(this.name + "@YourTurn | " + this.name + " következik \n");
        where.Act(this,0);
    }

    public void ChangeWhere(Component c){
        where = c;
        logger.info(this.name + "@ChangeWhere | " + this.name + " helyzete megváltoztatva a következőre: "+c.id+"\n");

    }

    public void AddPump() {
        havePump = true;
        logger.info(this.name + "@AddPump | " + this.name + " felvett egy pumpát | havepump:  "+this.GetPump()+"\n");
    }

    public boolean GetPump() {
        return havePump;
    }
}
